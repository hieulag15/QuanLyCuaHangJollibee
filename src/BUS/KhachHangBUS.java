/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhachHangDAO;
import Model.KhachHang;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class KhachHangBUS {
    private ArrayList<KhachHang> dskh = new ArrayList<>();
    private KhachHangDAO khachHangDAO = new KhachHangDAO();
    
    public ArrayList<KhachHang> getListKhachHang() {
        if (dskh == null) {
            this.dskh = khachHangDAO.getDanhSachKhachHang();
        }
        return dskh;
    }
}
