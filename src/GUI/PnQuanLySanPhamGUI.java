/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.CTHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.LoaiBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import Custom.MyDialog;
import Custom.MyFileChooser;
import Custom.Utils;
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
    private LoaiBUS loaiBUS = new LoaiBUS();
    DefaultTableModel dtmSanPham;
    File fileAnhSP;
    
    public PnQuanLySanPhamGUI() {
        initComponents();
        load();
        addControl();
    }
    
    private void addControl(){
        Utils.customTable(tblSanPham);
        
        //
        //Chỉnh bảng món ăn
        //
        //chỉnh sửa chiều rộng của các cột
        TableColumnModel columnModelSP = tblSanPham.getColumnModel();
        columnModelSP.getColumn(0).setPreferredWidth(80);
        columnModelSP.getColumn(1).setPreferredWidth(300);
        columnModelSP.getColumn(2).setPreferredWidth(150);
        columnModelSP.getColumn(3).setPreferredWidth(100);
        columnModelSP.getColumn(4).setPreferredWidth(120);
        //set chiều cao dòng
        tblSanPham.setRowHeight(50); 
        //chỉnh nội dung nằm giữa
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        columnModelSP.getColumn(0).setCellRenderer(centerRenderer);
        columnModelSP.getColumn(2).setCellRenderer(centerRenderer);
        columnModelSP.getColumn(3).setCellRenderer(centerRenderer);
        columnModelSP.getColumn(4).setCellRenderer(centerRenderer);
    }
    
    private void load(){
        loadDataTableSP();
        loadDataComboboxLoaiBanSP();
    }
    
     //định dạng tiền
    DecimalFormat dcf = new DecimalFormat("###,###");
    //định dạng ngày
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
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
            row[3] = dcf.format(sp.getDonGia());
            row[4] = sp.getDonViTinh();
            dtmSanPham.addRow(row);
        }          
    }
    
    private void loadAnhSP(String anh) {
        lblAnhSP.setIcon(Utils.getAnhSP(anh,fileAnhSP));
    }
    
    private void loadDataTableSP(){
        dtmSanPham = (DefaultTableModel) tblSanPham.getModel();       
        dtmSanPham.setRowCount(0);
        
        ArrayList<SanPham> dssp = spBUS.getListSanPham();
        addDataTableSanPham(dssp);
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
    
    private void lamMoiSP(){
        txtMaSP.setText("");
        txtTenSP.setText("");
        cbLoaiSP.setSelectedIndex(0);
        txtDonViTinhSP.setText("");
        txtDonGiaSP.setText("");
        loadAnhSP("default.png");
        
        loadDataTableSP();
    }
    
    private void xuLyThemSanPham() {
        if (!checkThem()) {
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
            luuFileAnh();
            lamMoiSP();
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
    
    private void timKiemSanPham(){
        dtmSanPham = (DefaultTableModel) tblSanPham.getModel();       
        dtmSanPham.setRowCount(0);
        
        ArrayList<SanPham> dssp = null;
        String tuKhoa = txtTuKhoa.getText().toLowerCase() + "";

        dssp = spBUS.getListSanPhamByKey(tuKhoa);

        addDataTableSanPham(dssp);
    }
    
    private void xuLySuaSanPham() {
        if (!checkSua()) {
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
            luuFileAnh();
            lamMoiSP();
        } catch (Exception e) {
            new MyDialog("Sửa thất bại.", MyDialog.ERROR_DIALOG);
        }
    
    }
    
    private boolean checkThem(){
        if (txtMaSP.getText().isEmpty()){
            new MyDialog("Vui lòng nhập mã sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (!Utils.checkMa(txtMaSP.getText()+"")){
            new MyDialog("Vui lòng nhập mã sản phẩm là số nguyên dương.", MyDialog.ERROR_DIALOG);
            return false;
        }
        ArrayList<SanPham> dssp = spBUS.getListSanPham();
        for (SanPham sp : dssp){
            if (sp.getMaSP() == Integer.parseInt(txtMaSP.getText()+"")){
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
            return false;
        }

        if(fileAnhSP == null){
            new MyDialog("Vui lòng chọn ảnh sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }

        return true;
    }
    
    private boolean checkSua(){
        if (txtMaSP.getText().isEmpty()){
            new MyDialog("Vui lòng nhập mã sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (!Utils.checkMa(txtMaSP.getText()+"")){
            new MyDialog("Vui lòng nhập mã sản phẩm là số nguyên dương.", MyDialog.ERROR_DIALOG);
            return false;
        }
        ArrayList<SanPham> dssp = spBUS.getListSanPham();
        int flag = 0;
        for (SanPham sp : dssp){
            if (sp.getMaSP() == Integer.parseInt(txtMaSP.getText()+"")){
                flag = 1;
            }
        }
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
            return false;
        }       

        if(!Utils.checkTien(txtDonGiaSP.getText()+"")){
            new MyDialog("Vui lòng nhập đúng định dạng tiền.", MyDialog.ERROR_DIALOG);
            return false;
        }

        if(fileAnhSP == null){
            new MyDialog("Vui lòng chọn ảnh sản phẩm.", MyDialog.ERROR_DIALOG);
            return false;
        }

        return true;
    }

    private void luuFileAnh() {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(fileAnhSP.getPath());
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "png", new File("image/SanPham/" + fileAnhSP.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }
    
    private void xuLyChonAnh() {
        JFileChooser fileChooser = new MyFileChooser("image/SanPham/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            lblAnhSP.setIcon(Utils.getAnhSP(fileAnhSP.getName(), fileAnhSP));
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
        jPanel2 = new javax.swing.JPanel();
        txtMaKhuyenMai1 = new javax.swing.JTextField();
        txtTenChuongTrinh1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDonViTin1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTuKhoaTim1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDonGia2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnThem1 = new javax.swing.JLabel();
        btnLuu1 = new javax.swing.JLabel();
        btnXuat1 = new javax.swing.JLabel();
        btnXoa1 = new javax.swing.JLabel();
        btnNhap1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNguyenLieu = new javax.swing.JTable();
        jSpinner1 = new javax.swing.JSpinner();

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
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn giá", "Đơn vị tính"
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAnhSP)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNhapSP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXuatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnChonAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
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

        txtMaKhuyenMai1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtTenChuongTrinh1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Đơn vị tính");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Đơn giá");

        txtDonViTin1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel11.setText("QUẢN LÝ NGUYÊN LIỆU");

        txtTuKhoaTim1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Từ khóa tìm");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Tên sản phẩm");

        txtDonGia2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Mã sản phẩm");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/default.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Số lượng");

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Chọn ảnh");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnThem1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnThem1.setText(" Thêm  ");
        btnThem1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255), null, null));
        btnThem1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThem1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThem1MouseExited(evt);
            }
        });

        btnLuu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLuu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnLuu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pencil.png"))); // NOI18N
        btnLuu1.setText("   Sửa   ");
        btnLuu1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLuu1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLuu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLuu1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLuu1MouseExited(evt);
            }
        });

        btnXuat1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXuat1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnXuat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        btnXuat1.setText(" Xuất   ");
        btnXuat1.setToolTipText("");
        btnXuat1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXuat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXuat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXuat1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXuat1MouseExited(evt);
            }
        });

        btnXoa1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        btnXoa1.setText("   Xóa   ");
        btnXoa1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoa1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoa1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoa1MouseExited(evt);
            }
        });

        btnNhap1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhap1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnNhap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        btnNhap1.setText(" Nhập  ");
        btnNhap1.setToolTipText("");
        btnNhap1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNhap1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNhap1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNhap1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNhap1MouseExited(evt);
            }
        });

        tblNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn giá", "Số lượng", "Đơn vị tính"
            }
        ));
        jScrollPane2.setViewportView(tblNguyenLieu);

        jSpinner1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addGap(79, 79, 79)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtTuKhoaTim1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtTenChuongTrinh1)
                                    .addComponent(txtMaKhuyenMai1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDonViTin1)
                                    .addComponent(txtDonGia2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLuu1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                .addComponent(btnNhap1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXuat1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 170, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(jLabel11)
                        .addGap(0, 359, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel11)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKhuyenMai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenChuongTrinh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonViTin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTuKhoaTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhap1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuat1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnThem1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseEntered
        btnThemSP.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnThem1MouseEntered

    private void btnThem1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseExited
        btnThemSP.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnThem1MouseExited

    private void btnLuu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuu1MouseEntered
        btnSuaSP.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnLuu1MouseEntered

    private void btnLuu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuu1MouseExited
        btnSuaSP.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnLuu1MouseExited

    private void btnXuat1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuat1MouseEntered
        btnXuatSP.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXuat1MouseEntered

    private void btnXuat1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuat1MouseExited
        btnXuatSP.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXuat1MouseExited

    private void btnXoa1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoa1MouseEntered
        btnXoaSP.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXoa1MouseEntered

    private void btnXoa1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoa1MouseExited
        btnXoaSP.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXoa1MouseExited

    private void btnNhap1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhap1MouseEntered
        btnNhapSP.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnNhap1MouseEntered

    private void btnNhap1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhap1MouseExited
        btnNhapSP.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnNhap1MouseExited

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        xuLyClickTblSanPham();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnChonAnhSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonAnhSPMouseClicked
        xuLyChonAnh();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnhSP;
    private javax.swing.JLabel btnLuu1;
    private javax.swing.JLabel btnNhap1;
    private javax.swing.JLabel btnNhapSP;
    private javax.swing.JLabel btnSuaSP;
    private javax.swing.JLabel btnThem1;
    private javax.swing.JLabel btnThemSP;
    private javax.swing.JLabel btnXoa1;
    private javax.swing.JLabel btnXoaSP;
    private javax.swing.JLabel btnXuat1;
    private javax.swing.JLabel btnXuatSP;
    private javax.swing.JComboBox<String> cbLoaiSP;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAnhSP;
    private javax.swing.JTable tblNguyenLieu;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDonGia2;
    private javax.swing.JTextField txtDonGiaSP;
    private javax.swing.JTextField txtDonViTin1;
    private javax.swing.JTextField txtDonViTinhSP;
    private javax.swing.JTextField txtMaKhuyenMai1;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtTenChuongTrinh1;
    private javax.swing.JTextArea txtTenSP;
    private javax.swing.JTextField txtTuKhoa;
    private javax.swing.JTextField txtTuKhoaTim1;
    // End of variables declaration//GEN-END:variables
}
