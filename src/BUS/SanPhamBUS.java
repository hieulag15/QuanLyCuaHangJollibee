package BUS;
import DAO.SanPhamDAO;
import Model.SanPham;
import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.List;
public class SanPhamBUS {
    private SanPhamDAO sanphamDAO = new SanPhamDAO();

    public SanPhamBUS() {
    }
    
    public ArrayList<SanPham> getListSanPhamActive() {
        return sanphamDAO.getListSanPhamActive();
    }
    
    public ArrayList<SanPham> getListSanPham() {
        return sanphamDAO.getAllSanPham();
    }
    
    public ArrayList<SanPham> getListSanPhamByIdLoai(int maLoai) {
        return sanphamDAO.getListSanPhamByIdLoai(maLoai);
    }
    
    public ArrayList<SanPham> getListSanPhamByKeyActive(String key) {
        return sanphamDAO.getListSanPhamByKeyActive(key);
    }
    
    public ArrayList<SanPham> getListSanPhamByKey(String key){
        return sanphamDAO.getListSanPhamByKey(key);
    }
    
    public SanPham getSanPham(int maSP){
        return sanphamDAO.getSanPham(maSP);
    }
    public boolean addSanPham(SanPham sp) {
        return sanphamDAO.addSanPham(sp);
    }
    public boolean updateSanPham(SanPham sp) {
        return sanphamDAO.updateSanPham(sp);
    }
    public boolean deleteSanPham(int masp) {
        return sanphamDAO.deleteSanPham(masp);
    }
}
