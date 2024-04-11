/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import java.sql.*;
import java.util.List;
import Model.GiamGia;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class GiamGiaDAO {
    MyConnect myConnect = new MyConnect();
    public ArrayList<GiamGia> getAllGiamGia() {     
        ArrayList<GiamGia> list = new ArrayList<>();
        String sql = "SELECT * FROM giamgia";
        try {
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maGiam = rs.getInt("MaGiam");
                String tenGiamGia = rs.getString("TenGiamGia");
                int phanTramGiam = rs.getInt("PhanTramGiam");
                int dieuKien = rs.getInt("DieuKien");
                Date ngayBD = rs.getDate("NgayBD");
                Date ngayKT = rs.getDate("NgayKT");

                GiamGia giamGia = new GiamGia(maGiam, tenGiamGia, phanTramGiam, dieuKien, ngayBD, ngayKT);
                list.add(giamGia);
            }
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    
    
    
      public boolean deleteGiamGia(int maGiam) {
        boolean result = false;
        String sql = "DELETE FROM giamgia WHERE MaGiam = ?";
        try {
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, maGiam);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
      
      public boolean themMaGiam(GiamGia gg) {
        try {
            String sql = "INSERT INTO giamgia(MaGiam,TenGiamGia, PhanTramGiam, DieuKien, NgayBD, NgayKT) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setInt(1, gg.getMaGiam());
            pre.setString(2, gg.getTenGiamGia());
            pre.setInt(3, gg.getPhanTramGiam());
            pre.setInt(4, gg.getDieuKien());

            pre.setTimestamp(5, new java.sql.Timestamp(gg.getNgayBD().getTime()));
            pre.setTimestamp(6, new java.sql.Timestamp(gg.getNgayKT().getTime()));

            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean suaMaGiam(GiamGia gg) {
        try {
            String sql = "UPDATE giamgia SET TenGiamGia=?, PhanTramGiam=?, DieuKien=?, NgayBD=?, NgayKT=? WHERE MaGiam=?";
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setString(1, gg.getTenGiamGia());
            pre.setInt(2, gg.getPhanTramGiam());
            pre.setInt(3, gg.getDieuKien());

            pre.setTimestamp(4, new java.sql.Timestamp(gg.getNgayBD().getTime()));
            pre.setTimestamp(5, new java.sql.Timestamp(gg.getNgayKT().getTime()));

            pre.setInt(6, gg.getMaGiam());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean kiemTraMaGiamDaTonTai(int maGiam) {
    String sql = "SELECT COUNT(*) FROM giamgia WHERE MaGiam = ?";
    try {
        PreparedStatement ps = myConnect.conn.prepareStatement(sql);
        ps.setInt(1, maGiam);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false;
}
    
}
      
     
      
        
    

