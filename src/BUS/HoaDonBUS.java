/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Model.HoaDon;
import java.sql.Date;
import java.util.ArrayList;
import DAO.HoaDonDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author manhq
 */
public class HoaDonBUS {
    
    private HoaDonDAO hoaDonDAO;
    
    public HoaDonBUS(){
        hoaDonDAO = new HoaDonDAO();
    }
    
    public ArrayList<HoaDon> getListHoaDon() {
        return hoaDonDAO.getListHoaDon();
    }
    
    public HoaDon getHoaDon(int ma){
        return hoaDonDAO.getHoaDon(ma);
    }

    public void addHoaDon(HoaDon hd) {
        hoaDonDAO.addHoaDon(hd);
    }

    public int getMaHoaDonMoiNhat() {
        return hoaDonDAO.getMaHoaDonMoiNhat();
    }
    
    public void luuHoaDon(String sdt, int maNV, int tongTien, String ghiChu) {      
        HoaDon hd = new HoaDon();
        hd.setMaNV(maNV);
        hd.setSdt(sdt);
        hd.setGhiChu(ghiChu);
        hd.setTongTien(tongTien);

        hoaDonDAO.addHoaDon(hd);
    }

    public ArrayList<HoaDon> getListHoaDonByDate(String startDate, String endDate) {
        return hoaDonDAO.getListHoaDonByDate(startDate, endDate);
    }
    
    public ArrayList<HoaDon> getListHoaDonByCost(int startCost, int endCost) {
        return hoaDonDAO.getListHoaDonByCost(startCost, endCost);
    }
    public int getDoanhThu(){
        return hoaDonDAO.getDoanhThu();
    }
    public int getDoanhThuTheoQuy(int quy, int nam){
        return hoaDonDAO.getDoanhThuTheoQuy(quy, nam);
    }
}
