package DAO;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Model.SanPham;
public class SanPhamDAO {
    public List<SanPham> getAllSanPham() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maSP = rs.getInt("MaSP");
                String tenSP = rs.getString("TenSP");
                int maLoai = rs.getInt("MaLoai");
                int soLuong = rs.getInt("SoLuong");
                String donViTinh = rs.getString("DonViTinh");
                String hinhAnh = rs.getString("HinhAnh");
                int donGia = rs.getInt("DonGia");

                SanPham sanPham = new SanPham(maSP, tenSP, maLoai, soLuong, donViTinh, hinhAnh, donGia);
                list.add(sanPham);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean addSanPham(SanPham sp) {
        boolean result = false;
        String sql = "INSERT INTO SanPham (MaSP, TenSP, MaLoai, SoLuong, DonViTinh, HinhAnh, DonGia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());
            ps.setInt(3, sp.getMaLoai());
            ps.setInt(4, sp.getSoLuong());
            ps.setString(5, sp.getDonViTinh());
            ps.setString(6, sp.getHinhAnh());
            ps.setInt(7, sp.getDonGia());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateSanPham(SanPham sp) {
        boolean result = false;
        String sql = "UPDATE SanPham SET TenSP = ?, MaLoai = ?, SoLuong = ?, DonViTinh = ?, HinhAnh = ?, DonGia = ? WHERE MaSP = ?";
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, sp.getTenSP());
            ps.setInt(2, sp.getMaLoai());
            ps.setInt(3, sp.getSoLuong());
            ps.setString(4, sp.getDonViTinh());
            ps.setString(5, sp.getHinhAnh());
            ps.setInt(6, sp.getDonGia());
            ps.setInt(7, sp.getMaSP());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteSanPham(int maSP) {
        boolean result = false;
        String sql = "DELETE FROM SanPham WHERE MaSP = ?";
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, maSP);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
