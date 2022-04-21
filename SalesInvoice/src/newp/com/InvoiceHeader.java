
package newp.com;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class InvoiceHeader {
    private int numb;
    private String Customer1;
    private Date Date;
    private ArrayList<InvoiceLine> lines;
    private DateFormat date=new SimpleDateFormat("dd-MM-yyyy");
    

    public InvoiceHeader() {
    }

    public InvoiceHeader(int numb, String Customer1, Date Date) {
        this.numb = numb;
        this.Customer1 = Customer1;
        this.Date = Date;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public String getCustomer1() {
        return Customer1;
    }

    public void setCustomer1(String Customer1) {
        this.Customer1 = Customer1;
    }

    public ArrayList<InvoiceLine> getLines() {
        if (lines==null){
            lines=new ArrayList<>();
            
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    public double  getInvoiceTotel(){
      double total = 0.0;
      
      for (int i=0; i<getLines().size();i++){
        total +=getLines().get(i).getLineTotel();
      }
      
      return total;
    }     

    @Override
    public String toString() {
        return  numb  + ","+date.format(Date) +","+ Customer1; 
    }
    
}
