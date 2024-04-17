package DAO;

import Model.CTPhieuNhap;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Model.PhieuNhap;
import java.time.LocalDateTime;


public class PhieuNhapDAO {
    MyConnect myConnect = new MyConnect();
    public ArrayList<PhieuNhap> getAllPhieuNhap() {
        ArrayList<PhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuNhap";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maPN = rs.getInt("MaPN");
                int maNCC = rs.getInt("MaNCC");
                int maNV = rs.getInt("MaNV");
                Date ngayLap = rs.getDate("NgayLap");
                int tongTien = rs.getInt("TongTien");

                PhieuNhap phieuNhap = new PhieuNhap(maPN, maNCC, maNV, ngayLap, tongTien);
                list.add(phieuNhap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            return null;
        }
        return list;
    }
    public PhieuNhap getPhieuNhap(int maPN){
        PhieuNhap pn = new PhieuNhap();
        String sql = "SELECT * FROM PhieuNhap WHERE MaPN = ?";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, maPN);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                pn.setMaPN(rs.getInt("MaPN"));
                pn.setMaNCC(rs.getInt("MaNCC"));
                pn.setMaNV(rs.getInt("MaNV"));
                pn.setNgayLap(rs.getDate("NgayLap"));
                pn.setTongTien(rs.getInt("TongTien"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            return null;
        }
        return pn;
    }
    
    public int getPhieuNhapMoiNhat() {
        try {
            String sql = "SELECT MAX(MaPN) FROM PhieuNhap";
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public boolean addPhieuNhap(PhieuNhap pn) {
        boolean result = false;
        String sql = "INSERT INTO PhieuNhap (MaNCC, MaNV, NgayLap, TongTien) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            ps.setInt(1, pn.getMaNCC());
            ps.setInt(2, pn.getMaNV());
            ps.setTimestamp(3, timestamp);
            ps.setInt(4, pn.getTongTien());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    
    public boolean updatePhieuNhap(PhieuNhap phieuNhap) {
        boolean result = false;
        String sql = "UPDATE PhieuNhap SET MaNCC = ?, MaNV = ?, NgayLap = ?, TongTien = ? WHERE MaPN = ?";

        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, phieuNhap.getMaNCC());
            ps.setInt(2, phieuNhap.getMaNV());
            ps.setDate(3, (Date) phieuNhap.getNgayLap());
            ps.setInt(4, phieuNhap.getTongTien());
            ps.setInt(5, phieuNhap.getMaPN());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    
    public boolean deletePhieuNhap(int maPN) {
        boolean result = false;
        String sql = "DELETE FROM PhieuNhap WHERE MaPN = ?";
        
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, maPN);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<PhieuNhap> getListHoaDonByDate(String startDate, String endDate) { 
        try {
            
            String sql = "SELECT * FROM PhieuNhap WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setString(1, startDate);
            pre.setString(2, endDate);
            ResultSet rs = pre.executeQuery();

            ArrayList<PhieuNhap> dsPhieuNhap = new ArrayList<>();

            while (rs.next()){
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPN(rs.getInt("MaPN"));
                pn.setMaNCC(rs.getInt("MaNCC"));
                pn.setMaNV(rs.getInt("MaNV"));
                pn.setNgayLap(rs.getDate("NgayLap"));
                pn.setTongTien(rs.getInt("TongTien"));
                dsPhieuNhap.add(pn);
            }
            return dsPhieuNhap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        return null;
    }
    
    public ArrayList<PhieuNhap> getListHoaDonByCost(int startCost, int endCost) {
    ArrayList<PhieuNhap> dsPhieuNhap = new ArrayList<>();
    try {
        String sql = "SELECT * FROM PhieuNhap WHERE TongTien >= ? AND TongTien <= ?";
        PreparedStatement ps = myConnect.conn.prepareStatement(sql);
        ps.setInt(1, startCost);
        ps.setInt(2, endCost);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()){
            PhieuNhap pn = new PhieuNhap();
            pn.setMaPN(rs.getInt("MaPN"));
            pn.setMaNCC(rs.getInt("MaNCC"));
            pn.setMaNV(rs.getInt("MaNV"));
            pn.setNgayLap(rs.getDate("NgayLap"));
            pn.setTongTien(rs.getInt("TongTien"));
            dsPhieuNhap.add(pn);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return dsPhieuNhap;
}
}
