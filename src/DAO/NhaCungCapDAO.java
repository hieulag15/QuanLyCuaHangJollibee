/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import Model.NhaCungCap;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class NhaCungCapDAO {
    
    MyConnect myConnect = new MyConnect();
    
    public ArrayList<NhaCungCap> getListNhaCungCap() {
        try {
            ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<>();
            String sql = "SELECT * FROM nhacungcap";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setDiaChi(rs.getString(3));
                ncc.setDienThoai(rs.getString(4));
                dsNhaCungCap.add(ncc);
            }
            return dsNhaCungCap;
        } catch (SQLException ex) {
            return null;
        }
    }

    public NhaCungCap getNhaCungCap(int maNCC) {
        NhaCungCap ncc = null;
        try {
            String sql = "SELECT * FROM nhacungcap WHERE MaNCC=?"; 
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, maNCC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setDiaChi(rs.getString(3));
                ncc.setDienThoai(rs.getString(4));
            }
        } catch (SQLException ex) {
            return null;
        }
        return ncc;
    }

    public boolean addNCC(NhaCungCap ncc) {
        boolean result = false;
        try {
            String sql = "INSERT INTO nhacungcap VALUES(?,?,?,?)";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, ncc.getMaNCC());
            ps.setString(2, ncc.getTenNCC());
            ps.setString(3, ncc.getDiaChi());
            ps.setString(4, ncc.getDienThoai());
            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateNCC(NhaCungCap ncc) {
        boolean result = false;
        try {
            String sql = "UPDATE nhacungcap SET TenNCC=?, DiaChi=?, DienThoai=? WHERE MaNCC=?";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getDiaChi());
            ps.setString(3, ncc.getDienThoai());
            ps.setInt(4, ncc.getMaNCC());
            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    public boolean deleteNCC(int maNCC) {
        boolean result = false;
        try {
            String sql = "DELETE FROM nhacungcap WHERE MaNCC=?";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, maNCC);
            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}
