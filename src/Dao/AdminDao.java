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

public class AdminDao {

    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public int getMaxRow() {
        int row = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select max(AdminID) from admin");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

    public boolean isEmailExist(String Email) {
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from admin where Email = ?");
            ps.setString(1, Email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isPhoneNoExist(String PhoneNo) {
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from admin where PhoneNo = ?");
            ps.setString(1, PhoneNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void insert(int AdminID, String UserName, String Email, String PhoneNo, String Password) {
        String sql = "insert into admin values(?,?,?,?,?)";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, AdminID);
            ps.setString(2, UserName);
            ps.setString(3, Email);
            ps.setString(4, PhoneNo);
            ps.setString(5, Password);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Admin added successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUName() {
        String uName = Login.jTextField1.getText();

        return uName;
    }

    public String[] getAdminValue(int AdminID) {
        String[] value = new String[5];
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from admin where AdminID = ?");
            ps.setInt(1, AdminID);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public int getAdminID(String UserName) {
        int AdminID = 0;
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select AdminID from admin where UserName = ?");
            ps.setString(1, UserName);
            rs = ps.executeQuery();
            if (rs.next()) {
                AdminID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AdminID;
    }

    public void update(int aID, String UserName, String Email, String PhoneNo, String Password) {
        String sql = "update admin set UserName = ?, Email = ?, PhoneNo = ?, Password = ? where AdminID = ?";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, UserName);
            ps.setString(2, Email);
            ps.setString(3, PhoneNo);
            ps.setString(4, Password);
            ps.setInt(5, aID);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Admin data successfully updated");
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int aID) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to delete this account?", "Delete Account", JOptionPane.YES_NO_OPTION, 0);
        if (x == JOptionPane.OK_OPTION) {
            try {
                Connection con = MyConnection.getConnection();
                ps = con.prepareStatement("delete from admin where AdminID = ?");
                ps.setInt(1, aID);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Account deleted");
                    System.exit(0);
                }else{
                    JOptionPane.showMessageDialog(null, "Error");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getAdminData(JTable table, String search) {
        String sql = "select * from admin where concat(AdminID,UserName) like ? order by AdminID asc";
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
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getPurchaseData(JTable table, String Status, String DeliveryName, String DeliveryDate) {
        String sql = "select * from purchasedetails where DeliveryName = ? and Status = ? and DeliveryDate = ? order by PurchaseID asc";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, DeliveryName);
            ps.setString(2, Status);
            ps.setString(3, DeliveryDate);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row = new Object[14];
            while (rs.next()) {
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getString(5);
                row[5] = rs.getInt(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getDouble(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);
                row[13] = rs.getString(14);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countDeliveries() {
        int total = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select count(*) as total from delivery_team");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public String[] getDeliveries() {
        String[] Deliveries = new String[countDeliveries()];
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select * from delivery_team");
            int i = 0;
            while (rs.next()) {
                Deliveries[i] = rs.getString(2);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Deliveries;
    }
    
    public void setDeliveryName(int PurchaseID, String DeliveryName){
        String sql = "update purchasedetails set DeliveryName = ? where PurchaseID = ?";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, DeliveryName);
            ps.setInt(2, PurchaseID);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Delivery Assigned successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double getTotalSales() {
        double Total = 0;
        String Status = "Paid";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select sum(total) from purchasedetails where Status = '" + Status + "'");
            rs = ps.executeQuery();
            if (rs.next()) {
                Total = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Total;
    }
}
