package Model;

public class LeveransAdress {
    private int id;
    private String address;

    public LeveransAdress(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Address{" +address + '\'' + '}';
    }
}
