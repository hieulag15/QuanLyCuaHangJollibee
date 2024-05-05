package GUI;

import BUS.KhachHangBUS;
import DAO.MyConnect;
import Model.KhachHang;
import Custom.MyTable;
import Custom.Utils;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DlgTimKhach extends JDialog {

    private KhachHangBUS khachHangBUS = new KhachHangBUS();
    public static KhachHang khachHangTimDuoc = null;

    public DlgTimKhach() {
        addControls();
        addEvents();
        Image icon = Toolkit.getDefaultToolkit().getImage("image/ManagerUI/logo-app.jpg");
        this.setIconImage(icon);
        this.setSize(750, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblKhachHang;
    private DefaultTableModel dtmKhachHang;
    private JButton btnChon, btnThemKhach;

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Từ khoá tìm");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmKhachHang = new DefaultTableModel();
        dtmKhachHang.addColumn("Số điện thoại");
        dtmKhachHang.addColumn("Họ");
        dtmKhachHang.addColumn("Tên");
        dtmKhachHang.addColumn("Giới tính");
        dtmKhachHang.addColumn("Tổng chi tiêu");
        tblKhachHang = new MyTable(dtmKhachHang);
        Utils.customTable(tblKhachHang);
        JScrollPane scrKhachHang = new JScrollPane(tblKhachHang);
        pnTable.add(scrKhachHang, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnThemKhach = new JButton("Thêm khách");
        btnChon.setFont(font);
        btnThemKhach.setFont(font);
        pnButton.add(btnChon);
        pnButton.add(btnThemKhach);
        con.add(pnButton, BorderLayout.SOUTH);

        btnChon.setPreferredSize(new Dimension(120, 40));
        btnThemKhach.setPreferredSize(btnChon.getPreferredSize());

        loadDataLenTable();
    }

    private void addEvents() {
        txtTuKhoa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTuKhoaCaretUpdate(evt);
            }
        });

        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonKhachHang();
            }
        });

        btnThemKhach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhach();
            }
        });
    }
    
    private void txtTuKhoaCaretUpdate(javax.swing.event.CaretEvent evt) {                                      
        loadDataLenTableByKey(txtTuKhoa.getText()+"");
    }

    private void xuLyChonKhachHang() {
        int row = tblKhachHang.getSelectedRow();
        if (row > -1) {
            String sdt = tblKhachHang.getValueAt(row, 0) + "";
           
            khachHangTimDuoc = khachHangBUS.getKhachHang(sdt);
        }
        this.dispose();
    }

    private void xuLyThemKhach() {
        DlgThemKhachHang dlg = new DlgThemKhachHang();
        dlg.setVisible(true);
        if (dlg.checkThemKhach) {
            loadDataLenTable();
        }
    }
    
    DecimalFormat dcf = new DecimalFormat("###,###VNĐ");

    private void loadDataLenTable() {
        dtmKhachHang.setRowCount(0);
        ArrayList<KhachHang> dskh = khachHangBUS.getListKhachHang();
        if (dskh != null) {
            for (KhachHang kh : dskh) {
                Object[] row = new Object[5];
                row[0] = kh.getSdt();
                row[1] = kh.getHo();
                row[2] = kh.getTen();
                row[3] = kh.getGioiTinh();
                row[4] = dcf.format(kh.getTongChiTieu());
                dtmKhachHang.addRow(row);
            }
        }
    }

    private void loadDataLenTableByKey(String tuKhoa) {
        dtmKhachHang.setRowCount(0);
        ArrayList<KhachHang> dskh = khachHangBUS.getDanhSachKhachHangByKey(tuKhoa);
        for (KhachHang kh : dskh) {
                Object[] row = new Object[5];
                row[0] = kh.getSdt();
                row[1] = kh.getHo();
                row[2] = kh.getTen();
                row[3] = kh.getGioiTinh();
                row[4] = dcf.format(kh.getTongChiTieu());
                dtmKhachHang.addRow(row);
            }
    }

}
