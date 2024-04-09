/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class HoaDon {
    
    private int maHD;
    private String sdt;
    private int maNV;
    private Date ngayLap;
    private int tongTien;
    private String ghiChu;

    public HoaDon() {
    }

    public HoaDon(int maHD, String sdt, int maNV, Date ngayLap, int tongTien, String ghiChu) {
        this.maHD = maHD;
        this.sdt = sdt;
        this.maNV = maNV;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getMaHD() {
        return maHD;
    }

    public String getSdt() {
        return sdt;
    }

    public int getMaNV() {
        return maNV;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public int getTongTien() {
        return tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    
}
