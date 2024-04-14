/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Nun-PC
 */
public class Utils {
    // Hàm để thêm một panel con vào panel cha
    public static void addPanelToPanel(JPanel parentPanel, JPanel childPanel) {
        parentPanel.add(childPanel); // Thêm panel con vào panel cha
        parentPanel.revalidate(); // Cập nhật lại panel cha để hiển thị panel con mới
        parentPanel.repaint(); // Vẽ lại panel cha để hiển thị panel con mới
    }
    
    public static void customTable(JTable tbl){
        tbl.setFocusable(false);
        tbl.setIntercellSpacing(new Dimension(0, 0));
        tbl.setRowHeight(25);
        tbl.setSelectionBackground(new Color(50, 154, 114));
        tbl.setSelectionForeground(Color.white);
        tbl.setFont(new Font("Arial", Font.PLAIN, 16));

        JTableHeader header = tbl.getTableHeader();
        header.setBackground(new Color(228,22,61));
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setOpaque(false);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }
        
    public static ImageIcon getAnhSP(String src, File fileAnh) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File("image/SanPham/" + src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("image/SanPham/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnh = new File("image/SanPham/" + src);
        } catch (IOException e) {
            fileAnh = new File("image/SanPham/default.png");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

            return new ImageIcon(dimg);
        }
        return null;
    }
    
    public static boolean checkMa(String input) {
        try {
            int number = Integer.parseInt(input);
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean checkTien(String input) {
        try {
            int number = Integer.parseInt(input.replace(",", ""));
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
