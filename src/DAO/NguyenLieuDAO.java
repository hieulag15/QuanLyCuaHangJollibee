/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.NguyenLieu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nun-PC
 */
public class NguyenLieuDAO {
    MyConnect myConnect = new MyConnect();
    
    public ArrayList<NguyenLieu> getAllNguyenLieu() {
        try {
            String sql = "SELECT * FROM NguyenLieu";
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<NguyenLieu> dsnl = new ArrayList<>();
            while (rs.next()) {
                NguyenLieu nl = new NguyenLieu();

                nl.setMaNL(rs.getInt(1));
                nl.setTenNL(rs.getString(2));
                nl.setSoLuong(rs.getInt(3));
                nl.setDonViTinh(rs.getString(4));
                nl.setHinhAnh(rs.getString(5));
                nl.setDonGia(rs.getInt(6));
                nl.setTinhTrang(rs.getInt(7));

                dsnl.add(nl);
            }
            return dsnl;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public ArrayList<NguyenLieu> getListNguyenLieuByActive(int tinhTrang) {
        try {
            String sql = "SELECT * FROM NguyenLieu WHERE TinhTrang = ?";
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setInt(1, tinhTrang);
            ResultSet rs = pre.executeQuery();
            ArrayList<NguyenLieu> dsnl = new ArrayList<>();
            while (rs.next()) {
                NguyenLieu nl = new NguyenLieu();

                nl.setMaNL(rs.getInt(1));
                nl.setTenNL(rs.getString(2));
                nl.setSoLuong(rs.getInt(3));
                nl.setDonViTinh(rs.getString(4));
                nl.setHinhAnh(rs.getString(5));
                nl.setDonGia(rs.getInt(6));
                nl.setTinhTrang(rs.getInt(7));
                dsnl.add(nl);
            }
            return dsnl;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public ArrayList<NguyenLieu> getListNguyenLieuByKey(String key, Integer tinhTrang) {
        try {
            String sql = "SELECT * FROM NguyenLieu WHERE (MaNL LIKE ? OR TenNL LIKE ? OR SoLuong LIKE ? OR DonViTinh LIKE ? OR DonGia LIKE ?)";
            if (tinhTrang != null) {
                sql += " AND TinhTrang = ?";
            } else {
                sql += " AND TinhTrang IN (0, 1)";
            }

            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            for (int i = 1; i <= 5; i++) {
                pre.setString(i, "%" + key + "%");
            }

            if (tinhTrang != null) {
                pre.setInt(6, tinhTrang);
            }
            
            ResultSet rs = pre.executeQuery();
            ArrayList<NguyenLieu> dsnl = new ArrayList<>();
            while (rs.next()) {
                NguyenLieu nl = new NguyenLieu();

                nl.setMaNL(rs.getInt(1));
                nl.setTenNL(rs.getString(2));
                nl.setSoLuong(rs.getInt(3));
                nl.setDonViTinh(rs.getString(4));
                nl.setHinhAnh(rs.getString(5));
                nl.setDonGia(rs.getInt(6));
                nl.setTinhTrang(rs.getInt(7));
                dsnl.add(nl);
            }
            return dsnl;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public NguyenLieu getNguyenLieu(int ma) {
        try {
            String sql = "SELECT * FROM NguyenLieu WHERE MaNL = ?";
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();
            NguyenLieu nl = new NguyenLieu();
            if (rs.next()) {
                nl.setMaNL(rs.getInt(1));
                nl.setTenNL(rs.getString(2));
                nl.setSoLuong(rs.getInt(3));
                nl.setDonViTinh(rs.getString(4));
                nl.setHinhAnh(rs.getString(5));
                nl.setDonGia(rs.getInt(6));
                nl.setTinhTrang(rs.getInt(7));
            }
            return nl;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public boolean addNguyenLieu(NguyenLieu nl) {
        boolean result = false;
        String sql = "INSERT INTO NguyenLieu (MaNL, TenNL, SoLuong, DonViTinh, HinhAnh, DonGia, TinhTrang) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, nl.getMaNL());
            ps.setString(2, nl.getTenNL());
            ps.setInt(3, nl.getSoLuong());
            ps.setString(4, nl.getDonViTinh());
            ps.setString(5, nl.getHinhAnh());
            ps.setInt(6, nl.getDonGia());
            ps.setInt(7, nl.getTinhTrang());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean updateNguyenLieu(NguyenLieu nl) {
        boolean result = false;
        String sql = "UPDATE NguyenLieu SET TenNL = ?, SoLuong = ?, DonViTinh = ?, HinhAnh = ?, DonGia = ?, TinhTrang = ? WHERE MaNL = ?";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);           
            ps.setString(1, nl.getTenNL());
            ps.setInt(2, nl.getSoLuong());
            ps.setString(3, nl.getDonViTinh());
            ps.setString(4, nl.getHinhAnh());
            ps.setInt(5, nl.getDonGia());
            ps.setInt(6, nl.getTinhTrang());
            ps.setInt(7, nl.getMaNL());
            
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean deleteNguyenLieu(int ma) {
        boolean result = false;
        String sql = "DELETE FROM NguyenLieu WHERE MaNL = ?";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, ma);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
