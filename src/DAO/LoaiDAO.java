/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.LoaiSP;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class LoaiDAO {

    MyConnect myConnect = new MyConnect();

    public ArrayList<LoaiSP> getDanhSachLoai() {
        try {
            String sql = "SELECT * FROM Loai";
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<LoaiSP> dsLoai = new ArrayList<>();
            while (rs.next()) {
                LoaiSP loai = new LoaiSP();
                loai.setMaLoai(rs.getInt(1));
                loai.setTenLoai(rs.getString(2));
                dsLoai.add(loai);
            }
            return dsLoai;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public LoaiSP getLoai(int ma) {
        try {
            String sql = "SELECT * FROM Loai where MaLoai = ?";
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, ma);
            ResultSet rs = ps.executeQuery();
            LoaiSP loai = new LoaiSP();
            if (rs.next()) {         
                loai.setMaLoai(rs.getInt(1));
                loai.setTenLoai(rs.getString(2));
            }
            return loai;
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean addLoai(String tenLoai) {
        try {

            String sql = "insert into loai(TenLoai) values (?)";
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setString(1, tenLoai);
            int x = ps.executeUpdate();
            return x > 0 ? true : false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteLoai(int maLoai) {
        try {
            String sql = "DELETE FROM Loai WHERE MaLoai=?";
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, maLoai);
            int x = ps.executeUpdate();
            return x > 0 ? true : false;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean updateLoai(int maLoai, String ten) {
        try {
            String sql = "UPDATE Loai SET TenLoai=? WHERE MaLoai=?";
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setString(1, ten);
            ps.setInt(2, maLoai);
            int x = ps.executeUpdate();
            return x > 0 ? true : false;
        } catch (SQLException e) {
        }
        return false;
    }
}
