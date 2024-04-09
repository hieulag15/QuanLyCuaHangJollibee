/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.KhachHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class KhachHangDAO {
    MyConnect myConnect = new MyConnect();
    public ArrayList<KhachHang> getDanhSachKhachHang() {
        String sql = "SELECT * From khachhang WHERE TinhTrang=1";
        ArrayList<KhachHang> dskh = new ArrayList<>();
        try {
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                KhachHang kh = new KhachHang();
                
                kh.setSdt(rs.getInt(1));
                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setGioiTinh(rs.getString(4));
                kh.setTongChiTieu(rs.getInt(5));
                
                dskh.add(kh);
            }
        } catch (SQLException ex) {
            
        }
        return dskh;
    }
    
    public KhachHang getKhachHang(int SoDienThoai) {
        String sql = "SELECT * From KhachHang where SoDienThoai=? and TinhTrang=1";
        KhachHang kh = new KhachHang();
        try {
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setInt(1, SoDienThoai);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                kh.setSdt(rs.getInt(1));
                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setGioiTinh(rs.getString(4));
                kh.setTongChiTieu(rs.getInt(5));
            }
        } catch (SQLException ex) {
            
        }
        return kh;
    }
    
    public boolean themKhachHang(KhachHang kh) {
        boolean result = false;
        String sql = "INSERT INTO KhachHang Values(?,?,?,?,?,1)";
        try {
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setInt(1, kh.getSdt());
            pre.setString(2, kh.getHo());
            pre.setString(3, kh.getTen());
            pre.setString(4, kh.getGioiTinh());
            pre.setInt(5, kh.getTongChiTieu());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
    public boolean xoaKhachHang(int SoDienThoai) {
        boolean result = false;
        String sql = "UPDATE khachhang SET TinhTrang=0 WHERE SoDienThoai=?";
        try {
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setInt(1, SoDienThoai);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
    public boolean capNhatKhachHang(KhachHang kh) {
        boolean result = false;
        String sql = "UPDATE khachhang SET Ho=?, Ten=?, GioiTinh=? WHERE SoDienThoai=?";
        try {
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setString(1, kh.getHo());
            pre.setString(2, kh.getTen());
            pre.setString(3, kh.getGioiTinh());
            pre.setInt(4, kh.getSdt());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
    public boolean capNhapTongChiPhi(int SoDienThoai, int tongChiTieu) {
        boolean result = false;
        String sql = "UPDATE khachhang SET TongChiTieu=? where SoDienThoai=?";
        try {
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setInt(1, tongChiTieu);
            pre.setInt(2, SoDienThoai);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}
