package BUS;
import DAO.PhieuNhapDAO;
import Model.PhieuNhap;
import java.util.ArrayList;
import java.util.List;
public class PhieuNhapBUS {
    private PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();

    public PhieuNhapBUS() {
        
    }
    public ArrayList<PhieuNhap> getAllPhieuNhap() {
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
    public ArrayList<PhieuNhap> getListHoaDonByDate(String startDate, String endDate) {
        return phieuNhapDAO.getListHoaDonByDate(startDate, endDate);
    }
    public ArrayList<PhieuNhap> getListHoaDonByCost(int startCost, int endCost) {
        return phieuNhapDAO.getListHoaDonByCost(startCost, endCost);
    }
}
