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
}
      
     
      
        
    

