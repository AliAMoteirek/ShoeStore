package Model;

import java.sql.Timestamp;

public class ShoeComment {
    private int id;
    private String commentary;
    private Shoe shoe;
    private Customer customer;
    private Timestamp datetime;

    public ShoeComment(int id,
                       String commentary,
                       Shoe shoe,
                       Customer customer,
                       Timestamp datetime) {
        this.id = id;
        this.commentary = commentary;
        this.shoe = shoe;
        this.customer = customer;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public String getCommentary() {
        return commentary;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void printComments() {
        System.out.println("Model Name: " + shoe.getModelName() + " - Brand: " + shoe.getBrands().getBrandName()
                + "\n------------------------------------------------------------------------\n" +
                "Comment: " + getCommentary() + " - commented by " + customer.getFirstName()
                + " on " + getDatetime() +  "\n");
    }

}
