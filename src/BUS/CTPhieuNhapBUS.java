/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTPhieuNhapDAO;
import Model.CTPhieuNhap;


/**
 *
 * @author chuot
 */
import java.util.ArrayList;
public class CTPhieuNhapBUS {
    
    private CTPhieuNhapDAO ctPhieuNhapDAO;

    public CTPhieuNhapBUS() {
        ctPhieuNhapDAO = new CTPhieuNhapDAO();
    }
    
    public ArrayList<CTPhieuNhap> getListChiTietPhieuNhap() {
        return ctPhieuNhapDAO.getListChiTietPhieuNhap();
    }
    
    public boolean themChiTietPhieuNhap(CTPhieuNhap ctpn) {
        return ctPhieuNhapDAO.themChiTietPhieuNhap(ctpn);
}
    
    public boolean xoaChiTietPhieuNhap(int MaPN) {
        return ctPhieuNhapDAO.xoaChiTietPhieuNhap(MaPN);
}
    public boolean capNhatPhieuNhap(CTPhieuNhap pn) {
        return ctPhieuNhapDAO.capNhatPhieuNhap(pn);
}

    public CTPhieuNhap getChiTietPhieuNhap(int maPN) {
        return ctPhieuNhapDAO.getChiTietPhieuNhap(maPN);
    }
    
    
}
