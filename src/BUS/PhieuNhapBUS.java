package BUS;
import DAO.PhieuNhapDAO;
import Model.PhieuNhap;
import java.util.ArrayList;
import java.util.List;
public class PhieuNhapBUS {
    private PhieuNhapDAO phieuNhapDAO;

    public PhieuNhapBUS(PhieuNhapDAO phieuNhapDAO) {
        this.phieuNhapDAO = phieuNhapDAO;
    }
    public List<PhieuNhap> getListHoaDon() {
        return phieuNhapDAO.getAllPhieuNhap();
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
