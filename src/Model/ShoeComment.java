package Model;

public class ShoeComment {
    private int id;
    private String commentary;
    private Shoe shoe;
    private Customer customer;

    public ShoeComment(int id,
                       String commentary,
                       Shoe shoe,
                       Customer customer) {
        this.id = id;
        this.commentary = commentary;
        this.shoe = shoe;
        this.customer = customer;
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

    @Override
    public String toString() {
        return "ShoeComment{" +
                "Commentary='" + commentary + '\'' +
                ", shoe=" + shoe +
                ", customer=" + customer +
                '}';
    }
}
