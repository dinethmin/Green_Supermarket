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

public class DeliveryDao {

    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public int getMaxRow() {
        int row = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select max(DeliveryID) from delivery_team");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

    public boolean isEmailExist(String Email) {
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from delivery_team where Email = ?");
            ps.setString(1, Email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isPhoneNoExist(String PhoneNo) {
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from delivery_team where PhoneNo = ?");
            ps.setString(1, PhoneNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void insert(int DeliveryID, String UserName, String Email, String PhoneNo, String Password) {
        String sql = "insert into delivery_team values(?,?,?,?,?)";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, DeliveryID);
            ps.setString(2, UserName);
            ps.setString(3, Email);
            ps.setString(4, PhoneNo);
            ps.setString(5, Password);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Delivery Team added successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUName() {
        String uName = Login.jTextField1.getText();

        return uName;
    }

    public String[] getDeliveryValue(int DeliveryID) {
        String[] value = new String[5];
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from delivery_team where DeliveryID = ?");
            ps.setInt(1, DeliveryID);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public int getDeliveryID(String UserName) {
        int DeliveryID = 0;
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select DeliveryID from delivery_team where UserName = ?");
            ps.setString(1, UserName);
            rs = ps.executeQuery();
            if (rs.next()) {
                DeliveryID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DeliveryID;
    }
    
    public void update(int dID, String UserName, String Email, String PhoneNo, String Password) {
        String sql = "update delivery_team set UserName = ?, Email = ?, PhoneNo = ?, Password = ? where DeliveryID = ?";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, UserName);
            ps.setString(2, Email);
            ps.setString(3, PhoneNo);
            ps.setString(4, Password);
            ps.setInt(5, dID);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Delivery Team data successfully updated");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int dID) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to delete this account?", "Delete Account", JOptionPane.YES_NO_OPTION, 0);
        if (x == JOptionPane.OK_OPTION) {
            try {
                Connection con = MyConnection.getConnection();
                ps = con.prepareStatement("delete from delivery_team where DeliveryID = ?");
                ps.setInt(1, dID);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Account deleted");
                    System.exit(0);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DeliveryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getDeliveryData(JTable table, String search) {
        String sql = "select * from delivery_team where concat(DeliveryID,UserName) like ? order by DeliveryID asc";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row = new Object[5];
            while (rs.next()) {
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
