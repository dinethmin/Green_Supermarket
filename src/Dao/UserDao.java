package Dao;

import Connection.MyConnection;
import User.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        return row + 1;
    }

    public boolean isEmailExist(String Email) {
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from user where Email = ?");
            ps.setString(1, Email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean isUserNameExist(String UserName) {
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from user where UserName = ?");
            ps.setString(1, UserName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isPhoneNoExist(String PhoneNo) {
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from user where PhoneNo = ?");
            ps.setString(1, PhoneNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void insert(int UserID, String UserName, String Email, String PhoneNo, String SecurityQuestion, String Answer, String Address, String Password) {
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
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "User Sign Up successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUName() {
        String uName = Login.jTextField1.getText();

        return uName;
    }
    
    public String getEmail(String UserName){
        String Email = null;
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select Email from user where UserName = ?");
            ps.setString(1, UserName);
            rs = ps.executeQuery();
            if (rs.next()) {
                Email = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Email;
    }

    public String[] getUserValue(int UserID) {
        String[] value = new String[8];
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from user where UserID = ?");
            ps.setInt(1, UserID);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
                value[5] = rs.getString(6);
                value[6] = rs.getString(7);
                value[7] = rs.getString(8);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public int getUserID(String UserName) {
        int UserID = 0;
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select UserID from user where UserName = ?");
            ps.setString(1, UserName);
            rs = ps.executeQuery();
            if (rs.next()) {
                UserID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return UserID;
    }

    public void update(int uID, String UserName, String Email, String PhoneNo, String Answer, String Address, String Password) {
        String sql = "update user set UserName = ?, Email = ?, PhoneNo = ?, Answer = ?, Address = ?, Password = ? where UserID = ?";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, UserName);
            ps.setString(2, Email);
            ps.setString(3, PhoneNo);
            ps.setString(4, Answer);
            ps.setString(5, Address);
            ps.setString(6, Password);
            ps.setInt(7, uID);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "User data successfully updated");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int uID) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to delete this account?", "Delete Account", JOptionPane.YES_NO_OPTION, 0);
        if (x == JOptionPane.OK_OPTION) {
            try {
                Connection con = MyConnection.getConnection();
                ps = con.prepareStatement("delete from user where UserID = ?");
                ps.setInt(1, uID);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Account deleted");
                    System.exit(0);
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getUserData(JTable table, String search) {
        String sql = "select * from user where concat(UserID,UserName) like ? order by UserID asc";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+search +"%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object [] row = new Object[8];
            while(rs.next()){
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPurchaseDetailsData(JTable table, String UserName, String Status) {
        String sql = "select PurchaseID, ProductID, ProductName, Quantity, Price, total, OrderDate, DeliveryDate, DeliveryName, Status from purchasedetails where UserName = ? and Status = ? order by PurchaseID asc";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, UserName);
            ps.setString(2, Status);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row = new Object[10];
            while (rs.next()) {
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getDouble(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
