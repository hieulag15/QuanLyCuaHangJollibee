/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import Model.NhanVien;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class NhanVienBUS {
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();
    private ArrayList<NhanVien> dsnv = new ArrayList<>();
    
    public ArrayList<NhanVien> getDanhSachNhanVien() {
        if (dsnv == null) {
            this.dsnv = nhanVienDAO.getDanhSachNhanVien();
        }
        return dsnv;
    }
}
