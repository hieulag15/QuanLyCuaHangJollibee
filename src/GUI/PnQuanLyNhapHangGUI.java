package GUI;
import BUS.CTPhieuNhapBUS;
import BUS.DangNhapBUS;
import BUS.NguyenLieuBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import Custom.MyDialog;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import Custom.MyTable;
import Custom.Utils;
import Model.CTPhieuNhap;
import Model.NguyenLieu;
import Model.NhaCungCap;
import Model.NhanVien;
import Model.PhieuNhap;
import Model.SanPham;
import java.awt.Image;
import java.io.File;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PnQuanLyNhapHangGUI extends javax.swing.JPanel {

    public PnQuanLyNhapHangGUI() {
        initComponents();
        load();
        customControls();
    }

    private DefaultTableModel dtmKho, dtmGioNhap, dtmPhieuNhap, dtmCTPhieuNhap;
    private NguyenLieuBUS nlBUS = new NguyenLieuBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private PhieuNhapBUS pnBUS = new PhieuNhapBUS();
    private CTPhieuNhapBUS ctpnBUS = new CTPhieuNhapBUS();

    private void customControls() {
        tblKho.setModel(dtmKho);
        Utils.customTable(tblKho);
        Utils.customTable(tblGioNhap);

        tblKho.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblKho.getColumnModel().getColumn(1).setPreferredWidth(350);
        tblKho.getColumnModel().getColumn(2).setPreferredWidth(67);

        tblGioNhap.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblGioNhap.getColumnModel().getColumn(1).setPreferredWidth(225);
        tblGioNhap.getColumnModel().getColumn(2).setPreferredWidth(42);
        tblGioNhap.getColumnModel().getColumn(3).setPreferredWidth(78);
        tblGioNhap.getColumnModel().getColumn(4).setPreferredWidth(77);

        //====================================================================
        //====================================================================
        //====================================================================
        //====================================================================
        //====================================================================
        Utils.customTable(tblPhieuNhap);
        Utils.customTable(tblCTPhieuNhap);

        //=========================================================
        //================CENTER CÁC CELL CỦA TABLE================
        //=========================================================
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        TableColumnModel columnModelKho = tblKho.getColumnModel();
        columnModelKho.getColumn(0).setCellRenderer(centerRenderer);
        columnModelKho.getColumn(2).setCellRenderer(centerRenderer);
        
        TableColumnModel columnModelGio = tblGioNhap.getColumnModel();
        columnModelGio.getColumn(0).setCellRenderer(centerRenderer);
        columnModelGio.getColumn(2).setCellRenderer(centerRenderer);
        columnModelGio.getColumn(3).setCellRenderer(centerRenderer);
        columnModelGio.getColumn(4).setCellRenderer(centerRenderer);
        
        //set chiều cao dòng
        tblKho.setRowHeight(50); 
        tblGioNhap.setRowHeight(50); 

    }
    
    private void load(){
        loadDataTableKho();
        loadDataTablePhieuNhap();
        loadDataTableCTPhieuNhap();
    }
    
    DecimalFormat dcf = new DecimalFormat("###,###Đ");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private void addDataTableKho(ArrayList<NguyenLieu> dsnl){ 
       for (NguyenLieu nl : dsnl) {
            Object[] row = new Object[3];
            row[0] = nl.getMaNL();
            row[1] = nl.getTenNL();          
            row[2] = nl.getSoLuong();
            dtmKho.addRow(row);
        }          
    }
    
    private void addDataTablePhieuNhap(ArrayList<PhieuNhap> dspn){ 
       for (PhieuNhap pn : dspn) {
            Object[] row = new Object[3];
            row[0] = pn.getMaPN();
            row[1] = sdf.format(pn.getNgayLap());          
            row[2] = dcf.format(pn.getTongTien());
            dtmPhieuNhap.addRow(row);
        }          
    }
    
    private void addDataTableCTPhieuNhap(ArrayList<CTPhieuNhap> dsctpn){ 
       for (CTPhieuNhap ctpn : dsctpn) {
            Object[] row = new Object[5];
            row[0] = ctpn.getMaPN();
            row[1] = ctpn.getMaSP();
            row[2] = ctpn.getSoLuong();          
            row[3] = dcf.format(ctpn.getDonGia());
            row[4] = dcf.format(ctpn.getThanhTien());
            dtmCTPhieuNhap.addRow(row);
        }          
    }
    
    private void loadDataTableKho(){
        dtmKho = (DefaultTableModel) tblKho.getModel();       
        dtmKho.setRowCount(0);
        
        ArrayList<NguyenLieu> dsnl = nlBUS.getListNguyenLieuByActive(1);
        addDataTableKho(dsnl);
    }
    
    private void loadDataTablePhieuNhap(){
        dtmPhieuNhap = (DefaultTableModel) tblPhieuNhap.getModel();       
        dtmPhieuNhap.setRowCount(0);
        
        ArrayList<PhieuNhap> dspn = pnBUS.getAllPhieuNhap();
        addDataTablePhieuNhap(dspn);
    }
    
    private void loadDataTableCTPhieuNhap(){
        dtmCTPhieuNhap = (DefaultTableModel) tblCTPhieuNhap.getModel();       
        dtmCTPhieuNhap.setRowCount(0);
        
        ArrayList<CTPhieuNhap> dsctpn = ctpnBUS.getListChiTietPhieuNhap();
        addDataTableCTPhieuNhap(dsctpn);
    }
    
    private void xuLyClickTableKho(){
        int row = tblKho.getSelectedRow();
        if (row > -1) {           
            String strMa = tblKho.getValueAt(row, 0) + "";
            int ma = Integer.parseInt(strMa);
            NguyenLieu nl = nlBUS.getNguyenLieu(ma);            
            txtMaNL.setText(strMa);
            txtTenNL.setText(nl.getTenNL());
            spnSoLuong.setValue(1);
        }
    }
    
    private void timKiemNguyenLieu(){
        dtmKho = (DefaultTableModel) tblKho.getModel();       
        dtmKho.setRowCount(0);
        
        ArrayList<NguyenLieu> dsnl = null;
        String tuKhoa = txtTuKhoa.getText().toLowerCase() + "";

        dsnl = nlBUS.getListNguyenLieuByKey(tuKhoa, 1);
        addDataTableKho(dsnl);
    }
    
    private void xuLyClickTablePhieuNhap(){
        int row = tblPhieuNhap.getSelectedRow();
        if (row > -1) {           
            String strMa = tblPhieuNhap.getValueAt(row, 0) + "";
            int ma = Integer.parseInt(strMa);
            PhieuNhap pn = pnBUS.getPhieuNhap(ma);            
            txtMaPN.setText(strMa);
            txtMaNCC.setText(pn.getMaNCC()+"");
            txtMaNV.setText(pn.getMaNV()+"");
            txtNgayLap.setText(sdf.format(pn.getNgayLap()));
            txtTongTienPN.setText(dcf.format(pn.getTongTien()));
            
            dtmCTPhieuNhap = (DefaultTableModel) tblCTPhieuNhap.getModel();
            dtmCTPhieuNhap.setRowCount(0);
            ArrayList<CTPhieuNhap> dsctpn = null;
            dsctpn = ctpnBUS.getListChiTietPhieuNhapTheoMaPN(ma);
            addDataTableCTPhieuNhap(dsctpn);
        }
    }
    
    private void xuLyClickTableCTPhieuNhap(){
        int row = tblCTPhieuNhap.getSelectedRow();
        if (row > -1) {           
            String strMaPN = tblCTPhieuNhap.getValueAt(row, 0) + "";
            String strMaSP = tblCTPhieuNhap.getValueAt(row, 1) + "";
            int maPN = Integer.parseInt(strMaPN);
            int maSP = Integer.parseInt(strMaSP);
            CTPhieuNhap ctpn = ctpnBUS.getChiTietPhieuNhap(maPN, maSP);
            NguyenLieu sp = nlBUS.getNguyenLieu(maSP);
            txtCTSanPham.setText(sp.getTenNL());
            txtCTSoLuong.setText(ctpn.getSoLuong()+"");
            txtCTDonGia.setText(dcf.format(ctpn.getDonGia()));
            txtCTThanhTien.setText(dcf.format(ctpn.getThanhTien()));
            loadAnh(sp.getHinhAnh());
        }
    }
    
    private void lamMoi(){
        txtMaNL.setText("");
        txtTenNL.setText("");
        spnSoLuong.setValue(0);
        txtDonGia.setText("");
        tblKho.getSelectionModel().clearSelection();
    }
    
    private void lamMoiChiTietPN(){
        txtMaPN.setText("");
        txtMaNCC.setText("");
        txtMaNV.setText("");
        txtNgayLap.setText("");
        txtTongTienPN.setText("");
        cbTimKiemTheoTongTien.setSelectedIndex(-1);
        dcStartDate.setDate(null);
        dcEndDate.setDate(null);
        txtCTSanPham.setText("");
        txtCTSoLuong.setText("");
        txtCTDonGia.setText("");
        txtCTThanhTien.setText("");
        loadAnh("default.png");
        tblKho.getSelectionModel().clearSelection();
        tblCTPhieuNhap.getSelectionModel().clearSelection();
    }
    
    File fileAnhSP;
    private void loadAnh(String anh) {
        fileAnhSP = Utils.getAnh("image/NguyenLieu/", anh, fileAnhSP);
        ImageIcon originalIcon = new ImageIcon(fileAnhSP.getPath());
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblAnhSP.setIcon(new ImageIcon(scaledImage));
    }
    
    private void xuLyThemVaoHangCho(){
        int row = tblKho.getSelectedRow();
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
        
        if (txtDonGia.getText().isEmpty()){
            new MyDialog("Vui lòng nhập đơn giá nhập cho sản phẩm.", MyDialog.ERROR_DIALOG);
            return;
        }
        
        if(!Utils.checkTien(txtDonGia.getText()+"")){
            new MyDialog("Vui lòng nhập đúng định dạng tiền.", MyDialog.ERROR_DIALOG);
            txtDonGia.setText("");
            return;
        }
        
        String strMa = txtMaNL.getText() + "";
        int ma = Integer.parseInt(strMa);
        NguyenLieu sp = nlBUS.getNguyenLieu(ma);
        String ten = sp.getTenNL();
        int donGia = Integer.parseInt(txtDonGia.getText()+"");
         
        for (int i = 0; i < tblGioNhap.getRowCount(); i++) {
            int masp = Integer.parseInt(tblGioNhap.getValueAt(i, 0) + "");
            //nếu mã sp trong giỏ hàng trùng với mã sp chọn thêm vào thì tăng số lượng hiện có
            if (masp == ma) {
                int soLuongHienTai = Integer.parseInt(tblGioNhap.getValueAt(i, 2) + "");
                soLuongHienTai += soLuong;
                tblGioNhap.setValueAt(soLuongHienTai, i, 2);
                tblGioNhap.setValueAt(donGia, i, 3);
                tblGioNhap.setValueAt(dcf.format(soLuongHienTai * donGia), i, 4);
                lamMoi();
                txtTuKhoa.requestFocus();
                return;
            }
        }
        
        //nếu không trùng thì thêm mới
        dtmGioNhap = (DefaultTableModel) tblGioNhap.getModel();
        Object[] rowGioNhap = new Object[5];
        rowGioNhap[0] = strMa;
        rowGioNhap[1] = ten;
        rowGioNhap[2] = soLuong;
        rowGioNhap[3] = dcf.format(donGia);
        rowGioNhap[4] = dcf.format(soLuong * donGia);
        lamMoi();
        txtTuKhoa.requestFocus();
        dtmGioNhap.addRow(rowGioNhap);
        
    }
    
    private void xuLyXoaSPHangCho(){
        dtmGioNhap = (DefaultTableModel)tblGioNhap.getModel();
        int selectedRow = tblGioNhap.getSelectedRow();

        if (selectedRow != -1) {
            dtmGioNhap.removeRow(selectedRow);
        } else {           
            new MyDialog("Vui lòng chọn một sản phẩm để xóa.", MyDialog.ERROR_DIALOG);
        }
    }
    
    private void xuLyChonNCC(){
        DlgChonNhaCungCap dlg = new DlgChonNhaCungCap();
        dlg.setVisible(true);
        
        NhaCungCap nhaCC = DlgChonNhaCungCap.nhaCungCapChon;
        
        if (dlg.getNhaCungCap() != null) {
            txtNhaCungCap.setText(nhaCC.getDienThoai() + " - " + nhaCC.getTenNCC());
        } else {
            txtNhaCungCap.setText("");
        }
    }
    
    private void xuLyXuatPhieuNhap() {
        ArrayList<Object> dsHangCho = new ArrayList<>();
        int row = tblGioNhap.getRowCount();
        if (row == 0) {
            new MyDialog("Chưa có sản phẩm nào trong hàng chờ.", MyDialog.ERROR_DIALOG);
            return;
        }
        int tongTien = 0;
        //xử lý lấy chi tiết phiếu nhập
        for (int i = 0; i < row; i++) {
            Object[] ob = new Object[4];
            ob[0] = tblGioNhap.getValueAt(i, 0);
            ob[1] = tblGioNhap.getValueAt(i, 2);
            ob[2] = tblGioNhap.getValueAt(i, 3);
            ob[3] = tblGioNhap.getValueAt(i, 4);
            tongTien += Integer.parseInt((tblGioNhap.getValueAt(i, 4) + "").replace(",", "").replace("Đ", ""));
            dsHangCho.add(ob);
        }
              
        int maNV = DangNhapBUS.taiKhoanLogin.getMaNhanVien();
        NhanVien nv = nvBUS.getNhanVien(maNV);
        NhaCungCap nhaCC = DlgChonNhaCungCap.nhaCungCapChon;
        XuatPhieuNhapGUI phieuNhapUI = new XuatPhieuNhapGUI(nhaCC.getMaNCC(), nv.getMaNV(), dsHangCho, tongTien);
        phieuNhapUI.setVisible(true);
    }
    
    private void loadDataCTPhieuNhapTheoDSPN (ArrayList<PhieuNhap> dspn){ 
        dtmCTPhieuNhap = (DefaultTableModel) tblCTPhieuNhap.getModel();
        dtmCTPhieuNhap.setRowCount(0);
        for (PhieuNhap pn : dspn){
            ArrayList<CTPhieuNhap> dsctpn = null;
            dsctpn = ctpnBUS.getListChiTietPhieuNhapTheoMaPN(pn.getMaPN());
            addDataTableCTPhieuNhap(dsctpn);
        }
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
        
        dtmPhieuNhap = (DefaultTableModel) tblPhieuNhap.getModel();
        dtmPhieuNhap.setRowCount(0);
        cbTimKiemTheoTongTien.setSelectedIndex(-1);
        
        String startDateStr = dateFormatDB.format(startDate);
        String endDateStr = dateFormatDB.format(endDate);

        ArrayList<PhieuNhap> dspn = null;
        dspn = pnBUS.getListHoaDonByDate(startDateStr, endDateStr);
        
        addDataTablePhieuNhap(dspn);       
        loadDataCTPhieuNhapTheoDSPN(dspn);
    }
    
    private void timKiemHoaDonTheoTongTien(){
        int startCost = 0, endCost = 0;
        
        dtmPhieuNhap = (DefaultTableModel) tblPhieuNhap.getModel();
        dtmPhieuNhap.setRowCount(0);
        dcStartDate.setDate(null);
        dcEndDate.setDate(null);
        
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 0){
            startCost = 0;
            endCost = 500000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 1){
            startCost = 5000000;
            endCost = 2000000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 2){
            startCost = 2000000;
            endCost = 5000000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 3){
            startCost = 5000000;
            endCost = 10000000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 4){
            startCost = 10000000;
            endCost = 15000000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 5){
            startCost = 15000000;
            endCost = 30000000;
        }
        if (cbTimKiemTheoTongTien.getSelectedIndex() == 6){
            startCost = 30000000;
            endCost = 1000000000;
        }

        ArrayList<PhieuNhap> dspn = null;
        dspn = pnBUS.getListHoaDonByCost(startCost, endCost);
        
        addDataTablePhieuNhap(dspn);        
        loadDataCTPhieuNhapTheoDSPN(dspn);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        tabNhapHang = new javax.swing.JTabbedPane();
        pnNhapHang = new javax.swing.JPanel();
        pnTable = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnResetKho = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTuKhoa = new javax.swing.JTextField();
        scrTblKho = new javax.swing.JScrollPane();
        tblKho = new MyTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        scrTblGioNhap = new javax.swing.JScrollPane();
        tblGioNhap = new MyTable();
        pnThongTin = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaNL = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtTenNL = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        btnThem = new javax.swing.JLabel();
        btnXoa = new javax.swing.JLabel();
        btnXuat = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtNhaCungCap = new javax.swing.JTextArea();
        btnChonNhaCungCap = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        pnCTPhieuNhap = new javax.swing.JPanel();
        pnPhieuNhap = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        btnResetTabXemLai = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        txtMaPN = new javax.swing.JTextField();
        txtMaNCC = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtNgayLap = new javax.swing.JTextField();
        txtTongTienPN = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuNhap = new MyTable();
        jPanel24 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cbTimKiemTheoTongTien = new javax.swing.JComboBox<>();
        dcStartDate = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnTimNgay = new javax.swing.JButton();
        dcEndDate = new com.toedter.calendar.JDateChooser();
        pnThongTinCT = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTPhieuNhap = new MyTable();
        txtCTSanPham = new javax.swing.JTextField();
        txtCTSoLuong = new javax.swing.JTextField();
        txtCTDonGia = new javax.swing.JTextField();
        txtCTThanhTien = new javax.swing.JTextField();
        lblAnhSP = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1033, 844));
        setMinimumSize(new java.awt.Dimension(1033, 844));
        setPreferredSize(new java.awt.Dimension(1033, 844));

        tabNhapHang.setBackground(new java.awt.Color(255, 255, 255));
        tabNhapHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        pnNhapHang.setBackground(new java.awt.Color(255, 255, 255));

        pnTable.setBackground(new java.awt.Color(255, 255, 255));
        pnTable.setPreferredSize(new java.awt.Dimension(1033, 844));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Kho hàng");
        jPanel2.add(jLabel1);

        btnResetKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnResetKho.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnResetKho.setPreferredSize(new java.awt.Dimension(40, 40));
        btnResetKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetKhoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResetKhoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetKhoMouseExited(evt);
            }
        });
        btnResetKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetKhoActionPerformed(evt);
            }
        });
        jPanel2.add(btnResetKho);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Tìm kiếm");
        jPanel15.add(jLabel12);

        txtTuKhoa.setColumns(20);
        txtTuKhoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTuKhoa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTuKhoaCaretUpdate(evt);
            }
        });
        jPanel15.add(txtTuKhoa);

        tblKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nguyên liệu", "Tên nguyên liệu", "Tồn kho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoMouseClicked(evt);
            }
        });
        scrTblKho.setViewportView(tblKho);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Hàng chờ nhập");
        jPanel3.add(jLabel2);

        tblGioNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên nguyên liệu", "SL", "Đơn giá", "Thành tiền"
            }
        ));
        tblGioNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioNhapMouseClicked(evt);
            }
        });
        scrTblGioNhap.setViewportView(tblGioNhap);

        javax.swing.GroupLayout pnTableLayout = new javax.swing.GroupLayout(pnTable);
        pnTable.setLayout(pnTableLayout);
        pnTableLayout.setHorizontalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrTblGioNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addComponent(scrTblKho, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnTableLayout.setVerticalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrTblKho, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrTblGioNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnThongTin.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Thông tin nguyên liệu");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Tên NL");

        txtMaNL.setEditable(false);
        txtMaNL.setBackground(new java.awt.Color(255, 255, 255));
        txtMaNL.setColumns(15);
        txtMaNL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaNL.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Mã NL");
        jLabel5.setPreferredSize(new java.awt.Dimension(56, 22));

        txtTenNL.setColumns(20);
        txtTenNL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenNL.setLineWrap(true);
        txtTenNL.setRows(5);
        txtTenNL.setFocusable(false);
        jScrollPane6.setViewportView(txtTenNL);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Thông tin phiếu nhập");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Số lượng nhập");

        spnSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Đơn giá nhập");
        jLabel4.setPreferredSize(new java.awt.Dimension(116, 22));

        txtDonGia.setColumns(15);
        txtDonGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDonGia.setHorizontalAlignment(javax.swing.JTextField.LEFT);

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

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Nhà cung cấp");

        txtNhaCungCap.setColumns(20);
        txtNhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNhaCungCap.setLineWrap(true);
        txtNhaCungCap.setRows(5);
        txtNhaCungCap.setFocusable(false);
        jScrollPane7.setViewportView(txtNhaCungCap);

        btnChonNhaCungCap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnChonNhaCungCap.setText("...");
        btnChonNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNhaCungCapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnThongTinLayout.createSequentialGroup()
                            .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnThongTinLayout.createSequentialGroup()
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnChonNhaCungCap)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinLayout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(228, 228, 228))
                            .addGroup(pnThongTinLayout.createSequentialGroup()
                                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(pnThongTinLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnThongTinLayout.createSequentialGroup()
                                                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel6))
                                                .addGap(18, 18, 18)
                                                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(txtMaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(pnThongTinLayout.createSequentialGroup()
                                                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(37, 37, 37)
                                                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel3))))
                                .addGap(32, 32, 32))))))
        );
        pnThongTinLayout.setVerticalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel3)
                .addGap(38, 38, 38)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnThem)
                .addGap(62, 62, 62)
                .addComponent(jLabel7)
                .addGap(33, 33, 33)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonNhaCungCap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("VNĐ");

        javax.swing.GroupLayout pnNhapHangLayout = new javax.swing.GroupLayout(pnNhapHang);
        pnNhapHang.setLayout(pnNhapHangLayout);
        pnNhapHangLayout.setHorizontalGroup(
            pnNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhapHangLayout.createSequentialGroup()
                .addComponent(pnTable, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(38, 38, 38))
            .addGroup(pnNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhapHangLayout.createSequentialGroup()
                    .addContainerGap(643, Short.MAX_VALUE)
                    .addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        pnNhapHangLayout.setVerticalGroup(
            pnNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTable, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
            .addGroup(pnNhapHangLayout.createSequentialGroup()
                .addGap(373, 373, 373)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabNhapHang.addTab("Nhập hàng", pnNhapHang);

        pnCTPhieuNhap.setLayout(new java.awt.BorderLayout());

        pnPhieuNhap.setBackground(new java.awt.Color(255, 255, 255));
        pnPhieuNhap.setPreferredSize(new java.awt.Dimension(350, 808));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setText("Phiếu nhập");
        jPanel22.add(jLabel17);

        btnResetTabXemLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnResetTabXemLai.setPreferredSize(new java.awt.Dimension(40, 40));
        btnResetTabXemLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetTabXemLaiMouseClicked(evt);
            }
        });
        btnResetTabXemLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTabXemLaiActionPerformed(evt);
            }
        });
        jPanel22.add(btnResetTabXemLai);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin PN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        txtMaPN.setEditable(false);
        txtMaPN.setBackground(new java.awt.Color(255, 255, 255));
        txtMaPN.setColumns(15);
        txtMaPN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaPN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaPN.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã PN"));
        jPanel18.add(txtMaPN);

        txtMaNCC.setEditable(false);
        txtMaNCC.setBackground(new java.awt.Color(255, 255, 255));
        txtMaNCC.setColumns(15);
        txtMaNCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaNCC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaNCC.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã NCC"));
        jPanel18.add(txtMaNCC);

        txtMaNV.setEditable(false);
        txtMaNV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaNV.setColumns(15);
        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaNV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaNV.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã NV"));
        jPanel18.add(txtMaNV);

        txtNgayLap.setEditable(false);
        txtNgayLap.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayLap.setColumns(15);
        txtNgayLap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNgayLap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNgayLap.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày lập"));
        jPanel18.add(txtNgayLap);

        txtTongTienPN.setEditable(false);
        txtTongTienPN.setBackground(new java.awt.Color(255, 255, 255));
        txtTongTienPN.setColumns(15);
        txtTongTienPN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTongTienPN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongTienPN.setBorder(javax.swing.BorderFactory.createTitledBorder("Tổng tiền"));
        jPanel18.add(txtTongTienPN);

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã PN", "Ngày lập", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuNhap);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setText("Tổng tiền");

        cbTimKiemTheoTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTimKiemTheoTongTien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dưới 500.000", "500.000 đến 2.000.000", "200.000.000 đến 5.000.000", "5.000.000 đến 10.000.000", "10.000.000 đến 15.000.000", "15.000.000 đến 30.000.000", "Trên 30.000.000" }));
        cbTimKiemTheoTongTien.setSelectedIndex(-1);
        cbTimKiemTheoTongTien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTimKiemTheoTongTienItemStateChanged(evt);
            }
        });

        dcStartDate.setDateFormatString("dd/MM/yyyy");
        dcStartDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("Ngày");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Đến");

        btnTimNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search (1).png"))); // NOI18N
        btnTimNgay.setPreferredSize(new java.awt.Dimension(30, 30));
        btnTimNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimNgayMouseClicked(evt);
            }
        });

        dcEndDate.setDateFormatString("dd/MM/yyyy");
        dcEndDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel24Layout.createSequentialGroup()
                            .addComponent(dcEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTimNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cbTimKiemTheoTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbTimKiemTheoTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(dcStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(btnTimNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addComponent(jLabel20)
                    .addComponent(dcEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnPhieuNhapLayout = new javax.swing.GroupLayout(pnPhieuNhap);
        pnPhieuNhap.setLayout(pnPhieuNhapLayout);
        pnPhieuNhapLayout.setHorizontalGroup(
            pnPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
            .addGroup(pnPhieuNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnPhieuNhapLayout.setVerticalGroup(
            pnPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPhieuNhapLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnCTPhieuNhap.add(pnPhieuNhap, java.awt.BorderLayout.WEST);

        pnThongTinCT.setBackground(new java.awt.Color(255, 255, 255));
        pnThongTinCT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(204, 204, 204)));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setText("Chi tiết phiếu nhập");
        jPanel23.add(jLabel18);

        tblCTPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã PN", "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTPhieuNhap);

        txtCTSanPham.setEditable(false);
        txtCTSanPham.setBackground(new java.awt.Color(255, 255, 255));
        txtCTSanPham.setColumns(15);
        txtCTSanPham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCTSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        txtCTSoLuong.setEditable(false);
        txtCTSoLuong.setBackground(new java.awt.Color(255, 255, 255));
        txtCTSoLuong.setColumns(15);
        txtCTSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCTSoLuong.setBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng"));

        txtCTDonGia.setEditable(false);
        txtCTDonGia.setBackground(new java.awt.Color(255, 255, 255));
        txtCTDonGia.setColumns(15);
        txtCTDonGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCTDonGia.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn giá"));

        txtCTThanhTien.setEditable(false);
        txtCTThanhTien.setBackground(new java.awt.Color(255, 255, 255));
        txtCTThanhTien.setColumns(15);
        txtCTThanhTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCTThanhTien.setBorder(javax.swing.BorderFactory.createTitledBorder("Thành tiền"));

        lblAnhSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/default.png"))); // NOI18N

        javax.swing.GroupLayout pnThongTinCTLayout = new javax.swing.GroupLayout(pnThongTinCT);
        pnThongTinCT.setLayout(pnThongTinCTLayout);
        pnThongTinCTLayout.setHorizontalGroup(
            pnThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnThongTinCTLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(pnThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCTSanPham)
                    .addGroup(pnThongTinCTLayout.createSequentialGroup()
                        .addGroup(pnThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCTThanhTien)
                            .addComponent(txtCTSoLuong)
                            .addComponent(txtCTDonGia))
                        .addGap(53, 53, 53)
                        .addComponent(lblAnhSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(98, Short.MAX_VALUE))
            .addGroup(pnThongTinCTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pnThongTinCTLayout.setVerticalGroup(
            pnThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinCTLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(txtCTSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinCTLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(txtCTSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCTDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCTThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(pnThongTinCTLayout.createSequentialGroup()
                        .addComponent(lblAnhSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnCTPhieuNhap.add(pnThongTinCT, java.awt.BorderLayout.CENTER);

        tabNhapHang.addTab("Xem lại phiếu nhập", pnCTPhieuNhap);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabNhapHang)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabNhapHang)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void tblKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoMouseClicked
        xuLyClickTableKho();
    }//GEN-LAST:event_tblKhoMouseClicked

    private void tblGioNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioNhapMouseClicked

    }//GEN-LAST:event_tblGioNhapMouseClicked

    private void btnChonNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNhaCungCapActionPerformed
        xuLyChonNCC();
    }//GEN-LAST:event_btnChonNhaCungCapActionPerformed

    private void tblPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseClicked
        xuLyClickTablePhieuNhap();
    }//GEN-LAST:event_tblPhieuNhapMouseClicked

    private void btnResetTabXemLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTabXemLaiActionPerformed

    }//GEN-LAST:event_btnResetTabXemLaiActionPerformed

    private void tblCTPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTPhieuNhapMouseClicked
        xuLyClickTableCTPhieuNhap();
    }//GEN-LAST:event_tblCTPhieuNhapMouseClicked

    private void btnResetKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetKhoActionPerformed
        
    }//GEN-LAST:event_btnResetKhoActionPerformed

    private void btnResetKhoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetKhoMouseEntered
        btnResetKho.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnResetKhoMouseEntered

    private void btnResetKhoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetKhoMouseExited
        btnResetKho.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnResetKhoMouseExited

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
       xuLyThemVaoHangCho();
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseEntered
        btnThem.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnThemMouseEntered

    private void btnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseExited
        btnThem.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnThemMouseExited

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        xuLyXoaSPHangCho();
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseEntered
        btnXoa.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXoaMouseEntered

    private void btnXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseExited
        btnXoa.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXoaMouseExited

    private void btnXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatMouseClicked
        if (DlgChonNhaCungCap.nhaCungCapChon == null){
            new MyDialog("Vui lòng chọn nhà cung cấp.", MyDialog.ERROR_DIALOG);
            return;
        } 
        
        if (tblGioNhap.getRowCount() == 0){
            new MyDialog("Chưa có sản phẩm nào trong hàng chờ.", MyDialog.ERROR_DIALOG);
            return;
        }
        xuLyXuatPhieuNhap();
    }//GEN-LAST:event_btnXuatMouseClicked

    private void btnXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatMouseEntered
        btnXuat.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXuatMouseEntered

    private void btnXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatMouseExited
        btnXuat.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXuatMouseExited

    private void btnResetKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetKhoMouseClicked
        loadDataTableKho();
        dtmGioNhap = (DefaultTableModel) tblGioNhap.getModel();
        dtmGioNhap.setRowCount(0);
        txtNhaCungCap.setText("");
        lamMoi();
    }//GEN-LAST:event_btnResetKhoMouseClicked

    private void txtTuKhoaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTuKhoaCaretUpdate
        timKiemNguyenLieu();
    }//GEN-LAST:event_txtTuKhoaCaretUpdate

    private void cbTimKiemTheoTongTienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTimKiemTheoTongTienItemStateChanged
        timKiemHoaDonTheoTongTien();
    }//GEN-LAST:event_cbTimKiemTheoTongTienItemStateChanged

    private void btnTimNgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimNgayMouseClicked
        timKiemHoaDonTheoNgay();
    }//GEN-LAST:event_btnTimNgayMouseClicked

    private void btnResetTabXemLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetTabXemLaiMouseClicked
        lamMoiChiTietPN();
        loadDataTablePhieuNhap();
        loadDataTableCTPhieuNhap();
    }//GEN-LAST:event_btnResetTabXemLaiMouseClicked

// <editor-fold defaultstate="collapsed" desc="Variable">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonNhaCungCap;
    private javax.swing.JButton btnResetKho;
    private javax.swing.JButton btnResetTabXemLai;
    private javax.swing.JLabel btnThem;
    private javax.swing.JButton btnTimNgay;
    private javax.swing.JLabel btnXoa;
    private javax.swing.JLabel btnXuat;
    private javax.swing.JComboBox<String> cbTimKiemTheoTongTien;
    private com.toedter.calendar.JDateChooser dcEndDate;
    private com.toedter.calendar.JDateChooser dcStartDate;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblAnhSP;
    private javax.swing.JPanel pnCTPhieuNhap;
    private javax.swing.JPanel pnNhapHang;
    private javax.swing.JPanel pnPhieuNhap;
    private javax.swing.JPanel pnTable;
    private javax.swing.JPanel pnThongTin;
    private javax.swing.JPanel pnThongTinCT;
    private javax.swing.JScrollPane scrTblGioNhap;
    private javax.swing.JScrollPane scrTblKho;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTabbedPane tabNhapHang;
    private javax.swing.JTable tblCTPhieuNhap;
    private javax.swing.JTable tblGioNhap;
    private javax.swing.JTable tblKho;
    private javax.swing.JTable tblPhieuNhap;
    private javax.swing.JTextField txtCTDonGia;
    private javax.swing.JTextField txtCTSanPham;
    private javax.swing.JTextField txtCTSoLuong;
    private javax.swing.JTextField txtCTThanhTien;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtMaNL;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaPN;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextArea txtNhaCungCap;
    private javax.swing.JTextArea txtTenNL;
    private javax.swing.JTextField txtTongTienPN;
    private javax.swing.JTextField txtTuKhoa;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>

}
