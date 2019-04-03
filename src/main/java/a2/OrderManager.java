package a2;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    //List of orders
    private List<Order> orderList;

    //Constructor - instantiates a new order List
    OrderManager(){
        this.orderList = new ArrayList<Order>();
    }

    //Adds order to the end of the orders list
    public void addOrder(Order orderToAdd){
        orderList.add(orderToAdd);
    }

    //Removes order by the order id
    public Order removeOrderByOrderId(int orderId){
        Order orderToRemove = getOrderById(orderId);
        if(orderToRemove != null) {
            orderList.remove(orderToRemove);
            return orderToRemove;
        }
        return null;
    }

    //Returns order by the order id
    public Order getOrderById(int orderId){
        for(int i = 0; i < orderList.size(); i++){
            if(orderList.get(i).getOrderId() == orderId) {
                return orderList.get(i);
            }
        }
        return null;
    }

    //Returns all orders - i.e. the full order list
    public List<Order> getAllOrders(){
        return this.orderList;
    }


}
