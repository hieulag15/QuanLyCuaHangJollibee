/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Custom.MyDialog;
import java.util.ArrayList;
import DAO.PhanQuyenDAO;
import Model.PhanQuyen;

/**
 *
 * @author manhq
 */
public class PhanQuyenBUS {
    public static PhanQuyen quyenTK = null;
    private PhanQuyenDAO phanQuyenDAO;
    public PhanQuyenBUS(){
        phanQuyenDAO = new PhanQuyenDAO();
    }
    
    public ArrayList<PhanQuyen> getListQuyen() {
        return phanQuyenDAO.getListQuyen();
    }

    public void getQuyen(String quyen) {
        quyenTK =  phanQuyenDAO.getQuyen(quyen);
    }
    public PhanQuyen getPhanQuyen(String quyen){
        return phanQuyenDAO.getQuyen(quyen);
    }

    public boolean updateQuyen(PhanQuyen phanQuyen) {
        boolean flag = phanQuyenDAO.updateQuyen(phanQuyen);
        if (!flag) {
            new MyDialog("Cập nhật thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Cập nhật thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public boolean addQuyen(PhanQuyen phanQuyen) {
        boolean flag = phanQuyenDAO.addQuyen(phanQuyen);
        if (!flag) {
            new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public boolean deleteQuyen(String phanQuyen) {
        try {
            MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                flag = phanQuyenDAO.deleteQuyen(phanQuyen);
                if (flag) {
                    new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
                } else {
                    new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
                }
            }
            return flag;
        } catch (Exception e) {
            new MyDialog("Chưa chọn nhân viên!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }
}
