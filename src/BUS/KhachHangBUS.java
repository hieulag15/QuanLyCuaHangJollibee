/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Custom.MyDialog;
import DAO.KhachHangDAO;
import Model.KhachHang;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class KhachHangBUS {

    private ArrayList<KhachHang> dskh = new ArrayList<>();
    private KhachHangDAO khachHangDAO = new KhachHangDAO();

    public ArrayList<KhachHang> getListKhachHang() {
        return khachHangDAO.getDanhSachKhachHang();
    }

    public ArrayList<KhachHang> getDanhSachKhachHangByKey(String key, String minChiTieu, String maxChiTieu) {
        boolean rs = true;
        int min = 0, max = 0;
        try {
            min = minChiTieu.equals("") ? 0 : Integer.valueOf(minChiTieu);
            max = maxChiTieu.equals("") ? Integer.MAX_VALUE : Integer.valueOf(maxChiTieu);
            if (max < min || max < 0 || min < 0) {
                rs = false;
            }
        } catch (Exception ex) {
            rs = false;
        }

        if (rs) {
            return khachHangDAO.getDanhSachKhachHangByKey(key, min, max);
        } else {
            new MyDialog("Thông tin kiếm không hợp lệ!!!", MyDialog.ERROR);
            return null;
        }
    }
    
    public ArrayList<KhachHang> getDanhSachKhachHangByKey(String key){
        return khachHangDAO.getDanhSachKhachHangByKey(key);
    }

    public boolean checkSoDienThoai(String sdt) {
        try {
            int number = Integer.parseInt(sdt);
            return number > -1;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public KhachHang getKhachHang(String SoDienThoai){
        return khachHangDAO.getKhachHang(SoDienThoai);
    }

    public boolean themKhachHang(String sdt, String ho, String ten, String gioiTinh) {
        if (sdt.trim().equals("")) {
            new MyDialog("Không được để trống số điện thoại!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (!checkSoDienThoai(sdt.trim())) {
            new MyDialog("Số điện thoại không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ho.trim().equals("")) {
            new MyDialog("Không được để trống họ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.trim().equals("")) {
            new MyDialog("Không được để trống tên!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (gioiTinh.equals("Chọn giới tính")) {
            new MyDialog("Hãy chọn giới tính!", MyDialog.ERROR_DIALOG);
            return false;
        }

        KhachHang kh = new KhachHang();
        kh.setSdt(sdt);
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setGioiTinh(gioiTinh);
        kh.setTongChiTieu(0);
        boolean flag = khachHangDAO.themKhachHang(kh);
        if (flag) {
            new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean suaKhachHang(String sdt, String ho, String ten, String gioiTinh) {
        if (ho.trim().equals("")) {
            new MyDialog("Không được để trống họ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.trim().equals("")) {
            new MyDialog("Không được để trống tên!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (gioiTinh.equals("Chọn giới tính")) {
            new MyDialog("Hãy chọn giới tính!", MyDialog.ERROR_DIALOG);
            return false;
        }
        KhachHang kh = new KhachHang();
        kh.setSdt(sdt);
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setGioiTinh(gioiTinh);
        boolean flag = khachHangDAO.capNhatKhachHang(kh);
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean xoaKhachHang(String sdt) {
        if (sdt.equals("")) {
            new MyDialog("Chưa chọn khách hàng!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;

        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.getAction() == MyDialog.CANCEL_OPTION) {
            return false;
        }

        flag = khachHangDAO.xoaKhachHang(sdt);

        if (flag) {
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
