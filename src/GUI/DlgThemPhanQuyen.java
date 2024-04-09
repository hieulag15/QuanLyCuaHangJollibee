package GUI;

import BUS.KhachHangBUS;
import BUS.PhanQuyenBUS;
import Model.PhanQuyen;
import Custom.MyDialog;
import java.util.ArrayList;

public class DlgThemPhanQuyen extends javax.swing.JDialog {

    public DlgThemPhanQuyen() {
        this.setTitle("Thêm phân quyền");
        initComponents();
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        ckbNhapHang = new javax.swing.JCheckBox();
        ckbSanPham = new javax.swing.JCheckBox();
        ckbNhanVien = new javax.swing.JCheckBox();
        ckbKhachHang = new javax.swing.JCheckBox();
        ckbThongKe = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ckbNhapHangADD = new javax.swing.JCheckBox();
        ckbSanPhamADD = new javax.swing.JCheckBox();
        ckbNhanVienADD = new javax.swing.JCheckBox();
        ckbKhachHangADD = new javax.swing.JCheckBox();
        ckbThongKeADD = new javax.swing.JCheckBox();
        txtQuyen = new javax.swing.JTextField();

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Nhóm quyền");

        ckbNhapHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ckbNhapHang.setText("Quản lý nhập hàng");

        ckbSanPham.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ckbSanPham.setText("Quản lý sản phẩm");

        ckbNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ckbNhanVien.setText("Quản lý nhân viên");

        ckbKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ckbKhachHang.setText("Quản lý khách hàng");

        ckbThongKe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ckbThongKe.setText("Quản lý thống kê");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThoatMouseClicked(evt);
            }
        });
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Nhóm quyền");

        ckbNhapHangADD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ckbNhapHangADD.setText("Quản lý nhập hàng");

        ckbSanPhamADD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ckbSanPhamADD.setText("Quản lý sản phẩm");

        ckbNhanVienADD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ckbNhanVienADD.setText("Quản lý nhân viên");

        ckbKhachHangADD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ckbKhachHangADD.setText("Quản lý khách hàng");

        ckbThongKeADD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ckbThongKeADD.setText("Quản lý thống kê");

        txtQuyen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnThem)
                        .addGap(5, 5, 5)
                        .addComponent(btnThoat))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(ckbSanPhamADD))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(ckbNhapHangADD)))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbKhachHangADD)
                            .addComponent(ckbNhanVienADD)
                            .addComponent(ckbThongKeADD))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckbNhanVienADD)
                            .addComponent(ckbNhapHangADD)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ckbKhachHangADD)
                        .addGap(18, 18, 18)
                        .addComponent(ckbThongKeADD))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbSanPhamADD)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnThoat))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        String quyen = txtQuyen.getText();
        int qlnhaphang = ckbNhapHangADD.isSelected() ? 1 : 0;
        int qlsanpham = ckbSanPhamADD.isSelected() ? 1 : 0;
        int qlnhanvien = ckbNhanVienADD.isSelected() ? 1 : 0;
        int qlkhachhang = ckbKhachHangADD.isSelected() ? 1 : 0;
        int qlthongke = ckbThongKeADD.isSelected() ? 1 : 0;
        PhanQuyenBUS pqbus = new PhanQuyenBUS();
        PhanQuyen phanQuyen = new PhanQuyen(quyen, qlnhaphang, qlsanpham, qlnhanvien, qlkhachhang, qlthongke);
        pqbus.addQuyen(phanQuyen);
        this.dispose();
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseClicked
        
    }//GEN-LAST:event_btnThoatMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JCheckBox ckbKhachHang;
    private javax.swing.JCheckBox ckbKhachHangADD;
    private javax.swing.JCheckBox ckbNhanVien;
    private javax.swing.JCheckBox ckbNhanVienADD;
    private javax.swing.JCheckBox ckbNhapHang;
    private javax.swing.JCheckBox ckbNhapHangADD;
    private javax.swing.JCheckBox ckbSanPham;
    private javax.swing.JCheckBox ckbSanPhamADD;
    private javax.swing.JCheckBox ckbThongKe;
    private javax.swing.JCheckBox ckbThongKeADD;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtQuyen;
    // End of variables declaration//GEN-END:variables
}
