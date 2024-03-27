/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.GiamGiaDAO;
import DAO.MyConnect;
import Model.GiamGia;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chuot
 */
public class GIamGiaBUS {
    
    private GiamGiaDAO giamgiaDAO;

    public GIamGiaBUS() {
    giamgiaDAO = new GiamGiaDAO();
    }
    
    public List<GiamGia> getAllGiamGia() {
        return giamgiaDAO.getAllGiamGia();
    }
    
    
    
   
    public boolean deleteGiamGia(int maGiam) {
        return giamgiaDAO.deleteGiamGia(maGiam);
    }
    
    
    
}
