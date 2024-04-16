package BUS;
import DAO.PhieuNhapDAO;
import Model.PhieuNhap;
import java.util.ArrayList;
import java.util.List;
public class PhieuNhapBUS {
    private PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();

    public PhieuNhapBUS() {
        
    }
    public List<PhieuNhap> getAllPhieuNhap() {
        return phieuNhapDAO.getAllPhieuNhap();
    }
    public int getPhieuNhapMoiNhat() {
        return phieuNhapDAO.getPhieuNhapMoiNhat();
    }
    public PhieuNhap getPhieuNhap(int maPN){
        return phieuNhapDAO.getPhieuNhap(maPN);
    }
    public void luuPhieuNhap(int maNCC, int maNV, int tongTien) {      
        PhieuNhap pnhap = new PhieuNhap();
        pnhap.setMaNCC(maNCC);
        pnhap.setMaNV(maNV);
        pnhap.setTongTien(tongTien);

        phieuNhapDAO.addPhieuNhap(pnhap);
    }
    public boolean addPhieuNhap(PhieuNhap pn) {
        return phieuNhapDAO.addPhieuNhap(pn);
    }
    public boolean updatePhieuNhap(PhieuNhap pn) {
        return phieuNhapDAO.updatePhieuNhap(pn);
    }
    public boolean deletePhieuNhap(int mapn) {
        return phieuNhapDAO.deletePhieuNhap(mapn);
    }
}
