package a2;

import java.util.List;

public class Order {
    static int orderCount = 0;

    private int orderId;
    private Customer customer;
    private Pizza pizza;
    private String receipt;
    private double cost;
    private List<String> drinks;

    Order () {
        orderId = orderCount++;
        this.cost = 0;
        this.receipt = "0";
    }

    /** Use builder methods to add attributes to Order object **/
    public void setCost(double cost) {
        this.cost = cost;
    }

    public void addCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setReceiptChoice(String receiptChoice) {
        this.receipt = receiptChoice;
    }

    public void addPizzaOrder(Pizza pizza) {
        this.pizza = pizza;
    }

    public void addDrinksList(List<String> drinks) {
        this.drinks = drinks;
    }

    /** getter methods **/

    public double getCost() {
        return this.cost;
    }

    public List<String> getDrinksOrder() {
        return this.drinks;
    }

    public Pizza getPizzaOrder() {
        return this.pizza;
    }

    public String getReceiptOption() {
        return this.receipt;
    }

    public Customer getCustomer() {
        return this.customer;
    }


    public int getOrderId(){
        return this.orderId;
    }

    public void updatePizza(Pizza pizzaToUpdate){
        this.pizza = pizzaToUpdate;
    }

    public void updateDrinks(List<String> drinksToUpdate){
        this.drinks = drinksToUpdate;
    }

    public String toString(){
        String orderDetails = "";

        System.out.println("-----------------------------------");
        orderDetails  = orderDetails + "Order ID: " + this.orderId + "\n";
        orderDetails  = orderDetails + "Customer Name: " + this.customer.getName() + "\n";
        orderDetails = orderDetails + "Pizza Type: " + this.pizza.getType() + "\n";
        orderDetails = orderDetails + "Pizza Size: " + this.pizza.getSize() + "\n";
        orderDetails = orderDetails + "Pizza Toppings: " + this.pizza.getToppings() + "\n";
        //orderDetails  = orderDetails + "Delivery: " + this.receipt + "\n";
        orderDetails = orderDetails + "Order Total: " + this.cost + "\n";
        orderDetails = orderDetails + "Drinks: " + this.drinks;

        return orderDetails;
    }

}
