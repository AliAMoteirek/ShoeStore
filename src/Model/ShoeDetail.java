package Model;

import java.math.BigDecimal;

import java.util.List;

public class ShoeDetail {
    private int id;
    private Shoe shoe;
    private Currency currency;
    private Color color;
    private SizeDetail sizeDetail;
    private List<OrderDetail> orderDetailList;
    private BigDecimal price;
    private int stock;

    public ShoeDetail(int id,
                      Shoe shoe,
                      Currency currency,
                      Color color,
                      SizeDetail sizeDetail,
                      BigDecimal price,
                      int stock) {
        this.id = id;
        this.shoe = shoe;
        this.currency = currency;
        this.color = color;
        this.sizeDetail = sizeDetail;
        this.price = price;
        this.stock = stock;
    }

    public ShoeDetail(int id,
                      Shoe shoe,
                      Currency currency,
                      Color color,
                      SizeDetail sizeDetail,
                      List<OrderDetail> orderDetailList,
                      BigDecimal price,
                      int stock) {
        this.id = id;
        this.shoe = shoe;
        this.currency = currency;
        this.color = color;
        this.sizeDetail = sizeDetail;
        this.orderDetailList = orderDetailList;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Color getColor() {
        return color;
    }

    public SizeDetail getSizeDetail() {
        return sizeDetail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    @Override
    public String toString() {
        return shoe + " " + currency + " " +color + " " + sizeDetail +
                ", Price =" + price +
                ", Stock =" + stock +
                '}';
    }
}
