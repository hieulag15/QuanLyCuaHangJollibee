/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.CTHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.LoaiBUS;
import BUS.NguyenLieuBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import Custom.MyDialog;
import Custom.MyFileChooser;
import Custom.Utils;
import Custom.XuLyFileExcel;
import Model.LoaiSP;
import Model.NguyenLieu;
import Model.SanPham;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Nun-PC
 */
public class PnQuanLySanPhamGUI extends javax.swing.JPanel {

    /**
     * Creates new form PnQuanLyBanHang
     */
    private SanPhamBUS spBUS = new SanPhamBUS();
    private NguyenLieuBUS nlBUS = new NguyenLieuBUS();
    private LoaiBUS loaiBUS = new LoaiBUS();
    DefaultTableModel dtmSanPham, dtmNguyenLieu;
    File fileAnhSP, fileAnhNL;
    
    public PnQuanLySanPhamGUI() {
        initComponents();
        load();
        addControl();
    }
    
    private void addControl(){
        Utils.customTable(tblSanPham);
        Utils.customTable(tblNguyenLieu);
        
        //
        //Chỉnh bảng sản phẩm
        //
        //chỉnh sửa chiều rộng của các cột
        TableColumnModel columnModelSP = tblSanPham.getColumnModel();
        columnModelSP.getColumn(0).setPreferredWidth(80);
        columnModelSP.getColumn(1).setPreferredWidth(320);
        columnModelSP.getColumn(2).setPreferredWidth(150);
        columnModelSP.getColumn(3).setPreferredWidth(120);
        columnModelSP.getColumn(4).setPreferredWidth(100);
        //set chiều cao dòng
        tblSanPham.setRowHeight(50); 
        //chỉnh nội dung nằm giữa
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        columnModelSP.getColumn(0).setCellRenderer(centerRenderer);
        columnModelSP.getColumn(2).setCellRenderer(centerRenderer);
        columnModelSP.getColumn(3).setCellRenderer(centerRenderer);
        columnModelSP.getColumn(4).setCellRenderer(centerRenderer);
        
        //
        //Chỉnh bảng nguyên liệu
        //
        //chỉnh sửa chiều rộng của các cột
        TableColumnModel columnModelNL = tblNguyenLieu.getColumnModel();
        columnModelNL.getColumn(0).setPreferredWidth(80);
        columnModelNL.getColumn(1).setPreferredWidth(320);
        columnModelSP.getColumn(2).setPreferredWidth(120);
        columnModelSP.getColumn(3).setPreferredWidth(100);
        columnModelSP.getColumn(4).setPreferredWidth(120);
        //set chiều cao dòng
        tblNguyenLieu.setRowHeight(50); 
        //chỉnh nội dung nằm giữa
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        columnModelNL.getColumn(0).setCellRenderer(centerRenderer);
        columnModelNL.getColumn(2).setCellRenderer(centerRenderer);
        columnModelNL.getColumn(3).setCellRenderer(centerRenderer);
        columnModelNL.getColumn(4).setCellRenderer(centerRenderer);
    }
    
    private void load(){
        loadDataTableSP();
        loadDataComboboxLoaiBanSP();
        loadDataTableNL();
    }
    
     //định dạng tiền
    DecimalFormat dcf = new DecimalFormat("###,###");
    //định dạng ngày
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String pathSP = "image/SanPham/";
    String pathNL = "image/NguyenLieu/";
    
    private void loadDataComboboxLoaiBanSP() {
        cbLoaiSP.removeAllItems();
        cbLoaiSP.addItem("0 - Chọn loại");
        ArrayList<LoaiSP> dsl = loaiBUS.getDanhSachLoai();

        for (LoaiSP loai : dsl) {
            cbLoaiSP.addItem(loai.getMaLoai() + " - " + loai.getTenLoai());
        }
    }
    
    private void addDataTableSanPham(ArrayList<SanPham> dssp){ 
       for (SanPham sp : dssp) {
            Object[] row = new Object[5];
            row[0] = sp.getMaSP();
            row[1] = sp.getTenSP();
            String tenLoai = loaiBUS.getLoai(sp.getMaLoai()).getTenLoai();
            row[2] = tenLoai;
            row[3] = sp.getDonViTinh();
            row[4] = dcf.format(sp.getDonGia());            
            dtmSanPham.addRow(row);
        }          
    }
    
    private void addDataTableNguyenLieu(ArrayList<NguyenLieu> dsnl){ 
       for (NguyenLieu nl : dsnl) {
            Object[] row = new Object[5];
            row[0] = nl.getMaNL();
            row[1] = nl.getTenNL();
            row[2] = nl.getDonViTinh();           
            row[3] = nl.getSoLuong();
            row[4] = dcf.format(nl.getDonGia());
            dtmNguyenLieu.addRow(row);
        }          
    }
    
    private void loadAnhSP(String anh) {
        fileAnhSP = Utils.getAnh(pathSP, anh, fileAnhSP);
        ImageIcon originalIcon = new ImageIcon(fileAnhSP.getPath());
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblAnhSP.setIcon(new ImageIcon(scaledImage));
    }
    
    private void loadAnhNL(String anh) {
        fileAnhNL = Utils.getAnh(pathNL, anh, fileAnhNL);
        ImageIcon originalIcon = new ImageIcon(fileAnhNL.getPath());
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblAnhNL.setIcon(new ImageIcon(scaledImage));
    }
    
    public ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(pathSP + src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File(pathSP + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSP = new File(pathSP + src);
        } catch (IOException e) {
            fileAnhSP = new File(pathSP + "default.png");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

            return new ImageIcon(dimg);
        }
        return null;
    }
    
    private void loadDataTableSP(){
        dtmSanPham = (DefaultTableModel) tblSanPham.getModel();       
        dtmSanPham.setRowCount(0);
        
        ArrayList<SanPham> dssp = spBUS.getListSanPham();
        addDataTableSanPham(dssp);
    }
    
    private void loadDataTableNL(){
        dtmNguyenLieu = (DefaultTableModel) tblNguyenLieu.getModel();       
        dtmNguyenLieu.setRowCount(0);
        
        ArrayList<NguyenLieu> dsnl = nlBUS.getAllNguyenLieu();
        addDataTableNguyenLieu(dsnl);
    }
    
    private void xuLyClickTblSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row > -1) {           
            String strMa = tblSanPham.getValueAt(row, 0) + "";
            int ma = Integer.parseInt(strMa);
            SanPham sp = spBUS.getSanPham(ma);            
            txtMaSP.setText(strMa);
            txtTenSP.setText(sp.getTenSP());
            cbLoaiSP.setSelectedIndex(sp.getMaLoai());
            txtDonGiaSP.setText(dcf.format(sp.getDonGia()));
            txtDonViTinhSP.setText(sp.getDonViTinh());
            loadAnhSP(sp.getHinhAnh());
        }
    }
    
    private void xuLyClickTblNguyenLieu() {
        int row = tblNguyenLieu.getSelectedRow();
        if (row > -1) {           
            String strMa = tblNguyenLieu.getValueAt(row, 0) + "";
            int ma = Integer.parseInt(strMa);
            NguyenLieu nl = nlBUS.getNguyenLieu(ma);
            txtMaNL.setText(strMa);
            txtTenNL.setText(nl.getTenNL());
            spnSoLuongSL.setValue(nl.getSoLuong());
            txtDonGiaNL.setText(dcf.format(nl.getDonGia()));
            txtDonViTinhNL.setText(nl.getDonViTinh());
            loadAnhNL(nl.getHinhAnh());
        }
    }
    
    private void lamMoiSP(){
        txtMaSP.setText("");
        txtTenSP.setText("");
        cbLoaiSP.setSelectedIndex(0);
        txtDonViTinhSP.setText("");
        txtDonGiaSP.setText("");
        loadAnhSP("default.png");
        
        loadDataTableSP();
    }
    
    private void lamMoiNL(){
        txtMaNL.setText("");
        txtTenNL.setText("");
        spnSoLuongSL.setValue(0);
        txtDonViTinhNL.setText("");
        txtDonGiaNL.setText("");
        loadAnhNL("default.png");
        
        loadDataTableNL();
    }
    
    private void xuLyThemSanPham() {
        if (!checkThemSP()) {
            return;
        }
        
        String anh = fileAnhSP.getName();
        System.out.println(fileAnhSP.getName());
        SanPham sp = new SanPham();
        
        int maSP = Integer.parseInt(txtMaSP.getText()+"");
        String tenSP = txtTenSP.getText() + "";
        int maLoai = cbLoaiSP.getSelectedIndex();
        String donViTinh = txtDonViTinhSP.getText() + "";
        int donGia = Integer.parseInt((txtDonGiaSP.getText() + "").replace(",", ""));
        
        sp.setMaSP(maSP);
        sp.setTenSP(tenSP);
        sp.setMaLoai(maLoai);
        sp.setDonViTinh(donViTinh);
        sp.setHinhAnh(anh);
        sp.setDonGia(donGia);

        try {
            spBUS.addSanPham(sp);
            Utils.luuFileAnh(pathSP, fileAnhSP);
            lamMoiSP();
        } catch (Exception e) {
            new MyDialog("Thêm thất bại.", MyDialog.ERROR_DIALOG);
        }
    
    }
    
    private void xuLyThemNguyenLieu() {
        if (!checkThemNL()) {
            return;
        }
        
        String anh = fileAnhNL.getName();
        System.out.println(fileAnhNL.getName());
        NguyenLieu nl = new NguyenLieu();
        
        int maNL = Integer.parseInt(txtMaNL.getText()+"");
        String tenNL = txtTenNL.getText() + "";
        int soLuong = (Integer)spnSoLuongSL.getValue();
        String donViTinh = txtDonViTinhNL.getText() + "";
        int donGia = Integer.parseInt((txtDonGiaNL.getText() + "").replace(",", ""));
        
        nl.setMaNL(maNL);
        nl.setTenNL(tenNL);
        nl.setSoLuong(soLuong);
        nl.setDonViTinh(donViTinh);
        nl.setHinhAnh(anh);
        nl.setDonGia(donGia);

        try {
            nlBUS.addNguyenLieu(nl);
            Utils.luuFileAnh(pathNL, fileAnhNL);
            lamMoiNL();
        } catch (Exception e) {
            new MyDialog("Thêm thất bại.", MyDialog.ERROR_DIALOG);
        }
    
    }
    
    private void xuLyXoaSanPham(){
        int selectedRow = tblSanPham.getSelectedRow();
        
        if (selectedRow > -1){
            int maSP = Integer.parseInt(txtMaSP.getText()+"");
            spBUS.deleteSanPham(maSP);
            dtmSanPham.removeRow(selectedRow);
            lamMoiSP();
        }
        else{
            new MyDialog("Vui lòng chọn một sản phẩm để xóa.", MyDialog.ERROR_DIALOG);
        }
    }
    
    private void xuLyXoaNguyenLieu(){
        int selectedRow = tblNguyenLieu.getSelectedRow();
        
        if (selectedRow > -1){
            int maNL = Integer.parseInt(txtMaNL.getText()+"");
            nlBUS.deleteNguyenLieu(maNL);
            dtmNguyenLieu.removeRow(selectedRow);
            lamMoiNL();
        }
        else{
            new MyDialog("Vui lòng chọn một nguyên liệu để xóa.", MyDialog.ERROR_DIALOG);
        }
    }
    
    private void timKiemSanPham(){
        dtmSanPham = (DefaultTableModel) tblSanPham.getModel();       
        dtmSanPham.setRowCount(0);
        
        ArrayList<SanPham> dssp = null;
        String tuKhoa = txtTuKhoa.getText().toLowerCase() + "";

        dssp = spBUS.getListSanPhamByKey(tuKhoa);

        addDataTableSanPham(dssp);
    }
    
    private void timKiemNguyenLieu(){
        dtmNguyenLieu = (DefaultTableModel) tblNguyenLieu.getModel();       
        dtmNguyenLieu.setRowCount(0);
        
        ArrayList<NguyenLieu> dsnl = null;
        String tuKhoa = txtTuKhoaNL.getText().toLowerCase() + "";

        dsnl = nlBUS.getListNguyenLieuByKey(tuKhoa);

        addDataTableNguyenLieu(dsnl);
    }
    
    private void xuLySuaSanPham() {
        if (!checkSuaSP()) {
            return;
        }
        
        String anh = fileAnhSP.getName();
        System.out.println(fileAnhSP.getName());
        SanPham sp = new SanPham();
        
        int maSP = Integer.parseInt(txtMaSP.getText()+"");
        String tenSP = txtTenSP.getText() + "";
        int maLoai = cbLoaiSP.getSelectedIndex();
        String donViTinh = txtDonViTinhSP.getText() + "";
        int donGia = Integer.parseInt((txtDonGiaSP.getText() + "").replace(",", ""));
        
        sp.setMaSP(maSP);
        sp.setTenSP(tenSP);
        sp.setMaLoai(maLoai);
        sp.setDonViTinh(donViTinh);
        sp.setHinhAnh(anh);
        sp.setDonGia(donGia);

        try {
            spBUS.updateSanPham(sp);
            Utils.luuFileAnh(pathSP, fileAnhSP);
            lamMoiSP();
        } catch (Exception e) {
            new MyDialog("Sửa thất bại.", MyDialog.ERROR_DIALOG);
        }
    
    }
    
    private void xuLySuaNguyenLieu() {
        if (!checkSuaNL()) {
            return;
        }
        
        String anh = fileAnhNL.getName();
        System.out.println(fileAnhNL.getName());
        NguyenLieu nl = new NguyenLieu();
        
        int maNL = Integer.parseInt(txtMaNL.getText()+"");
        String tenNL = txtTenNL.getText() + "";
        int soLuong = (Integer)spnSoLuongSL.getValue();
        String donViTinh = txtDonViTinhNL.getText() + "";
        int donGia = Integer.parseInt((txtDonGiaNL.getText() + "").replace(",", ""));
        
        nl.setMaNL(maNL);
        nl.setTenNL(tenNL);
        nl.setSoLuong(soLuong);
        nl.setDonViTinh(donViTinh);
        nl.setHinhAnh(anh);
        nl.setDonGia(donGia);

        try {
            nlBUS.updateNguyenLieu(nl);
            Utils.luuFileAnh(pathNL, fileAnhNL);
            lamMoiNL();
        } catch (Exception e) {
            new MyDialog("Sửa thất bại.", MyDialog.ERROR_DIALOG);
        }
    
    }
    
    private boolean checkThemSP(){
        if (txtMaSP.getText().isEmpty()){
            new MyDialog("Vui lòng nhập mã sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (!Utils.checkMa(txtMaSP.getText()+"")){
            new MyDialog("Vui lòng nhập mã sản phẩm là số nguyên dương.", MyDialog.ERROR_DIALOG);
            return false;
        }
        int maSP = Integer.parseInt(txtMaSP.getText()+"");
        ArrayList<SanPham> dssp = spBUS.getListSanPham();
        for (SanPham sp : dssp){
            if (sp.getMaSP() == maSP){
                new MyDialog("Mã sản phẩm đã tồn tại.", MyDialog.ERROR_DIALOG);
                return false;
            }
        }
        
        if (txtTenSP.getText().isEmpty()){
            new MyDialog("Vui lòng nhập tên sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (cbLoaiSP.getSelectedIndex() == 0){
            new MyDialog("Vui lòng nhập chọn loại sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (txtDonViTinhSP.getText().isEmpty()){
            new MyDialog("Vui lòng nhập đơn vị tính cho sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (txtDonGiaSP.getText().isEmpty()){
            new MyDialog("Vui lòng nhập đơn giá cho sản phẩm.", MyDialog.ERROR_DIALOG);           
            return false;
        }       
        
        if(!Utils.checkTien(txtDonGiaSP.getText()+"")){
            new MyDialog("Vui lòng nhập đúng định dạng tiền.", MyDialog.ERROR_DIALOG);
            txtDonGiaSP.setText("");
            return false;
        }

        return true;
    }
    
    private boolean checkThemNL(){
        if (txtMaNL.getText().isEmpty()){
            new MyDialog("Vui lòng nhập mã nguyên liệu.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (!Utils.checkMa(txtMaNL.getText()+"")){
            new MyDialog("Vui lòng nhập mã nguyên liệu là số nguyên dương.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        int maNL = Integer.parseInt(txtMaNL.getText()+"");
        ArrayList<NguyenLieu> dsnl = nlBUS.getAllNguyenLieu();
        for (NguyenLieu nl : dsnl){
            if (nl.getMaNL() == maNL){
                new MyDialog("Mã nguyên liệu đã tồn tại.", MyDialog.ERROR_DIALOG);
                return false;
            }
        }
        
        if (txtTenNL.getText().isEmpty()){
            new MyDialog("Vui lòng nhập tên nguyên liệu.", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (txtDonViTinhNL.getText().isEmpty()){
            new MyDialog("Vui lòng nhập đơn vị tính cho sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (txtDonGiaNL.getText().isEmpty()){
            new MyDialog("Vui lòng nhập đơn giá cho sản phẩm.", MyDialog.ERROR_DIALOG);
            txtDonGiaNL.setText("");
            return false;
        }       
        
        if(!Utils.checkTien(txtDonGiaNL.getText()+"")){
            new MyDialog("Vui lòng nhập đúng định dạng tiền.", MyDialog.ERROR_DIALOG);
            txtDonGiaNL.setText("");
            return false;
        }
        
        JTextField textField = ((JSpinner.DefaultEditor) spnSoLuongSL.getEditor()).getTextField();
        String strSoLuong = textField.getText() + "";
        int soLuong;
        
        try {
            soLuong = Integer.parseInt(strSoLuong);
        } catch (NumberFormatException e) {
            new MyDialog("Vui lòng nhập đúng định dạng số lượng.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (soLuong < 0){
            new MyDialog("Vui lòng nhập số lượng lớn hơn hoặc bằng 0.", MyDialog.ERROR_DIALOG);
            spnSoLuongSL.setValue(0);
            return false;
        }

        return true;
    }
    
    private boolean checkSuaSP(){
        if (txtMaSP.getText().isEmpty()){
            new MyDialog("Vui lòng nhập mã sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (!Utils.checkMa(txtMaSP.getText()+"")){
            new MyDialog("Vui lòng nhập mã sản phẩm là số nguyên dương.", MyDialog.ERROR_DIALOG);
            return false;
        }
        int maSP = Integer.parseInt(txtMaSP.getText()+"");
        ArrayList<SanPham> dssp = spBUS.getListSanPham();
        int flag = 0;
        for (SanPham sp : dssp){
            if (sp.getMaSP() == maSP){
                flag = 1;
            }
        }
        
        SanPham sp = spBUS.getSanPham(maSP);
        if (flag == 0){
            new MyDialog("Mã sản phẩm không tồn tại.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (txtTenSP.getText().isEmpty()){
            new MyDialog("Vui lòng nhập tên sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (cbLoaiSP.getSelectedIndex() == 0){
            new MyDialog("Vui lòng nhập chọn loại sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (txtDonViTinhSP.getText().isEmpty()){
            new MyDialog("Vui lòng nhập đơn vị tính cho sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
               
        if (txtDonGiaSP.getText().isEmpty()){
            new MyDialog("Vui lòng nhập đơn giá cho sản phẩm.", MyDialog.ERROR_DIALOG); 
            txtDonGiaSP.setText(dcf.format(sp.getDonGia()));
            return false;
        }       
        
        if(!Utils.checkTien(txtDonGiaSP.getText()+"")){
            new MyDialog("Vui lòng nhập đúng định dạng tiền.", MyDialog.ERROR_DIALOG);
            txtDonGiaSP.setText(dcf.format(sp.getDonGia()));
            return false;
        }

        return true;
    }
    
    private boolean checkSuaNL(){
        if (txtMaNL.getText().isEmpty()){
            new MyDialog("Vui lòng nhập mã nguyên liệu.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (!Utils.checkMa(txtMaNL.getText()+"")){
            new MyDialog("Vui lòng nhập mã nguyên liệu là số nguyên dương.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        int maNL = Integer.parseInt(txtMaNL.getText()+"");
        ArrayList<NguyenLieu> dsnl = nlBUS.getAllNguyenLieu();
        int flag = 0;
        for (NguyenLieu nl : dsnl){
            if (nl.getMaNL() == maNL){
                flag = 1;
            }
        }
        
        NguyenLieu nl = nlBUS.getNguyenLieu(maNL);
        if (flag == 0){
            new MyDialog("Mã nguyên liệu không tồn tại.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (txtTenNL.getText().isEmpty()){
            new MyDialog("Vui lòng nhập tên nguyên liệu.", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (txtDonViTinhNL.getText().isEmpty()){
            new MyDialog("Vui lòng nhập đơn vị tính cho sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
              
        if (txtDonGiaNL.getText().isEmpty()){
            new MyDialog("Vui lòng nhập đơn giá cho sản phẩm.", MyDialog.ERROR_DIALOG);   
            txtDonGiaNL.setText(dcf.format(nl.getDonGia()));
            return false;
        }       
        
        
        if(!Utils.checkTien(txtDonGiaNL.getText()+"")){
            new MyDialog("Vui lòng nhập đúng định dạng tiền.", MyDialog.ERROR_DIALOG);
            txtDonGiaNL.setText(dcf.format(nl.getDonGia()));
            return false;
        }
        
        JTextField textField = ((JSpinner.DefaultEditor) spnSoLuongSL.getEditor()).getTextField();
        String strSoLuong = textField.getText() + "";
        int soLuong;
        
        try {
            soLuong = Integer.parseInt(strSoLuong);
        } catch (NumberFormatException e) {
            new MyDialog("Vui lòng nhập đúng định dạng số lượng.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (soLuong < 0){
            new MyDialog("Vui lòng nhập số lượng lớn hơn hoặc bằng 0.", MyDialog.ERROR_DIALOG);
            spnSoLuongSL.setValue(0);
            return false;
        }

        return true;
    }
    
    private void xuLyChonAnhSP() {
        JFileChooser fileChooser = new MyFileChooser(pathSP);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            lblAnhSP.setIcon(Utils.getAnh(pathSP, fileAnhSP.getName()));
        }
    }
    
    private void xuLyChonAnhNL() {
        JFileChooser fileChooser = new MyFileChooser(pathNL);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhNL = fileChooser.getSelectedFile();
            lblAnhNL.setIcon(Utils.getAnh(pathNL, fileAnhNL.getName()));
        }
    }
    
    private void xuLyXuatFileExcel() {
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblSanPham);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtMaSP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDonViTinhSP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTuKhoa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDonGiaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbLoaiSP = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        lblAnhSP = new javax.swing.JLabel();
        btnChonAnhSP = new javax.swing.JButton();
        btnThemSP = new javax.swing.JLabel();
        btnSuaSP = new javax.swing.JLabel();
        btnXuatSP = new javax.swing.JLabel();
        btnXoaSP = new javax.swing.JLabel();
        btnNhapSP = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtTenSP = new javax.swing.JTextArea();
        btnResetSanPham = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnThemNL = new javax.swing.JLabel();
        btnSuaNL = new javax.swing.JLabel();
        btnXuatNL = new javax.swing.JLabel();
        btnXoaNL = new javax.swing.JLabel();
        btnNhapNL = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNguyenLieu = new javax.swing.JTable();
        spnSoLuongSL = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        txtMaNL = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtTenNL = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDonViTinhNL = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDonGiaNL = new javax.swing.JTextField();
        lblAnhNL = new javax.swing.JLabel();
        btnChonAnhNL = new javax.swing.JButton();
        txtTuKhoaNL = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnResetNguyenLieu = new javax.swing.JButton();

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Đơn vị tính");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Đơn giá");

        txtDonViTinhSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setText("QUẢN LÝ MÓN ĂN & NƯỚC UỐNG");

        txtTuKhoa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTuKhoa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTuKhoaCaretUpdate(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Từ khóa tìm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Tên sản phẩm");

        txtDonGiaSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Mã sản phẩm");

        cbLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Loại sản phẩm");

        lblAnhSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/default.png"))); // NOI18N
        lblAnhSP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnChonAnhSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnChonAnhSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png"))); // NOI18N
        btnChonAnhSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnChonAnhSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonAnhSPMouseClicked(evt);
            }
        });

        btnThemSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThemSP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnThemSP.setText(" Thêm  ");
        btnThemSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255), null, null));
        btnThemSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemSPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemSPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemSPMouseExited(evt);
            }
        });

        btnSuaSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSuaSP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnSuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pencil.png"))); // NOI18N
        btnSuaSP.setText("   Sửa   ");
        btnSuaSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSuaSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaSPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSuaSPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSuaSPMouseExited(evt);
            }
        });

        btnXuatSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXuatSP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnXuatSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        btnXuatSP.setText(" Xuất   ");
        btnXuatSP.setToolTipText("");
        btnXuatSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXuatSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXuatSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXuatSPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXuatSPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXuatSPMouseExited(evt);
            }
        });

        btnXoaSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoaSP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnXoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        btnXoaSP.setText("   Xóa   ");
        btnXoaSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoaSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoaSPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoaSPMouseExited(evt);
            }
        });

        btnNhapSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhapSP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnNhapSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        btnNhapSP.setText(" Nhập  ");
        btnNhapSP.setToolTipText("");
        btnNhapSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNhapSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNhapSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNhapSPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNhapSPMouseExited(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn vị tính", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        txtTenSP.setColumns(20);
        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenSP.setLineWrap(true);
        txtTenSP.setRows(5);
        jScrollPane4.setViewportView(txtTenSP);

        btnResetSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnResetSanPham.setPreferredSize(new java.awt.Dimension(40, 40));
        btnResetSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResetSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetSanPhamMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtDonViTinhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDonGiaSP))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(cbLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAnhSP)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNhapSP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXuatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnChonAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnResetSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDonViTinhSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDonGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnResetSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAnhSP, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnChonAnhSP, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhapSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Món ăn", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel11.setText("QUẢN LÝ NGUYÊN LIỆU");

        btnThemNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThemNL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnThemNL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnThemNL.setText(" Thêm  ");
        btnThemNL.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255), null, null));
        btnThemNL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemNLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemNLMouseExited(evt);
            }
        });

        btnSuaNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSuaNL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnSuaNL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pencil.png"))); // NOI18N
        btnSuaNL.setText("   Sửa   ");
        btnSuaNL.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSuaNL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaNLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSuaNLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSuaNLMouseExited(evt);
            }
        });

        btnXuatNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXuatNL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnXuatNL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        btnXuatNL.setText(" Xuất   ");
        btnXuatNL.setToolTipText("");
        btnXuatNL.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXuatNL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXuatNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXuatNLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXuatNLMouseExited(evt);
            }
        });

        btnXoaNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoaNL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnXoaNL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        btnXoaNL.setText("   Xóa   ");
        btnXoaNL.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoaNL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaNLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoaNLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoaNLMouseExited(evt);
            }
        });

        btnNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhapNL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnNhapNL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        btnNhapNL.setText(" Nhập  ");
        btnNhapNL.setToolTipText("");
        btnNhapNL.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNhapNL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNhapNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNhapNLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNhapNLMouseExited(evt);
            }
        });

        tblNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nguyên liệu", "Tên nguyên liệu", "Đơn vị tính", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguyenLieuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNguyenLieu);

        spnSoLuongSL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spnSoLuongSL.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Mã nguyên liệu");

        txtMaNL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtTenNL.setColumns(20);
        txtTenNL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenNL.setLineWrap(true);
        txtTenNL.setRows(5);
        jScrollPane6.setViewportView(txtTenNL);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Tên nguyên liệu");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Đơn giá");

        txtDonViTinhNL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Đơn vị tính");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Số lượng");

        txtDonGiaNL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lblAnhNL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/default.png"))); // NOI18N
        lblAnhNL.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnChonAnhNL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnChonAnhNL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png"))); // NOI18N
        btnChonAnhNL.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnChonAnhNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonAnhNLMouseClicked(evt);
            }
        });

        txtTuKhoaNL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTuKhoaNL.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTuKhoaNLCaretUpdate(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Từ khóa tìm");

        btnResetNguyenLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnResetNguyenLieu.setPreferredSize(new java.awt.Dimension(40, 40));
        btnResetNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetNguyenLieuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResetNguyenLieuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetNguyenLieuMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTuKhoaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtDonViTinhNL, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(jLabel14))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addGap(35, 35, 35)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtDonGiaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnSoLuongSL, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel8)))))
                        .addGap(76, 76, 76)
                        .addComponent(lblAnhNL))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThemNL, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(btnNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatNL, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChonAnhNL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(btnResetNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtMaNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDonGiaNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDonViTinhNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(spnSoLuongSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChonAnhNL, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAnhNL, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTuKhoaNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatNL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nguyên liệu", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSPMouseEntered
        btnThemSP.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnThemSPMouseEntered

    private void btnThemSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSPMouseExited
        btnThemSP.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnThemSPMouseExited

    private void btnSuaSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaSPMouseEntered
        btnSuaSP.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnSuaSPMouseEntered

    private void btnSuaSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaSPMouseExited
        btnSuaSP.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnSuaSPMouseExited

    private void btnXuatSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatSPMouseEntered
        btnXuatSP.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXuatSPMouseEntered

    private void btnXuatSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatSPMouseExited
        btnXuatSP.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXuatSPMouseExited

    private void btnXoaSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSPMouseEntered
        btnXoaSP.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXoaSPMouseEntered

    private void btnXoaSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSPMouseExited
        btnXoaSP.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXoaSPMouseExited

    private void btnNhapSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapSPMouseEntered
        btnNhapSP.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnNhapSPMouseEntered

    private void btnNhapSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapSPMouseExited
        btnNhapSP.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnNhapSPMouseExited

    private void btnThemNLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNLMouseEntered
        btnThemNL.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnThemNLMouseEntered

    private void btnThemNLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNLMouseExited
        btnThemNL.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnThemNLMouseExited

    private void btnSuaNLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNLMouseEntered
        btnSuaNL.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnSuaNLMouseEntered

    private void btnSuaNLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNLMouseExited
        btnSuaNL.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnSuaNLMouseExited

    private void btnXuatNLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatNLMouseEntered
        btnXuatNL.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXuatNLMouseEntered

    private void btnXuatNLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatNLMouseExited
        btnXuatNL.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXuatNLMouseExited

    private void btnXoaNLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNLMouseEntered
        btnXoaNL.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXoaNLMouseEntered

    private void btnXoaNLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNLMouseExited
        btnXoaNL.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXoaNLMouseExited

    private void btnNhapNLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapNLMouseEntered
        btnNhapNL.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnNhapNLMouseEntered

    private void btnNhapNLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapNLMouseExited
        btnNhapNL.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnNhapNLMouseExited

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        xuLyClickTblSanPham();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnChonAnhSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonAnhSPMouseClicked
        xuLyChonAnhSP();
    }//GEN-LAST:event_btnChonAnhSPMouseClicked

    private void btnThemSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSPMouseClicked
        xuLyThemSanPham();
    }//GEN-LAST:event_btnThemSPMouseClicked

    private void btnXoaSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSPMouseClicked
        xuLyXoaSanPham();
    }//GEN-LAST:event_btnXoaSPMouseClicked

    private void btnSuaSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaSPMouseClicked
        xuLySuaSanPham();
    }//GEN-LAST:event_btnSuaSPMouseClicked

    private void txtTuKhoaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTuKhoaCaretUpdate
        timKiemSanPham();
    }//GEN-LAST:event_txtTuKhoaCaretUpdate

    private void btnChonAnhNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonAnhNLMouseClicked
        xuLyChonAnhNL();
    }//GEN-LAST:event_btnChonAnhNLMouseClicked

    private void txtTuKhoaNLCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTuKhoaNLCaretUpdate
        timKiemNguyenLieu();
    }//GEN-LAST:event_txtTuKhoaNLCaretUpdate

    private void tblNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguyenLieuMouseClicked
        xuLyClickTblNguyenLieu();
    }//GEN-LAST:event_tblNguyenLieuMouseClicked

    private void btnThemNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNLMouseClicked
        xuLyThemNguyenLieu();
    }//GEN-LAST:event_btnThemNLMouseClicked

    private void btnXoaNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNLMouseClicked
        xuLyXoaNguyenLieu();
    }//GEN-LAST:event_btnXoaNLMouseClicked

    private void btnSuaNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNLMouseClicked
        xuLySuaNguyenLieu();
    }//GEN-LAST:event_btnSuaNLMouseClicked

    private void btnResetNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetNguyenLieuMouseClicked
        lamMoiNL();
    }//GEN-LAST:event_btnResetNguyenLieuMouseClicked

    private void btnResetNguyenLieuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetNguyenLieuMouseEntered
        btnResetNguyenLieu.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnResetNguyenLieuMouseEntered

    private void btnResetNguyenLieuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetNguyenLieuMouseExited
        btnResetNguyenLieu.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnResetNguyenLieuMouseExited

    private void btnResetSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetSanPhamMouseClicked
        lamMoiSP();
    }//GEN-LAST:event_btnResetSanPhamMouseClicked

    private void btnResetSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetSanPhamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetSanPhamMouseEntered

    private void btnResetSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetSanPhamMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetSanPhamMouseExited

    private void btnXuatSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatSPMouseClicked
        xuLyXuatFileExcel();
    }//GEN-LAST:event_btnXuatSPMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnhNL;
    private javax.swing.JButton btnChonAnhSP;
    private javax.swing.JLabel btnNhapNL;
    private javax.swing.JLabel btnNhapSP;
    private javax.swing.JButton btnResetNguyenLieu;
    private javax.swing.JButton btnResetSanPham;
    private javax.swing.JLabel btnSuaNL;
    private javax.swing.JLabel btnSuaSP;
    private javax.swing.JLabel btnThemNL;
    private javax.swing.JLabel btnThemSP;
    private javax.swing.JLabel btnXoaNL;
    private javax.swing.JLabel btnXoaSP;
    private javax.swing.JLabel btnXuatNL;
    private javax.swing.JLabel btnXuatSP;
    private javax.swing.JComboBox<String> cbLoaiSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAnhNL;
    private javax.swing.JLabel lblAnhSP;
    private javax.swing.JSpinner spnSoLuongSL;
    private javax.swing.JTable tblNguyenLieu;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDonGiaNL;
    private javax.swing.JTextField txtDonGiaSP;
    private javax.swing.JTextField txtDonViTinhNL;
    private javax.swing.JTextField txtDonViTinhSP;
    private javax.swing.JTextField txtMaNL;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtTenNL;
    private javax.swing.JTextArea txtTenSP;
    private javax.swing.JTextField txtTuKhoa;
    private javax.swing.JTextField txtTuKhoaNL;
    // End of variables declaration//GEN-END:variables
}
