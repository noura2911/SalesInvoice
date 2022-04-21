/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.com.see.InvoiceFrame;
import newp.com.InvoiceHeader;
import newp.com.InvoiceLine;
import newp.com.invoiceLineTableModel;

/**
 *
 * @author HP
 */
public class TableSelcetionListener implements ListSelectionListener {

    private InvoiceFrame frame;

    public TableSelcetionListener(InvoiceFrame frame) {
        this.frame = frame;
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        int SelectedInvIndex = frame.gethTbl().getSelectedRow();
        System.out.println("Invoice Selected:" + SelectedInvIndex);
        if (SelectedInvIndex != -1) {
            InvoiceHeader selectedInv = frame.getInvoiceArr().get(SelectedInvIndex);
            ArrayList<InvoiceLine> lines = selectedInv.getLines();
            invoiceLineTableModel lineTableModel = new invoiceLineTableModel(lines);
            frame.setLineArray(lines);
            frame.getlTbl().setModel(lineTableModel);
            frame.getNameLbl().setText("" + selectedInv.getCustomer1());
            frame.getNumLbl().setText("" + selectedInv.getNumb());
            frame.getTotelLbl().setText("" + selectedInv.getInvoiceTotel());
            frame.getDateLbl().setText(selectedInv.getDate().toString());
        }
    }

}
