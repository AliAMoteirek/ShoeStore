package Controller;

import UI.PrintHandler;
import UI.PrintListener;

import java.util.Scanner;

import static View.General.loggedInCustomer;

public class Demo {
    Controller controller = new Controller();

    public Demo() {
        controller.login();
        PrintListener printListener = new PrintHandler();
        printListener.printGreetings(loggedInCustomer);
        printListener.printOptions();
        chooseOption();
    }

    private void chooseOption() {
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        while (option != 0) {
            onOptionSelected(option);
            option = input.nextInt();
        }
    }

    private void onOptionSelected(int option) {
        switch (option) {
            case 1 -> controller.printingAllShoesInStock();
            case 2 -> controller.usingAddToCart();
            case 3 -> controller.printListOfAllOrderedItems();
            case 4 -> controller.printListOfASelectedInvoice();
            case 5 -> controller.ratingShoe();
            default -> controller.getASpecificShoeAverageRatingAndComments();
        }
    }
}
