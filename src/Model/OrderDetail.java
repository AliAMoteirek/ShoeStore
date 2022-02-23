package Model;


import View.CurrentCustomerOrder;

public class OrderDetail {
    private int id;
    private int quantity;
    private ShoeDetail shoeDetail;
    private CustomerOrder customerOrder;
    private CurrentCustomerOrder currentCustomerOrder;

    public OrderDetail(int id, int quantity, ShoeDetail shoeDetail, CustomerOrder customerOrder) {
        this.id = id;
        this.quantity = quantity;
        this.shoeDetail = shoeDetail;
        this.customerOrder = customerOrder;
    }

    public OrderDetail(int id,
                       int quantity,
                       ShoeDetail shoeDetail,
                       CurrentCustomerOrder currentCustomerOrder) {
        this.id = id;
        this.quantity = quantity;
        this.shoeDetail = shoeDetail;
        this.currentCustomerOrder = currentCustomerOrder;
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

    public CurrentCustomerOrder getCurrentCustomerOrder() {
        return currentCustomerOrder;
    }

    public void printAllOrderDetails(){
        System.out.println("Invoice Number: " + currentCustomerOrder.getInvoiceNumber() + " - Date: " +
                currentCustomerOrder.getDateTime() + "\n-----------------------------------------------------------\n"
                + "Quantity: " + getQuantity() + " - Model Name: " + shoeDetail.getShoe().getModelName() + ", Color: " +
                shoeDetail.getColor().getColorName() + ", Size: " + shoeDetail.getSizeDetail().getSizeNumber() + ", Price: " +
                shoeDetail.getPrice() + "\n-----------------------------------------------------------\n");
    }

    @Override
    public String toString() {
        return "OrderDetail {Quantity = " + quantity + '}';
    }
}
