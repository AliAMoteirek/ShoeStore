package Model;

import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private List<CustomerOrder> customerOrderList;            //There is customerOrderList because of 1:N relation

    public Customer(int id,
                    String firstName,
                    String lastName,
                    String phoneNumber,
                    String email,
                    String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public Customer(int id,
                    String firstName,
                    String lastName,
                    String phoneNumber,
                    String email,
                    String password,
                    List<CustomerOrder> customerOrderList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.customerOrderList = customerOrderList;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    @Override
    public String toString() {
        return "Customer{" +"First name='" + firstName + '\'' +
                ", Last name='" + lastName + '\'' +
                ", Phone number='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +'}';
    }
}
