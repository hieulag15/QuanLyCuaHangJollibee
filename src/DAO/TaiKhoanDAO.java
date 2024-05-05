package DAO;

import BUS.DangNhapBUS;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import Model.TaiKhoan;
import java.sql.SQLException;
import java.sql.Statement;

public class TaiKhoanDAO {
    MyConnect myConnect = new MyConnect();
    public List<TaiKhoan> getAllTaiKhoan() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maNhanVien = rs.getInt("MaNV");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                String quyen = rs.getString("Quyen");

                TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, tenDangNhap, matKhau, quyen);
                list.add(taiKhoan);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public TaiKhoan getTaiKhoan_DatLaiMatKhau(int maNhanVien) {
        TaiKhoan tk = null;
        String sql = "SELECT * FROM TaiKhoan WHERE MaNV = ?";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, maNhanVien);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) { 
                int maNhanvien = rs.getInt("MaNV");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                String quyen = rs.getString("Quyen");

                tk = new TaiKhoan(maNhanvien, tenDangNhap, matKhau, quyen);
            } else {
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tk;
    }

    
    public TaiKhoan getTaiKhoan(int maNhanVien){
        TaiKhoan tk = new TaiKhoan();
        String sql = "SELECT * FROM `TaiKhoan` WHERE MaNV = ?";
//        String sql = "SELECT * FROM TaiKhoan WHERE MaNV = "+ maNhanVien;
        try {
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNhanVien);
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
                tk.setMaNhanVien(rs.getInt("MaNV"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setQuyen(rs.getString("Quyen"));
                tk.setTrangThai(rs.getInt("TrangThai"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return tk;
    }

    public boolean addTaiKhoan(TaiKhoan tk) {
        boolean result = false;
        String sql = "INSERT INTO TaiKhoan (MaNV, TenDangNhap, MatKhau, Quyen, TrangThai) VALUES (?, ?, ?, ?, 1)";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, tk.getMaNhanVien());
            ps.setString(2, tk.getTenDangNhap());
            ps.setString(3, tk.getMatKhau());
            ps.setString(4, tk.getQuyen());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateTaiKhoan(TaiKhoan tk) {
        boolean result = false;
        String sql = "UPDATE TaiKhoan SET TenDangNhap = ?, MatKhau = ?, Quyen = ? WHERE MaNV = ?";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setString(1, tk.getTenDangNhap());
            ps.setString(2, tk.getMatKhau());
            ps.setString(3, tk.getQuyen());
            ps.setInt(4, tk.getMaNhanVien());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteTaiKhoan(int maNhanVien) {
        boolean result = false;
        String sql = "DELETE FROM TaiKhoan WHERE MaNV = ?";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, maNhanVien);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean doiMatKhau(String mkCu, String mkMoi) {
        try {
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNV=? AND MatKhau=?";
            PreparedStatement pre = myConnect.conn.prepareStatement(sql);
            pre.setString(1, mkMoi);
            pre.setInt(2, DangNhapBUS.taiKhoanLogin.getMaNhanVien());
            pre.setString(3, mkCu);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public String getQuyenTheoMa(int maNV) {
        try {
            String sql = "SELECT Quyen FROM TaiKhoan WHERE MaNV=" + maNV;
            Statement st = myConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public String getTenDangNhapTheoMa(String maNV) {
        try {
            String sql = "SELECT TenDangNhap FROM TaiKhoan WHERE MaNV=" + maNV;
            Statement st = myConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean datLaiMatKhau(int maNV, String tenDangNhap) {
        try {
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNV=?";
            PreparedStatement pre = myConnect.getConn().prepareStatement(sql);
            pre.setString(1, tenDangNhap);
            pre.setInt(2, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = '" + tenDangNhap + "'";
            Statement st = myConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public TaiKhoan getTaiKhoan(String manv){
        TaiKhoan tk = new TaiKhoan();
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE MaNV = '" + manv + "'";
            Statement st = myConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            tk.setMaNhanVien(rs.getInt("MaNV"));
            tk.setTenDangNhap(rs.getString("TenDangNhap"));
            tk.setMatKhau(rs.getString("MatKhau"));
            tk.setQuyen(rs.getString("Quyen"));
            tk.setTrangThai(rs.getInt("TrangThai"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
    public List<TaiKhoan> getTaiKhoanByQuyen(String quyen) {
    List<TaiKhoan> list = new ArrayList<>();
    String sql = "SELECT * FROM TaiKhoan WHERE Quyen = ?";
    try {
        PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
        ps.setString(1, quyen);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int maNhanVien = rs.getInt("MaNV");
            String tenDangNhap = rs.getString("TenDangNhap");
            String matKhau = rs.getString("MatKhau");

            TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, tenDangNhap, matKhau, quyen);
            list.add(taiKhoan);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return list;
}

    public boolean khoaTaiKhoan(int maNV){
        boolean result = false;
        String sql = "UPDATE TaiKhoan SET TrangThai = 0 WHERE MaNV = ?";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, maNV);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean moTaiKhoan(int maNV){
        boolean result = false;
        String sql = "UPDATE TaiKhoan SET TrangThai = 1 WHERE MaNV = ?";
        try {
            PreparedStatement ps = myConnect.getConn().prepareStatement(sql);
            ps.setInt(1, maNV);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
