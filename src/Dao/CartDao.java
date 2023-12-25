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

public class CartDao {

    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public int getRowCount() {
        int rowCount = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) FROM cart");
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount + 1;
    }

    public void insert(int PurchaseID, int ProductID, String ProductName, String CategoryName, int Quantity, double Price, double Total) {
        String sql = "insert into cart values(?,?,?,?,?,?,?)";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, PurchaseID);
            ps.setInt(2, ProductID);
            ps.setString(3, ProductName);
            ps.setString(4, CategoryName);
            ps.setInt(5, Quantity);
            ps.setDouble(6, Price);
            ps.setDouble(7, Total);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product added to Cart");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(int ProductID, int Quantity, double Total) {
        String sql = "UPDATE cart SET Quantity = ?, Total = ? WHERE ProductID = ?";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Quantity);
            ps.setDouble(2, Total);
            ps.setInt(3, ProductID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cart updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update cart");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int ProductID) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to remove this product?", "Remove Product", JOptionPane.YES_NO_OPTION, 0);
        if (x == JOptionPane.OK_OPTION) {
            try {
                Connection con = MyConnection.getConnection();
                ps = con.prepareStatement("delete from cart where ProductID = ?");
                ps.setInt(1, ProductID);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Product removed");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isProductExist(String ProductName) {
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from cart where ProductName = ?");
            ps.setString(1, ProductName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void getCartData(JTable table) {
        String sql = "select * from cart order by ProductID asc";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row = new Object[7];
            while (rs.next()) {
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getDouble(6);
                row[6] = rs.getDouble(7);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getSubTotal() {
        double SubTotal = 0;
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select sum(total) from cart");
            rs = ps.executeQuery();
            if (rs.next()) {
                SubTotal = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SubTotal;
    }

    public String[] getUserValue(String UserName) {
        String[] value = new String[4];
        String sql = "select UserID,Email,PhoneNo,Address from user where UserName = '" + UserName + "'";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

}
