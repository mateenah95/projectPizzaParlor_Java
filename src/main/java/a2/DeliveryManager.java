package a2;

import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryManager {


    public void prepareDelivery(Order order) {
        String deliveryOption = order.getReceiptOption();

        if (deliveryOption.equals("1")) {
            prepareInHouseDelivery(order);
        }
        else if (deliveryOption.equals("2")) {
           prepareUberEatsDelivery(order);
        }
        else if (deliveryOption.equals("3")) {
            prepareFoodoraDelivery(order);
        }
    }

    public void prepareInHouseDelivery(Order order) {
        order.setCost(order.getCost() + 3);
        System.out.println("Thank you for choosing our delivery service");
        System.out.println("Please wait 30 minutes or your order is free!");
    }

    public JSONArray listToJSONArray(List<String> itemsList) {
        JSONArray itemsJSONArray = new JSONArray();

        for (String topping : itemsList) {
            itemsJSONArray.add(topping);
        }
        return itemsJSONArray;
    }

    public JSONObject orderToJSON(Order order) {
        JSONObject orderJSON = new JSONObject();

        Pizza pizza = order.getPizzaOrder();

        orderJSON.put("type", pizza.getType());
        orderJSON.put("drinks", listToJSONArray(order.getDrinksOrder()));
        orderJSON.put("toppings", listToJSONArray(pizza.getToppings()));
        orderJSON.put("cost", order.getCost());

        return orderJSON;
    }

    public void writeJSONToFile(JSONObject jsonObject, String filepath) {
        try {
            FileWriter uberEatsWriter = new FileWriter(filepath);

            uberEatsWriter.write(jsonObject.toJSONString());
            uberEatsWriter.flush();
            uberEatsWriter.close();

            System.out.println("Your order has been succesfully sent to UberEats");

        } catch (IOException e) {
            System.out.println("Could not write to uber eats file");
        }
    }

    public void prepareUberEatsDelivery(Order order) {
        Customer customer = order.getCustomer();

        JSONObject uberEatsJSON = new JSONObject();

        uberEatsJSON.put("orderNumber", order.getOrderId());
        uberEatsJSON.put("address", customer.getAddress());

        uberEatsJSON.put("orderDetails", orderToJSON(order));

        writeJSONToFile(uberEatsJSON, "./data/order.json");
    }

    public void prepareFoodoraDelivery(Order order) {
        Customer customer = order.getCustomer();
        List<String[]> orderData = new ArrayList<String[]>();
        orderData.add(new String[] { "orderId", Integer.toString(order.getOrderId()) });
        orderData.add(new String[] { "orderCost", Double.toString(order.getCost()) });
        orderData.add(new String[] {"address", customer.getAddress() });
        orderData.add(new String[] {"pizzaType", order.getPizzaOrder().getType()});
        orderData.add(listToStringArray(order.getPizzaOrder().getToppings(), "toppings"));
        orderData.add(listToStringArray(order.getDrinksOrder(), "drinks"));

        writeCSVToFile(orderData, "./data/order.csv");
    }

    /* Writes CSV to file.
      Adpated code from: https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/
     */
    private void writeCSVToFile(List<String[]> dataRows, String filepath) {
        try {
            FileWriter output = new FileWriter(filepath);
            CSVWriter csvWriter = new CSVWriter(output);

            csvWriter.writeAll(dataRows);

            csvWriter.close();

            System.out.println("Your order has been succesfully sent to Foodora");

        } catch (IOException e) {
            System.out.println("Could not write to Foodora output file");
        }

    }

    public String[] listToStringArray(List<String> itemList, String rowType) {
        String itemArray[] = new String[itemList.size() + 1];

        itemArray[0] = rowType;

        for (int i = 1; i <= itemList.size(); i++) {
            itemArray[i] = itemList.get(i-1);
        }

        return itemArray;
    }

}
