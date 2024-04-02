package BUS;
import Custom.MyDialog;
import DAO.TaiKhoanDAO;
import Model.TaiKhoan;
import java.util.List;
public class TaiKhoanBUS {
    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public TaiKhoanBUS() { 
    }
    public List<TaiKhoan> getListTaiKhoan() {
        return taiKhoanDAO.getAllTaiKhoan();
    }
    public boolean addTaiKhoan(TaiKhoan tk) {
        return taiKhoanDAO.addTaiKhoan(tk);
    }
    public boolean updateTaiKhoan(TaiKhoan tk) {
        return taiKhoanDAO.updateTaiKhoan(tk);
    }
    public boolean deleteTaiKhoan(int manv) {
        return taiKhoanDAO.deleteTaiKhoan(manv);
    }
    public boolean doiMatKhau(String mkCu, String mkMoi, String nhapLaiMk) {
        if(!mkMoi.equals(nhapLaiMk)) {
            new MyDialog("Mật khẩu mới không khớp!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = taiKhoanDAO.doiMatKhau(mkCu, mkMoi);
        if (flag) {
            new MyDialog("Đổi thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Mật khẩu cũ nhập sai!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public String getQuyenByMaNV(String manv) {
        int maNV = Integer.parseInt(manv);
        return taiKhoanDAO.getQuyenTheoMa(maNV);
    }
    
    public String getTenDangNhapByMaNV(String manv) {
        int maNV = Integer.parseInt(manv);
        return taiKhoanDAO.getTenDangNhapTheoMa(maNV);
    }
    
    public void datLaiMatKhau(String ma, String tenDangNhap) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.datLaiMatKhau(maNV, tenDangNhap);
        if (flag) {
            new MyDialog("Đặt lại thành công! Mật khẩu mới là: " + tenDangNhap, MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Đặt lại thất bại!", MyDialog.ERROR_DIALOG);
        }
    }
}
