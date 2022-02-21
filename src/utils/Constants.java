package utils;

//We have all queries here to facilitate their use in the Repository class

public class Constants {

    public static final String QUERY_LIST_SPECIFIC_ORDER = "SELECT * FROM customerorderwithinvoicenumber ci " +
            "INNER JOIN orderDetail o ON o.customerOrder_id = ci.id " +
            "INNER JOIN shoeDetail sd ON o.shoeDetail_id = sd.id " +
            "INNER JOIN color c ON sd.color_id = c.id " +
            "INNER JOIN sizeDetail si ON sd.sizeDetail_id = si.id " +
            "INNER JOIN currency cu ON sd.currency_id = cu.id " +
            "INNER JOIN shoe s ON sd.shoe_id = s.id " +
            "INNER JOIN brand b ON s.brand_id = b.id " +
            "WHERE ci.customer_id = ? AND ci.invoiceNumber = ?;";

    public static final String QQUERY_LIST_ALL_ORDER = "SELECT * FROM customerorderwithinvoicenumber ci " +
            "INNER JOIN orderDetail o ON o.customerOrder_id = ci.id " +
            "INNER JOIN shoeDetail sd ON o.shoeDetail_id = sd.id " +
            "INNER JOIN color c ON sd.color_id = c.id " +
            "INNER JOIN sizeDetail si ON sd.sizeDetail_id = si.id " +
            "INNER JOIN currency cu ON sd.currency_id = cu.id " +
            "INNER JOIN shoe s ON sd.shoe_id = s.id " +
            "INNER JOIN brand b ON s.brand_id = b.id " +
            "WHERE ci.customer_id = ?;";

    public static final String QUERY_ALL_SHOES_IN_STOCK = "SELECT" +
            "    *" +
            "FROM shoeDetail shd" +
            "         INNER JOIN color co ON shd.color_id = co.id" +
            "         INNER JOIN currency cu ON shd.currency_id = cu.id" +
            "         INNER JOIN sizeDetail sd ON shd.sizeDetail_id = sd.id" +
            "         INNER JOIN shoe s ON shd.shoe_id = s.id" +
            "         INNER JOIN brand b ON s.brand_id = b.id/*" +
            "         INNER JOIN shoeCategory sc ON sc.shoe_id = s.id" +
            "         INNER JOIN category c ON sc.category_id = c.id*/;";

    public static final String QUERY_ADD_TO_CART = "CALL addToCart(?,?,?,?)";

   /* public static final String QUERY_CUSTOMERORDER_WITH_INVOICE_NUMBER = "SELECT * FROM customerorderwithinvoicenumber " +
            "WHERE Date(datetime) = current_date AND customer_id = ? ORDER BY datetime DESC LIMIT 1;";*/

    public static final String QUERY_CREATE_LEVERANSADDRESS = "SELECT * FROM customerOrder co " +
            "INNER JOIN leveransAdress l ON co.leveransAdress_id = l.id" +
            " WHERE customer_id = ? LIMIT 1;";

    public static final String QUERY_SHOEDETAIL_ID =  "SELECT" +
            "    *" +
            "FROM shoeDetail shd" +
            "         INNER JOIN color co ON shd.color_id = co.id" +
            "         INNER JOIN currency cu ON shd.currency_id = cu.id" +
            "         INNER JOIN sizeDetail sd ON shd.sizeDetail_id = sd.id" +
            "         INNER JOIN shoe s ON shd.shoe_id = s.id" +
            "         INNER JOIN brand b ON s.brand_id = b.id" +
            " WHERE modelName like ? AND colorName like ? AND sizeNumber like ?;";

    public static final String QUERY_LOGGED_IN_CUSTOMER =  "SELECT * FROM customer WHERE email like ? AND password LIKE ?;";

    public static final String QUERY_GET_CURRENT_CUSTOMER_ORDER = "SELECT * FROM shoe_store_db.customerorderwithinvoicenumber ci " +
            "INNER JOIN orderDetail o ON o.customerOrder_id = ci.id " +
            "INNER JOIN shoeDetail sd ON o.shoeDetail_id = sd.id " +
            "WHERE customer_id = ? AND shoeDetail_id = ?;";

    public static final String QUERY_GET_SHOE_TO_RATE = "SELECT * FROM shoeDetail sd " +
            "INNER JOIN shoe s ON sd.shoe_id = s.id " +
            "INNER JOIN orderDetail o ON o.shoeDetail_id = sd.id " +
            "INNER JOIN customerOrder c ON o.customerOrder_id = c.id " +
            "WHERE c.customer_id = ? AND s.modelName like ?;" ;

    public static final String QUERY_GET_SHOE_COMMENT = "SELECT * FROM shoeComment sc " +
            "INNER JOIN customer c ON sc.customer_id = c.id " +
            "INNER JOIN shoe s ON sc.shoe_id = s.id " +
            "INNER JOIN brand b ON s.brand_id = b.id " +
            "WHERE s.id = ?;";
}
