package Model;

import java.util.List;

public class Category {
    private int id;
    private String categoryName;
    private List<Shoe> shoeList;            //There is shoeList because of 1:N relation

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Category(int id,
                    String categoryName,
                    List<Shoe> shoeList) {
        this.id = id;
        this.categoryName = categoryName;
        this.shoeList = shoeList;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Shoe> getShoeList() {
        return shoeList;
    }

    @Override
    public String toString() {
        return "Category{" +categoryName + '\'' +'}';
    }
}
