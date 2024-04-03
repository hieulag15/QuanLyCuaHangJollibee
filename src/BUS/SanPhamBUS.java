package BUS;
import DAO.SanPhamDAO;
import Model.SanPham;
import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.List;
public class SanPhamBUS {
    
    private ArrayList<SanPham> listSanPham = null;
    private SanPhamDAO sanphamDAO = new SanPhamDAO();

    public SanPhamBUS() {
        
    }
    
    public void docListSanPham() {
        listSanPham = sanphamDAO.getAllSanPham();
    }
    
    public ArrayList<SanPham> getListSanPham() {
        if (listSanPham == null) {
            docListSanPham();
        }
        return listSanPham;
    }
    
    public boolean addSanPham(SanPham sp) {
        return true;
    }
    public boolean updateSanPham(SanPham sp) {
        return true;
    }
    public boolean deleteSanPham(int masp) {
        return true;
    }
}
