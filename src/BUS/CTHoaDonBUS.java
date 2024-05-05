/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.CTHoaDonDAO;
import Model.CTHoaDon;
import java.sql.Date;
import java.util.ArrayList;



/**
 *
 * @author chuot
 */
public class CTHoaDonBUS {
    private CTHoaDonDAO ctHoaDonDAO;
    private HoaDonBUS hdBUS = new HoaDonBUS();
    

    public CTHoaDonBUS() {
       ctHoaDonDAO = new CTHoaDonDAO();
    }
    
    
    public ArrayList<CTHoaDon> getListChiTietHoaDon() {
        return ctHoaDonDAO.getListChiTietHoaDon();
    }
    
    public ArrayList<CTHoaDon> getListChiTietHoaDonTheoMaHD(int maHD) {
        return ctHoaDonDAO.getListChiTietHoaDonTheoMaHD(maHD);
    }
    
    public boolean addChiTietHoaDon(CTHoaDon cthd) {
       return ctHoaDonDAO.addChiTietHoaDon(cthd);
    }
    
    public boolean xoaChiTietHoaDon(int MaHD) {  
       return ctHoaDonDAO.xoaChiTietHoaDon(MaHD);
    }
    
    public boolean capNhatChiTietHoaDon(CTHoaDon cthd) {
       return ctHoaDonDAO.capNhatChiTietHoaDon(cthd);
    }
    
    public CTHoaDon getChiTietHoaDon(int maHD, int maSP) {
        return ctHoaDonDAO.getChiTietHoaDon(maHD, maSP);
    }    
    
    public void addCTHoaDon(String maSP, String soLuong, String donGia, String thanhTien) {
        int ma = hdBUS.getMaHoaDonMoiNhat();

        donGia = donGia.replace(",","").replace("Đ", "");
        thanhTien = thanhTien.replace(",", "").replace("Đ", "");

        CTHoaDon cthd = new CTHoaDon();

        cthd.setMaHD(ma);
        cthd.setMaSP(Integer.parseInt(maSP));
        cthd.setDonGia(Integer.parseInt(donGia));
        cthd.setSoLuong(Integer.parseInt(soLuong));
        cthd.setThanhTien(Integer.parseInt(thanhTien));

        ctHoaDonDAO.addChiTietHoaDon(cthd);
    }
}

    
    

