package a2;

import java.util.List;
import java.util.Map;

public class PriceCalculator {
    private DataAccessObject daoInstance;
    private final double drinksPrice = 1;

    PriceCalculator(DataAccessObject myDAO){
        this.daoInstance = myDAO;
    }

    public double getTotalCost(Order order){
        double totalCost = 0;
        totalCost += getPizzaCost(order.getPizzaOrder());
        totalCost += getDrinksCost(order.getDrinksOrder());
        totalCost += getReceiptCost(order.getReceiptOption());

        return totalCost;
    }


    public double getSingleDrinkCost() {
        return this.drinksPrice;
    }

    public double getReceiptCost(String receiptOption) {
        return 0;
    }

    public double getPizzaCost(Pizza pizza) {
        double pizzaCost = 0;

        String pizzaChoice = pizza.getSize();
        pizzaCost += daoInstance.getPriceByPizzaSize(pizzaChoice);

        List<String> myToppings = pizza.getToppings();

        for(int i = 0; i < myToppings.size(); i++){
            pizzaCost += daoInstance.getPriceByTopping(myToppings.get(i));
        }

        return pizzaCost;
    }

    public double getDrinksCost(List<String> drinkList){
        return drinkList.size() * drinksPrice;
    }
}
