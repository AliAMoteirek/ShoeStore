package Model;

import java.util.Date;
import java.util.List;

public class CustomerOrder {
    private int id;
    private Date dateTime;
    private Customer customer;
    private LeveransAdress leveransAdress;
    private List<OrderDetail> orderDetailList;

    public CustomerOrder(int id,
                         Date dateTime,
                         Customer customer,
                         LeveransAdress leveransAdress) {
        this.id = id;
        this.dateTime = dateTime;
        this.customer = customer;
        this.leveransAdress = leveransAdress;
    }

    public CustomerOrder(int id,
                         Date dateTime,
                         Customer customer,
                         LeveransAdress leveransAdress,
                         List<OrderDetail> orderDetailList) {
        this.id = id;
        this.dateTime = dateTime;
        this.customer = customer;
        this.leveransAdress = leveransAdress;
        this.orderDetailList = orderDetailList;
    }

    public int getId() {
        return id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LeveransAdress getLeveransAdress() {
        return leveransAdress;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "Date=" + dateTime +
                ", Customer=" + customer +
                ", Address=" + leveransAdress +'}';
    }
}
