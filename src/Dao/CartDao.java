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
    
    public int getMaxRow() {
        int row = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select count(ProductID) from cart");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public void insert(int ProductID, String ProductName, String CategoryName, int Quantity, double Price, double Total) {
        String sql = "insert into cart values(?,?,?,?,?,?)";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ProductID);
            ps.setString(2, ProductName);
            ps.setString(3, CategoryName);
            ps.setInt(4, Quantity);
            ps.setDouble(5, Price);
            ps.setDouble(6, Total);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product added to Cart");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(int ProductID, int Quantity, double Total) {
        String sql = "update cart set Quantity = ?, Total = ? where ProductID = ?";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Quantity);
            ps.setDouble(2, Total);
            ps.setInt(3, ProductID);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product added to cart");
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
    
    public boolean isProductExist(String ProductName){
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
            Object[] row = new Object[6];
            while (rs.next()) {
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getDouble(6);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double getSubTotal(){
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
    
    public String[] getUserValue(int UserID) {
        String[] value = new String[3];
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select Email,PhoneNo,Address from user where UserID = ?");
            ps.setInt(1, UserID);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }
    
    public int getPurchaseMaxRow() {
        int row = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select count(PurchaseID) from purchase");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1 ;
    }

}
