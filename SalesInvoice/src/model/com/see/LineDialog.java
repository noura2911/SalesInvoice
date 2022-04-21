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
 * @author DELL
 */
public class LineDialog extends JDialog{
    private JTextField itemnameField;
    private JTextField itemCountField;
    private JTextField itemPriceField;
    private JLabel itemnameLbl;
    private JLabel itemCountLbl;
    private JLabel itemPriceLbl;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public LineDialog(InvoiceFrame frame) {
        itemnameField = new JTextField(20);
        itemnameLbl = new JLabel("Item Name");
        
        itemCountField = new JTextField(20);
        itemCountLbl = new JLabel("Item Count");
        
        itemPriceField = new JTextField(20);
        itemPriceLbl = new JLabel("Item Price");
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("newLineOK");
        cancelBtn.setActionCommand("newLineCancel");
        
        okBtn.addActionListener(frame.getActionListener());
        cancelBtn.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(4, 2));
        
        add(itemnameLbl);
        add(itemnameField);
        add(itemCountLbl);
        add(itemCountField);
        add(itemPriceLbl);
        add(itemPriceField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemnameField() {
        return itemnameField;
    }

    public JTextField getItemCounterField() {
        return itemCountField;
    }

    public JTextField getItemrateField() {
        return itemPriceField;
    }
}

    
    

