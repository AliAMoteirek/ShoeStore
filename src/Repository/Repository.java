package Repository;

import Model.*;
import Model.Currency;
import View.General;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import static Repository.Constants.*;

public class Repository {
    private String url;
    private String username;
    private String password;

    public Repository() {
        // We create a connection with the database by using properties.
        Properties p = new Properties();
        try (FileReader fileReader = new FileReader("Resources/Setting.properties")) {
            p.load(fileReader);
            url = p.getProperty("URLConnection");
            username = p.getProperty("username");
            password = p.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getASpecificShoeAverageRating(int shoeId) {
        double rating = 0.0;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                CallableStatement stmt = connection.prepareCall("{? = CALL shoeAverageRate(?)}")
        ) {
            stmt.registerOutParameter(1, Types.DOUBLE);
            stmt.setInt(2, shoeId);
            stmt.execute();
            rating = stmt.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rating;
    }

    public List<ShoeComment> getASpecificShoeComments(int shoeId) {
        List<ShoeComment> shoeCommentList = new ArrayList<>();
        ResultSet rs;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_SHOE_COMMENT)
        ) {
            preparedStatement.setInt(1, shoeId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                shoeCommentList.add(new ShoeComment(
                        rs.getInt("sc.id"),
                        rs.getString("commentary"),
                        new Shoe(rs.getInt("s.id"),
                                rs.getString("modelName"),
                                new Brand(rs.getInt("b.id"),
                                        rs.getString("brandName"))),
                        new Customer(rs.getInt("c.id"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("phoneNumber"),
                                rs.getString("email"),
                                rs.getString("password")),
                        rs.getTimestamp("sc.created")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoeCommentList;
    }

    public int getShoeToDoARate(int customerId,
                                String shoeName) {
        int i = 0;
        ResultSet rs;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_SHOE_TO_RATE)
        ) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setString(2, shoeName);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                i = rs.getInt("shoe_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public String writingARateAndComment(int customerId, int shoeId, int rateTypeId, String inCommentary) {
        ResultSet rs;
        String errormessage = "";

        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                CallableStatement stmt = connection.prepareCall("CALL rate(?,?," +
                        "(SELECT id FROM shoe_store_db.rateType r WHERE r.rateByNumber = " + rateTypeId + " ),?)")
        ) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, shoeId);
            stmt.setString(3, inCommentary);
            rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                errormessage = rs.getString("ERROR");
            }
            if (!errormessage.equals("")) {
                return errormessage;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Could not make a review";
        }
        return "Added a review";
    }


    public List<OrderDetail> listOfASpecificOrderedItem(int customerId, String invoiceNumber) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        ResultSet rs;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LIST_SPECIFIC_ORDER)
        ) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setString(2, invoiceNumber);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Currency currency = new Currency(
                        rs.getInt("currency_id"),
                        rs.getString("currencyCode"));
                Color color = new Color(
                        rs.getInt("color_id"),
                        rs.getString("colorName"));
                Brand brand = new Brand(
                        rs.getInt("brand_id"),
                        rs.getString("brandName"));
                SizeDetail sizeDetail = new SizeDetail(
                        rs.getInt("sizeDetail_id"),
                        rs.getBigDecimal("sizeNumber"));
                BigDecimal price = rs.getBigDecimal("price");
                int stock = rs.getInt("stock");
                int id = rs.getInt("sd.id");
                Shoe shoe = new Shoe(
                        rs.getInt("sd.shoe_id"),
                        rs.getString("modelName"), brand);
                orderDetailList.add(new OrderDetail(rs.getInt("o.id"),
                        rs.getInt("quantity"),
                        new ShoeDetail(id, shoe, currency, color, sizeDetail, price, stock),
                        new CustomerOrder(rs.getInt("ci.id"),
                                rs.getString("invoiceNumber"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetailList;
    }

    public List<OrderDetail> listOfAllOrderedItems(int customerId) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        ResultSet rs;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(QQUERY_LIST_ALL_ORDER)
        ) {
            preparedStatement.setInt(1, customerId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Currency currency = new Currency(
                        rs.getInt("currency_id"),
                        rs.getString("currencyCode"));
                SizeDetail sizeDetail = new SizeDetail(
                        rs.getInt("sizeDetail_id"),
                        rs.getBigDecimal("sizeNumber"));
                Color color = new Color(
                        rs.getInt("color_id"),
                        rs.getString("colorName"));
                Brand brand = new Brand(
                        rs.getInt("brand_id"),
                        rs.getString("brandName"));
                Shoe shoe = new Shoe(
                        rs.getInt("sd.shoe_id"),
                        rs.getString("modelName"), brand);
                BigDecimal price = rs.getBigDecimal("price");
                int stock = rs.getInt("stock");
                int id = rs.getInt("sd.id");
                orderDetailList.add(new OrderDetail(rs.getInt("o.id"),
                        rs.getInt("quantity"),
                        new ShoeDetail(id, shoe, currency, color, sizeDetail, price, stock),
                        new CustomerOrder(rs.getInt("ci.id"),
                                rs.getString("invoiceNumber"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetailList;
    }

    public String addToCart(int customerId, int customerOrderId, int shoeDetailId, int leveransAdressId) {
        ResultSet rs;
        String errormessage = "";

        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                CallableStatement stmt = connection.prepareCall(QUERY_ADD_TO_CART)
        ) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, customerOrderId);
            stmt.setInt(3, shoeDetailId);
            stmt.setInt(4, leveransAdressId);

            rs = stmt.executeQuery();

            while (rs != null && rs.next()) {
                errormessage = rs.getString("ERROR");
            }
            if (!errormessage.equals("")) {
                return errormessage;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Could not add to cart";
        }
        return "Added to database.";
    }

    public void getCurrentCustomerOrder(int customerId, int shoeDetailId) {
        ResultSet rs;
        CustomerOrder customerOrder = null;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_CURRENT_CUSTOMER_ORDER)
        ) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, shoeDetailId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                customerOrder = new CustomerOrder(
                        rs.getInt("id"),
                        rs.getString("invoiceNumber"));
            }
            General.setCustomerOrder(customerOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LeveransAdress createLeveransAddres(int customerId) {
        LeveransAdress leveransAdress = null;
        ResultSet rs;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE_LEVERANSADDRESS)
        ) {
            preparedStatement.setInt(1, customerId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                leveransAdress = new LeveransAdress(rs.getInt("l.id"),
                        rs.getString("adress"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leveransAdress;
    }

    public int getShoeDetailID(String modelName, String color, String size) {
        int i = 0;
        ResultSet rs;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SHOEDETAIL_ID)
        ) {
            preparedStatement.setString(1, modelName);
            preparedStatement.setString(2, color);
            preparedStatement.setString(3, size);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                i = rs.getInt("shd.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    // Prompt the user by printing all the products that are in stock, the user can choose one.
    public List<ShoeDetail> getAllShoesInStock() {
        List<ShoeDetail> shoeDetailList = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(QUERY_ALL_SHOES_IN_STOCK)
        ) {
            while (rs.next()) {
                Color color = new Color(
                        rs.getInt("color_id"),
                        rs.getString("colorName"));
                Currency currency = new Currency(
                        rs.getInt("currency_id"),
                        rs.getString("currencyCode"));
                SizeDetail sizeDetail = new SizeDetail(
                        rs.getInt("sizeDetail_id"),
                        rs.getBigDecimal("sizeNumber"));
                Brand brand = new Brand(
                        rs.getInt("brand_id"),
                        rs.getString("brandName"));
                Shoe shoe = new Shoe(
                        rs.getInt("shd.shoe_id"),
                        rs.getString("modelName"), brand);
                BigDecimal price = rs.getBigDecimal("price");
                int stock = rs.getInt("stock");
                int id = rs.getInt("shd.id");
                shoeDetailList.add(new ShoeDetail(id, shoe, currency, color, sizeDetail, price, stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoeDetailList;
    }

    public void loggingInCustomer(String username1, String password1) {
        Customer customer = null;
        ResultSet rs;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LOGGED_IN_CUSTOMER)
        ) {
            preparedStatement.setString(1, username1);
            preparedStatement.setString(2, password1);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                customer = new Customer(rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
            General.setLoggedInCustomer(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}