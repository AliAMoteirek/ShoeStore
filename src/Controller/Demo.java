package Controller;

import UI.PrintHandler;
import UI.PrintListener;

import java.util.Scanner;

import static View.General.loggedInCustomer;

public class Demo {
    Controller controller = new Controller();
    PrintListener printListener = new PrintHandler();

    public Demo() {
        controller.login();
        boolean dontExit = true;
        while (dontExit) {
            printListener.printGreetings(loggedInCustomer);
            printListener.printOptions();
            dontExit = chooseOption();
        }
    }

    private boolean chooseOption() {
        Scanner input = new Scanner(System.in);
        String tmpoption = input.nextLine();
        int option = -1;
        try {
            option = Integer.parseInt(tmpoption);

        } catch (Exception e) {
            System.out.println("Please enter a correct number");
        }

        if (option == 0) {
            return false;
        } else {
            onOptionSelected(option);
        }
        return true;
    }

    private void onOptionSelected(int option) {
        switch (option) {
            case 1 -> controller.printingAllShoesInStock();
            case 2 -> controller.usingAddToCart();
            case 3 -> controller.printListOfAllOrderedItems();
            case 4 -> controller.printListOfASelectedInvoice();
            case 5 -> controller.ratingShoe();
            case 6 -> controller.getASpecificShoeAverageRatingAndComments();
            default -> printListener.printOptions();
        }
    }
}
