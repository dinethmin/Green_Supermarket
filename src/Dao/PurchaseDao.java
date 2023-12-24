package Dao;

import Connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseDao {

    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public void insert(int PurchaseID, int UserID, String UserName, int ProductID, String ProductName, int Quantity,
            double Price, double total, String PhoneNo, String Address, String OrderDate, String DeliveryDate,
            String DeliveryName, String Status) {

        String sql = "insert into purchase values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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

    public int getQuantity(int ProductID) {
        int Quantity = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select Quantity from product where ProductID = ?");
            ps.setInt(1, ProductID);
            if (rs.next()) {
                Quantity = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Quantity;
    }

    public void updateQuantity(int ProductID, int Quantity) {
        String sql = "update product set Quantity = ? where ProductID = ?";
        try {
            Connection con = MyConnection.getConnection();
            st = con.prepareStatement(sql);
            ps.setInt(1, Quantity);
            ps.setInt(2, ProductID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
