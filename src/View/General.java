package View;

import Model.Customer;

// We use this class just to check if a customer is logged in or not
// We call the methods of this class from Repository and Controller

public class General {
    public static Customer loggedInCustomer = null;
    //public static CustomerOrder customerOrderWithInvoiceNumber = null;

    public static boolean isCustomerLoggedIn(Customer loggedInCustomer) {
        return loggedInCustomer != null;
    }

    public static void setLoggedInCustomer(Customer loggedInCustomer) {
        General.loggedInCustomer = loggedInCustomer;
    }

}
