/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Custom.MyDialog;
import DAO.GiamGiaDAO;
import DAO.MyConnect;
import Model.GiamGia;
import Model.KhachHang;
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
public class GiamGiaBUS {

    private ArrayList<GiamGia> dsgg = new ArrayList<>();
    private GiamGiaDAO giamgiaDAO;

    public GiamGiaBUS() {
        giamgiaDAO = new GiamGiaDAO();
    }

    public ArrayList<GiamGia> getAllGiamGia() {
        return giamgiaDAO.getAllGiamGia();
    }

    public boolean deleteGiamGia(int maGiam) {
        return giamgiaDAO.deleteGiamGia(maGiam);
    }

    public boolean themMaGiam(int Ma, String ten, String phanTram, String dieuKien, java.util.Date ngayBD, java.util.Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");

        if (giamgiaDAO.kiemTraMaGiamDaTonTai(Ma)) {
            new MyDialog("Mã giảm giá đã tồn tại!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;

        int phanTramGiam = Integer.parseInt(phanTram);
        int dieuKienGiam = Integer.parseInt(dieuKien);

        GiamGia gg = new GiamGia();
        gg.setMaGiam(Ma);
        gg.setTenGiamGia(ten);
        gg.setPhanTramGiam(phanTramGiam);
        gg.setDieuKien(dieuKienGiam);
        gg.setNgayBD(ngayBD);
        gg.setNgayKT(ngayKT);

        flag = giamgiaDAO.themMaGiam(gg);

        return flag;
    }

    public boolean suaMaGiam(String ma, String ten, String phanTram, String dieuKien, java.util.Date ngayBD, java.util.Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        if (ma.equals("")) {
            new MyDialog("Chưa chọn mã để sửa!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;

        int maGiam = Integer.parseInt(ma);
        int phanTramGiam = Integer.parseInt(phanTram);
        int dieuKienGiam = Integer.parseInt(dieuKien);

        GiamGia gg = new GiamGia();
        gg.setMaGiam(maGiam);
        gg.setTenGiamGia(ten);
        gg.setPhanTramGiam(phanTramGiam);
        gg.setDieuKien(dieuKienGiam);
        gg.setNgayBD(ngayBD);
        gg.setNgayKT(ngayKT);

        flag = giamgiaDAO.suaMaGiam(gg);

        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

}
