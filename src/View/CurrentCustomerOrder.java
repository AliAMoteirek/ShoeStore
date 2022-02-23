package View;

import java.sql.Timestamp;

public record CurrentCustomerOrder(int id, String invoiceNumber, Timestamp dateTime) {

    /*
    public int getId() {
         return id;
     }

     public String getInvoiceNumber() {
         return invoiceNumber;
     }

     public Timestamp getDateTime() {
         return dateTime;
     }
    */
}
