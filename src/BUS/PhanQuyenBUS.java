/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;
import DAO.PhanQuyenDAO;
import Model.PhanQuyen;

/**
 *
 * @author manhq
 */
public class PhanQuyenBUS {
    
    private PhanQuyenDAO phanQuyenDAO;
    public PhanQuyenBUS(){
        phanQuyenDAO = new PhanQuyenDAO();
    }
    
    public ArrayList<PhanQuyen> getListQuyen() {
        return phanQuyenDAO.getListQuyen();
    }

    public PhanQuyen getQuyen(String quyen) {
        return phanQuyenDAO.getQuyen(quyen);
    }

    public boolean updateQuyen(PhanQuyen phanQuyen) {
        return phanQuyenDAO.updateQuyen(phanQuyen);
    }

    public boolean addQuyen(PhanQuyen phanQuyen) {
        return phanQuyenDAO.addQuyen(phanQuyen);
    }

    public boolean deleteQuyen(String phanQuyen) {
        return phanQuyenDAO.deleteQuyen(phanQuyen);
    }
}
