/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.sql.*;
import Model.HoaDon;

/**
 *
 * @author ADMIN
 */
public class HoaDonDAO {
    
    MyConnect myConnect = new MyConnect();
    
    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hoadon";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                hd.setGhiChu(rs.getString(6));
                dsHoaDon.add(hd);
            }
        } catch (SQLException ex) {
            return null;
        }
        return dsHoaDon;
    }

    public boolean addHoaDon(HoaDon hd) {
        boolean result = false;
        try {
            
            String sql1 = "UPDATE KhachHang SET TongChiTieu = TongChiTieu + ? WHERE MaKH=?";
            PreparedStatement ps1 = myConnect.conn.prepareStatement(sql1);
            ps1.setInt(1, hd.getTongTien());
            ps1.setInt(2, hd.getMaKH());
            ps1.executeUpdate();
            
            String sql = "INSERT INTO hoadon(MaKH, MaNV, NgayLap, TongTien, GhiChu) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps2 = MyConnect.conn.prepareStatement(sql);
            ps2.setInt(1, hd.getMaKH());
            ps2.setInt(2, hd.getMaNV());
            ps2.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            ps2.setInt(4, hd.getTongTien());
            ps2.setString(5, hd.getGhiChu());
            result = ps2.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int getMaHoaDonMoiNhat() {
        try {
            String sql = "SELECT MAX(maHD) FROM hoadon";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<HoaDon> getListHoaDon(Date dateStart, Date dateEnd) {
        try {
            String sql = "SELECT * FROM hoadon WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setDate(1, dateStart);
            pre.setDate(2, dateEnd);
            ResultSet rs = pre.executeQuery();

            ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                hd.setGhiChu(rs.getString(6));
                dsHoaDon.add(hd);
            }
            return dsHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
