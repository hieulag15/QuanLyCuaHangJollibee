/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.*;
import Custom.MyDialog;
import Custom.Utils;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import Model.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nun-PC
 */
public class PnQuanLyKhachHangGUI extends javax.swing.JPanel {

    private KhachHangBUS khachHangBUS = new KhachHangBUS();

    /**
     * Creates new form PnQuanLyKhachHang
     */
    public PnQuanLyKhachHangGUI() {
        initComponents();
        addControls();
        loadData();
    }

    private void addControls() {
        Utils.customTable(tblKhachHang);
    }

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        ArrayList<KhachHang> lsKhachHang = khachHangBUS.getListKhachHang();
        for (KhachHang khachHang : lsKhachHang) {
            model.addRow(new Object[]{khachHang.getSdt(), khachHang.getHo(), khachHang.getTen(), khachHang.getGioiTinh(), khachHang.getTongChiTieu()});
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

        txtTen = new javax.swing.JTextField();
        btnThem = new javax.swing.JLabel();
        btnSua = new javax.swing.JLabel();
        btnXoa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        txtHoDem = new javax.swing.JTextField();
        cbGioiTinh = new javax.swing.JComboBox<>();
        txtTongChiTieu = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtStart = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTuKhoa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEnd = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Họ đệm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Số điện thoại");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Tên");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Giới tính");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Tổng chi tiêu");

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSoDienThoai.setEnabled(false);

        txtHoDem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cbGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        txtTongChiTieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTongChiTieu.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Từ khóa tìm");

        txtStart.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Chi tiêu từ");

        txtTuKhoa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("đến");

        txtEnd.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Tìm kiếm");

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTimKiem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTimKiem.setText("   Tìm kiếm  ");
        btnTimKiem.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTimKiem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseExited(evt);
            }
        });

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số điện thoại", "Họ đệm", "Tên", "Giới tính", "Tổng chi tiêu"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(82, 82, 82)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtHoDem, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbGioiTinh, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtTongChiTieu, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(119, 119, 119)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel6)
                                                    .addComponent(txtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(btnTimKiem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel7)))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel10)
                                                .addGap(97, 97, 97))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jLabel1)))
                        .addGap(0, 94, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHoDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTongChiTieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );
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

    private void btnTimKiemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseEntered
        btnTimKiem.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    }//GEN-LAST:event_btnTimKiemMouseEntered

    private void btnTimKiemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseExited
        btnTimKiem.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    }//GEN-LAST:event_btnTimKiemMouseExited

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        // TODO add your handling code here:
        DlgThemKhachHang dlgThemKhachHang = new DlgThemKhachHang();
        dlgThemKhachHang.setVisible(true);
        if (dlgThemKhachHang.checkThemKhach) {
            loadData();
        }
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        // TODO add your handling code here:
        khachHangBUS.suaKhachHang(txtSoDienThoai.getText(),
                txtHoDem.getText().trim().toString(),
                txtTen.getText().trim().toString(),
                cbGioiTinh.getSelectedItem().toString());
        loadData();
    }//GEN-LAST:event_btnSuaMouseClicked

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        int index = tblKhachHang.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        if (index != -1) {

            txtSoDienThoai.setText(model.getValueAt(index, 0).toString());
            txtHoDem.setText(model.getValueAt(index, 1).toString());
            txtTen.setText(model.getValueAt(index, 2).toString());
            cbGioiTinh.setSelectedItem(model.getValueAt(index, 3).toString());
            txtTongChiTieu.setText(model.getValueAt(index, 4).toString());

        }
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        // TODO add your handling code here:
        khachHangBUS.xoaKhachHang(txtSoDienThoai.getText());
        loadData();
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        // TODO add your handling code here:

        String min = txtStart.getText().trim();
        String max = txtEnd.getText().trim();
        String key = txtTuKhoa.getText().trim().toLowerCase();

        ArrayList<KhachHang> lsKhachHang = khachHangBUS.getDanhSachKhachHangByKey(key, min, max);

        if (lsKhachHang == null) {
            loadData();
        } else {
            DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
            model.setRowCount(0);
            for (KhachHang khachHang : lsKhachHang) {
                model.addRow(new Object[]{khachHang.getSdt(), khachHang.getHo(), khachHang.getTen(), khachHang.getGioiTinh(), khachHang.getTongChiTieu()});
            }
        }
    }//GEN-LAST:event_btnTimKiemMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnSua;
    private javax.swing.JLabel btnThem;
    private javax.swing.JLabel btnTimKiem;
    private javax.swing.JLabel btnXoa;
    private javax.swing.JComboBox<String> cbGioiTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtHoDem;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtStart;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTongChiTieu;
    private javax.swing.JTextField txtTuKhoa;
    // End of variables declaration//GEN-END:variables
}
