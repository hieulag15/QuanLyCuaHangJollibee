/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.NguyenLieu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nun-PC
 */
public class NguyenLieuDAO {
    MyConnect myConnect = new MyConnect();
    
    public ArrayList<NguyenLieu> getAllNguyenLieu() {
        try {
            String sql = "SELECT * FROM NguyenLieu";
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<NguyenLieu> dsnl = new ArrayList<>();
            while (rs.next()) {
                NguyenLieu nl = new NguyenLieu();

                nl.setMaNL(rs.getInt(1));
                nl.setTenNL(rs.getString(2));
                nl.setSoLuong(rs.getInt(3));
                nl.setDonViTinh(rs.getString(4));
                nl.setHinhAnh(rs.getString(5));
                nl.setDonGia(rs.getInt(6));

                dsnl.add(nl);
            }
            return dsnl;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public NguyenLieu getNguyenLieu(int ma) {
        try {
            String sql = "SELECT * FROM NguyenLieu WHERE MaNL = ?";
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();
            NguyenLieu nl = new NguyenLieu();
            if (rs.next()) {
                nl.setMaNL(rs.getInt(1));
                nl.setTenNL(rs.getString(2));
                nl.setSoLuong(rs.getInt(3));
                nl.setDonViTinh(rs.getString(4));
                nl.setHinhAnh(rs.getString(5));
                nl.setDonGia(rs.getInt(6));
            }
            return nl;
        } catch (SQLException e) {
        }

        return null;
    }
}
