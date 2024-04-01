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
}
