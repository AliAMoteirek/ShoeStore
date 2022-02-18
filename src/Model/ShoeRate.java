package Model;

public class ShoeRate {
    private int id;
    private RateType rateType;
    private Shoe shoeList;
    private Customer customerList;

    public ShoeRate(int id,
                    RateType rateType,
                    Shoe shoeList,
                    Customer customerList) {
        this.id = id;
        this.rateType = rateType;
        this.shoeList = shoeList;
        this.customerList = customerList;
    }

    public int getId() {
        return id;
    }

    public RateType getRateType() {
        return rateType;
    }

    public Shoe getShoeList() {
        return shoeList;
    }

    public Customer getCustomerList() {
        return customerList;
    }

    @Override
    public String toString() {
        return "ShoeRate{" +
                "Rate Type=" + rateType +
                ", shoeList=" + shoeList +
                ", customerList=" + customerList +
                '}';
    }
}
