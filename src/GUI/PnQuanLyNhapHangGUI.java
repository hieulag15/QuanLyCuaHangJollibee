package GUI;
import BUS.NguyenLieuBUS;
import Custom.MyDialog;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import Custom.MyTable;
import Custom.Utils;
import Model.NguyenLieu;
import Model.NhaCungCap;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PnQuanLyNhapHangGUI extends javax.swing.JPanel {

    public PnQuanLyNhapHangGUI() {
        initComponents();
        load();
        customControls();
    }

    private DefaultTableModel dtmKho, dtmGioNhap, dtmPhieuNhap, dtmCTPhieuNhap;
    private NguyenLieuBUS nlBUS = new NguyenLieuBUS();

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
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

    }
    
    private void load(){
        loadDataTableKho();
    }
    
    DecimalFormat dcf = new DecimalFormat("###,###");
    private void addDataTableKho(ArrayList<NguyenLieu> dsnl){ 
       for (NguyenLieu nl : dsnl) {
            Object[] row = new Object[3];
            row[0] = nl.getMaNL();
            row[1] = nl.getTenNL();          
            row[2] = nl.getSoLuong();
            dtmKho.addRow(row);
        }          
    }
    
    private void loadDataTableKho(){
        dtmKho = (DefaultTableModel) tblKho.getModel();       
        dtmKho.setRowCount(0);
        
        ArrayList<NguyenLieu> dsnl = nlBUS.getAllNguyenLieu();
        addDataTableKho(dsnl);
    }
    
    private void xuLyClickTableKho(){
        int row = tblKho.getSelectedRow();
        if (row > -1) {           
            String strMa = tblKho.getValueAt(row, 0) + "";
            int ma = Integer.parseInt(strMa);
            NguyenLieu nl = nlBUS.getNguyenLieu(ma);            
            txtMaNL.setText(strMa);
            txtTenNL.setText(nl.getTenNL());
        }
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
                tblGioNhap.setValueAt(dcf.format(soLuongHienTai * donGia), i, 4);
//                lamMoiBanHang();
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
//        lamMoiBanHang();
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
        btnTimKiem = new javax.swing.JButton();
        scrTblKho = new javax.swing.JScrollPane();
        tblKho = new MyTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        scrTblGioNhap = new javax.swing.JScrollPane();
        tblGioNhap = new MyTable();
        pnThongTin = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
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
        jPanel26 = new javax.swing.JPanel();
        txtGiaThap = new javax.swing.JTextField();
        txtGiaCao = new javax.swing.JTextField();
        txtTuNgay = new javax.swing.JTextField();
        txtDenNgay = new javax.swing.JTextField();
        pnThongTinCT = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTPhieuNhap = new MyTable();
        jPanel17 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        txtCTSanPham = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        txtCTSoLuong = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        txtCTDonGia = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        txtCTThanhTien = new javax.swing.JTextField();

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
        pnTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnTable.setPreferredSize(new java.awt.Dimension(1033, 844));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Kho hàng");
        jPanel2.add(jLabel1);

        btnResetKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnResetKho.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnResetKho.setPreferredSize(new java.awt.Dimension(40, 40));
        btnResetKho.addMouseListener(new java.awt.event.MouseAdapter() {
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
        jPanel15.add(txtTuKhoa);

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTimKiem.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseExited(evt);
            }
        });
        jPanel15.add(btnTimKiem);

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
                "Mã NL", "Tên NL", "Số lượng", "Đơn giá", "Thành tiền"
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
                    .addComponent(scrTblGioNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addComponent(scrTblKho, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnTableLayout.setVerticalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTableLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrTblKho, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrTblGioNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnThongTin.setBackground(new java.awt.Color(255, 255, 255));
        pnThongTin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

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
        jScrollPane7.setViewportView(txtNhaCungCap);

        javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
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
                        .addGap(48, 48, 48)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(pnThongTinLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnThongTinLayout.setVerticalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(52, 52, 52)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnChonNhaCungCap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnChonNhaCungCap.setText("...");
        btnChonNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNhaCungCapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnNhapHangLayout = new javax.swing.GroupLayout(pnNhapHang);
        pnNhapHang.setLayout(pnNhapHangLayout);
        pnNhapHangLayout.setHorizontalGroup(
            pnNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhapHangLayout.createSequentialGroup()
                .addComponent(pnTable, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(325, 325, 325)
                .addComponent(btnChonNhaCungCap)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(pnNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhapHangLayout.createSequentialGroup()
                    .addGap(0, 641, Short.MAX_VALUE)
                    .addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnNhapHangLayout.setVerticalGroup(
            pnNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTable, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhapHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChonNhaCungCap)
                .addGap(81, 81, 81))
            .addGroup(pnNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnThongTin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabNhapHang.addTab("Nhập hàng", pnNhapHang);

        pnCTPhieuNhap.setLayout(new java.awt.BorderLayout());

        pnPhieuNhap.setPreferredSize(new java.awt.Dimension(350, 808));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setText("Phiếu nhập");
        jPanel22.add(jLabel17);

        btnResetTabXemLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnResetTabXemLai.setPreferredSize(new java.awt.Dimension(40, 40));
        btnResetTabXemLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTabXemLaiActionPerformed(evt);
            }
        });
        jPanel22.add(btnResetTabXemLai);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin PN"));

        txtMaPN.setEditable(false);
        txtMaPN.setColumns(15);
        txtMaPN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaPN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaPN.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã PN"));
        jPanel18.add(txtMaPN);

        txtMaNCC.setEditable(false);
        txtMaNCC.setColumns(15);
        txtMaNCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaNCC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaNCC.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã NCC"));
        jPanel18.add(txtMaNCC);

        txtMaNV.setEditable(false);
        txtMaNV.setColumns(15);
        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaNV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaNV.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã NV"));
        jPanel18.add(txtMaNV);

        txtNgayLap.setEditable(false);
        txtNgayLap.setColumns(15);
        txtNgayLap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNgayLap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNgayLap.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày lập"));
        jPanel18.add(txtNgayLap);

        txtTongTienPN.setEditable(false);
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
        ));
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuNhap);

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jPanel26.setPreferredSize(new java.awt.Dimension(350, 91));

        txtGiaThap.setColumns(10);
        txtGiaThap.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGiaThap.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá từ"));
        txtGiaThap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaThapActionPerformed(evt);
            }
        });
        jPanel26.add(txtGiaThap);

        txtGiaCao.setColumns(10);
        txtGiaCao.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGiaCao.setBorder(javax.swing.BorderFactory.createTitledBorder("tới"));
        txtGiaCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaCaoActionPerformed(evt);
            }
        });
        jPanel26.add(txtGiaCao);

        txtTuNgay.setColumns(10);
        txtTuNgay.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTuNgay.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ ngày"));
        txtTuNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTuNgayActionPerformed(evt);
            }
        });
        jPanel26.add(txtTuNgay);

        txtDenNgay.setColumns(10);
        txtDenNgay.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtDenNgay.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến ngày"));
        txtDenNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDenNgayActionPerformed(evt);
            }
        });
        jPanel26.add(txtDenNgay);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout pnPhieuNhapLayout = new javax.swing.GroupLayout(pnPhieuNhap);
        pnPhieuNhap.setLayout(pnPhieuNhapLayout);
        pnPhieuNhapLayout.setHorizontalGroup(
            pnPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnPhieuNhapLayout.setVerticalGroup(
            pnPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPhieuNhapLayout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnCTPhieuNhap.add(pnPhieuNhap, java.awt.BorderLayout.WEST);

        pnThongTinCT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(204, 204, 204)));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setText("Chi tiết phiếu nhập");
        jPanel23.add(jLabel18);

        tblCTPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tblCTPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTPhieuNhap);

        jPanel17.setLayout(new javax.swing.BoxLayout(jPanel17, javax.swing.BoxLayout.Y_AXIS));

        txtCTSanPham.setEditable(false);
        txtCTSanPham.setColumns(15);
        txtCTSanPham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCTSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));
        jPanel20.add(txtCTSanPham);

        jPanel17.add(jPanel20);

        txtCTSoLuong.setEditable(false);
        txtCTSoLuong.setColumns(15);
        txtCTSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCTSoLuong.setBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng"));
        jPanel19.add(txtCTSoLuong);

        jPanel17.add(jPanel19);

        txtCTDonGia.setEditable(false);
        txtCTDonGia.setColumns(15);
        txtCTDonGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCTDonGia.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn giá"));
        jPanel25.add(txtCTDonGia);

        jPanel17.add(jPanel25);

        txtCTThanhTien.setEditable(false);
        txtCTThanhTien.setColumns(15);
        txtCTThanhTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCTThanhTien.setBorder(javax.swing.BorderFactory.createTitledBorder("Thành tiền"));
        jPanel21.add(txtCTThanhTien);

        jPanel17.add(jPanel21);

        javax.swing.GroupLayout pnThongTinCTLayout = new javax.swing.GroupLayout(pnThongTinCT);
        pnThongTinCT.setLayout(pnThongTinCTLayout);
        pnThongTinCTLayout.setHorizontalGroup(
            pnThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnThongTinCTLayout.setVerticalGroup(
            pnThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinCTLayout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
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
            .addComponent(tabNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        
    }//GEN-LAST:event_tblPhieuNhapMouseClicked

    private void btnResetTabXemLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTabXemLaiActionPerformed

    }//GEN-LAST:event_btnResetTabXemLaiActionPerformed

    private void tblCTPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTPhieuNhapMouseClicked
        
    }//GEN-LAST:event_tblCTPhieuNhapMouseClicked

    private void txtGiaThapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaThapActionPerformed

    }//GEN-LAST:event_txtGiaThapActionPerformed

    private void txtGiaCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaCaoActionPerformed
        
    }//GEN-LAST:event_txtGiaCaoActionPerformed

    private void txtTuNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuNgayActionPerformed

    }//GEN-LAST:event_txtTuNgayActionPerformed

    private void txtDenNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDenNgayActionPerformed
        
    }//GEN-LAST:event_txtDenNgayActionPerformed

    private void btnResetKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetKhoActionPerformed
        
    }//GEN-LAST:event_btnResetKhoActionPerformed

    private void btnResetKhoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetKhoMouseEntered
        btnResetKho.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnResetKhoMouseEntered

    private void btnResetKhoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetKhoMouseExited
        btnResetKho.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnResetKhoMouseExited

    private void btnTimKiemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseEntered
        btnTimKiem.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnTimKiemMouseEntered

    private void btnTimKiemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseExited
        btnTimKiem.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnTimKiemMouseExited

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
 
    }//GEN-LAST:event_btnXuatMouseClicked

    private void btnXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatMouseEntered
        btnXuat.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXuatMouseEntered

    private void btnXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatMouseExited
        btnXuat.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXuatMouseExited

// <editor-fold defaultstate="collapsed" desc="Variable">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonNhaCungCap;
    private javax.swing.JButton btnResetKho;
    private javax.swing.JButton btnResetTabXemLai;
    private javax.swing.JLabel btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JLabel btnXoa;
    private javax.swing.JLabel btnXuat;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
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
    private javax.swing.JTextField txtDenNgay;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGiaCao;
    private javax.swing.JTextField txtGiaThap;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtMaNL;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaPN;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextArea txtNhaCungCap;
    private javax.swing.JTextArea txtTenNL;
    private javax.swing.JTextField txtTongTienPN;
    private javax.swing.JTextField txtTuKhoa;
    private javax.swing.JTextField txtTuNgay;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>

}
