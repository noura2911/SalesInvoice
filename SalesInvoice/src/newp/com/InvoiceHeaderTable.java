/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newp.com;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.com.see.InvoiceFrame;

/**
 *
 * @author HP
 */
public class InvoiceHeaderTable  extends AbstractTableModel{
  
    private ArrayList<InvoiceHeader>invoiceArr;
    
    private String[] columns={"Invoice Number","Invoice Date","Customer Name","Invoice Totel"};
    

    public InvoiceHeaderTable(ArrayList<InvoiceHeader> invoiceArr) {
        this.invoiceArr = invoiceArr;
    }
    


    /**
     *
     * @return
     */
    @Override
    public int getRowCount() {
      
        return invoiceArr.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       InvoiceHeader inv=invoiceArr.get(rowIndex);
      switch (columnIndex){
          case 0:return inv.getNumb();     
          case 1:return InvoiceFrame.date.format (inv.getDate());
          case 2:return inv.getCustomer1();
          case 3:return inv.getInvoiceTotel();
        }     
      return"";
    }
    
     @Override
    public String getColumnName(int column) {
        return columns[column];
    }    
        
   
        
     
}                  
      
           
 
    
    
    
    


    



   
        
         