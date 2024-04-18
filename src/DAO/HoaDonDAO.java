/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.sql.*;
import Model.HoaDon;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                hd.setSdt(rs.getString(2));
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
    
    public HoaDon getHoaDon(int ma) {
        HoaDon hd = new HoaDon();
        try {
            String sql = "SELECT * FROM hoadon where MaHD = ?";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hd.setMaHD(rs.getInt(1));
                hd.setSdt(rs.getString(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                hd.setGhiChu(rs.getString(6));
            }
        } catch (SQLException ex) {
            return null;
        }
        return hd;
    }

    public void addHoaDon(HoaDon hd) {
        try {
            
            String sql1 = "UPDATE KhachHang SET TongChiTieu = TongChiTieu + ? WHERE SoDienThoai=?";
            PreparedStatement ps1 = myConnect.conn.prepareStatement(sql1);
            ps1.setInt(1, hd.getTongTien());
            ps1.setString(2, hd.getSdt());
            ps1.executeUpdate();
            
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            String sql = "INSERT INTO hoadon(SoDienThoai, MaNV, NgayLap, TongTien, GhiChu) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps2 = myConnect.conn.prepareStatement(sql);
            ps2.setString(1, hd.getSdt());
            ps2.setInt(2, hd.getMaNV());
            ps2.setTimestamp(3, timestamp);
            ps2.setInt(4, hd.getTongTien());
            ps2.setString(5, hd.getGhiChu());
            ps2.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
    public ArrayList<HoaDon> getListHoaDonByDate(String startDate, String endDate) { 
        try {
            
            String sql = "SELECT * FROM hoadon WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setString(1, startDate);
            pre.setString(2, endDate);
            ResultSet rs = pre.executeQuery();

            ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setSdt(rs.getString(2));
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
    
    public ArrayList<HoaDon> getListHoaDonByCost(int startCost, int endCost) {
    ArrayList<HoaDon> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hoadon WHERE TongTien >= ? AND TongTien <= ?";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, startCost);
            ps.setInt(2, endCost);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setSdt(rs.getString(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                hd.setGhiChu(rs.getString(6));
                result.add(hd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public int getDoanhThu(){
        int doanhthu = 0;
        try {
            String sql = "SELECT SUM(TongTien) AS total_tongtien FROM hoadon";
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doanhthu = rs.getInt("total_tongtien");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return doanhthu;
    }
    public int getDoanhThuTheoQuy(int quy, int year){
        int doanhthu = 0;
        String thangquy = "";
        if (quy == 1){
            thangquy = "1 AND 3";
        }else if (quy == 2){
            thangquy = "4 AND 6";
        }else if (quy == 3){
            thangquy = "7 AND 9";
        }else{
            thangquy = "10 AND 12";
        }
        String sql = "SELECT SUM(TongTien) AS total_tongtien FROM hoadon WHERE YEAR(NgayLap) = "+year +" AND MONTH(NgayLap) BETWEEN " + thangquy;
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doanhthu = rs.getInt("total_tongtien");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return doanhthu;
    }

}
