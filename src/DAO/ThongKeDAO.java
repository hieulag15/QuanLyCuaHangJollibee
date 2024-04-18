package DAO;

import java.sql.PreparedStatement;
import java.util.List;
import Model.*;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
    
    public ArrayList<SanPham> getTop5SanPhamBanChay(int year){
        ArrayList<SanPham> arrTop5 = new ArrayList<>();
        String sql = "SELECT sanpham.MaSP, sanpham.TenSP, temp.tongsoluong FROM sanpham,(SELECT cthoadon.MaSP, sum(cthoadon.SoLuong) as tongsoluong FROM cthoadon, hoadon WHERE cthoadon.MaHD = hoadon.MaHD AND YEAR(hoadon.NgayLap) = ? GROUP BY cthoadon.MaSP ORDER BY sum(cthoadon.SoLuong) DESC) AS temp WHERE sanpham.MaSP = temp.MaSP LIMIT 5;";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getInt(1), rs.getString(2), rs.getInt(3));
                arrTop5.add(sanPham);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
        return arrTop5;
    }    
    
    public int getDoanhThuThang(int month, int year){
        String sql = "SELECT SUM(hoadon.TongTien) as doanhthu FROM hoadon WHERE MONTH(hoadon.NgayLap) = ? AND YEAR(hoadon.NgayLap) = ?";
        int doanhthu = 0;
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doanhthu = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
        return doanhthu;
    }

}
