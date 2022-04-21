/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newp.com;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class invoiceLineTableModel extends AbstractTableModel  {
    private ArrayList<InvoiceLine>linesArray;
    
    private String[] columns={"Item Name","Unite Price","Count","Line Totel"};

    public invoiceLineTableModel(ArrayList<InvoiceLine> linesArray) {
        this.linesArray = linesArray;
    }

    @Override
    public int getRowCount() {
       return linesArray== null ? 0:linesArray.size(); 
        
    }

    @Override
    public int getColumnCount() {
        return columns.length;
       
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (linesArray==null){
        return "" ;
        }else{ 
            InvoiceLine line=linesArray.get(rowIndex);
            switch (columnIndex){
             case 0:
                return line.getItem();
             case 1:
                    return line.getRate();
             case 2:
                    return line.getCounter();
             case 3:
                    return line.getLineTotel();
             default: return"";
        }
    
    }
      
    }
    
    @Override
     
    public String getColumnName(int column) {
        return columns[column];
}
}
