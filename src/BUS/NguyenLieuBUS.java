/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NguyenLieuDAO;
import Model.NguyenLieu;
import java.util.ArrayList;

/**
 *
 * @author Nun-PC
 */
public class NguyenLieuBUS {
    NguyenLieuDAO nlDAO = new NguyenLieuDAO();
    public ArrayList<NguyenLieu> getAllNguyenLieu() {
        return nlDAO.getAllNguyenLieu();
    }
    
    public NguyenLieu getNguyenLieu(int ma) {
        return nlDAO.getNguyenLieu(ma);
    }
    
    public boolean addNguyenLieu(NguyenLieu nl) {
        return nlDAO.addNguyenLieu(nl);
    }
    
    public boolean deleteNguyenLieu(int ma) {
        return nlDAO.deleteNguyenLieu(ma);
    }
    
    public boolean updateNguyenLieu(NguyenLieu nl) {
        return nlDAO.updateNguyenLieu(nl);
    }
}
