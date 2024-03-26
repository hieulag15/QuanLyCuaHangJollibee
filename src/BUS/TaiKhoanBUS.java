package BUS;
import DAO.TaiKhoanDAO;
import Model.TaiKhoan;
import java.util.List;
public class TaiKhoanBUS {
    private TaiKhoanDAO taiKhoanDAO;

    public TaiKhoanBUS(TaiKhoanDAO taiKhoanDAO) {
        this.taiKhoanDAO = taiKhoanDAO;
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
}
