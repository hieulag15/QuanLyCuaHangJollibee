/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.LoaiSP;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Test {
    public static void main(String[] args) {
        LoaiDAO loaiDao = new LoaiDAO();
        ArrayList<LoaiSP> ds = new ArrayList<>();
        ds = loaiDao.getDanhSachLoai();
        if (ds.isEmpty()) {
            System.out.println("DAO.Test.main()");
        } else {
            System.out.println("co du lieu");
        }
    }
    
}
