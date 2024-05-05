package GUI;

import Custom.MyDialog;
import Model.HoaDon;
import BUS.CTHoaDonBUS;
import BUS.DangNhapBUS;
import BUS.HoaDonBUS;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class XuatHoaDonGUI extends JDialog {
    private DlgTimKhach timKhachUI = new DlgTimKhach();
    private DlgTimMaGiam timMaUI = null;
    HoaDonBUS hoadonBUS = new HoaDonBUS();
    CTHoaDonBUS ctHoaDonBUS = new CTHoaDonBUS();
    
    public XuatHoaDonGUI() {
        checkBanHang = false;
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/ManagerUI/logo-app.jpg");
        this.setIconImage(icon);
        customEvents();
    }

    private ArrayList<Object> dsGioHang;
    private int tongTien;
    private String nhanVien;
    private int tongTienFinal = 0;

    public XuatHoaDonGUI(ArrayList<Object> dsGioHang, int tongTien, String nhanVien) {
        this();
        this.tongTien = tongTien;
        this.dsGioHang = dsGioHang;
        this.nhanVien = nhanVien;
        DecimalFormat dcf = new DecimalFormat("###,###VNĐ");
        txtTongTien.setText(dcf.format(tongTien)); 
    }

    private void customEvents() {
        txtTenKhach.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                checkKhachMa();
            }

            public void removeUpdate(DocumentEvent e) {
                checkKhachMa();
            }

            public void insertUpdate(DocumentEvent e) {
                checkKhachMa();
            }
        });

        txtMaGiam.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                checkKhachMa();
            }

            public void removeUpdate(DocumentEvent e) {
                checkKhachMa();
            }

            public void insertUpdate(DocumentEvent e) {
                checkKhachMa();
            }
        });
    }

    private void checkKhachMa() {
        if (!txtTenKhach.getText().equals("") && !txtMaGiam.getText().equals("")) {
            btnThanhToan.setEnabled(true);
        } else {
            btnThanhToan.setEnabled(false);
        }
    }

    private void xuLyHienThiHoaDon() {
        txtHoaDon.setContentType("text/html");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        DecimalFormat dcf = new DecimalFormat("###,### VNĐ");       
        
        if (txtTenKhach.getText().isEmpty()){
            txtTenKhach.setText("Khách vãng lai");
            DlgTimKhach.khachHangTimDuoc = null;
        }

        String hd = "<style> "
                + "table {"
                + "border: 1px solid;"
                + "border-bottom: none"
                + "}"
                + "tr {"
                + "border-bottom: 1px solid;"
                + "}"
                + "td {"
                + "padding: 8px;"
                + "} "
                + "th {"
                + "font-size:16pt"
                + "}"
                + "</style>";
        hd += "<h1 style='text-align:center;'>HOÁ ĐƠN THANH TOÁN</h1>";
        hd += "Nhân viên: " + nhanVien + "<br/>";
        hd += "Ngày lập: " + dtf.format(now) + "<br/>";
        hd += "Khách hàng: " + txtTenKhach.getText() + "<br/>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        hd += "<div style='text-align:center'>";
        hd += "<table style='max-width:100%'>";
        hd += "<tr>"
                + "<th>Tên sản phẩm</th>"
                + "<th>Đơn giá</th>"
                + "<th>Số lượng</th>"
                + "<th>Thành tiền</th>"
                + "</tr>";
        for (Object ob : dsGioHang) {
            if (ob instanceof Object[]) {
                Object[] array = (Object[]) ob;
                hd += "<tr>";
                hd += "<td style='text-align:left; width: 160px'>" + array[1] + "</td>";
                hd += "<td style='text-align:center; width: 70px'>" + array[2] + "</td>";
                hd += "<td style='text-align:center; width: 30px'>" + array[3] + "</td>";
                hd += "<td style='text-align:center;'>" + array[4] + "</td>";
                hd += "</tr>";
            }
        }
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Tổng cộng</td>";
        hd += "<td style='text-align:center;'>" + dcf.format(tongTien) + "</td>";
        hd += "</tr>";
        if (timMaUI.maGiamTimDuoc != null) {
            int percent = 0;
            // lấy phần trăm giảm
            percent = timMaUI.maGiamTimDuoc.getPhanTramGiam();
            if (tongTien >= timMaUI.maGiamTimDuoc.getDieuKien()) {
                tongTienFinal = tongTien - (tongTien * percent / 100);
            } else {
                tongTienFinal = tongTien;
                new MyDialog("Không đủ điều kiện nhận ưu đãi!", MyDialog.ERROR_DIALOG);
                btnTimMaGiam.setEnabled(true);
                return;
            }
        }
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Khuyến mãi</td>";
        hd += "<td style='text-align:center;'>" + timMaUI.maGiamTimDuoc.getPhanTramGiam() + "%" + "</td>";
        hd += "</tr>";
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Thành tiền</td>";
        hd += "<td style='text-align:center;'>" + dcf.format(tongTienFinal) + "</td>";
        hd += "</tr>";
        hd += "</table>";
        hd += "</div>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        txtHoaDon.setText(hd);
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHoaDon = new javax.swing.JEditorPane();
        txtTenKhach = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaGiam = new javax.swing.JTextField();
        btnTimMaGiam = new javax.swing.JButton();
        btnTimKhach = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        jLabel5.setText("jLabel5");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(228, 22, 61));
        jLabel1.setText("Chi tiết hoá đơn");
        jPanel1.add(jLabel1);

        btnThanhToan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setPreferredSize(new java.awt.Dimension(128, 45));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel2.add(btnThanhToan);

        btnInHoaDon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInHoaDon.setText("In hoá đơn");
        btnInHoaDon.setEnabled(false);
        btnInHoaDon.setPreferredSize(new java.awt.Dimension(128, 45));
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });
        jPanel2.add(btnInHoaDon);

        txtHoaDon.setEditable(false);
        jScrollPane1.setViewportView(txtHoaDon);

        txtTenKhach.setEditable(false);
        txtTenKhach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenKhach.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Khách hàng");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Mã giảm");

        txtMaGiam.setEditable(false);
        txtMaGiam.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaGiam.setFocusable(false);

        btnTimMaGiam.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimMaGiam.setText("...");
        btnTimMaGiam.setPreferredSize(new java.awt.Dimension(40, 28));
        btnTimMaGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMaGiamActionPerformed(evt);
            }
        });

        btnTimKhach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimKhach.setText("...");
        btnTimKhach.setPreferredSize(new java.awt.Dimension(40, 28));
        btnTimKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKhachActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng tiền");

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTongTien.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addComponent(txtTenKhach)
                    .addComponent(txtMaGiam))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimMaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimMaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static boolean checkBanHang = false;

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {
        checkBanHang = false;
        if (txtTenKhach.getText().equals("")) {
            new MyDialog("Xin chọn khách hàng", MyDialog.ERROR_DIALOG);
            return;
        }
        if (txtMaGiam.getText().equals("")) {
            new MyDialog("Xin chọn mã giảm", MyDialog.ERROR_DIALOG);
            return;
        }
        btnInHoaDon.setEnabled(true);
        
        String sdt;
        if (DlgTimKhach.khachHangTimDuoc == null){
            sdt = null;
        }
        else{
            sdt = DlgTimKhach.khachHangTimDuoc.getSdt();
        }

        hoadonBUS.luuHoaDon(sdt, DangNhapBUS.taiKhoanLogin.getMaNhanVien(), tongTienFinal, "Đã thanh toán");

        for (Object ob : dsGioHang) {
            if (ob instanceof Object[]) {
                Object[] array = (Object[]) ob;
                String maSP = array[0] + "";
                String donGia = array[2] + "";
                String soLuong = array[3] + "";
                String thanhTien = array[4] + "";
                ctHoaDonBUS.addCTHoaDon(maSP, soLuong, donGia, thanhTien);
            }          
        }       
        btnThanhToan.setEnabled(false);
        btnTimMaGiam.setEnabled(false);
        btnTimKhach.setEnabled(false);
        checkBanHang = true;
        timKhachUI.khachHangTimDuoc = null;
        timMaUI.maGiamTimDuoc = null;
        new MyDialog("Thanh toán thành công", MyDialog.SUCCESS_DIALOG);
    }

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (!txtHoaDon.getText().equals("")) {
                txtHoaDon.print();
                this.dispose();
            }
        } catch (PrinterException ex) {
        }
    }


    private void btnTimKhachActionPerformed(java.awt.event.ActionEvent evt) {
        timKhachUI.setVisible(true);
        if (timKhachUI.khachHangTimDuoc != null) {
            txtTenKhach.setText(timKhachUI.khachHangTimDuoc.getSdt() + " - " + timKhachUI.khachHangTimDuoc.getHo() + " " + timKhachUI.khachHangTimDuoc.getTen());
        }
        if (timMaUI.maGiamTimDuoc != null) {
            xuLyHienThiHoaDon();
        }
    }


    private void btnTimMaGiamActionPerformed(java.awt.event.ActionEvent evt) {
        timMaUI = new DlgTimMaGiam(tongTien);
        timMaUI.setVisible(true);
        if (timMaUI.maGiamTimDuoc != null) {
            txtMaGiam.setText(timMaUI.maGiamTimDuoc.getMaGiam() + " - " + timMaUI.maGiamTimDuoc.getTenGiamGia());
        }
        xuLyHienThiHoaDon();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKhach;
    private javax.swing.JButton btnTimMaGiam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane txtHoaDon;
    private javax.swing.JTextField txtMaGiam;
    private javax.swing.JTextField txtTenKhach;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
