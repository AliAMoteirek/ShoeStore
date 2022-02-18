package Model;

public class Brand {
    private int id;
    private String brandName;

    public Brand(int id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public int getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    @Override
    public String toString() {
        return "Brand: " + brandName + ",";
    }
}
