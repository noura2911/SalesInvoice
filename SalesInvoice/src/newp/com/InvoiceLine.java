
package newp.com;


public class InvoiceLine {
    
    private String item;
    private double rate;
    private int Counter;
    private InvoiceHeader head;
    public InvoiceLine() {
    }

    public InvoiceLine(String item, double rate, int counter, InvoiceHeader head) {
        this.item = item;
        this.rate= rate;
        this.Counter = counter;
        this.head = head;
    }

    public InvoiceHeader getHead() {
        return head;
    }

    public void setHead(InvoiceHeader head) {
        this.head = head;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double Rate) {
        this.rate = rate;
    }

    public int getCounter() {
        return Counter;
    }

    public void setCounter(int Counter) {
        this.Counter = Counter;
    }
    public double getLineTotel() {
        return rate *Counter;
    }

    @Override
    public String toString() {
        return head.getNumb()+","+item+","+rate+","+Counter;
    }
    
}
