/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author CYH
 */
public class GUIHelper {

    public GUIHelper() {
    }

    public void showMessageBox(String message) {
        final JFrame parent = new JFrame();
        parent.setVisible(true);
        JOptionPane.showMessageDialog(parent, message, "Pop Up ", 1);
        parent.setVisible(false);
    }

    public void showWarningBox(String message) {
        final JFrame parent = new JFrame();
        parent.setVisible(true);
        JOptionPane.showMessageDialog(parent, message, "Pop Up ", 0);
        parent.setVisible(false);
    }


}
