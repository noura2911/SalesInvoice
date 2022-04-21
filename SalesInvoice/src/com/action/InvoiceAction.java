
package com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;


import java.util.Date;

import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.com.see.InvoiceFrame;
import model.com.see.HeaderDialog;
import model.com.see.LineDialog;
import newp.com.InvoiceHeader;
import newp.com.InvoiceHeaderTable;
import newp.com.InvoiceLine;
import newp.com.invoiceLineTableModel;


public class InvoiceAction implements ActionListener {
  private InvoiceFrame frame ;
  private HeaderDialog headerDialog;
  private LineDialog lineDialog;
  
  public InvoiceAction(InvoiceFrame frame){
      this.frame=frame;
      
  }
  


    @Override
    public void actionPerformed(ActionEvent ae) {
      switch  (ae.getActionCommand()) {
          case "Save Files":
              saveFiles();
             break;
          case "Load Files":
              loadFiles ();
              break;
          case "New Invoice":
              creatInvoice();
              break;
          case "Delet Invoice":
              deletInvoice();
              break;
          case "New Line":
              newLine();
              break;
          case "Delet Line":
              deletLine();
              break;
          case "newInvoiceOK":
              newInvoiceDialogOk();
              break;
          case "newInvoiceCancel":   
              newInvoiceDialogCancel();
              break;
          case   "newLineCancel":
              newLineDialogCancel();
              break;
          case "newLineOK":
              newLineDialogOK();
              break;
         
          
      }
    }

    private void saveFiles() {
       ArrayList<InvoiceHeader>invoiceArr=frame.getInvoiceArr();
       JFileChooser fch=new JFileChooser();
        try {
         int result= fch.showSaveDialog(frame);
        if (result==JFileChooser.APPROVE_OPTION) {
            File hFile=fch.getSelectedFile();
            FileWriter fhw=new FileWriter(hFile);
            String headers="";
            String lines="";
            for(InvoiceHeader invoice: invoiceArr ){
                headers+=invoice.toString();
                headers+="\n";
                for(InvoiceLine line:invoice.getLines()){
                    lines+=line.toString();
                    lines+="\n";
                    
                }
            }
             headers = headers.substring(0,headers.length()-1);
             lines = lines.substring(0, lines.length()-1);
            
            result=fch.showSaveDialog(frame);
            File lineFile=fch.getSelectedFile();
            FileWriter flw=new FileWriter(lineFile);
            fhw.write(headers);
            flw.write(lines);
            fhw.close();
            flw.close();
        }   
        } catch (IOException e) {    
            JOptionPane.showMessageDialog(frame, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                  
                
            
        }
       
        
    }

    private void loadFiles() {
        JFileChooser fileChooser=new JFileChooser(); 
        try{
        int outcome=fileChooser.showOpenDialog(frame);
        if (outcome==JFileChooser.APPROVE_OPTION){
         File header= fileChooser.getSelectedFile();
        
        Path headerPath= Paths.get(header.getAbsolutePath());
        List<String>headerLines = Files.readAllLines(headerPath);
        
        ArrayList<InvoiceHeader>invoiceHeaders=new ArrayList<>();
        
        for(String headerLine:headerLines){
             String [] array=headerLine.split(",");
             String s1=array[0];
             String s2=array[1];
             String s3=array[2];
             int core =Integer.parseInt(s1);
             Date invDate=InvoiceFrame.date.parse(s2);
             InvoiceHeader head=new InvoiceHeader(core,s3,invDate);
             invoiceHeaders.add(head);
        }
            frame.setInvoiceArr(invoiceHeaders);
            outcome=fileChooser.showOpenDialog(frame);
            if (outcome==JFileChooser.APPROVE_OPTION){
                File lineF=fileChooser.getSelectedFile();
                Path lineP=Paths.get(lineF.getAbsolutePath());
                List<String>lineL=Files.readAllLines(lineP);
                ArrayList<InvoiceLine>invoiceline=new ArrayList<>();
                
                for(String liness:lineL){
                    String[] array= liness.split(",");
                    String s1=array[0];
                    String s2=array[1];
                    String s3=array[2];
                    String s4=array[3];
                    int code= Integer.parseInt(s1);
                    double Price= Double.parseDouble(s3);
                    int Count =Integer.parseInt(s4);
                   InvoiceHeader inv= frame.getobj(code);
                   InvoiceLine line=new InvoiceLine(s2,Price,Count,inv);
                   inv.getLines().add(line);
                    
                    
                }
                
            }
            InvoiceHeaderTable headerTable=new InvoiceHeaderTable(invoiceHeaders);
            frame.setHeaderTable(headerTable);
            frame.gethTbl().setModel(headerTable);
            System.out.println("files read");
            
       }
        
       } catch ( IOException exC){
           JOptionPane.showMessageDialog(frame, exC.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
       
           
       }catch(ParseException exC){
           JOptionPane.showMessageDialog(frame, exC.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
           
       }
       
    }

    private void creatInvoice() {
         headerDialog = new HeaderDialog(frame);
        headerDialog.setVisible(true);
          
        
    }

    private void deletInvoice() {
      int selectedInvoiceIndex = frame.gethTbl().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
         frame.getInvoiceArr().remove(selectedInvoiceIndex);
         frame.getHeaderTable().fireTableDataChanged();
         
         frame.getlTbl().setModel(new invoiceLineTableModel(null));   
        }      
      frame.setLineArray(null);
      frame.getNameLbl().setText("");
      frame.getNumLbl().setText("");
      frame.getTotelLbl().setText("");
      frame.getDateLbl().setText("") ;
      
        }

    private void newLine() {
        lineDialog = new LineDialog(frame);
        lineDialog.setVisible(true);
          
       
         
    }
    private void deletLine() {
        int selectedLineIndex=frame.getlTbl().getSelectedRow();
        int selectedInvoiceIndex=frame.gethTbl().getSelectedRow();
        if (selectedLineIndex!=-1) {
            frame.getLinesArray().remove(selectedLineIndex);
            invoiceLineTableModel lineTableModel=(invoiceLineTableModel) frame.getlTbl().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getTotelLbl().setText(""+frame.getInvoiceArr().get(selectedInvoiceIndex).getInvoiceTotel());
            frame.getHeaderTable().fireTableDataChanged();
            frame.gethTbl().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
           
        }
                
        
    }
     private void newInvoiceDialogCancel() {
       headerDialog.setVisible(false);
       headerDialog.dispose();
       headerDialog=null;
       
        
    }

    private void newInvoiceDialogOk() {
       headerDialog.setVisible(false);
       String CustomerName=headerDialog.getNameField().getText();
       String st=headerDialog.getDateField().getText();
       Date dd=new Date();
       try{
            dd=InvoiceFrame.date.parse(st) ;
       }catch(ParseException ex){
          JOptionPane.showMessageDialog(frame ,"parse date notallowed,resetting to Today.","Invaild Date" ,JOptionPane.ERROR_MESSAGE);
       
       }   
       int invNum=0;
       for(InvoiceHeader inv:frame.getInvoiceArr() ){
           if(inv.getNumb()>invNum)invNum=inv.getNumb();
           
       }
       invNum++;
       InvoiceHeader newInv=new InvoiceHeader(invNum,CustomerName,dd);
       frame.getInvoiceArr().add(newInv);
       frame.getHeaderTable().fireTableDataChanged();
       headerDialog.dispose();
       headerDialog=null;  
    

}

    private void newLineDialogCancel() {
       lineDialog.setVisible(false);
       lineDialog.dispose();
       lineDialog=null;
       
    }

    private void newLineDialogOK() {
       lineDialog.setVisible(false);
       String name = lineDialog.getItemnameField().getText();
       String st1=lineDialog.getItemCounterField().getText();
       String st2=lineDialog.getItemrateField().getText();
       int count=1;
       double price=1;
        try {
            count =Integer.parseInt(st1);
                    
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(frame,"Cannot convert number", "Invaild number",JOptionPane.ERROR_MESSAGE);
            
        }
        try {
            price=Double.parseDouble(st2);
                    
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(frame,"Cannot convert price", "Invaild number",JOptionPane.ERROR_MESSAGE);
            
        }
        int selectediHeader=frame.gethTbl().getSelectedRow();
        if (selectediHeader !=-1) {
            InvoiceHeader iHeader=frame.getInvoiceArr().get(selectediHeader);
           
                 
          InvoiceLine line=new  InvoiceLine(name, price, count, iHeader);
          
          frame.getLinesArray().add(line);
          invoiceLineTableModel lineTableModel=(invoiceLineTableModel) frame.getlTbl().getModel();
          lineTableModel.fireTableDataChanged();
          frame.getHeaderTable().fireTableDataChanged();
  
        }
        frame.gethTbl().setRowSelectionInterval(selectediHeader, selectediHeader);           
        lineDialog.dispose();
        lineDialog=null;
       
       
    }
}

        
    
        
        

