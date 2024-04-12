/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Model.LoaiSP;
import java.util.ArrayList;
import DAO.LoaiDAO;

/**
 *
 * @author manhq
 */
public class LoaiBUS {
    
    private LoaiDAO loaiDAO;
    
    public LoaiBUS(){
        loaiDAO = new LoaiDAO();
    };
    
    public ArrayList<LoaiSP> getDanhSachLoai() {
        return loaiDAO.getDanhSachLoai();
    }
    
    public LoaiSP getLoai(int ma) {
        return loaiDAO.getLoai(ma);
    }

    public boolean addLoai(String tenLoai) {
        return loaiDAO.addLoai(tenLoai);
    }

    public boolean deleteLoai(int maLoai) {
        return loaiDAO.deleteLoai(maLoai);
    }

    public boolean updateLoai(int maLoai, String ten) {
        return loaiDAO.updateLoai(maLoai, ten);
    }
}
