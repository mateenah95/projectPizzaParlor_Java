package a2;

import jdk.internal.util.xml.impl.Input;

import java.util.List;

public class Customer {
    private static int customerCount = 0;
    private int customerId;
    private String name;
    private String address;
    private String phoneNumber;
    private List<String> orders;

    Customer() {

    }

    private Customer(String name, String address, String phoneNumber){
        this.customerId = customerCount++;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }

    public static Customer createCustomer(String cusName, String cusAddress, String cusPhone){
        if(InputValidator.isNameValid(cusName) && InputValidator.isAddressValid(cusAddress) && InputValidator.isPhoneValid(cusPhone)){
            Customer myCustomer = new Customer(cusName, cusAddress, cusPhone);
            return myCustomer;
        }
        else
            return null;
    }

    public int getId(){
        return customerId;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public List<String> getOrders(){
        return orders;
    }
}
