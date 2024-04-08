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
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                KhachHang kh = new KhachHang();
                
                kh.setMaKH(rs.getInt(1));
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
    
    public KhachHang getKhachHang(int maKH) {
        String sql = "SELECT * From KhachHang where MaKH=? and TinhTrang=1";
        KhachHang kh = new KhachHang();
        try {
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                kh.setMaKH(rs.getInt(1));
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
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setInt(1, kh.getMaKH());
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
    
    public boolean xoaKhachHang(int MaKH) {
        boolean result = false;
        String sql = "UPDATE khachhang SET TinhTrang=0 WHERE MaKH=?";
        try {
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setInt(1, MaKH);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
    public boolean capNhatKhachHang(KhachHang kh) {
        boolean result = false;
        String sql = "UPDATE khachhang SET Ho=?, Ten=?, GioiTinh=? WHERE MaKH=?";
        try {
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setString(1, kh.getHo());
            pre.setString(2, kh.getTen());
            pre.setString(3, kh.getGioiTinh());
            pre.setInt(4, kh.getMaKH());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
    public boolean capNhapTongChiPhi(int maKH, int tongChiTieu) {
        boolean result = false;
        String sql = "UPDATE khachhang SET TongChiTieu=? where MaKH=?";
        try {
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setInt(1, tongChiTieu);
            pre.setInt(2, maKH);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}
