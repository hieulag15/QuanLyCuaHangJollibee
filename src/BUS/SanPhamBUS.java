package BUS;
import DAO.SanPhamDAO;
import Model.SanPham;
import java.awt.image.SampleModel;
import java.util.List;
public class SanPhamBUS {
    private SanPhamDAO sanPhamDAO;

    public SanPhamBUS() {
    }

    public SanPhamBUS(SanPhamDAO sanPhamDAO) {
        this.sanPhamDAO = sanPhamDAO;
    }
    
    public List<SanPham> getListSanPham() {
        return sanPhamDAO.getAllSanPham();
    }
    
    public boolean addSanPham(SanPham sp) {
        return sanPhamDAO.addSanPham(sp);
    }
    public boolean updateSanPham(SanPham sp) {
        return sanPhamDAO.updateSanPham(sp);
    }
    public boolean deleteSanPham(int masp) {
        return sanPhamDAO.deleteSanPham(masp);
    }
}
