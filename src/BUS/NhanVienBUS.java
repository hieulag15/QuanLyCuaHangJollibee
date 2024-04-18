/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Custom.MyDialog;
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
    
    public NhanVienBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.dsnv = nhanVienDAO.getDanhSachNhanVien();
    }
    
    public ArrayList<NhanVien> getDanhSachNhanVien() {
        if (dsnv == null) {
            this.dsnv = nhanVienDAO.getDanhSachNhanVien();
        }
        return dsnv;
    }
    
    public NhanVien getNhanVien(int ma) {
        return nhanVienDAO.getNhanVien(ma);
    }
    
    public boolean themNhanVien(int manv, String ho, String ten, String gioiTinh, String chucVu) {
        if (ho.trim().equals("")) {
            new MyDialog("Họ không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.trim().equals("")) {
            new MyDialog("Tên không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.trim().equals("")) {
            new MyDialog("Chức vụ không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        NhanVien nv = new NhanVien();
        nv.setMaNV(manv);
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        nv.setTrangThai(1);
        boolean flag = nhanVienDAO.themNhanVien(nv);
        if (!flag) {
            new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public boolean updateNhanVien(String ma, String ho, String ten, String gioiTinh, String chucVu) {
        int maNV = Integer.parseInt(ma);
        if (ho.trim().equals("")) {
            new MyDialog("Họ không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.trim().equals("")) {
            new MyDialog("Tên không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.trim().equals("")) {
            new MyDialog("Chức vụ không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        NhanVien nv = new NhanVien();
        nv.setMaNV(maNV);
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        boolean flag = nhanVienDAO.suaNhanVien(nv);
        if (!flag) {
            new MyDialog("Cập nhập thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Cập nhập thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }
    
    public boolean xoaNhanVien(String ma) {
        try {
            int maNV = Integer.parseInt(ma);
            MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                flag = nhanVienDAO.xoaNhanVien(maNV);
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
    public boolean deleteNhanVien_xoamem(int ma) {
        boolean flag = nhanVienDAO.xoamemNhanVien(ma);
        if (!flag) {
            new MyDialog("Xóa thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Xóa thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }
}
