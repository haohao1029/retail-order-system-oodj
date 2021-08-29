/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail.order.system;

import GUI.Admin.CustomerProducts;
import GUI.Admin.ManageOrders;
import GUI.Admin.ManageProducts;
import GUI.Login;

/**
 *
 * @author GJH
 */
public class RETAILORDERSYSTEM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Login().setVisible(true);
        //new ManageProducts().setVisible(true);
        //new CustomerProducts().setVisible(true);
        new ManageOrders().setVisible(true);
    }
    
}
