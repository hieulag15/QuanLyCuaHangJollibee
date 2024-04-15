/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nun-PC
 */
public class NguyenLieu {
    private int maNL;
    private String tenNL;
    private int soLuong;
    private String donViTinh;
    private String hinhAnh;
    private int donGia;
    private int tinhTrang;

    public void setMaNL(int maNL) {
        this.maNL = maNL;
    }

    public void setTenNL(String tenNL) {
        this.tenNL = tenNL;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getMaNL() {
        return maNL;
    }

    public String getTenNL() {
        return tenNL;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public int getDonGia() {
        return donGia;
    }

    public NguyenLieu() {
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public NguyenLieu(int maNL, String tenNL, int soLuong, String donViTinh, String hinhAnh, int donGia) {
        this.maNL = maNL;
        this.tenNL = tenNL;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
        this.hinhAnh = hinhAnh;
        this.donGia = donGia;
    }
    
    
}
