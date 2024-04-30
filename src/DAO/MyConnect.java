/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Custom.MyDialog;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author ADMIN
 */
public class MyConnect {
    
    public Connection conn = null;
    
    public MyConnect() {
       //docFileText();

//        String strConnect = "jdbc:mysql://" + severName + "/" + dbName + "?useUnicode=true&characterEncoding=utf-8";
        String strConnect = "jdbc:mysql://localhost:3306/jollibee";
        Properties pro = new Properties();
        pro.put("user", "root");
        pro.put("password", "");
        try {
            com.mysql.jdbc.Driver driver = new Driver();
            conn = driver.connect(strConnect, pro);
        } catch (SQLException ex) {
            System.out.print(ex);
            new MyDialog("Không kết nối được tới CSDL!", MyDialog.ERROR_DIALOG);
            System.exit(0);
        }
    }

    public void CloseConnect()  {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Đã đóng kết nối đến CSDL.");
            } catch (SQLException ex) {
                System.out.println("Lỗi khi đóng kết nối đến CSDL: " + ex.getMessage());
            }
        }
    }
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
