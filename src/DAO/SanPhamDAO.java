package DAO;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Model.SanPham;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SanPhamDAO {
    MyConnect myConnect = new MyConnect();
    public ArrayList<SanPham> getAllSanPham() {
        try {
            String sql = "SELECT * FROM SanPham";
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setSoLuong(rs.getInt(4));
                sp.setDonViTinh(rs.getString(5));
                sp.setHinhAnh(rs.getString(6));
                sp.setDonGia(rs.getInt(7));

                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }
    public SanPham getSanPham(int maSP){
        try {
            String sql = "SELECT * FROM SanPham WHERE MaSP = "+maSP;
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            SanPham sp = new SanPham();
            sp.setMaSP(rs.getInt(1));
            sp.setTenSP(rs.getString(2));
            sp.setMaLoai(rs.getInt(3));
            sp.setSoLuong(rs.getInt(4));
            sp.setDonViTinh(rs.getString(5));
            sp.setHinhAnh(rs.getString(6));
            sp.setDonGia(rs.getInt(7));
            return sp;
        } catch (SQLException e) {
        }

        return null;
    }

    public boolean addSanPham(SanPham sp) {
        boolean result = false;
        String sql = "INSERT INTO SanPham (MaSP, TenSP, MaLoai, SoLuong, DonViTinh, HinhAnh, DonGia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
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
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
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
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, maSP);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
