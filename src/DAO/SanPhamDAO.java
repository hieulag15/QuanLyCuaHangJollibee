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
    public ArrayList<SanPham> getListSanPhamByActive(int tinhTrang) {
        try {
            String sql = "SELECT * FROM SanPham WHERE TinhTrang = ?";
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setInt(1, tinhTrang);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setDonViTinh(rs.getString(4));
                sp.setHinhAnh(rs.getString(5));
                sp.setDonGia(rs.getInt(6));
                sp.setTinhTrang(rs.getInt(7));
                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public ArrayList<SanPham> getAllSanPham() {
        try {
            String sql = "SELECT * FROM SanPham";
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setDonViTinh(rs.getString(4));
                sp.setHinhAnh(rs.getString(5));
                sp.setDonGia(rs.getInt(6));
                sp.setTinhTrang(rs.getInt(7));
                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public ArrayList<SanPham> getListSanPhamByIdLoai(int maLoai) {
        try {
            String sql = "SELECT * FROM SanPham WHERE MaLoai = ? AND TinhTrang = 1";
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setInt(1, maLoai);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setDonViTinh(rs.getString(4));
                sp.setHinhAnh(rs.getString(5));
                sp.setDonGia(rs.getInt(6));
                sp.setTinhTrang(rs.getInt(7));
                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public ArrayList<SanPham> getListSanPhamByKey(String key, Integer tinhTrang) {
        try {
            String sql = "SELECT s.*, l.TenLoai " +
                         "FROM SanPham s " +
                         "INNER JOIN Loai l ON s.MaLoai = l.MaLoai " +
                         "WHERE (s.MaSP LIKE ? OR s.TenSP LIKE ? OR s.DonViTinh LIKE ? OR s.DonGia LIKE ? OR l.TenLoai LIKE ?)";

            if (tinhTrang != null) {
                sql += " AND s.TinhTrang = ?";
            } else {
                sql += " AND s.TinhTrang IN (0, 1)";
            }

            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            for (int i = 1; i <= 5; i++) {
                pre.setString(i, "%" + key + "%");
            }

            if (tinhTrang != null) {
                pre.setInt(6, tinhTrang);
            }

            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();

            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setMaLoai(rs.getInt("MaLoai"));
                sp.setDonViTinh(rs.getString("DonViTinh"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                sp.setDonGia(rs.getInt("DonGia"));
                sp.setTinhTrang(rs.getInt("TinhTrang"));
                dssp.add(sp);
            }

            return dssp;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public SanPham getSanPham(int maSP){
        try {
            String sql = "SELECT * FROM SanPham WHERE MaSP = ?";
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setInt(1, maSP);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setDonViTinh(rs.getString(4));
                sp.setHinhAnh(rs.getString(5));
                sp.setDonGia(rs.getInt(6));
                sp.setTinhTrang(rs.getInt(7));
                return sp;
            }
        } catch (SQLException e) {
        }

        return null;
    }

    public boolean addSanPham(SanPham sp) {
        boolean result = false;
        String sql = "INSERT INTO SanPham (MaSP, TenSP, MaLoai, DonViTinh, HinhAnh, DonGia, TinhTrang) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());
            ps.setInt(3, sp.getMaLoai());
            ps.setString(4, sp.getDonViTinh());
            ps.setString(5, sp.getHinhAnh());
            ps.setInt(6, sp.getDonGia());
            ps.setInt(7, sp.getTinhTrang());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateSanPham(SanPham sp) {
        boolean result = false;
        String sql = "UPDATE SanPham SET TenSP = ?, MaLoai = ?, DonViTinh = ?, HinhAnh = ?, DonGia = ?, TinhTrang = ? WHERE MaSP = ?";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setString(1, sp.getTenSP());
            ps.setInt(2, sp.getMaLoai());
            ps.setString(3, sp.getDonViTinh());
            ps.setString(4, sp.getHinhAnh());
            ps.setInt(5, sp.getDonGia());
            ps.setInt(6, sp.getTinhTrang());
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
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, maSP);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
