package DAO;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import Model.TaiKhoan;
import java.sql.SQLException;

public class TaiKhoanDAO {
    public List<TaiKhoan> getAllTaiKhoan() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maNhanVien = rs.getInt("MaNhanVien");
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

    public boolean addTaiKhoan(TaiKhoan tk) {
        boolean result = false;
        String sql = "INSERT INTO TaiKhoan (MaNhanVien, TenDangNhap, MatKhau, Quyen) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
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
        String sql = "UPDATE TaiKhoan SET TenDangNhap = ?, MatKhau = ?, Quyen = ? WHERE MaNhanVien = ?";
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
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
        String sql = "DELETE FROM TaiKhoan WHERE MaNhanVien = ?";
        try {
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, maNhanVien);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
