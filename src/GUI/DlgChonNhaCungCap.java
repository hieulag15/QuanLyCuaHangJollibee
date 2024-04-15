/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.NhaCungCapBUS;
import Custom.MyDialog;
import Custom.MyTable;
import Model.NhaCungCap;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class DlgChonNhaCungCap extends javax.swing.JDialog {

    public static NhaCungCap nhaCungCapChon = null;
    private NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
    private DefaultTableModel dtmNhaCungCap;

    public DlgChonNhaCungCap() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setResizable(false);
        
        loadData();
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCapChon;
    }

    private void loadData() {
        dtmNhaCungCap = (DefaultTableModel) tblNhaCungCap.getModel();
        dtmNhaCungCap.setRowCount(0);
        ArrayList<NhaCungCap> dsNhaCungCap = nhaCungCapBUS.getListNhaCungCap();

        if (dsNhaCungCap != null) {
            for (NhaCungCap ncc : dsNhaCungCap) {
                dtmNhaCungCap.addRow(new Object[]{ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getDienThoai()});
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhaCungCap = new MyTable();
        jPanel2 = new javax.swing.JPanel();
        btnChonNCC = new javax.swing.JButton();
        btnThemNCC = new javax.swing.JButton();
        btnSuaNCC = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Chọn nhà cung cấp");
        jPanel1.add(jLabel1);

        tblNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Địa chỉ", "Điện thoại"
            }
        ));
        jScrollPane1.setViewportView(tblNhaCungCap);

        btnChonNCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnChonNCC.setText("Chọn");
        btnChonNCC.setPreferredSize(new java.awt.Dimension(141, 40));
        btnChonNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNCCActionPerformed(evt);
            }
        });
        jPanel2.add(btnChonNCC);

        btnThemNCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThemNCC.setText("Thêm mới");
        btnThemNCC.setPreferredSize(new java.awt.Dimension(141, 40));
        btnThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCCActionPerformed(evt);
            }
        });
        jPanel2.add(btnThemNCC);

        btnSuaNCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSuaNCC.setText("Sửa thông tin");
        btnSuaNCC.setPreferredSize(new java.awt.Dimension(141, 40));
        btnSuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNCCActionPerformed(evt);
            }
        });
        jPanel2.add(btnSuaNCC);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNCCActionPerformed
        int row = tblNhaCungCap.getSelectedRow();
        if (row == -1) {
            new MyDialog("Chưa chọn nhà cung cấp!", MyDialog.ERROR_DIALOG);
            return;
        }
        int maNCC = Integer.parseInt(tblNhaCungCap.getValueAt(row, 0) + "");
        String tenNCC = tblNhaCungCap.getValueAt(row, 1) + "";
        String diaChi = tblNhaCungCap.getValueAt(row, 2) + "";
        String dienThoai = tblNhaCungCap.getValueAt(row, 3) + "";
        nhaCungCapChon = new NhaCungCap(maNCC, tenNCC, diaChi, dienThoai);
        this.dispose();
    }//GEN-LAST:event_btnChonNCCActionPerformed

    private void btnThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCCActionPerformed
        DlgThemNhaCungCap dlg = new DlgThemNhaCungCap();
        dlg.setVisible(true);
        if (dlg.getCheckThemNCC()) {
            loadData();
        }
    }//GEN-LAST:event_btnThemNCCActionPerformed

    private void btnSuaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNCCActionPerformed
        int row = tblNhaCungCap.getSelectedRow();
        if (row == -1) {
            new MyDialog("Chưa chọn nhà cung cấp!", MyDialog.ERROR_DIALOG);
            return;
        }
        
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNCC(Integer.parseInt(tblNhaCungCap.getValueAt(row, 0) + ""));
        ncc.setTenNCC(tblNhaCungCap.getValueAt(row, 1) + "");
        ncc.setDiaChi(tblNhaCungCap.getValueAt(row, 2) + "");
        ncc.setDienThoai(tblNhaCungCap.getValueAt(row, 3) + "");

        DlgSuaNhaCungCap dlg = new DlgSuaNhaCungCap(ncc);
        dlg.setVisible(true);
        if (dlg.getCheckSuaNCC()) {
            loadData();
        }
    }//GEN-LAST:event_btnSuaNCCActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonNCC;
    private javax.swing.JButton btnSuaNCC;
    private javax.swing.JButton btnThemNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNhaCungCap;
    // End of variables declaration//GEN-END:variables
}
