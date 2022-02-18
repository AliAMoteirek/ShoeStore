package Model;

public class RateType {
    private int id;
    private String rateType;
    private int rateByNumber;

    public RateType(int id, String rateType, int rateByNumber) {
        this.id = id;
        this.rateType = rateType;
        this.rateByNumber = rateByNumber;
    }

    public int getId() {
        return id;
    }

    public String getRateType() {
        return rateType;
    }

    public int getRateByNumber() {
        return rateByNumber;
    }

    @Override
    public String toString() {
        return "RateType{" + rateType + '\'' +
                ", rateByNumber=" + rateByNumber +
                '}';
    }
}
