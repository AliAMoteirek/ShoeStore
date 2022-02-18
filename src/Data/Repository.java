package Data;

import Model.*;
import Model.Currency;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

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

    // Prompt the user by printing all the products that are in stock, the user can choose one.
    public List<ShoeDetail> getAllShoesInStock() {

        List<ShoeDetail> shoeDetailList = new ArrayList<>();
        String query = "SELECT" +
                "    *" +
                "FROM shoeDetail shd" +
                "         INNER JOIN color co ON shd.color_id = co.id" +
                "         INNER JOIN currency cu ON shd.currency_id = cu.id" +
                "         INNER JOIN sizeDetail sd ON shd.sizeDetail_id = sd.id" +
                "         INNER JOIN shoe s ON shd.shoe_id = s.id" +
                "         INNER JOIN brand b ON s.brand_id = b.id/*" +
                "         INNER JOIN shoeCategory sc ON sc.shoe_id = s.id" +
                "         INNER JOIN category c ON sc.category_id = c.id*/;";

        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(query)
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


    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String query = "SELECT * From customer;";
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(query)
        ) {
            while (rs.next()) {
                customerList.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public Customer findCustomer(String input, List<Customer> allCustomers) {
        for (Customer existingCustomer : allCustomers) {
            if (existingCustomer.getEmail().equalsIgnoreCase(input.trim()) ||
                    existingCustomer.getPassword().equals(input.trim())){
                return existingCustomer ;
            }
        }
        return null ;
    }


    public Map <String,String> getLoginInfo() {
        return getAllCustomers().stream().
                collect(Collectors.toMap(Customer::getEmail, Customer::getPassword));
    }

}
