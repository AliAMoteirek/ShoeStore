package Model;

import java.util.List;

public class Shoe {
    private int id;
    private String modelName;
    private Brand brands;
    private List<Category> categories;
    private List<ShoeDetail> shoeDetailList;

    public Shoe(int id, String modelName, Brand brands) {
        this.id = id;
        this.modelName = modelName;
        this.brands = brands;
    }

    public Shoe(int id,
                String modelName,
                Brand brands,
                List<Category> categories,
                List<ShoeDetail> shoeDetailList) {
        this.id = id;
        this.modelName = modelName;
        this.brands = brands;
        this.categories = categories;
        this.shoeDetailList = shoeDetailList;
    }

    public int getId() {
        return id;
    }

    public String getModelName() {
        return modelName;
    }

    public Brand getBrands() {
        return brands;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<ShoeDetail> getShoeDetailList() {
        return shoeDetailList;
    }

    public void setShoeDetailList(List<ShoeDetail> shoeDetailList) {
        this.shoeDetailList = shoeDetailList;
    }

    @Override
    public String toString() {
        return "Model: " + modelName + ", " + brands;
    }
}
