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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PurchaseDao {

    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public int getMaxRow() {
        int row = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select max(PurchaseID) from purchasedetails");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public void insert(int PurchaseID, int UserID, String UserName, int ProductID, String ProductName, int Quantity,
            double Price, double total, String PhoneNo, String Address, String OrderDate, String DeliveryDate,
            String DeliveryName, String Status) {

        String sql = "insert into purchasedetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, PurchaseID);
            ps.setInt(2, UserID);
            ps.setString(3, UserName);
            ps.setInt(4, ProductID);
            ps.setString(5, ProductName);
            ps.setInt(6, Quantity);
            ps.setDouble(7, Price);
            ps.setDouble(8, total);
            ps.setString(9, PhoneNo);
            ps.setString(10, Address);
            ps.setString(11, OrderDate);
            ps.setString(12, DeliveryDate);
            ps.setString(13, DeliveryName);
            ps.setString(14, Status);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int PurchaseID) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to remove this product?", "Cancel Purchase", JOptionPane.YES_NO_OPTION, 0);
        if (x == JOptionPane.OK_OPTION) {
            try {
                Connection con = MyConnection.getConnection();
                ps = con.prepareStatement("delete from purchasedetails where PurchaseID = ?");
                ps.setInt(1, PurchaseID);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Purchase Cancelled");
                }
            } catch (SQLException ex) {
                Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(String UserName, String Status) {
        String sql = "UPDATE purchasedetails SET Status = ? WHERE UserName = ?";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Status);
            ps.setString(2, UserName);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getPurchaseData(JTable table, String UserName, String Status) {
        String sql = "select * from purchasedetails where UserName = ? and Status = ? order by PurchaseID asc";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, UserName);
            ps.setString(2, Status);
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
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getSubTotalPrice(String UserName, String Status) {
        double SubTotalPrice = 0;
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select sum(total) from purchasedetails where UserName = '" + UserName + "' and Status = '" + Status + "'");
            rs = ps.executeQuery();
            if (rs.next()) {
                SubTotalPrice = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SubTotalPrice;
    }

    public void sendEmail(String UserName) {
        String sql = "select PurchaseID, ProductID, ProductName, Quantity, Price, total from purchasedetails where UserName = ?";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, UserName);
            rs = ps.executeQuery();
            while (rs.next()) {
                String column1Value = String.valueOf(rs.getInt("PurchaseID"));
                String column2Value = String.valueOf(rs.getInt("ProductID"));
                String column3Value = rs.getString("ProductName");
                String column4Value = String.valueOf(rs.getInt("Quantity"));
                String column5Value = String.valueOf(rs.getDouble("Price"));
                String column6Value = String.valueOf(rs.getDouble("total"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
