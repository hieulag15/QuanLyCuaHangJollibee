/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.sql.*;
import Model.PhanQuyen;

/**
 *
 * @author ADMIN
 */
public class PhanQuyenDAO {
    
    MyConnect myConnect = new MyConnect();
    
    public ArrayList<PhanQuyen> getListQuyen() {
        try {
            String sql = "SELECT * FROM PhanQuyen";
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<PhanQuyen> dsPhanQuyen = new ArrayList<>();
            while (rs.next()) {
                PhanQuyen phanQuyen = new PhanQuyen();
                phanQuyen.setQuyen(rs.getString(1));
                phanQuyen.setNhapHang(rs.getInt(2));
                phanQuyen.setQlSanPham(rs.getInt(3));
                phanQuyen.setQlNhanVien(rs.getInt(4));
                phanQuyen.setQlKhachHang(rs.getInt(5));
                phanQuyen.setThongKe(rs.getInt(6));
                dsPhanQuyen.add(phanQuyen);
            }
            return dsPhanQuyen;
        } catch (Exception e) {
        }
        return null;
    }

    public PhanQuyen getQuyen(String quyen) {
        try {
            String sql = "SELECT * FROM PhanQuyen WHERE Quyen=?";
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setString(1, quyen);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PhanQuyen phanQuyen = new PhanQuyen();
                phanQuyen.setQuyen(quyen);
                phanQuyen.setNhapHang(rs.getInt(2));
                phanQuyen.setQlSanPham(rs.getInt(3));
                phanQuyen.setQlNhanVien(rs.getInt(4));
                phanQuyen.setQlKhachHang(rs.getInt(5));
                phanQuyen.setThongKe(rs.getInt(6));
                return phanQuyen;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean updateQuyen(PhanQuyen phanQuyen) {
        try {
            String sql = "UPDATE phanquyen SET NhapHang=?,QLSanPham=?,QLNhanVien=?,QLKhachHang=?,ThongKe=? WHERE Quyen=?";
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, phanQuyen.getNhapHang());
            ps.setInt(2, phanQuyen.getQlSanPham());
            ps.setInt(3, phanQuyen.getQlNhanVien());
            ps.setInt(4, phanQuyen.getQlKhachHang());
            ps.setInt(5, phanQuyen.getThongKe());
            ps.setString(6, phanQuyen.getQuyen());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean addQuyen(PhanQuyen phanQuyen) {
        try {
            String sql = "INSERT INTO phanquyen VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setString(1, phanQuyen.getQuyen());
            ps.setInt(2, phanQuyen.getNhapHang());
            ps.setInt(3, phanQuyen.getQlSanPham());
            ps.setInt(4, phanQuyen.getQlNhanVien());
            ps.setInt(5, phanQuyen.getQlKhachHang());
            ps.setInt(6, phanQuyen.getThongKe());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean deleteQuyen(String phanQuyen) {
        try {
            String sql1 = "UPDATE TaiKhoan SET Quyen='Default' WHERE Quyen=?";
            PreparedStatement ps1 = myConnect.getConn().prepareStatement(sql1);
            ps1.setString(1, phanQuyen);
            ps1.executeUpdate();
            
            String sql2 = "DELETE FROM PhanQuyen WHERE Quyen=?";
            PreparedStatement ps2 = myConnect.getConn().prepareStatement(sql2);
            ps2.setString(1, phanQuyen);
            return ps2.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
