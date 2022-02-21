package Controller;

import Model.OrderDetail;
import Repository.Repository;
import Model.ShoeComment;
import Model.ShoeDetail;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

import static View.General.*;

public class Controller {

    Repository r = new Repository();
    Scanner scan = new Scanner(System.in);

    public void login() {
        while (!isCustomerLoggedIn(loggedInCustomer)) {
            String userNameValue = null;
            String passwordValue = null;
            UIManager.put("OptionPane.minimumSize", new Dimension(280, 280));
            JLabel jUserName = new JLabel("Username");
            JTextField userName = new JTextField();
            JLabel jPassword = new JLabel("Password");
            JTextField password1 = new JPasswordField();
            Object[] ob = {jUserName, userName, jPassword, password1};
            int result = JOptionPane.showConfirmDialog(null, ob, "Log in", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                userNameValue = userName.getText();
                passwordValue = password1.getText();
            } else {
                System.exit(0);
            }
            r.loggingInCustomer(userNameValue, passwordValue);
        }
    }

    public void printingAllShoesInStock() {
        int index = 0;
        for (ShoeDetail shoeDetail : r.getAllShoesInStock()) {
            System.out.print(++index + ". ");
            System.out.println(shoeDetail);
        }
    }

    private String readShoeModel() {
        System.out.println("\nWhich shoe model do you want to buy");
        return scan.nextLine().trim();
    }

    private String readShoeModelForGettingComment() {
        System.out.println("\nWhich shoe model do you want to see it's review");
        return scan.nextLine().trim();
    }

    private String readColor() {
        System.out.println("\nWhich color do you want to choose");
        return scan.nextLine().trim();
    }

    private String readSize() {
        System.out.println("\nWhich size do you want");
        return scan.nextLine().trim();
    }

    private String readInvoiceNumber() {
        System.out.println("\nWhich invoice number do you choose");
        return scan.nextLine().trim();
    }

    private String rateShoeModel() {
        System.out.println("\nWhich shoe model do you want to rate");
        return scan.nextLine().trim();
    }


    private int readRate() {
        while (true) {
            System.out.println("""
                    Please rate the shoe with a number between 1 and 5\s
                    (1 is the lowest rate and 5 is the hightest)""");
            try {
                int readNumber = Integer.parseInt(scan.nextLine());
                if (readNumber < 1 || readNumber > 5)
                    throw new RuntimeException();
                return readNumber;
            } catch (Exception e) {
                System.out.println("Input should be between 1 and 5.");
            }
        }
    }

    private String readComment() {
        System.out.println("\nPlease write a comment");
        return scan.nextLine().trim();
    }

    public void usingAddToCart() {
        System.out.println("Adding to cart...");
        String modelName = readShoeModel();
        String color = readColor();
        String size = readSize();

        if (!isCurrentCustomerInvoiceNumber()) {
            System.out.println(r.addToCart(loggedInCustomer.getId()
                    , 0,
                    r.getShoeDetailID(modelName, color, size),
                    r.createLeveransAddres(loggedInCustomer.getId()).getId()));
            r.getCurrentCustomerOrder(loggedInCustomer.getId(), r.getShoeDetailID(modelName, color, size));
        } else {
            //r.getCurrentCustomerOrder(loggedInCustomer.getId(), r.getShoeDetailID(modelName, color, size));
            System.out.println(r.addToCart(loggedInCustomer.getId(),
                    customerOrderWithInvoiceNumber.getId(),
                    r.getShoeDetailID(modelName, color, size),
                    r.createLeveransAddres(loggedInCustomer.getId()).getId()));
        }
    }

    public void printListOfAllOrderedItems() {
        for (int i = 0; i < r.listOfAllOrderedItems(loggedInCustomer.getId()).size(); i++) {
            r.listOfAllOrderedItems(loggedInCustomer.getId()).get(i).printAllOrderDetails();
        }
    }

    public void printListOfASelectedInvoice() {
        String invoiceNumber = readInvoiceNumber();
        List<OrderDetail> orderDetails = r.listOfASpecificOrderedItem(loggedInCustomer.getId(), invoiceNumber);
        if (orderDetails.isEmpty()) {
            System.out.println("You entered a wrong invoice number");
        }
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.printAllOrderDetails();
        }
    }

    public void ratingShoe() {
        printListOfAllOrderedItems();
        String shoeModel;
        int rate;
        String comment;
        String invoiceNumber = readInvoiceNumber();
        if (r.listOfASpecificOrderedItem(loggedInCustomer.getId(), invoiceNumber).isEmpty()) {
            System.out.println("You entered a wrong invoice number");
        } else {
            shoeModel = rateShoeModel();
            rate = readRate();
            comment = readComment();
            int shoeToDoARate = r.getShoeToDoARate(loggedInCustomer.getId(), shoeModel);
            System.out.println(r.writingARateAndComment(loggedInCustomer.getId(),
                    shoeToDoARate,
                    rate, comment));
        }
    }

    public void getASpecificShoeAverageRatingAndComments() {
        String shoeModel;
        shoeModel = readShoeModelForGettingComment();
        int shoeId = r.getShoeToDoARate(loggedInCustomer.getId(), shoeModel);
        System.out.println(shoeModel + " rating: " + r.getASpecificShoeAverageRating(shoeId));
        if (r.getASpecificShoeComments(shoeId).isEmpty()) {
            System.out.println("There are no comments for this shoe");
        } else {
            r.getASpecificShoeComments(shoeId).
                    forEach(ShoeComment::printComments);
        }
    }
}
