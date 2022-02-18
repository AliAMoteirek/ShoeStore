package Model;


public class OrderDetail {
    private int id;
    private int quantity;
    private ShoeDetail shoeDetail;
    private CustomerOrder customerOrder;

    public OrderDetail(int id,
                       int quantity,
                       ShoeDetail shoeDetail,
                       CustomerOrder customerOrder) {
        this.id = id;
        this.quantity = quantity;
        this.shoeDetail = shoeDetail;
        this.customerOrder = customerOrder;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public ShoeDetail getShoeDetail() {
        return shoeDetail;
    }

    public CustomerOrder getCustomerOrderList() {
        return customerOrder;
    }

    @Override
    public String toString() {
        return "OrderDetail {Quantity = " + quantity + '}';
    }
}
