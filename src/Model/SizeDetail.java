package Model;

import java.math.BigDecimal;

public class SizeDetail {
    private int id;
    private BigDecimal sizeNumber;

    public SizeDetail(int id, BigDecimal sizeNumber) {
        this.id = id;
        this.sizeNumber = sizeNumber;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getSizeNumber() {
        return sizeNumber;
    }

    @Override
    public String toString() {
        return "Size: " + sizeNumber + ",";
    }
}
