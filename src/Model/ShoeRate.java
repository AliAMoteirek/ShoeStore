package Model;

public class ShoeRate {
    private int id;
    private RateType rateType;
    private Shoe shoe;
    private Customer customer;

    public ShoeRate(int id,
                    RateType rateType,
                    Shoe shoe,
                    Customer customer) {
        this.id = id;
        this.rateType = rateType;
        this.shoe = shoe;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public RateType getRateType() {
        return rateType;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "ShoeRate{" +
                "Rate Type=" + rateType +
                ", shoeList=" + shoe +
                ", customerList=" + customer +
                '}';
    }
}
