package DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Model.PhieuNhap;


public class PhieuNhapDAO {
    public List<PhieuNhap> getAllPhieuNhap() {
        List<PhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuNhap";
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maPN = rs.getInt("MaPN");
                int maSP = rs.getInt("MaSP");
                int soLuong = rs.getInt("SoLuong");
                int donGia = rs.getInt("DonGia");
                int thanhTien = rs.getInt("ThanhTien");
                
                PhieuNhap phieuNhap = new PhieuNhap(maPN, maSP, soLuong, donGia, thanhTien);
                list.add(phieuNhap);
            }
        } catch (SQLException ex) {
            return null;
        }
        return list;
    }
    
    public boolean addPhieuNhap(PhieuNhap pn) {
        boolean result = false;
        String sql = "INSERT INTO PhieuNhap (MaPN, MaSP, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, pn.getMaPN());
            ps.setInt(2, pn.getMaSP());
            ps.setInt(3, pn.getSoLuong());
            ps.setInt(4, pn.getDonGia());
            ps.setInt(5, pn.getThanhTien());
            
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean updatePhieuNhap(PhieuNhap phieuNhap) {
        boolean result = false;
        String sql = "UPDATE PhieuNhap SET MaSP = ?, SoLuong = ?, DonGia = ?, ThanhTien = ? WHERE MaPN = ?";
        
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, phieuNhap.getMaSP());
            ps.setInt(2, phieuNhap.getSoLuong());
            ps.setInt(3, phieuNhap.getDonGia());
            ps.setInt(4, phieuNhap.getThanhTien());
            ps.setInt(5, phieuNhap.getMaPN());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean deletePhieuNhap(int maPhieuNhap) {
        boolean result = false;
        String sql = "DELETE FROM PhieuNhap WHERE MaPN = ?";
        
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, maPhieuNhap);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
