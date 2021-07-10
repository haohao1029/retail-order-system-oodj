/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Admin;

import Helper.Connection;
import Model.Orders.Orders;
import Model.OrderItems.OrderItems;
import Model.Products.Products;
import Model.Session.Session;
import Model.User.Customers;
import Model.User.DeliveryStaff;
import Model.User.User;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ashwe
 */
public class CustomerProducts extends javax.swing.JFrame {

    DefaultTableModel model;
    private Double totalAmount = 0.0;
    private DecimalFormat df = new DecimalFormat("###.##");

    /**
     * Creates new form ManageDelivery
     */
    public CustomerProducts() {
        initComponents();
        this.initTable();
    }

    private void initTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        ArrayList<Products> Products = new Products().all();

        for (Products product : Products) {
            model.addRow(new Object[]{
                product.getID(),
                product.getName(),
                product.getPrice(),
                product.getBalance()
            });
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jTable1.setBackground(new java.awt.Color(0, 204, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "price", "item left"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jLabel9.setFont(new java.awt.Font("Georgia", 3, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("RETAIL ORDER SYSTEM");

        jLabel1.setFont(new java.awt.Font("Georgia", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total Amount:");

        jLabel3.setFont(new java.awt.Font("Georgia", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jLabel1)
                .addGap(70, 70, 70)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(24, 24, 24))
        );

        jLabel2.setFont(new java.awt.Font("Georgia", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PRODUCT");

        jTable2.setBackground(new java.awt.Color(0, 204, 204));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "price", "quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton2.setText("CHECKOUT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ADD PRODUCT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("DEDUCT PRODUCT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Georgia", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SHOPPING CART");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addGap(83, 83, 83)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 636, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(130, 130, 130)
                .addComponent(jButton4)
                .addGap(151, 151, 151)
                .addComponent(jButton2)
                .addGap(183, 183, 183))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(645, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(166, 166, 166)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(32, 32, 32))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(130, 130, 130)
                    .addComponent(jLabel4)
                    .addContainerGap(263, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    
        int cartRowCount = jTable1.getRowCount();
        int cartColumnCount = jTable1.getColumnCount();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        Customers customer= new Session().getCustomer();  
        Orders order = new Orders();

        if (cartRowCount == 0) {
            JOptionPane.showMessageDialog(null, "Please add product to shopping cart");
            return;
        }

                order.setTotalAmount(totalAmount);
                order.setCreatedAt(createdAt);
                order.setUpdateAt(updatedAt);
                order.setCustomer(customer);
                
                int orderID = order.create();
                System.out.println("order");
                int as = 4;

                String orderIDString = Integer.toString(orderID);
                order = new Orders().where("id", orderIDString);

        for (int i = 0; i < cartRowCount; i++) {
                
                OrderItems orderitem = new OrderItems();
                Products product = new Products().where("id", jTable2.getValueAt(i, 0).toString());
                String productName = product.getName();
                double productPrice = product.getPrice();
                int productBalance = product.getBalance();
                LocalDateTime productCreatedAt = product.getCreatedAt();
                LocalDateTime productUpdatedAt = LocalDateTime.now();
                int quantity = Integer.parseInt(jTable2.getValueAt(i, 3).toString());
                int finalBalance = productBalance - quantity;
                orderitem.setCreatedAt(createdAt);
                orderitem.setUpdateAt(updatedAt);
                orderitem.setProduct(product);
                orderitem.setQuantity(quantity);
                orderitem.setOrder(order);
                orderitem.create();
                
                product = new Products(
                        Integer.parseInt(jTable2.getValueAt(i, 0).toString()),
                        productName, 
                        productPrice, 
                        finalBalance, 
                        productCreatedAt, 
                        productUpdatedAt
                );
                product.update();
        }
        
                
      JOptionPane.showMessageDialog(null, "You Have Success Purchased!");
      for (int i = 0; i < cartRowCount; i ++) {
        DefaultTableModel orderModel = (DefaultTableModel) jTable2.getModel();
        orderModel.removeRow(i);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel productModel = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel orderModel = (DefaultTableModel) jTable2.getModel();
        if (jTable1.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Select an Product first!");
            return;
        }
            // get SelectedRow in Product Table
            int selectedRow = jTable1.getSelectedRow();
            deductQuantityInProductTable(jTable1, selectedRow);
            // get Id of SelectedRow
            String id = productModel.getValueAt(selectedRow, 0).toString();
            Products product = new Products().where("id", id);
            String name = product.getName();
            double price = product.getPrice();
            System.out.println(price);

            totalAmount = totalAmount + price;

            jLabel3.setText(df.format((totalAmount)).toString());
            int existsInTable = existsInTable(jTable2, name);
            if (existsInTable == -1) {
                Object[] row = {id, name, price, 1 };
                orderModel.addRow(row);  
            } else {
                int rowCount = existsInTable;
                int quantityInRowCart = Integer.parseInt(orderModel.getValueAt(rowCount, 3).toString());
                int newestQuantityInRowCart = quantityInRowCart + 1;
                Object newestQuantityInRow = (Integer) newestQuantityInRowCart;
                orderModel.setValueAt(newestQuantityInRow, rowCount, 3);
            }
            return;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DefaultTableModel productModel = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel orderModel = (DefaultTableModel) jTable2.getModel();
        if (jTable2.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Select an Cart Item first!");
            return;
        }
            // get SelectedRow in Cart Table
            int selectedRow = jTable2.getSelectedRow();
            deductQuantityInOrderTable(jTable2, selectedRow);
            // get Id of SelectedRow
            String id = productModel.getValueAt(selectedRow, 0).toString();
            Products product = new Products().where("id", id);
            Double price = product.getPrice();
            String name = product.getName();
            int existsInProductTable = existsInProductTable(jTable1, name);

            totalAmount = totalAmount - price;
            jLabel3.setText(df.format((totalAmount)).toString());

            if (existsInProductTable == -1) {
            } else {
                int rowCount = existsInProductTable;
                int quantityInRowCart = Integer.parseInt(productModel.getValueAt(rowCount, 3).toString());
                int newestQuantityInRowCart = quantityInRowCart + 1;
                Object newestQuantityInRow = (Integer) newestQuantityInRowCart;
                productModel.setValueAt(newestQuantityInRow, rowCount, 3);
            }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerProducts().setVisible(true);
            }
        });
    } 
    
public int existsInTable(JTable table, String entry) {

    // Get row and column count
    int rowCount = table.getRowCount();
    int colCount = 1;
        
    // Check against all entries
    for (int i = 0; i < rowCount; i++) {
        String rowEntry = "";
        rowEntry = table.getValueAt(i, colCount).toString();
         System.out.println(rowEntry);
         
        if (rowEntry.equalsIgnoreCase(entry)) {
            return i;
        }
    }
    return -1;
}
public int existsInProductTable(JTable table, String entry) {

    // Get row and column count
    int rowCount = table.getRowCount();
    int colCount = 1;
        
    // Check against all entries
    for (int i = 0; i < rowCount; i++) {
        String rowEntry = "";
        rowEntry = table.getValueAt(i, colCount).toString();
        if (rowEntry.equalsIgnoreCase(entry)) {
            return i;
        }
    }
    return -1;
}

public void deductQuantityInProductTable(JTable table,int row) {
     DefaultTableModel productModel = (DefaultTableModel) table.getModel();
     int quantityInRowCart = Integer.parseInt(productModel.getValueAt(row, 3).toString());
     int newestQuantityInRowCart = quantityInRowCart - 1;
     Object newestQuantityInRow = (Integer) newestQuantityInRowCart;
     productModel.setValueAt(newestQuantityInRow, row, 3);
}
public void deductQuantityInOrderTable(JTable table,int row) {
     DefaultTableModel orderModel = (DefaultTableModel) table.getModel();
     int quantityInRowCart = Integer.parseInt(orderModel.getValueAt(row, 3).toString());
     int newestQuantityInRowCart = quantityInRowCart - 1;

     if (newestQuantityInRowCart == 0) {
         orderModel.removeRow(row);
         return;
     }
     Object newestQuantityInRow = (Integer) newestQuantityInRowCart;
     orderModel.setValueAt(newestQuantityInRow, row, 3);
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
