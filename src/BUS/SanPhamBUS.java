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
    
    public ArrayList<SanPham> getListSanPhamByActive(int tinhTrang) {
        return sanphamDAO.getListSanPhamByActive(tinhTrang);
    }
    
    public ArrayList<SanPham> getListSanPham() {
        return sanphamDAO.getAllSanPham();
    }
    
    public ArrayList<SanPham> getListSanPhamByIdLoai(int maLoai) {
        return sanphamDAO.getListSanPhamByIdLoai(maLoai);
    }
    
    public ArrayList<SanPham> getListSanPhamByKey(String key, Integer tinhTrang) {
        return sanphamDAO.getListSanPhamByKey(key, tinhTrang);
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
