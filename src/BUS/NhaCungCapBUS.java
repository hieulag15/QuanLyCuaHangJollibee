/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaCungCapDAO;
import java.util.ArrayList;
import Model.NhaCungCap;

/**
 *
 * @author manhq
 */
public class NhaCungCapBUS {

    private NhaCungCapDAO nhaCungCapDAO;

    public NhaCungCapBUS() {
        nhaCungCapDAO = new NhaCungCapDAO();
    }

    public NhaCungCap getNhaCungCap(int maNCC) {
        return nhaCungCapDAO.getNhaCungCap(maNCC);
    }

    public ArrayList<NhaCungCap> getListNhaCungCap() {
        return nhaCungCapDAO.getListNhaCungCap();
    }

    public boolean themNhaCungCap(int maNCC, String tenNCC, String diaChi, String dienThoai) {
        NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, dienThoai);
        boolean flag = nhaCungCapDAO.addNCC(ncc);
        return flag;
    }

    public boolean suaNhaCungCap(String maNCC, String tenNCC, String diaChi, String dienThoai) {
        int ma = Integer.parseInt(maNCC);
        NhaCungCap ncc = new NhaCungCap(ma, tenNCC, diaChi, dienThoai);
        boolean flag = nhaCungCapDAO.updateNCC(ncc);
        return flag;
    }

    public boolean deleteNCC(int maNCC) {
        return nhaCungCapDAO.deleteNCC(maNCC);
    }
}
