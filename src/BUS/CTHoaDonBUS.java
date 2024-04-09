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
    }    }

    
    

