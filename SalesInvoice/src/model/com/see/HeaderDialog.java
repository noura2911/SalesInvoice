/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.com.see;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
 


public class HeaderDialog extends JDialog {
    private JTextField NameField;
    private JTextField DateField;
    private JLabel NameLbl;
    private JLabel DateLbl;
    private JButton okBtn;
    private JButton cancelBtn;

    public HeaderDialog(InvoiceFrame frame) {
        NameLbl = new JLabel("Customer Name:");
        NameField = new JTextField(20);
        DateLbl = new JLabel("Invoice Date:");
        DateField = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("newInvoiceOK");
        cancelBtn.setActionCommand("newInvoiceCancel");
        
        okBtn.addActionListener(frame.getActionListener());
        cancelBtn.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(3, 2));
        
        add(DateLbl);
        add(DateField);
        add(NameLbl);
        add(NameField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }

    public JTextField getNameField() {
        return NameField;
    }

    public JTextField getDateField() {
        return DateField;
    }
    
}
    

