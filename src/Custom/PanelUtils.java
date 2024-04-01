/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;
import javax.swing.JPanel;

/**
 *
 * @author Nun-PC
 */
public class PanelUtils {
    // Hàm để thêm một panel con vào panel cha
    public static void addPanelToPanel(JPanel parentPanel, JPanel childPanel) {
        parentPanel.add(childPanel); // Thêm panel con vào panel cha
        parentPanel.revalidate(); // Cập nhật lại panel cha để hiển thị panel con mới
        parentPanel.repaint(); // Vẽ lại panel cha để hiển thị panel con mới
    }
}
