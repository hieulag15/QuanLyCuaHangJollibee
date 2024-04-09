package DAO;

import java.sql.PreparedStatement;
import java.util.List;
import Model.*;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongKeDAO {
    MyConnect myConnect = new MyConnect();
    public List<ThongKe> getAllThongKe() {
        List<ThongKe> list = new ArrayList<>();
        String sql = "SELECT * FROM ThongKe";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int soLuongSP = rs.getInt("SoLuongSP");
                int soLuongKH = rs.getInt("SoLuongKH");
                int soLuongNV = rs.getInt("SoLuongNV");
                int[] tongThuQuy = new int[]{
                    rs.getInt("TongThuQuy1"),
                    rs.getInt("TongThuQuy2"),
                    rs.getInt("TongThuQuy3"),
                    rs.getInt("TongThuQuy4")
                };
                ArrayList<SanPham> topSanPhamBanChay = null; // chưa biết cách lấy sp bán chạy 
                
                ThongKe thongKe = new ThongKe(soLuongSP, soLuongKH, soLuongNV, tongThuQuy, topSanPhamBanChay);
                list.add(thongKe);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
