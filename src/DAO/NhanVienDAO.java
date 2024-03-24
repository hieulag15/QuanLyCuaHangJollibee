/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.NhanVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class NhanVienDAO {
    
    public ArrayList<NhanVien> getDanhSachNhanVien() {
        String sql = "SELECT * FROM NhanVien";
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                NhanVien nv = new NhanVien();
                
                nv.setMaNV(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setChucVu(rs.getString(5));
                
                dsnv.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsnv;
    }
    
    public NhanVien getNhanVien(int ma) {
        String sql = "SELECT * FROM NhanVien where MaNV = ?";
        NhanVien nv = new NhanVien();
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                nv.setMaNV(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setChucVu(rs.getString(5));
            } 
        } catch (SQLException ex) {
            
        }
        return nv;
    }
    
    public boolean themNhanVien(NhanVien nv) {
        boolean result = false;
        String sql = "INSERT INTO NhanVien(Ho, Ten, GioiTinh, ChucVu) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, nv.getHo());
            pre.setString(2, nv.getTen());
            pre.setString(3, nv.getGioiTinh());
            pre.setString(4, nv.getChucVu());
            
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
    public boolean suaNhanVien(NhanVien nv) {
        boolean result = false;
        String sql = "UPDATE nhanvien SET Ho=?, Ten=?, GioiTinh=?, ChucVu=? WHERE MaNV=?";
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, nv.getHo());
            pre.setString(2, nv.getTen());
            pre.setString(3, nv.getGioiTinh());
            pre.setString(4, nv.getChucVu());
            pre.setInt(5, nv.getMaNV());
            
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
    public boolean xoaNhanVien(int maNV) {
        boolean result = false;
        String sql = "DELETE From NhanVien Where maNV=?";
        
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}
