package Model;

public class Color {
    private int id;
    private String colorName;

    public Color(int id, String colorName) {
        this.id = id;
        this.colorName = colorName;
    }

    public int getId() {
        return id;
    }

    public String getColorName() {
        return colorName;
    }

    @Override
    public String toString() {
        return "Color: " + colorName + ",";
    }
}
