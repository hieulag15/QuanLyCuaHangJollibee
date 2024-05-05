package GUI;

import BUS.CTPhieuNhapBUS;
import BUS.NguyenLieuBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import Model.CTPhieuNhap;
import Model.SanPham;
import Custom.MyDialog;
import Model.NguyenLieu;
import Model.NhaCungCap;
import Model.NhanVien;
import Model.PhieuNhap;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class XuatPhieuNhapGUI extends javax.swing.JDialog {

    private ArrayList<Object> listCTPhieuNhap = null;
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private NguyenLieuBUS spBUS = new NguyenLieuBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private NguyenLieuBUS nlBUS = new NguyenLieuBUS();
    private int tongTien;
    private boolean checkNhap = false;
    int maNhaCungCap;
    int maNhanVien;

    public XuatPhieuNhapGUI(int maNhaCungCap, int maNhanVien, ArrayList<Object> listCTPhieuNhap, int tongTien) {
        Main.main.changLNF("Windows");
        checkNhap = false;

        this.maNhaCungCap = maNhaCungCap;
        this.maNhanVien = maNhanVien;
        this.listCTPhieuNhap = listCTPhieuNhap;
        this.tongTien = tongTien;

        initComponents();
        
        txtChiTiet.setEditable(false);
        hienThiPhieuNhap();

        this.setLocationRelativeTo(null);
        this.setModal(true);
        btnInPhieu.setEnabled(false);
    }

    public boolean getCheckNhap() {
        return checkNhap;
    }
    
    private void hienThiPhieuNhap(){
        txtChiTiet.setContentType("text/html");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        DecimalFormat dcf = new DecimalFormat("###,### VNĐ");
        
        NhaCungCap nhaCC = nccBUS.getNhaCungCap(maNhaCungCap);
        NhanVien nv = nvBUS.getNhanVien(maNhanVien);

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
        hd += "<h1 style='text-align:center;'>CHI TIẾT PHIẾU NHẬP</h1>";
        hd += "Nhân viên: " + nv.getHo() + " " + nv.getTen() + "<br/>";
        hd += "Ngày lập: " + dtf.format(now) + "<br/>";
        hd += "Nhà cung cấp: " + nhaCC.getTenNCC() + "<br/>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        hd += "<div style='text-align:center'>";
        hd += "<table style='max-width:100%'>";
        hd += "<tr>"
                + "<th>Mã SP</th>"
                + "<th>Tên sản phẩm</th>"
                + "<th>Số lượng</th>"
                + "<th>Đơn giá</th>"
                + "<th>Thành tiền</th>"
                + "</tr>";
        for (Object ob : listCTPhieuNhap) {
            if (ob instanceof Object[]) {
                Object[] array = (Object[]) ob;
                hd += "<tr>";
                hd += "<td style='text-align:center; width: 50px'>" + array[0] + "</td>";
                NguyenLieu sp = spBUS.getNguyenLieu(Integer.parseInt(array[0]+""));
                hd += "<td style='text-align:left; width: 150px'>" + sp.getTenNL() + "</td>";
                hd += "<td style='text-align:center; width: 80px'>" + array[1] + "</td>";
                hd += "<td style='text-align:center; width: 80px'>" + array[2] + "</td>";
                hd += "<td style='text-align:center; width: 100p'>" + array[3] + "</td>";
                hd += "</tr>";
            }
        }
        
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Thành tiền</td>";
        hd += "<td style='text-align:center;'>" + dcf.format(tongTien) + "</td>";
        hd += "</tr>";
        hd += "</table>";
        hd += "</div>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";

        txtChiTiet.setText(hd);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtChiTiet = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        btnXacNhan = new javax.swing.JButton();
        btnInPhieu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtChiTiet.setEditable(false);
        jScrollPane1.setViewportView(txtChiTiet);

        btnXacNhan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setPreferredSize(new java.awt.Dimension(145, 40));
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        jPanel2.add(btnXacNhan);

        btnInPhieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInPhieu.setText("In phiếu ");
        btnInPhieu.setPreferredSize(new java.awt.Dimension(145, 40));
        btnInPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPhieuActionPerformed(evt);
            }
        });
        jPanel2.add(btnInPhieu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        checkNhap = true;
        btnXacNhan.setEnabled(false);
        btnInPhieu.setEnabled(true);

        // Lưu Phiếu nhập trước để xíu lấy cái mã
        PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        PhieuNhap phieuNhap = new PhieuNhap();
        phieuNhap.setMaNCC(maNhaCungCap);
        phieuNhap.setMaNV(maNhanVien);
        phieuNhap.setTongTien(tongTien);
        phieuNhapBUS.addPhieuNhap(phieuNhap);

        int maPN = phieuNhapBUS.getPhieuNhapMoiNhat();
        CTPhieuNhapBUS ctPhieuNhapBUS = new CTPhieuNhapBUS();

        for (Object ob : listCTPhieuNhap) {
            if (ob instanceof Object[]) {
                Object[] array = (Object[]) ob;

                //===================================================
                //===================LƯU CTPN VÀO DB=================
                //===================================================
                CTPhieuNhap ctpn = new CTPhieuNhap();
                
                int maNL = Integer.parseInt(array[0]+"");
                int soLuong = Integer.parseInt(array[1]+"");
                
                ctpn.setMaPN(maPN);
                ctpn.setMaSP(maNL);
                ctpn.setSoLuong(soLuong);
                ctpn.setDonGia(Integer.parseInt((array[2]+"").replace(",", "").replace("Đ", "")));
                ctpn.setThanhTien(Integer.parseInt((array[3]+"").replace(",", "").replace("Đ", "")));
                ctPhieuNhapBUS.themChiTietPhieuNhap(ctpn);
                
                //cập nhật số lượng nguyên liệu trong database
                NguyenLieu nl = nlBUS.getNguyenLieu(maNL);
                nl.setSoLuong(nl.getSoLuong() + soLuong);
                nlBUS.updateNguyenLieu(nl);
            }
        }               
        
        new MyDialog("Xác nhận thành công!", MyDialog.SUCCESS_DIALOG);
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnInPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInPhieuActionPerformed
        try {
            txtChiTiet.print();
        } catch (PrinterException e) {
        }
        this.dispose();
    }//GEN-LAST:event_btnInPhieuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInPhieu;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane txtChiTiet;
    // End of variables declaration//GEN-END:variables
}
