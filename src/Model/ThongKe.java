package Model;

import java.util.ArrayList;

public class ThongKe {
    public int soLuongSP;
    public int soLuongKH;
    public int soLuongNV;
    public int[] tongThuQuy;
    public ArrayList<SanPham> topSanPhamBanChay;

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public int getSoLuongKH() {
        return soLuongKH;
    }

    public void setSoLuongKH(int soLuongKH) {
        this.soLuongKH = soLuongKH;
    }

    public int getSoLuongNV() {
        return soLuongNV;
    }

    public void setSoLuongNV(int soLuongNV) {
        this.soLuongNV = soLuongNV;
    }

    public int[] getTongThuQuy() {
        return tongThuQuy;
    }

    public void setTongThuQuy(int[] tongThuQuy) {
        this.tongThuQuy = tongThuQuy;
    }

    public ArrayList<SanPham> getTopSanPhamBanChay() {
        return topSanPhamBanChay;
    }

    public void setTopSanPhamBanChay(ArrayList<SanPham> topSanPhamBanChay) {
        this.topSanPhamBanChay = topSanPhamBanChay;
    }

    public ThongKe() {
    }

    public ThongKe(int soLuongSP, int soLuongKH, int soLuongNV, int[] tongThuQuy, ArrayList<SanPham> topSanPhamBanChay) {
        this.soLuongSP = soLuongSP;
        this.soLuongKH = soLuongKH;
        this.soLuongNV = soLuongNV;
        this.tongThuQuy = tongThuQuy;
        this.topSanPhamBanChay = topSanPhamBanChay;
    }

    
}
