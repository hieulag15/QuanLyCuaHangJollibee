/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.sql.*;
import Model.CTPhieuNhap;
/**
 *
 * @author ADMIN
 */
public class CTPhieuNhapDAO {
    MyConnect myConnect = new MyConnect();
    public ArrayList<CTPhieuNhap> getListChiTietPhieuNhap() {
    ArrayList<CTPhieuNhap> dsChiTietPhieuNhap = new ArrayList<>();
    try {
        String sql = "SELECT * FROM ctphieunhap";
        PreparedStatement ps = myConnect.conn.prepareStatement(sql);
       
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CTPhieuNhap ctpn = new CTPhieuNhap();
            ctpn.setMaPN(rs.getInt("MaPN"));
            ctpn.setMaSP(rs.getInt("MaSP"));
            ctpn.setSoLuong(rs.getInt("SoLuong"));
            ctpn.setDonGia(rs.getInt("DonGia"));
            ctpn.setThanhTien(rs.getInt("ThanhTien"));
            dsChiTietPhieuNhap.add(ctpn);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return dsChiTietPhieuNhap;
}
    
    public boolean themChiTietPhieuNhap(CTPhieuNhap ctpn) {
        boolean result = false;
        String sql = "INSERT INTO ctphieunhap (MaPN, MaSP, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setInt(1, ctpn.getMaPN());
            pre.setInt(2, ctpn.getMaSP());
            pre.setInt(3, ctpn.getSoLuong());
            pre.setInt(4, ctpn.getDonGia());
            pre.setInt(5, ctpn.getThanhTien());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
}
    
    public boolean xoaChiTietPhieuNhap(int MaPN) {
        boolean result = false;
        String sql = "DELETE FROM ctphieunhap WHERE MaPN = ?";
        try {
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setInt(1, MaPN);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
}
    public boolean capNhatPhieuNhap(CTPhieuNhap pn) {
    boolean result = false;
    String sql = "UPDATE phieunhap SET MaSP=?, SoLuong=?, DonGia=?, ThanhTien=? WHERE MaPN=?";
    try {
        PreparedStatement pre = myConnect.conn.prepareStatement(sql);
        pre.setInt(1, pn.getMaSP());
        pre.setInt(2,pn.getSoLuong());
        pre.setInt(3, pn.getDonGia());
        pre.setInt(4, pn.getThanhTien());
        pre.setInt(5, pn.getMaPN());
        result = pre.executeUpdate() > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return result;
}
    
        public ArrayList<CTPhieuNhap> getListChiTietPhieuNhapTheoMaPN(int maPN) {
            ArrayList<CTPhieuNhap> dsCTPhieuNhap = new ArrayList<>();
            try {
                String sql = "SELECT * FROM ctphieunhap where MaPN = ?";
                PreparedStatement ps = myConnect.conn.prepareStatement(sql);
                ps.setInt(1, maPN);
                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    CTPhieuNhap ctpn = new CTPhieuNhap();
                    ctpn.setMaPN(rs.getInt("MaPN"));
                    ctpn.setMaSP(rs.getInt("MaSP"));
                    ctpn.setSoLuong(rs.getInt("SoLuong"));
                    ctpn.setDonGia(rs.getInt("DonGia"));
                    ctpn.setThanhTien(rs.getInt("ThanhTien"));
                    dsCTPhieuNhap.add(ctpn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return dsCTPhieuNhap;
        }

    public CTPhieuNhap getChiTietPhieuNhap(int maPN, int maSP) {
    String sql = "SELECT * FROM ctphieunhap WHERE MaPN = ? and MaSP = ?";
    CTPhieuNhap ctpn = new CTPhieuNhap();
    try {
        PreparedStatement pre = myConnect.conn.prepareStatement(sql);
        pre.setInt(1, maPN);
        pre.setInt(2, maSP);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            ctpn.setMaPN(rs.getInt("MaPN"));
            ctpn.setMaSP(rs.getInt("MaSP"));
            ctpn.setSoLuong(rs.getInt("SoLuong"));
            ctpn.setDonGia(rs.getInt("DonGia"));
            ctpn.setThanhTien(rs.getInt("ThanhTien"));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return ctpn;
}




    
}
