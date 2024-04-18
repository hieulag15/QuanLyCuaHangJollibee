package BUS;
import DAO.ThongKeDAO;
import Model.SanPham;
import Model.ThongKe;
import java.util.ArrayList;
import java.util.List;
public class ThongKeBUS {
    private ThongKeDAO thongKeDAO;
    
    public ThongKeBUS() {
        thongKeDAO = new ThongKeDAO();
    }

    public ThongKeBUS(ThongKeDAO thongKeDAO) {
        this.thongKeDAO = thongKeDAO;
    }
    
    public List<ThongKe> getAllThongKe() {
        return thongKeDAO.getAllThongKe();
    }
    
    public ArrayList<SanPham> getTop5SanPhamBanChay(int year){
         return thongKeDAO.getTop5SanPhamBanChay(year);
    } 
    
    public int getDoanhThuThang(int month, int year){
        return thongKeDAO.getDoanhThuThang(month, year);
    }
}
