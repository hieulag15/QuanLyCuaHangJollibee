/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CTHoaDon;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class CTHoaDonDAO {
    
    MyConnect myConnect = new MyConnect();
    public ArrayList<CTHoaDon> getListChiTietHoaDon() {
        ArrayList<CTHoaDon> dsChiTietHoaDon = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cthoadon";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTHoaDon cthd = new CTHoaDon();
                cthd.setMaHD(rs.getInt("MaHD"));
                cthd.setMaSP(rs.getInt("MaSP"));
                cthd.setSoLuong(rs.getInt("SoLuong"));
                cthd.setDonGia(rs.getInt("DonGia"));
                cthd.setThanhTien(rs.getInt("ThanhTien"));
                dsChiTietHoaDon.add(cthd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dsChiTietHoaDon;
    }
    
    public ArrayList<CTHoaDon> getListChiTietHoaDonTheoMaHD(int maHD) {
        ArrayList<CTHoaDon> dsChiTietHoaDon = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cthoadon where MaHD = ?";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTHoaDon cthd = new CTHoaDon();
                cthd.setMaHD(rs.getInt("MaHD"));
                cthd.setMaSP(rs.getInt("MaSP"));
                cthd.setSoLuong(rs.getInt("SoLuong"));
                cthd.setDonGia(rs.getInt("DonGia"));
                cthd.setThanhTien(rs.getInt("ThanhTien"));
                dsChiTietHoaDon.add(cthd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dsChiTietHoaDon;
    }
    
    public boolean addChiTietHoaDon(CTHoaDon cthd) {
        boolean result = false;
        try {
            String sql = "INSERT INTO cthoadon(MaHD, MaSP, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, cthd.getMaHD());
            ps.setInt(2, cthd.getMaSP());
            ps.setInt(3, cthd.getSoLuong());
            ps.setInt(4, cthd.getDonGia());
            ps.setInt(5, cthd.getThanhTien());
            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public boolean xoaChiTietHoaDon(int MaHD) {
        boolean result = false;
        String sql = "DELETE FROM cthoadon WHERE MaHD = ?";
        try {
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setInt(1, MaHD);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
}
    
    public boolean capNhatChiTietHoaDon(CTHoaDon cthd) {
        boolean result = false;
        String sql = "UPDATE cthoadon SET MaSP=?, SoLuong=?, DonGia=?, ThanhTien=? WHERE MaHD=?";
        try {
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setInt(1, cthd.getMaSP());
            pre.setInt(2, cthd.getSoLuong());
            pre.setInt(3, cthd.getDonGia());
            pre.setInt(4, cthd.getThanhTien());
            pre.setInt(5, cthd.getMaHD());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
}
    
    public CTHoaDon getChiTietHoaDon(int maHD, int maSP) {
    String sql = "SELECT * FROM cthoadon WHERE MaHD = ? and MaSP = ?";
    CTHoaDon cthd = new CTHoaDon();
    try {
        PreparedStatement pre = myConnect.conn.prepareStatement(sql);
        pre.setInt(1, maHD);
        pre.setInt(2, maSP);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            cthd.setMaHD(rs.getInt("MaHD"));
            cthd.setMaSP(rs.getInt("MaSP"));
            cthd.setSoLuong(rs.getInt("SoLuong"));
            cthd.setDonGia(rs.getInt("DonGia"));
            cthd.setThanhTien(rs.getInt("ThanhTien"));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return cthd;
}



    
}
