package ui;

import Model.Customer;

public class PrintHandler implements PrintListener{

    @Override
    public void printOptions() {
        System.out.println("""
                Please choose an option:
                -----------------
                (0) Exit
                (1) Print all shoes in stock
                (2) Add to cart
                (3) Print list of all orders
                (4) Print a specific invoice
                (5) Rate a shoe
                (6) Get the average rate and comment(s) of a specific shoe
                """);
    }

    @Override
    public void printGreetings(Customer customer) {
        System.out.println("Hey " + customer.getFirstName() +
                "\n-----------------------\n" +
                "Welcome to our store" +
                "\n-----------------------");
    }

}
