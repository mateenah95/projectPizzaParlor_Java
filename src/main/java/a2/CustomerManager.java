package a2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private ArrayList<Customer> myCustomers;

    CustomerManager(){
        this.myCustomers = new ArrayList<Customer>();
    }

    public Customer getCustomerById(int customerId){
        for(int i = 0; i < myCustomers.size(); i++){
            if(myCustomers.get(i).getId() == customerId)
                return myCustomers.get(i);
        }
        return null;
    }

    public Customer removeCustomerById(int customerId){
        Customer customerToRemove = getCustomerById(customerId);
        myCustomers.remove(customerToRemove);
        return customerToRemove;
    }

    public Customer createCustomer(String customerName, String customerAddress, String customerPhone){
        Customer newCustomer = Customer.createCustomer(customerName, customerAddress, customerPhone);
        myCustomers.add(newCustomer);
        return newCustomer;
    }
}
