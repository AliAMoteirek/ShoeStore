package UI;

import Model.Customer;

import java.util.Scanner;

public interface PrintListener {

    void printOptions();

    void printGreetings(Customer customer);

    void printErrorNumber();

    void addingToCart();

    String readShoeModel(Scanner scan);

    String readShoeModelForGettingComment(Scanner scan);

    String readColor(Scanner scan);

    String readSize(Scanner scan);

    String rateShoeModel(Scanner scan);

    void rateShoeMessage();

    void rateShoeInputError();

    String readComment(Scanner scan);

    void printErrorForPrintingInvoiceWithNoOrder();

    void printErrorCanNotReviewWithoutOrder();

    void printingAverageRateOfASpecificShoe(String shoeModel, double aSpecificShoeAverageRating);

    void printingNoCommentFound();
}
