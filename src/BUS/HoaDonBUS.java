/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Model.HoaDon;
import java.sql.Date;
import java.util.ArrayList;
import DAO.HoaDonDAO;

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

    public boolean addHoaDon(HoaDon hd) {
        return hoaDonDAO.addHoaDon(hd);
    }

    public int getMaHoaDonMoiNhat() {
        return hoaDonDAO.getMaHoaDonMoiNhat();
    }

    public ArrayList<HoaDon> getListHoaDon(Date dateStart, Date dateEnd) {
        return hoaDonDAO.getListHoaDon(dateStart, dateEnd);
    }
}
