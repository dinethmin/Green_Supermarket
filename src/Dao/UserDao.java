package Dao;

import Connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UserDao {
    
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public int getMaxRow() {
        int row = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select max(UserID) from user");
            while (rs.next()) {
                row = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row +1;
    }
    
    public boolean isEmailExist(String Email){
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from user where Email = ?");
            ps.setString(1, Email);
            rs =ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean isPhoneNoExist(String PhoneNo){
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from user where PhoneNo = ?");
            ps.setString(1, PhoneNo);
            rs =ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void insert(int UserID, String UserName, String Email, String PhoneNo, String SecurityQuestion, String Answer, String Address, String Password){
        String sql = "insert into user values(?,?,?,?,?,?,?,?)";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setString(2, UserName);
            ps.setString(3, Email);
            ps.setString(4, PhoneNo);
            ps.setString(5, SecurityQuestion);
            ps.setString(6, Answer);
            ps.setString(7, Address);
            ps.setString(8, Password);
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "User Sign Up successfully");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
