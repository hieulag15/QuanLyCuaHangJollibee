package BUS;
import DAO.ThongKeDAO;
import Model.ThongKe;
import java.util.List;
public class ThongKeBUS {
    private ThongKeDAO thongKeDAO;

    public ThongKeBUS(ThongKeDAO thongKeDAO) {
        this.thongKeDAO = thongKeDAO;
    }
    
    public List<ThongKe> getAllThongKe() {
        return thongKeDAO.getAllThongKe();
    }
}
