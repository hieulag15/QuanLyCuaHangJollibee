/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.CTHoaDonBUS;
import BUS.DangNhapBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.LoaiBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import Custom.MyDialog;
import Custom.Utils;
import Model.CTHoaDon;
import Model.HoaDon;
import Model.KhachHang;
import Model.LoaiSP;
import Model.NhanVien;
import Model.SanPham;
import Model.TaiKhoan;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Nun-PC
 */
public class PnQuanLyBanHangGUI extends javax.swing.JPanel {

    /**
     * Creates new form PnBangHang
    */
    private DangNhapBUS dnBus = new DangNhapBUS();
    private SanPhamBUS spBUS = new SanPhamBUS();
    private KhachHangBUS khBUS = new KhachHangBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private LoaiBUS loaiBUS = new LoaiBUS();
    private HoaDonBUS hoaDonBUS = new HoaDonBUS();
    private CTHoaDonBUS ctHoaDonBUS = new CTHoaDonBUS();
    DefaultTableModel dtmSanPhamBan, dtmGioHang, dtmHoaDon, dtmCTHoaDon;
    File fileAnhSP;
    
    public PnQuanLyBanHangGUI() {
        initComponents();
        pnBanHang.setVisible(true);
        pnHoaDon.setVisible(false);
        addControl();
        load();
    }
    
    private void addControl(){       
        Utils.customTable(tblGioHang);
        Utils.customTable(tblSanPham);
        Utils.customTable(tblHoaDon);
        Utils.customTable(tblChiTietHoaDon);
               
        //
        //Chỉnh bảng sản phẩm
        //
        //chỉnh sửa chiều rộng của các cột
        TableColumnModel columnModelSP = tblSanPham.getColumnModel();
        columnModelSP.getColumn(0).setPreferredWidth(50);
        columnModelSP.getColumn(1).setPreferredWidth(250);
        columnModelSP.getColumn(2).setPreferredWidth(190);
        columnModelSP.getColumn(3).setPreferredWidth(120);
        //set chiều cao dòng
        tblSanPham.setRowHeight(180); 
        //chỉnh nội dung nằm giữa
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        columnModelSP.getColumn(0).setCellRenderer(centerRenderer);
        columnModelSP.getColumn(2).setCellRenderer(centerRenderer);
        columnModelSP.getColumn(3).setCellRenderer(centerRenderer);
        
        //
        //Chỉnh bảng giỏ hàng
        //
        //chỉnh sửa chiều rộng của các cột
        TableColumnModel columnModelGH = tblGioHang.getColumnModel();
        columnModelGH.getColumn(0).setPreferredWidth(50);
        columnModelGH.getColumn(1).setPreferredWidth(200);
        columnModelGH.getColumn(2).setPreferredWidth(100);
        columnModelGH.getColumn(3).setPreferredWidth(100);
        columnModelGH.getColumn(4).setPreferredWidth(50);
        columnModelGH.getColumn(5).setPreferredWidth(100); 
        //set chiều cao dòng
        tblGioHang.setRowHeight(50); 
        //chỉnh nội dung nằm giữa
        columnModelGH.getColumn(0).setCellRenderer(centerRenderer);
        columnModelGH.getColumn(2).setCellRenderer(centerRenderer);
        columnModelGH.getColumn(3).setCellRenderer(centerRenderer);
        columnModelGH.getColumn(4).setCellRenderer(centerRenderer);
        columnModelGH.getColumn(5).setCellRenderer(centerRenderer);
        
        //
        //Chỉnh bảng hóa đơn
        //
        //chỉnh sửa chiều rộng của các cột
        TableColumnModel columnModelHD = tblHoaDon.getColumnModel();
        columnModelHD.getColumn(0).setPreferredWidth(80);
        columnModelHD.getColumn(1).setPreferredWidth(150);
        columnModelHD.getColumn(2).setPreferredWidth(100); 
        columnModelHD.getColumn(3).setPreferredWidth(120);
        //chỉnh nội dung nằm giữa
        columnModelHD.getColumn(0).setCellRenderer(centerRenderer);
        columnModelHD.getColumn(1).setCellRenderer(centerRenderer);
        columnModelHD.getColumn(2).setCellRenderer(centerRenderer);
        columnModelHD.getColumn(3).setCellRenderer(centerRenderer);
        
        //
        //Chỉnh bảng chi tiết hóa đơn
        //
        //chỉnh sửa chiều rộng của các cột
        TableColumnModel columnModelCTHD = tblChiTietHoaDon.getColumnModel();
        columnModelCTHD.getColumn(0).setPreferredWidth(80);
        columnModelCTHD.getColumn(1).setPreferredWidth(80);
        columnModelCTHD.getColumn(2).setPreferredWidth(50); 
        columnModelCTHD.getColumn(3).setPreferredWidth(100);
        columnModelCTHD.getColumn(4).setPreferredWidth(100);
        //chỉnh nội dung nằm giữa
        columnModelCTHD.getColumn(0).setCellRenderer(centerRenderer);
        columnModelCTHD.getColumn(1).setCellRenderer(centerRenderer);
        columnModelCTHD.getColumn(2).setCellRenderer(centerRenderer);
        columnModelCTHD.getColumn(3).setCellRenderer(centerRenderer);
        columnModelCTHD.getColumn(4).setCellRenderer(centerRenderer);
    }
    
    private void load(){       
        loadDataComboboxLoaiBanSP();
        loadDataTableSanPhamBan();
        loadDataHoaDon();
        loadDataCTHoaDon();
    }
    
    //định dạng tiền
    DecimalFormat dcf = new DecimalFormat("###,###Đ");
    //định dạng ngày
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String pathSP = "image/SanPham/";
    
    private void addDataTableSanPhamBan(ArrayList<SanPham> dssp){
       for (SanPham sp : dssp) {
            Object[] row = new Object[4];
            row[0] = sp.getMaSP();
            row[1] = sp.getTenSP();
            //Hiển thị ảnh sp
            JLabel imgJL = new JLabel();
            ImageIcon imgIcon = Utils.getAnh(pathSP, sp.getHinhAnh());
            imgIcon = new ImageIcon(imgIcon.getImage().getScaledInstance(175, 175, Image.SCALE_SMOOTH));
            imgJL.setIcon(imgIcon);
            row[2] = imgJL;
            row[3] = dcf.format(sp.getDonGia());
            dtmSanPhamBan.addRow(row);
        }          
    }
    
     private void loadDataTableSanPhamBan() {
        tblSanPham.getColumn("Hình ảnh").setCellRenderer(new myTableCellRender());
        dtmSanPhamBan = (DefaultTableModel) tblSanPham.getModel();       
        dtmSanPhamBan.setRowCount(0);
        ArrayList<SanPham> dssp = null;
        
        int maLoai = cb_loaisp.getSelectedIndex();

        if (maLoai == 0) {
            dssp = spBUS.getListSanPhamByActive(1); 
        } else {
            dssp = spBUS.getListSanPhamByIdLoai(maLoai);
        }

        addDataTableSanPhamBan(dssp);
        lamMoiBanHang();
    }
     
    // class render hiển thị ảnh
    class myTableCellRender implements TableCellRenderer{
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return (Component) value;
        }
    }
    
    public void xuLyThoat() {

    }
    
    private void loadDataComboboxLoaiBanSP() {
        cb_loaisp.removeAllItems();
        cb_loaisp.addItem("Tất cả");
        ArrayList<LoaiSP> dsl = loaiBUS.getDanhSachLoai();

        for (LoaiSP loai : dsl) {
            cb_loaisp.addItem(loai.getTenLoai());
        }
    }
    
    private void xuLyClickTblSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row > -1) {
            
            //xóa select của bảng giỏ hàng
            tblGioHang.clearSelection();
            
            String strMa = tblSanPham.getValueAt(row, 0) + "";
            int ma = Integer.parseInt(strMa);
            SanPham sp = spBUS.getSanPham(ma);
            spnSoLuong.setValue(1);
            txtMaSP.setText(strMa);
            txtTenSP.setText(sp.getTenSP());
            txtDonGia.setText(dcf.format(sp.getDonGia()));
            loadAnhSPBH(sp.getHinhAnh());
        }
    }
    
    private void xuLyClickTblGioHang() {
        int row = tblGioHang.getSelectedRow();
        if (row > -1) {
            lamMoiBanHang();
            String strMa = tblGioHang.getValueAt(row, 0) + "";
            int ma = Integer.parseInt(strMa);
            SanPham sp = spBUS.getSanPham(ma);
            loadAnhSPBH(sp.getHinhAnh());
        }
    }
    
    private void xuLyThemVaoGioHang(){
        int row = tblSanPham.getSelectedRow();
        if (row < 0) {
            new MyDialog("Vui lòng chọn một sản phẩm để thêm.", MyDialog.ERROR_DIALOG);
            return;
        }
        
        JTextField textField = ((JSpinner.DefaultEditor) spnSoLuong.getEditor()).getTextField();
        String strSoLuong = textField.getText() + "";
        int soLuong;
        
        try {
            soLuong = Integer.parseInt(strSoLuong);
        } catch (NumberFormatException e) {
            new MyDialog("Vui lòng nhập đúng định dạng số lượng.", MyDialog.ERROR_DIALOG);
            return;
        }
        
        if (soLuong <= 0){
            new MyDialog("Vui lòng nhập số lượng lớn hơn 0.", MyDialog.ERROR_DIALOG);
            spnSoLuong.setValue(1);
            return;
        }
        
        String strMa = txtMaSP.getText() + "";
        int ma = Integer.parseInt(strMa);
        SanPham sp = spBUS.getSanPham(ma);
        String ten = sp.getTenSP();
        String donViTinh = sp.getDonViTinh();
        int donGia = sp.getDonGia();
         
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            int masp = Integer.parseInt(tblGioHang.getValueAt(i, 0) + "");
            //nếu mã sp trong giỏ hàng trùng với mã sp chọn thêm vào thì tăng số lượng hiện có
            if (masp == ma) {
                int soLuongHienTai = Integer.parseInt(tblGioHang.getValueAt(i, 4) + "");
                soLuongHienTai += soLuong;
                tblGioHang.setValueAt(soLuongHienTai, i, 4);
                tblGioHang.setValueAt(dcf.format(soLuongHienTai * donGia), i, 5);
                lamMoiBanHang();
                txtTuKhoa.requestFocus();
                return;
            }
        }
        
        //nếu không trùng thì thêm mới
        dtmGioHang = (DefaultTableModel) tblGioHang.getModel();
        Object[] rowGioHang = new Object[6];
        rowGioHang[0] = strMa;
        rowGioHang[1] = ten;
        rowGioHang[2] = dcf.format(donGia);
        rowGioHang[3] = donViTinh;
        rowGioHang[4] = soLuong;
        rowGioHang[5] = dcf.format(soLuong * donGia);
        lamMoiBanHang();
        txtTuKhoa.requestFocus();
        dtmGioHang.addRow(rowGioHang);
    }
    
    private void xuLyXoaSPGioHang(){
        dtmGioHang = (DefaultTableModel)tblGioHang.getModel();
        int selectedRow = tblGioHang.getSelectedRow();

        if (selectedRow != -1) {
            dtmGioHang.removeRow(selectedRow);
        } else {           
            new MyDialog("Vui lòng chọn một sản phẩm để xóa.", MyDialog.ERROR_DIALOG);
        }
    }
    
    private void lamMoiBanHang(){
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtDonGia.setText("");       
        tblSanPham.getSelectionModel().clearSelection();
        spnSoLuong.setValue(0);
        loadAnhSPBH("default.png");
    }

    private void loadAnhSPBH(String anh) {
        fileAnhSP = Utils.getAnh(pathSP, anh, fileAnhSP);
        ImageIcon originalIcon = new ImageIcon(fileAnhSP.getPath());
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblAnhSP.setIcon(new ImageIcon(scaledImage));
    }
    
    private void loadAnhSPHD(String anh) {
        fileAnhSP = Utils.getAnh(pathSP, anh, fileAnhSP);
        ImageIcon originalIcon = new ImageIcon(fileAnhSP.getPath());
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblAnhSPHD.setIcon(new ImageIcon(scaledImage));
    }   
    
    private void addDataTableHoaDon(ArrayList<HoaDon> dshd){
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        for (HoaDon hd : dshd) {
            Object[] row = new Object[4];
            row[0] = hd.getMaHD();
            row[1] = hd.getSdt();
            row[2] = dateFormat.format(hd.getNgayLap());
            row[3] = dcf.format(hd.getTongTien());
            dtmHoaDon.addRow(row);
        }
    }
    
    private void loadDataHoaDon(){
        ArrayList<HoaDon> dshd = null;
        dshd = hoaDonBUS.getListHoaDon();      
        addDataTableHoaDon(dshd);
    }
    
    private void loadDataCTHoaDon(){
        ArrayList<CTHoaDon> dscthd = null;
        dscthd = ctHoaDonBUS.getListChiTietHoaDon();
        dtmCTHoaDon = (DefaultTableModel) tblChiTietHoaDon.getModel();
        
        for (CTHoaDon cthd : dscthd) {
            Object[] row = new Object[5];
            row[0] = cthd.getMaHD();
            row[1] = cthd.getMaSP();
            row[2] = cthd.getSoLuong();
            row[3] = cthd.getDonGia();
            row[4] = dcf.format(cthd.getThanhTien());
            dtmCTHoaDon.addRow(row);
        }   
    }
    
    private void addDateTableCTHoaDon(ArrayList<CTHoaDon> dsCTHoaDon){      
        for (CTHoaDon cthd : dsCTHoaDon) {
            Object[] row = new Object[5];
            row[0] = cthd.getMaHD();
            row[1] = cthd.getMaSP();
            row[2] = cthd.getSoLuong();
            row[3] = cthd.getDonGia();
            row[4] = dcf.format(cthd.getThanhTien());
            dtmCTHoaDon.addRow(row);
        }   
    }
    
    private void xuLyClickTblHoaDon() {
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD > -1) {
            String strMa = tblHoaDon.getValueAt(rowHD, 0) + "";
            int ma = Integer.parseInt(strMa);
            HoaDon hd = hoaDonBUS.getHoaDon(ma);
            //lấy chi tiết hóa đơn
            ArrayList<CTHoaDon> dsCTHoaDon = null;
            dsCTHoaDon = ctHoaDonBUS.getListChiTietHoaDonTheoMaHD(ma);
            
            //đổ dữ liệu hóa đơn
            txtMaHD.setText(strMa);
            txtSDT.setText(hd.getSdt());
            txtMaNV.setText(String.valueOf(hd.getMaNV()));
            txtNgayLap.setText(dateFormat.format(hd.getNgayLap()));
            txtTongTien.setText(dcf.format(hd.getTongTien()));
            txtGhiChu.setText(hd.getGhiChu());
            
            //hiển thị danh sách chi tiết hóa đơn khi click vào một hóa đơn
            dtmCTHoaDon = (DefaultTableModel) tblChiTietHoaDon.getModel();
            dtmCTHoaDon.setRowCount(0);
            addDateTableCTHoaDon(dsCTHoaDon);
        }
    }
    
    private void xuLyClickTblCTHoaDon() {
        int rowHD = tblChiTietHoaDon.getSelectedRow();
        if (rowHD > -1) {
            String strMaHD = tblChiTietHoaDon.getValueAt(rowHD, 0) + "";
            String strMaSP = tblChiTietHoaDon.getValueAt(rowHD, 1) + "";
            int maHD = Integer.parseInt(strMaHD);
            int maSP = Integer.parseInt(strMaSP);
            
            SanPham sp = spBUS.getSanPham(maSP);
            CTHoaDon cthd = ctHoaDonBUS.getChiTietHoaDon(maHD, maSP);
            
            //đổ dữ liệu chi tiết một sp trong hóa đơn
            txtMaSPHD.setText(strMaSP);
            txtTenSPHD.setText(sp.getTenSP());
            txtSoLuongSPHD.setText(String.valueOf(cthd.getSoLuong()));
            txtDonGiaSPHD.setText(dcf.format(cthd.getDonGia()));
            txtThanhTien.setText(dcf.format(cthd.getThanhTien()));     
            loadAnhSPHD(sp.getHinhAnh());
        }
    }
    
    private void timKiemSanPham(){
        lamMoiBanHang();
        tblSanPham.getColumn("Hình ảnh").setCellRenderer(new myTableCellRender());
        dtmSanPhamBan = (DefaultTableModel) tblSanPham.getModel();       
        dtmSanPhamBan.setRowCount(0);
        ArrayList<SanPham> dssp = null;
        String tuKhoa = txtTuKhoa.getText().toLowerCase() + "";

        dssp = spBUS.getListSanPhamByKey(tuKhoa, 1);

        addDataTableSanPhamBan(dssp);
    }
    
    SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyy-MM-dd");
   
    private void timKiemHoaDonTheoNgay() {
        Date startDate, endDate;
        startDate = dcStartDate.getDate();
        endDate = dcEndDate.getDate();
        
        if (startDate == null || endDate == null){
            new MyDialog("Vui lòng nhập hoặc chọn đúng định dạng ngày.", MyDialog.ERROR_DIALOG);
            dcStartDate.setDate(null);
            dcEndDate.setDate(null);
            return;
        }
        
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        dtmHoaDon.setRowCount(0);
        cbTimKiemTheoTongTien.setSelectedIndex(-1);
        
        String startDateStr = dateFormatDB.format(startDate);
        String endDateStr = dateFormatDB.format(endDate);

        ArrayList<HoaDon> dshd = null;
        dshd = hoaDonBUS.getListHoaDonByDate(startDateStr, endDateStr);
        
        addDataTableHoaDon(dshd);       
        loadDataCTHoaDonTheoDSHD(dshd);
    }
    
    private void timKiemHoaDonTheoTongTien() {             
        int startCost = 0, endCost = 0;
        
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        dtmHoaDon.setRowCount(0);
        dcStartDate.setDate(null);
        dcEndDate.setDate(null);
        
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 0){
            startCost = 0;
            endCost = 300000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 1){
            startCost = 300000;
            endCost = 500000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 2){
            startCost = 500;
            endCost = 1000000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 3){
            startCost = 1000000;
            endCost = 2000000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 4){
            startCost = 2000000;
            endCost = 5000000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 5){
            startCost = 5000000;
            endCost = 10000000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 6){
            startCost = 10000000;
            endCost = 1000000000;
        }

        ArrayList<HoaDon> dshd = null;
        dshd = hoaDonBUS.getListHoaDonByCost(startCost, endCost);
        
        addDataTableHoaDon(dshd);        
        loadDataCTHoaDonTheoDSHD(dshd);
    }
    
    private void loadDataCTHoaDonTheoDSHD (ArrayList<HoaDon> dshd){ 
        dtmCTHoaDon = (DefaultTableModel) tblChiTietHoaDon.getModel();
        dtmCTHoaDon.setRowCount(0);
        for (HoaDon hd : dshd){
            ArrayList<CTHoaDon> dscthd = null;
            dscthd = ctHoaDonBUS.getListChiTietHoaDonTheoMaHD(hd.getMaHD());
            addDateTableCTHoaDon(dscthd);
        }
    }
    
    private void lamMoiHoaDon(){
        cbTimKiemTheoTongTien.setSelectedIndex(-1);
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        dtmHoaDon.setRowCount(0);
        loadDataHoaDon();
        
        dtmCTHoaDon = (DefaultTableModel) tblChiTietHoaDon.getModel();
        dtmCTHoaDon.setRowCount(0);
        loadDataCTHoaDon();
        
        txtMaHD.setText("");
        txtSDT.setText("");
        txtMaNV.setText("");
        txtNgayLap.setText("");
        txtTongTien.setText("");
        txtGhiChu.setText("");
        
        dcStartDate.setDate(null);
        dcEndDate.setDate(null);
        txtMaSPHD.setText("");
        txtTenSPHD.setText("");
        txtSoLuongSPHD.setText("");
        txtDonGiaSPHD.setText("");
        txtThanhTien.setText("");    
        loadAnhSPHD("default.png");
    }
    
    private void xuLyXuatHoaDonBanHang() {
        ArrayList<Object> dsGioHang = new ArrayList<>();
        int row = tblGioHang.getRowCount();
        if (row == 0) {
            new MyDialog("Chưa có sản phẩm nào trong giỏ.", MyDialog.ERROR_DIALOG);
            return;
        }
        int tongTien = 0;
        for (int i = 0; i < row; i++) {
            Object[] ob = new Object[5];
            ob[0] = tblGioHang.getValueAt(i, 0);
            ob[1] = tblGioHang.getValueAt(i, 1);
            ob[2] = tblGioHang.getValueAt(i, 2);
            ob[3] = tblGioHang.getValueAt(i, 4);
            ob[4] = tblGioHang.getValueAt(i, 5);
            tongTien += Integer.parseInt((tblGioHang.getValueAt(i, 5) + "").replace(",", "").replace("Đ", ""));
            dsGioHang.add(ob);
        }
              
        int maNV = DangNhapBUS.taiKhoanLogin.getMaNhanVien();
        NhanVien nv = nvBUS.getNhanVien(maNV);
        XuatHoaDonGUI hoaDonUI = new XuatHoaDonGUI(dsGioHang, tongTien, nv.getHo()+ " " + nv.getTen());
        hoaDonUI.setVisible(true);
        if (hoaDonUI.checkBanHang) {
            dtmGioHang.setRowCount(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel32 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnBanHang = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pnHoaDon = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtMaSPHD = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtSoLuongSPHD = new javax.swing.JTextField();
        txtDonGiaSPHD = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblChiTietHoaDon = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtTenSPHD = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        lblAnhSPHD = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnResetHoaDon = new javax.swing.JButton();
        lblXemTenKhach = new javax.swing.JLabel();
        lblXemTenNV = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cbTimKiemTheoTongTien = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        dcStartDate = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        dcEndDate = new com.toedter.calendar.JDateChooser();
        btnTimNgay = new javax.swing.JButton();
        pnBanHang = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cb_loaisp = new javax.swing.JComboBox<>();
        txtMaSP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblAnhSP = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnThem = new javax.swing.JLabel();
        btnXoa = new javax.swing.JLabel();
        btnXuat = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtTenSP = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        txtTuKhoa = new javax.swing.JTextField();

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel32.setText("CHI TIẾT HÓA ĐƠN");

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnBanHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/selling.png"))); // NOI18N
        btnBanHang.setText("Bán hàng");
        btnBanHang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBanHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBanHangMouseExited(evt);
            }
        });
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bill.png"))); // NOI18N
        btnHoaDon.setText(" Hóa đơn");
        btnHoaDon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseExited(evt);
            }
        });
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setLayout(new javax.swing.OverlayLayout(jPanel1));

        pnHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnHoaDon.setPreferredSize(new java.awt.Dimension(1024, 871));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMaSPHD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMaSPHD.setFocusable(false);

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setText("Tên SP");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setText("Số lượng");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel36.setText("Thành tiền");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("Đơn giá");

        txtSoLuongSPHD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSoLuongSPHD.setFocusable(false);

        txtDonGiaSPHD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDonGiaSPHD.setFocusable(false);

        txtThanhTien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtThanhTien.setFocusable(false);

        tblChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Mã SP", "SL", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietHoaDonMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblChiTietHoaDon);

        txtTenSPHD.setColumns(20);
        txtTenSPHD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenSPHD.setLineWrap(true);
        txtTenSPHD.setRows(5);
        txtTenSPHD.setFocusable(false);
        jScrollPane6.setViewportView(txtTenSPHD);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel21.setText("CHI TIẾT HÓA ĐƠN");

        lblAnhSPHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/default.png"))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setText("Mã SP");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane6))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36)
                            .addComponent(jLabel33))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSPHD)
                            .addComponent(txtThanhTien)
                            .addComponent(txtDonGiaSPHD)
                            .addComponent(txtSoLuongSPHD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAnhSPHD)))
                .addGap(17, 17, 17))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(txtSoLuongSPHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblAnhSPHD)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaSPHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel37)
                                .addComponent(txtDonGiaSPHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(24, 24, 24)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel36)))))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel11.setText("THÔNG TIN HÓA ĐƠN");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("SĐT khách hàng");

        txtMaHD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMaHD.setFocusable(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Mã hóa đơn");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Ghi chú");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Tổng tiền");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Mã nhân viên");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Ngày lập");

        txtNgayLap.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNgayLap.setFocusable(false);

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTongTien.setFocusable(false);

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSDT.setFocusable(false);

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMaNV.setFocusable(false);

        txtGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtGhiChu.setFocusable(false);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "SĐT Khách hàng", "Ngày lập", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        btnResetHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnResetHoaDon.setPreferredSize(new java.awt.Dimension(40, 40));
        btnResetHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResetHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetHoaDonMouseExited(evt);
            }
        });

        lblXemTenKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/look-for.png"))); // NOI18N
        lblXemTenKhach.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblXemTenKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblXemTenKhachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblXemTenKhachMouseExited(evt);
            }
        });

        lblXemTenNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/look-for.png"))); // NOI18N
        lblXemTenNV.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblXemTenNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblXemTenNVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblXemTenNVMouseExited(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Tổng hóa đơn");

        cbTimKiemTheoTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbTimKiemTheoTongTien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dưới 300.000", "300.000 đến 500.000", "500.000 đến 1.000.000", "1.000.000 đến 2.000.000", "2.000.000 đến 5.000.000", "5.000.000 đến 10.000.000", "Trên 10.000.000 " }));
        cbTimKiemTheoTongTien.setSelectedIndex(-1);
        cbTimKiemTheoTongTien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTimKiemTheoTongTienItemStateChanged(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Ngày");

        dcStartDate.setDateFormatString("dd/MM/yyyy");
        dcStartDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Đến");

        dcEndDate.setDateFormatString("dd/MM/yyyy");
        dcEndDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnTimNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search (1).png"))); // NOI18N
        btnTimNgay.setPreferredSize(new java.awt.Dimension(30, 30));
        btnTimNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimNgayMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(cbTimKiemTheoTongTien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dcStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(cbTimKiemTheoTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(dcStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18))
                            .addComponent(dcEndDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel17))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addComponent(btnResetHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtMaHD)
                                    .addComponent(txtSDT)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                    .addComponent(txtNgayLap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                    .addComponent(txtGhiChu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblXemTenKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblXemTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 22, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnResetHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblXemTenKhach)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(lblXemTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnHoaDonLayout = new javax.swing.GroupLayout(pnHoaDon);
        pnHoaDon.setLayout(pnHoaDonLayout);
        pnHoaDonLayout.setHorizontalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHoaDonLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnHoaDonLayout.setVerticalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(pnHoaDon);

        pnBanHang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel10.setText("Thực đơn");

        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDonGia.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Chi tiết sản phẩm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Loại SP");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Tên SP");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Mã SP");

        cb_loaisp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cb_loaisp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_loaispItemStateChanged(evt);
            }
        });

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaSP.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMaSP.setFocusable(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Số lượng");

        lblAnhSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/default.png"))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên sản phẩm", "Hình ảnh", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setText("Giỏ hàng");

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên sản phẩm", "Đơn giá", "Đơn vị tính", "SL", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add-to-cart.png"))); // NOI18N
        btnThem.setText("  Thêm  ");
        btnThem.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemMouseExited(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cart.png"))); // NOI18N
        btnXoa.setText("  Xóa");
        btnXoa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoaMouseExited(evt);
            }
        });

        btnXuat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bill.png"))); // NOI18N
        btnXuat.setText("  Xuất");
        btnXuat.setToolTipText("");
        btnXuat.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXuat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXuatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXuatMouseExited(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnReset.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnReset.setPreferredSize(new java.awt.Dimension(40, 40));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetMouseExited(evt);
            }
        });

        txtTenSP.setColumns(20);
        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenSP.setLineWrap(true);
        txtTenSP.setRows(5);
        txtTenSP.setFocusable(false);
        jScrollPane4.setViewportView(txtTenSP);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Đơn giá");

        spnSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Tìm kiếm");

        txtTuKhoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTuKhoa.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập tên sản phẩm"));
        txtTuKhoa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTuKhoaCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout pnBanHangLayout = new javax.swing.GroupLayout(pnBanHang);
        pnBanHang.setLayout(pnBanHangLayout);
        pnBanHangLayout.setHorizontalGroup(
            pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBanHangLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnBanHangLayout.createSequentialGroup()
                        .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnBanHangLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(lblAnhSP))
                            .addGroup(pnBanHangLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnBanHangLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(19, 19, 19)
                                .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnBanHangLayout.createSequentialGroup()
                                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2)
                                    .addComponent(cb_loaisp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaSP)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txtDonGia)
                                    .addComponent(txtTuKhoa))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnBanHangLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(869, 869, 869))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnBanHangLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnBanHangLayout.setVerticalGroup(
            pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBanHangLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBanHangLayout.createSequentialGroup()
                        .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_loaisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnBanHangLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4))
                        .addGap(18, 18, 18)
                        .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(29, 29, 29)
                        .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(jLabel6)
                            .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBanHangLayout.createSequentialGroup()
                        .addComponent(lblAnhSP)
                        .addGap(18, 18, 18)
                        .addGroup(pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel1.add(pnBanHang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        pnBanHang.setVisible(true);
        pnHoaDon.setVisible(false);
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        pnBanHang.setVisible(false);
        pnHoaDon.setVisible(true);
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseEntered
        btnXoa.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXoaMouseEntered

    private void btnXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseExited
        btnXoa.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXoaMouseExited

    private void btnXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatMouseEntered
        btnXuat.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXuatMouseEntered

    private void btnXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatMouseExited
        btnXuat.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXuatMouseExited

    private void btnResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseEntered
        btnReset.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnResetMouseEntered

    private void btnResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseExited
        btnReset.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnResetMouseExited

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        xuLyClickTblSanPham();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        xuLyThemVaoGioHang();
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseEntered
        btnThem.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnThemMouseEntered

    private void btnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseExited
        btnThem.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnThemMouseExited

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        xuLyXoaSPGioHang();
        lamMoiBanHang();
        txtTuKhoa.requestFocus();
    }//GEN-LAST:event_btnXoaMouseClicked

    private void cb_loaispItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_loaispItemStateChanged
        if (!txtTuKhoa.getText().isEmpty()){
            txtTuKhoa.setText("");            
        }
        loadDataTableSanPhamBan();
        txtTuKhoa.requestFocus();
    }//GEN-LAST:event_cb_loaispItemStateChanged

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        xuLyClickTblGioHang();
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseEntered
        btnBanHang.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnBanHangMouseEntered

    private void btnBanHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseExited
        btnBanHang.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnBanHangMouseExited

    private void btnHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseEntered
        btnHoaDon.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnHoaDonMouseEntered

    private void btnHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseExited
        btnHoaDon.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnHoaDonMouseExited

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        lamMoiBanHang();
        if (!txtTuKhoa.getText().isEmpty()){
            txtTuKhoa.setText("");            
        } else{
            loadDataTableSanPhamBan();
        }
        txtTuKhoa.requestFocus();
        //Xóa sạch giỏ hàng
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_btnResetMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        xuLyClickTblHoaDon();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblChiTietHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietHoaDonMouseClicked
        xuLyClickTblCTHoaDon();
    }//GEN-LAST:event_tblChiTietHoaDonMouseClicked

    private void btnResetHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetHoaDonMouseEntered
        btnResetHoaDon.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnResetHoaDonMouseEntered

    private void btnResetHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetHoaDonMouseExited
        btnResetHoaDon.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnResetHoaDonMouseExited

    private void btnResetHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetHoaDonMouseClicked
        lamMoiHoaDon();
    }//GEN-LAST:event_btnResetHoaDonMouseClicked

    private void txtTuKhoaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTuKhoaCaretUpdate
        timKiemSanPham();
    }//GEN-LAST:event_txtTuKhoaCaretUpdate

    private void btnTimNgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimNgayMouseClicked
        timKiemHoaDonTheoNgay();
    }//GEN-LAST:event_btnTimNgayMouseClicked

    private void btnXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatMouseClicked
        if (tblGioHang.getRowCount() == 0){
            new MyDialog("Chưa có sản phẩm nào trong giỏ hàng.", MyDialog.ERROR_DIALOG);
            return;
        }
        xuLyXuatHoaDonBanHang();
    }//GEN-LAST:event_btnXuatMouseClicked

    private void lblXemTenKhachMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXemTenKhachMouseEntered
        lblXemTenKhach.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        if (txtSDT.getText().isEmpty()){
            txtSDT.setText("Khách vãng lai");
            return;
        }
        else {
            KhachHang kh = khBUS.getKhachHang(txtSDT.getText()+"");
            txtSDT.setText(kh.getHo() + " " + kh.getTen());
        }
    }//GEN-LAST:event_lblXemTenKhachMouseEntered

    private void lblXemTenKhachMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXemTenKhachMouseExited
        lblXemTenKhach.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        if (!txtMaHD.getText().isEmpty()){
            HoaDon hd = hoaDonBUS.getHoaDon(Integer.parseInt(txtMaHD.getText()+""));
            txtSDT.setText(hd.getSdt());      
        } else{
            txtSDT.setText("");
        }
    }//GEN-LAST:event_lblXemTenKhachMouseExited

    private void lblXemTenNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXemTenNVMouseEntered
        lblXemTenNV.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        if (!txtMaNV.getText().isEmpty()){
            NhanVien nv = nvBUS.getNhanVien(Integer.parseInt(txtMaNV.getText()+""));
            txtMaNV.setText(nv.getHo() + " " + nv.getTen());
        }
    }//GEN-LAST:event_lblXemTenNVMouseEntered

    private void lblXemTenNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXemTenNVMouseExited
        lblXemTenNV.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        if (!txtMaHD.getText().isEmpty()){
            HoaDon hd = hoaDonBUS.getHoaDon(Integer.parseInt(txtMaHD.getText()+""));
            NhanVien nv = nvBUS.getNhanVien(hd.getMaNV());
            txtMaNV.setText(String.valueOf(nv.getMaNV()));
        }
    }//GEN-LAST:event_lblXemTenNVMouseExited

    private void cbTimKiemTheoTongTienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTimKiemTheoTongTienItemStateChanged
        timKiemHoaDonTheoTongTien();
    }//GEN-LAST:event_cbTimKiemTheoTongTienItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnResetHoaDon;
    private javax.swing.JLabel btnThem;
    private javax.swing.JButton btnTimNgay;
    private javax.swing.JLabel btnXoa;
    private javax.swing.JLabel btnXuat;
    private javax.swing.JComboBox<String> cbTimKiemTheoTongTien;
    private javax.swing.JComboBox<String> cb_loaisp;
    private com.toedter.calendar.JDateChooser dcEndDate;
    private com.toedter.calendar.JDateChooser dcStartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblAnhSP;
    private javax.swing.JLabel lblAnhSPHD;
    private javax.swing.JLabel lblXemTenKhach;
    private javax.swing.JLabel lblXemTenNV;
    private javax.swing.JPanel pnBanHang;
    private javax.swing.JPanel pnHoaDon;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tblChiTietHoaDon;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonGiaSPHD;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSPHD;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoLuongSPHD;
    private javax.swing.JTextArea txtTenSP;
    private javax.swing.JTextArea txtTenSPHD;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTuKhoa;
    // End of variables declaration//GEN-END:variables
}
