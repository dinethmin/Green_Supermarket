package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MyConnection {
    
    public static final String username = "avnadmin";
    public static final String password = "AVNS_SAamzfMBhAbouv5GD4v";
    public static final String url = "jdbc:mysql://mysql-2d7a96e2-reoakio-7b0f.a.aivencloud.com:11430/Green_Supermarket";
    public static Connection con = null;
    
    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,""+ex,"",JOptionPane.WARNING_MESSAGE);
        }
        return con;
    }
    
}
