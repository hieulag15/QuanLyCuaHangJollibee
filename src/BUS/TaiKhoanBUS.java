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
    public TaiKhoan getTaiKhoan(int maNhanVien){
        return taiKhoanDAO.getTaiKhoan(maNhanVien);
    }
    public boolean addTaiKhoan(TaiKhoan tk) {
        if (kiemTraTrungTenDangNhap(tk.getTenDangNhap())) {
            MyDialog dlg = new MyDialog("Tên đăng nhập bị trùng! Có thể tài khoản bị khoá, thực hiện mở khoá?", MyDialog.WARNING_DIALOG);
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                return false;
            }
            return false;
        }
        boolean flag = taiKhoanDAO.addTaiKhoan(tk);
        if (flag) {
            new MyDialog("Cấp tài khoản thành công! Mật khẩu là " + tk.getTenDangNhap(), MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Cấp tài khoản thất bại! Tài khoản đã tồn tại", MyDialog.ERROR_DIALOG);
        }
        return flag;
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
        return taiKhoanDAO.getTenDangNhapTheoMa(manv);
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
    
    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        return taiKhoanDAO.kiemTraTrungTenDangNhap(tenDangNhap);
    }
    
    public TaiKhoan getTaiKhoan (String manv){
        return taiKhoanDAO.getTaiKhoan(manv);
    }
    public List<TaiKhoan> getTaiKhoanByQuyen(String quyen){
        return taiKhoanDAO.getTaiKhoanByQuyen(quyen);
    }
    public boolean khoaTaiKhoan(int maNV){
        return taiKhoanDAO.khoaTaiKhoan(maNV);
    }
    public boolean moTaiKhoan(int maNV){
        return taiKhoanDAO.moTaiKhoan(maNV);
    }
    public TaiKhoan getTaiKhoan_DatLaiMatKhau(int manv){
        return taiKhoanDAO.getTaiKhoan_DatLaiMatKhau(manv);
    }
}
