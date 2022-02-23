package UI;

import Model.Customer;

import java.util.Scanner;

public class PrintHandler implements PrintListener {

    @Override
    public void printOptions() {
        System.out.println("""
                \nPlease choose an option:
                -----------------
                (0) Exit
                (1) Print all shoes in stock
                (2) Add to cart
                (3) Print the current invoice
                (4) Print list of all orders
                (5) Rate a shoe
                (6) Get the average rate and comment(s) of a specific shoe
                """);
    }

    @Override
    public void printGreetings(Customer customer) {
        System.out.println("\n\nHey " + customer.getFirstName() +
                "\n-----------------------\n" +
                "Welcome to our store" +
                "\n-----------------------");
    }

    @Override
    public void printErrorNumber() {
        System.out.println("Please enter a number between and 6");
    }

    @Override
    public void addingToCart(){
        System.out.println("Adding to cart...");
    }

    @Override
    public String readShoeModel(Scanner scan) {
        System.out.println("\nWhich shoe model do you want to buy");
        return scan.nextLine().trim();
    }

    @Override
    public String readShoeModelForGettingComment(Scanner scan) {
        System.out.println("\nWhich shoe model do you want to see it's review");
        return scan.nextLine().trim();
    }

    @Override
    public String readColor(Scanner scan) {
        System.out.println("\nWhich color do you want to choose");
        return scan.nextLine().trim();
    }

    @Override
    public String readSize(Scanner scan) {
        System.out.println("\nWhich size do you want");
        return scan.nextLine().trim();
    }

    @Override
    public String rateShoeModel(Scanner scan) {
        System.out.println("\nWhich shoe model do you want to rate");
        return scan.nextLine().trim();
    }

    @Override
    public void rateShoeMessage(){
        System.out.println("""
                    Please rate the shoe with a number between 1 and 5\s
                    (1 is the lowest rate and 5 is the hightest)""");
    }

    @Override
    public void rateShoeInputError(){
        System.out.println("Input should be between 1 and 5.");
    }

    @Override
    public String readComment(Scanner scan) {
        System.out.println("\nPlease write a comment");
        return scan.nextLine().trim();
    }

    @Override
    public void printErrorForPrintingInvoiceWithNoOrder(){
        System.out.println("You have not ordered anything yet");
    }

    @Override
    public void printErrorCanNotReviewWithoutOrder(){
        System.out.println("You need to do an order first inorder to do a review");
    }

    @Override
    public void printingAverageRateOfASpecificShoe(String shoeModel, double aSpecificShoeAverageRating){
        System.out.println(shoeModel + " rating: " + aSpecificShoeAverageRating);
    }

    @Override
    public void printingNoCommentFound(){
        System.out.println("There are no comments for this shoe");
    }

}
