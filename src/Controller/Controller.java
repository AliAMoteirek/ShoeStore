package Controller;

import Model.OrderDetail;
import Repository.Repository;
import Model.ShoeComment;
import Model.ShoeDetail;
import UI.PrintHandler;
import UI.PrintListener;
import View.CurrentCustomerOrder;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

import static View.General.*;

public class Controller {

    private final Repository r = new Repository();
    private final Scanner scan = new Scanner(System.in);
    private final PrintListener printListener = new PrintHandler();
    CurrentCustomerOrder currentCustomerOrder = null;

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

    private int readRate() {
        while (true) {
            printListener.rateShoeMessage();
            try {
                int readNumber = Integer.parseInt(scan.nextLine());
                if (readNumber < 1 || readNumber > 5)
                    throw new RuntimeException();
                return readNumber;
            } catch (Exception e) {
                printListener.rateShoeInputError();
            }
        }
    }

    public void usingAddToCart() {
        printListener.addingToCart();
        String modelName = printListener.readShoeModel(scan);
        String color = printListener.readColor(scan);
        String size = printListener.readSize(scan);

        if(currentCustomerOrder == null){
            System.out.println(r.addToCart(loggedInCustomer.getId()
                    , 0,
                    r.getShoeDetailID(modelName, color, size),
                    r.createLeveransAddres(loggedInCustomer.getId()).getId()));
            currentCustomerOrder = r.getCurrentCustomerOrder(loggedInCustomer.getId(),
                    r.getShoeDetailID(modelName, color, size));
        }
        else{
            System.out.println(r.addToCart(loggedInCustomer.getId(),
                    currentCustomerOrder.id(),
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

        if (currentCustomerOrder == null) {
            printListener.printErrorForPrintingInvoiceWithNoOrder();
        }else {
            List<OrderDetail> orderDetails =
                    r.listOfASpecificOrderedItem(loggedInCustomer.getId(), currentCustomerOrder.invoiceNumber());
            for (OrderDetail orderDetail : orderDetails) {
                orderDetail.printAllOrderDetails();
            }
        }
    }

    public void ratingShoe() {
        printListOfASelectedInvoice();
        String shoeModel;
        int rate;
        String comment;
        if (currentCustomerOrder == null) {
            printListener.printErrorCanNotReviewWithoutOrder();
        } else {
            shoeModel = printListener.rateShoeModel(scan);
            rate = readRate();
            comment = printListener.readComment(scan);
            int shoeToDoARate = r.getShoeToDoARate(shoeModel);
            System.out.println(r.writingARateAndComment(loggedInCustomer.getId(),
                    shoeToDoARate,
                    rate, comment));
        }
    }

    public void getASpecificShoeAverageRatingAndComments() {
        String shoeModel;
        shoeModel = printListener.readShoeModelForGettingComment(scan);
        int shoeId = r.getShoeToDoARate(shoeModel);
        double aSpecificShoeAverageRating = r.getASpecificShoeAverageRating(shoeId);
        printListener.printingAverageRateOfASpecificShoe(shoeModel, aSpecificShoeAverageRating);
        if (r.getASpecificShoeComments(shoeId).isEmpty()) {
            printListener.printingNoCommentFound();
        } else {
            r.getASpecificShoeComments(shoeId).
                    forEach(ShoeComment::printComments);
        }
    }
}
