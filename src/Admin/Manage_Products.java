/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Dao.ProductDao;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Manage_Products extends javax.swing.JFrame {

    /**
     * Creates new form Manage_Products
     */
    ProductDao product = new ProductDao();
    DefaultTableModel model;
    int rowIndex;
    String[] Categories;
    File file = null;
    int a = 30;
    
    public Manage_Products() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable()
        ;
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1030, 640));
        setMinimumSize(new java.awt.Dimension(1030, 640));

        jPanel1.setBackground(new java.awt.Color(231, 255, 206));
        jPanel1.setMinimumSize(new java.awt.Dimension(1030, 640));
        jPanel1.setPreferredSize(new java.awt.Dimension(1030, 640));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/011.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 90));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/avatar 1.png"))); // NOI18N
        jButton1.setToolTipText("");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 70, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 255, 102));
        jLabel3.setText("Products");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 200, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 90));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Category", "Quantity", "Price", "Image"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 690, 510));

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 130, 30));

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 130, 30));

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 590, 130, 30));

        jButton6.setBackground(new java.awt.Color(204, 204, 204));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 130, 30));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 290, 30));

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.setText("0");
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 290, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Product ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 100, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Category");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 90, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Price");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 60, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Product Name");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 120, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Quantity");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 120, -1));

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField5.setText("0.0");
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 290, 30));

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 290, 30));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 290, 30));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, 270, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Search");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 70, 30));

        jButton7.setBackground(new java.awt.Color(204, 204, 204));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Upload an image to the product");
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 300, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void init() {
        jTextField3.setText(String.valueOf(product.getMaxRow()));
        Categories = new String[product.countCategories()];
        productTable();
        setCategory();
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new ImageIconRenderer());
    }

    class ImageIconRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();
            if (value != null && value instanceof ImageIcon) {
                label.setIcon((ImageIcon) value);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
            }
            return label;
        }
    }

    private void setCategory() {
        Categories = product.getCategory();
        for (String s : Categories) {
            jComboBox1.addItem(s);
        }
    }

    private void productTable() {
        product.getProductData(jTable1, "",a);
        model = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowHeight(30);
        jTable1.setShowGrid(true);
        jTable1.setGridColor(Color.BLACK);
        jTable1.setBackground(Color.WHITE);
    }

    private void clear() {
        jTextField1.setText("");
        jTextField3.setText(String.valueOf(product.getMaxRow()));
        jTextField6.setText("");
        jTextField4.setText("0");
        jTextField5.setText("0.0");
        jComboBox1.setSelectedIndex(0);
        jTable1.clearSelection();
        file = null;
    }

    private boolean isEmpty() {
        if (jTextField6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Category Name is required", "Warning", 2);
            return false;
        }
        if (Integer.parseInt(jTextField4.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Please increase the Quantity", "Warning", 2);
            return false;
        }
        if (jTextField5.getText().equals("0.0")) {
            JOptionPane.showMessageDialog(this, "Product Price is required", "Warning", 2);
            return false;
        }
        return true;
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        new Admin_Dashbord().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (isEmpty()) {
            int ProductID = Integer.parseInt(jTextField3.getText());
            String ProductName = jTextField6.getText();
            String Category = jComboBox1.getSelectedItem().toString();
            int Quantity = Integer.parseInt(jTextField4.getText());
            File f = file;
            if (isNumeric(jTextField5.getText())) {
                double Price = Double.parseDouble(jTextField5.getText());
                if (!product.isProductIDExist(ProductID)) {
                    if (!product.isProductCategoryNameExist(ProductName, Category)) {
                        if (!(f == null)) {
                            try {
                                product.insert(ProductID, ProductName, Category, Quantity, Price, f);
                                jTable1.setModel(new DefaultTableModel(null, new Object[]{"ProductID", "ProductName", "Category", "Quantity", "Price", "Image"}));
                                product.getProductData(jTable1, "", a);
                                jTable1.getColumnModel().getColumn(5).setCellRenderer(new ImageIconRenderer());
                                clear();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Manage_Products.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Please choose a Product image", "Warning", 2);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "This Product and Category already exists", "Warning", 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This ProductID is already exists", "Warning", 2);
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        file = chooser.getSelectedFile();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"ProductID", "ProductName", "Category", "Quantity", "Price", "Image"}));
        product.getProductData(jTable1, jTextField1.getText(), a);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new ImageIconRenderer());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clear();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (jTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a product");
        } else {
            int ProductID = Integer.parseInt(jTextField3.getText());
            if (product.isProductIDExist(ProductID)) {
                product.delete(ProductID);
                jTable1.setModel(new DefaultTableModel(null, new Object[]{"ProductID", "ProductName", "Category", "Quantity", "Price", "Image"}));
                product.getProductData(jTable1, "", a);
                jTable1.getColumnModel().getColumn(5).setCellRenderer(new ImageIconRenderer());
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "This Product doesn't exist", "Warning", 2);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        model = (DefaultTableModel) jTable1.getModel();
        rowIndex = jTable1.getSelectedRow();
        jTextField3.setText(model.getValueAt(rowIndex, 0).toString());
        jTextField6.setText(model.getValueAt(rowIndex, 1).toString());
        jComboBox1.setSelectedItem(model.getValueAt(rowIndex, 2).toString());
        jTextField4.setText(model.getValueAt(rowIndex, 3).toString());
        jTextField5.setText(model.getValueAt(rowIndex, 4).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (isEmpty()) {
            int ProductID = Integer.parseInt(jTextField3.getText());
            String ProductName = jTextField6.getText();
            String Category = jComboBox1.getSelectedItem().toString();
            int Quantity = Integer.parseInt(jTextField4.getText());
            File f = file;
            if (isNumeric(jTextField5.getText())) {
                double Price = Double.parseDouble(jTextField5.getText());
                if (product.isProductIDExist(ProductID)) {
                    if (!check()) {
                        if (file == null) {
                            product.updateWithoutImage(ProductID, ProductName, Category, Quantity, Price);
                            jTable1.setModel(new DefaultTableModel(null, new Object[]{"ProductID", "ProductName", "Category", "Quantity", "Price", "Image"}));
                            product.getProductData(jTable1, "", a);
                            jTable1.getColumnModel().getColumn(5).setCellRenderer(new ImageIconRenderer());
                            clear();
                        } else {
                            try {
                                product.update(ProductID, ProductName, Category, Quantity, Price, f);
                                jTable1.setModel(new DefaultTableModel(null, new Object[]{"ProductID", "ProductName", "Category", "Quantity", "Price", "Image"}));
                                product.getProductData(jTable1, "", a);
                                jTable1.getColumnModel().getColumn(5).setCellRenderer(new ImageIconRenderer());
                                clear();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Manage_Products.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This ProductID is already exists", "Warning", 2);
                }
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private boolean check() {
        String newProduct = jTextField6.getText();
        String oldProduct = model.getValueAt(rowIndex, 1).toString();
        String newCategory = jComboBox1.getSelectedItem().toString();
        String oldCategory = model.getValueAt(rowIndex, 2).toString();

        if (newProduct.equals(oldProduct) && newCategory.equals(oldCategory)) {
            return false;
        } else {

            boolean x = product.isProductCategoryNameExist(newProduct, newCategory);
            if (x) {
                JOptionPane.showMessageDialog(this, "This Product Name and Category already exists", "Warning", 2);
                return x;
            }
        }
        return false;
    }

    private boolean isNumeric(String s) {
        try {
            double d = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "" + ex + "\nNumeric value is requird for the price field");
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manage_Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Products().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
