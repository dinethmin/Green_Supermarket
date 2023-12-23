package Dao;

import Connection.MyConnection;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductDao {
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public int getMaxRow() {
        int row = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select max(ProductID) from product");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
    
    public int countCategories(){
        int total = 0;
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select count(*) as total from category");
            if(rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public String[] getCategory(){
        String [] categories = new String[countCategories()];
        try {
            Connection con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select * from category");
            int i = 0;
            while (rs.next()) {
                categories[i] = rs.getString(2);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
    
    public boolean isProductIDExist(int ProductID) {
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from product where ProductID = ?");
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean isProductCategoryNameExist(String ProductName, String CategoryName) {
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement("select * from product where ProductName = ? and CategoryName = ?");
            ps.setString(1, ProductName);
            ps.setString(2, CategoryName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void insert(int ProductID, String ProductName, String CategoryName, int Quantity, double Price, File file) throws FileNotFoundException {
        String sql = "insert into product values(?,?,?,?,?,?)";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            InputStream is = new FileInputStream(file);
            ps.setInt(1, ProductID);
            ps.setString(2, ProductName);
            ps.setString(3, CategoryName);
            ps.setInt(4, Quantity);
            ps.setDouble(5, Price);
            ps.setBinaryStream(6, is,(int)file.length());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product added successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(int ProductID, String ProductName, String CategoryName, int Quantity, double Price, File file) throws FileNotFoundException {
        String sql = "update product set ProductName = ?, CategoryName = ?, Quantity = ?, Price = ?, Image = ? where ProductID = ?";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            InputStream is = new FileInputStream(file);
            ps.setString(1, ProductName);
            ps.setString(2, CategoryName);
            ps.setInt(3, Quantity);
            ps.setDouble(4, Price);
            ps.setBinaryStream(5, is,(int)file.length());
            ps.setInt(6, ProductID);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product data successfully updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateWithoutImage(int ProductID, String ProductName, String CategoryName, int Quantity, double Price) {
        String sql = "update product set ProductName = ?, CategoryName = ?, Quantity = ?, Price = ? where ProductID = ?";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ProductName);
            ps.setString(2, CategoryName);
            ps.setInt(3, Quantity);
            ps.setDouble(4, Price);
            ps.setInt(5, ProductID);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product data successfully updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int ProductID) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to delete this product?", "Delete Product", JOptionPane.YES_NO_OPTION, 0);
        if (x == JOptionPane.OK_OPTION) {
            try {
                Connection con = MyConnection.getConnection();
                ps = con.prepareStatement("delete from product where ProductID = ?");
                ps.setInt(1, ProductID);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Product deleted");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void getProductData(JTable table, String search) {
        String sql = "select * from product where concat(ProductID,ProductName) like ? order by ProductID asc";
        try {
            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row = new Object[6];
            while (rs.next()) {
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getDouble(5);
                byte[] imageData = rs.getBytes(6);
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image image = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(image);
                //Vector<Object> row = new Vector<>();
                row[5] = scaledImageIcon;
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
