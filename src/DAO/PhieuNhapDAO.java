package DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Model.PhieuNhap;


public class PhieuNhapDAO {
    MyConnect myConnect = new MyConnect();
    public List<PhieuNhap> getAllPhieuNhap() {
        List<PhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuNhap";
        try {
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
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
        String sql = "SELECT * FROM PhieuNhap WHERE MaPN = "+ maPN;
        try {
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int maPNh = rs.getInt("MaPN");
            int maNCC = rs.getInt("MaNCC");
            int maNV = rs.getInt("MaNV");
            Date ngayLap = rs.getDate("NgayLap");
            int tongTien = rs.getInt("TongTien");

            pn = new PhieuNhap(maPNh, maNCC, maNV, ngayLap, tongTien);
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            return null;
        }
        return pn;
    }
    
    public boolean addPhieuNhap(PhieuNhap pn) {
        boolean result = false;
        String sql = "INSERT INTO PhieuNhap (MaPN, MaNCC, MaNV, NgayLap, TongTien) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, pn.getMaPN());
            ps.setInt(2, pn.getMaNCC());
            ps.setInt(3, pn.getMaNV());
            ps.setDate(4, (Date) pn.getNgayLap());
            ps.setInt(5, pn.getTongTien());

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
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
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
            PreparedStatement ps = myConnect.conn.prepareStatement(sql);
            ps.setInt(1, maPN);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
