package Model;

public class Currency {
    private int id;
    private String currencyCode;

    public Currency(int id, String currencyCode) {
        this.id = id;
        this.currencyCode = currencyCode;
    }

    public int getId() {
        return id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String toString() {
        return " " + currencyCode + ".";
    }
}
