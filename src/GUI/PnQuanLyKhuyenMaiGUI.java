/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Custom.Utils;
import Model.GiamGia;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import BUS.*;
import Custom.MyDialog;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Nun-PC
 */
public class PnQuanLyKhuyenMaiGUI extends javax.swing.JPanel {

    private GiamGiaBUS giamgiabus = new GiamGiaBUS();
    /**
     * Creates new form PnKhuyenMai
     */
    public PnQuanLyKhuyenMaiGUI() {
        initComponents();
        addControl();
        loadData();
    }
    
    private void addControl(){
        Utils.customTable(tblKhuyenMai);
        
        //
        //Chỉnh bảng khuyến mãi
        //
        //chỉnh sửa chiều rộng của các cột
        TableColumnModel columnModel = tblKhuyenMai.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(20);
        columnModel.getColumn(3).setPreferredWidth(60);
        //set chiều cao dòng
        tblKhuyenMai.setRowHeight(50); 
        //chỉnh nội dung nằm giữa
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);
        columnModel.getColumn(5).setCellRenderer(centerRenderer);
        columnModel.getColumn(6).setCellRenderer(centerRenderer);
    }
    private void xuLyClickTblKhuyenMai() {
        int row = tblKhuyenMai.getSelectedRow();
        if (row > -1) {
            String ma = tblKhuyenMai.getValueAt(row, 0) + "";
            String ten = tblKhuyenMai.getValueAt(row, 1) + "";
            String phanTram = tblKhuyenMai.getValueAt(row, 2) + "";
            String dieuKien = tblKhuyenMai.getValueAt(row, 3) + "";
            String start = tblKhuyenMai.getValueAt(row, 4) + "";
            String end = tblKhuyenMai.getValueAt(row, 5) + "";

            dieuKien = dieuKien.replace(">", "");
            dieuKien = dieuKien.replace(",", "");
            dieuKien = dieuKien.replace("Đ", "");
            java.util.Date ngayBD = new Date();
            java.util.Date ngayKT = new Date();
            try {
                ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(start);
                ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            } catch (Exception e) {
            }

            txtMaKhuyenMai.setText(ma);
            txtTenChuongTrinh.setText(ten);
            txtPhanTramGiam.setText(phanTram);
            txtDieuKien.setText(dieuKien);
            dateBD.setDate(ngayBD);
            dateKT.setDate(ngayKT);
        }};
    private void xuLyThemKhuyenMai() {
    // Kiểm tra không để trống các trường
    if (txtMaKhuyenMai.getText().isEmpty() || txtTenChuongTrinh.getText().isEmpty() ||
            txtPhanTramGiam.getText().isEmpty() || txtDieuKien.getText().isEmpty() ||
            dateBD.getDate() == null || dateKT.getDate() == null) {
        // Thông báo cho người dùng nhập đầy đủ thông tin
        new MyDialog("Vui lòng nhập đầy đủ thông tin.", MyDialog.ERROR_DIALOG);
        return;
    }
    
    // Kiểm tra mã giảm giá không âm
    int maGiamGia;
    try {
        maGiamGia = Integer.parseInt(txtMaKhuyenMai.getText());
        if (maGiamGia < 0) {
            new MyDialog("Mã giảm giá không được âm.", MyDialog.ERROR_DIALOG);
            return;
        }
    } catch (NumberFormatException e) {
        
        new MyDialog("Mã giảm giá phải là số nguyên.", MyDialog.ERROR_DIALOG);
        
        return;
    }

    
    try {
        int phanTramValue = Integer.parseInt(txtPhanTramGiam.getText());
        if (phanTramValue < 0 || phanTramValue > 100) {
            new MyDialog("Phần trăm giảm giá phải nằm trong khoảng từ 0 đến 100.", MyDialog.ERROR_DIALOG);
            return;
        }
    } catch (NumberFormatException e) {
        new MyDialog("Phần trăm giảm giá phải là một số nguyên.", MyDialog.ERROR_DIALOG);
        return;
    }
    
    try {
        int dieukien = Integer.parseInt(txtDieuKien.getText());
        if (dieukien < 0 ) {
            new MyDialog("Điều kiện không được âm.", MyDialog.ERROR_DIALOG);
            return;
        }
    } catch (NumberFormatException e) {
        new MyDialog("Điều kiện giảm giá phải là một số nguyên.", MyDialog.ERROR_DIALOG);
        return;
    }
    
    boolean flag = giamgiabus.themMaGiam(maGiamGia, txtTenChuongTrinh.getText(), txtPhanTramGiam.getText(), txtDieuKien.getText(), dateBD.getDate(), dateKT.getDate());
    if (flag) {
        loadData();
        new MyDialog("Thêm mã giảm giá thành công.", MyDialog.SUCCESS_DIALOG);
        
    } else {
        new MyDialog("Thêm mã giảm giá không thành công.", MyDialog.ERROR_DIALOG);
    }
}


    private void xuLySuaKhuyenMai() {
        try {
        int phanTramValue = Integer.parseInt(txtPhanTramGiam.getText());
        if (phanTramValue < 0 || phanTramValue > 100) {
            new MyDialog("Phần trăm giảm giá phải nằm trong khoảng từ 0 đến 100.", MyDialog.ERROR_DIALOG);
            return;
        }
    } catch (NumberFormatException e) {
        new MyDialog("Phần trăm giảm giá phải là một số nguyên.", MyDialog.ERROR_DIALOG);
        return;
    }
    
    try {
        int dieukien = Integer.parseInt(txtDieuKien.getText());
        if (dieukien < 0 ) {
            new MyDialog("Điều kiện không được âm.", MyDialog.ERROR_DIALOG);
            return;
        }
    } catch (NumberFormatException e) {
        new MyDialog("Điều kiện giảm giá phải là một số nguyên.", MyDialog.ERROR_DIALOG);
        return;
    }
        boolean flag = giamgiabus.suaMaGiam(txtMaKhuyenMai.getText(), txtTenChuongTrinh.getText(), txtPhanTramGiam.getText(), txtDieuKien.getText(), dateBD.getDate(), dateKT.getDate());
        if (flag)
            loadData();
    }
    
    private void xuLyXoaKhuyenMai() {
        int maKhuyenMai = Integer.parseInt(txtMaKhuyenMai.getText());
        
        boolean flag = giamgiabus.deleteGiamGia(maKhuyenMai);
        if (flag)
            loadData();
    }
    private void loadData() {
        DefaultTableModel dtmKhuyenMai = (DefaultTableModel) tblKhuyenMai.getModel();
        
        dtmKhuyenMai.setRowCount(0);
        giamgiabus.getAllGiamGia();
        ArrayList<GiamGia> dsg = giamgiabus.getAllGiamGia();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat dcf = new DecimalFormat(">###,###Đ");
        for (GiamGia gg : dsg) {
            Vector vec = new Vector();
            vec.add(gg.getMaGiam());
            vec.add(gg.getTenGiamGia());
            vec.add(gg.getPhanTramGiam());
            vec.add(dcf.format(gg.getDieuKien()));
            vec.add(sdf.format(gg.getNgayBD()));
            vec.add(sdf.format(gg.getNgayKT()));

            Date now = new Date();
            if (gg.getNgayBD().before(now) && gg.getNgayKT().after(now)) {
                vec.add("Có hiệu lực");
            } else {
                vec.add("Không hiệu lực");
            }

            dtmKhuyenMai.addRow(vec);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaKhuyenMai = new javax.swing.JTextField();
        txtTenChuongTrinh = new javax.swing.JTextField();
        txtPhanTramGiam = new javax.swing.JTextField();
        txtDieuKien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        btnThem = new javax.swing.JLabel();
        btnSua = new javax.swing.JLabel();
        btnXoa = new javax.swing.JLabel();
        dateBD = new com.toedter.calendar.JDateChooser();
        dateKT = new com.toedter.calendar.JDateChooser();
        btnReset = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ MÃ KHUYẾN MÃI");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Tên chương trình");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Mã khuyến mãi");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Phần trăm giảm");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Ngày kết thúc");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Điều kiện (>x)");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Ngày bắt đầu");

        txtMaKhuyenMai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtTenChuongTrinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtPhanTramGiam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtDieuKien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KM", "Tên chương trình", "% giảm", "Điều kiện", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng"
            }
        ));
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuyenMai);

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnThem.setText(" Thêm  ");
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

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pencil.png"))); // NOI18N
        btnSua.setText("   Sửa   ");
        btnSua.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSuaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSuaMouseExited(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        btnXoa.setText("   Xóa   ");
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

        dateBD.setDateFormatString("dd/MM/yyyy");
        dateBD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        dateKT.setDateFormatString("dd/MM/yyyy");
        dateKT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

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
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6))
                        .addGap(67, 67, 67))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaKhuyenMai)
                            .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(txtTenChuongTrinh)
                            .addComponent(txtDieuKien)
                            .addComponent(dateBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 268, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(dateBD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(dateKT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSua.getAccessibleContext().setAccessibleName(" Xóa  ");
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseEntered
        btnThem.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnThemMouseEntered

    private void btnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseExited
        btnThem.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnThemMouseExited

    private void btnSuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseEntered
        btnSua.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnSuaMouseEntered

    private void btnSuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseExited
        btnSua.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnSuaMouseExited

    private void btnXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseEntered
        btnXoa.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnXoaMouseEntered

    private void btnXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseExited
        btnXoa.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnXoaMouseExited

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
                // TODO add your handling code here:
                xuLyClickTblKhuyenMai();
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        // TODO add your handling code here:
        xuLyThemKhuyenMai();
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        xuLySuaKhuyenMai();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        xuLyXoaKhuyenMai();        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        txtMaKhuyenMai.setText("");
        txtTenChuongTrinh.setText("");
        txtDieuKien.setText("");
        txtPhanTramGiam.setText("");
    }//GEN-LAST:event_btnResetMouseClicked

    private void btnResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseEntered
        btnReset.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnResetMouseEntered

    private void btnResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseExited
        btnReset.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnResetMouseExited

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel btnSua;
    private javax.swing.JLabel btnThem;
    private javax.swing.JLabel btnXoa;
    private com.toedter.calendar.JDateChooser dateBD;
    private com.toedter.calendar.JDateChooser dateKT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextField txtMaKhuyenMai;
    private javax.swing.JTextField txtPhanTramGiam;
    private javax.swing.JTextField txtTenChuongTrinh;
    // End of variables declaration//GEN-END:variables
}
