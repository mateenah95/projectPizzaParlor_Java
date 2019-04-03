package a2;

import java.io.IOException;
import java.util.*;

public class PizzaParlour {
    static Scanner scanner = new Scanner(System.in);
    static DataAccessObject dao = new DataAccessObject();
    static PriceCalculator priceCalc = new PriceCalculator(dao);
    static InputValidator inputValidator = new InputValidator();
    static int mainMenuOptionsCount = 4;
    static OrderManager orderManager = new OrderManager();
    static CustomerManager customerManager = new CustomerManager();
    static DeliveryManager deliveryManager = new DeliveryManager();

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to 301 Pizza!: ");
        while(true){
            displayMainMenu();
            String orderType = readMainMenuSelection(mainMenuOptionsCount);

            if(orderType.equals("0"))
                break;

            if (orderType.equals("1")) {
                takeOrder();
            }
            else if (orderType.equals("2")) {
                updateOrder();
            }
            else if (orderType.equals("3")) {
                deleteOrder();
            }
            else if (orderType.equals("4")) {
                displayFullMenu();
            }

        }

        scanner.close();
    }

    public static void displayFullMenu() {
        List<String> basePizzas = dao.getBasePizzasList();
        List<String> toppingsList = dao.getToppingsList();
        List<String> drinksList = dao.getDrinksList();
        List<String> sizesList = dao.getPizzaSizes();


        System.out.println("Pizzas");
        System.out.println("--------------------");

        for (String size : sizesList) {
            System.out.println(size + ":\t\t$" + dao.getPriceByPizzaSize(size));
        }

        System.out.println("");

        for (String pizza : basePizzas) {
            System.out.println(pizza);
        }

        System.out.println("");
        System.out.println("Additional Toppings");
        System.out.println("--------------------");
        for (String topping : toppingsList) {
            System.out.println(topping + " :\t\t$" + dao.getPriceByTopping(topping));
        }


        System.out.println("");
        System.out.println("Drinks:\t\t$" + priceCalc.getSingleDrinkCost());
        System.out.println("--------------------");
        for (String drink : drinksList) {
            System.out.println(drink);
        }

        System.out.println("");

    }

    public static String readReceiptChoice() {
        System.out.println("Would you like to order in, or make a delivery order? ");
        System.out.println("1. Pick up");
        System.out.println("2. Delivery");

        return readSelection(2);

    }

    public static String readDeliveryChoice() {
        System.out.println("Please Choose a Delivery Option:");
        System.out.println("1. In-house");
        System.out.println("2. UberEats");
        System.out.println("3. Foodora");

        return readSelection(3);
    }

    public static void takeOrder () throws IOException{
        Order order = new Order();

        Pizza pizzaOrder = getPizzaOrder();
        List<String> drinksOrder = getDrinks();

        order.addPizzaOrder(pizzaOrder);
        order.addDrinksList(drinksOrder);

        System.out.println("Please enter your details");
        Customer customer = readCustomerDetails();

        order.addCustomer(customer);

        /* Get choice for receipt: pickup or delivery */
        String receiptChoice = readReceiptChoice();


        if (receiptChoice.equals("1")) {
            double totalCost = priceCalc.getTotalCost(order);
            order.setCost(totalCost);
            orderManager.addOrder(order);
            showOrderConfirmationPrompts(order);
        }

        else if (receiptChoice.equals("2")) {

            String deliveryChoice = readDeliveryChoice();
            order.setReceiptChoice(deliveryChoice);

            double totalCost = priceCalc.getTotalCost(order);
            order.setCost(totalCost);

            orderManager.addOrder(order);

            deliveryManager.prepareDelivery(order);
        }

        orderManager.addOrder(order);

        System.out.println(order.toString());
    }

    public static void showOrderConfirmationPrompts(Order order) {
        System.out.println("Your order has been placed!");
        System.out.println("Order Reference Number: " + order.getOrderId());
        System.out.println("Your total is " + order.getCost());
    }

    public static Customer readCustomerDetails() {
        String customerName = readCustomerName();
        String customerAddress = readCustomerAddress();
        String customerPhone = readCustomerPhone();

        Customer customer = Customer.createCustomer(customerName, customerAddress, customerPhone);

        return customer;
    }

    public static String readCustomerName(){
        System.out.println("Please enter your name (2-30 Characters):");
        String customerName = scanner.nextLine();
        boolean nameCheck = InputValidator.isNameValid(customerName);

        while(!nameCheck){
            System.out.println("Please enter your name (2-30 Characters):");
            customerName = scanner.nextLine();
            nameCheck = InputValidator.isNameValid(customerName);

        }

        return customerName;
    }

    public static String readCustomerAddress(){
        System.out.println("Please enter your address (10-30 Characters):");
        String customerAddress = scanner.nextLine();
        boolean addressCheck = InputValidator.isAddressValid(customerAddress);

        while(!addressCheck){
            System.out.println("Please enter your address (10-30 Characters):");
            customerAddress = scanner.nextLine();
            addressCheck = InputValidator.isAddressValid(customerAddress);

        }

        return customerAddress;
    }

    public static String readCustomerPhone(){
        System.out.println("Please enter your phone number (10 Characters):");
        String customerPhone = scanner.nextLine();
        boolean phoneCheck = InputValidator.isPhoneValid(customerPhone);

        while(!phoneCheck){
            System.out.println("Please enter your phone number (10 Characters):");
            customerPhone = scanner.nextLine();
            phoneCheck = InputValidator.isPhoneValid(customerPhone);

        }

        return customerPhone;
    }

    /* displays initial prompts that allow the user to
    choose whether to create or update an order
     */
    public static void displayMainMenu() {
        //System.out.println("Welcome to 301 Pizza!: ");
        System.out.println("-----------------------------------");
        System.out.println("Please select an option from the menu below or press 0 to exit");

        System.out.println("1. Create New Order");
        System.out.println("2. Update Existing Order");
        System.out.println("3. Remove Existing Order");
        System.out.println("4. See Full Menu");
    }


    public static String readSelection(int optionsCount) {
        String input;
        input = scanner.nextLine();

        // validation
        while (!inputValidator.isNumericAndWithinRange(input, optionsCount)) {
            System.out.println("Invalid input. Please try again");
            input = scanner.nextLine();
        }

        return input;
    }

    public static String readMainMenuSelection(int optionsCount) {
        String input;
        input = scanner.nextLine();

        // validation
        while (!inputValidator.isMenuNumericAndWithinRange(input, optionsCount)) {
            System.out.println("Invalid input. Please try again");
            input = scanner.nextLine();
        }

        return input;
    }

    /* general function for displaying menu items list of
    any type
     */
    public static void displayMenuItems(List<String> menuItems) {
        int menuItemIndex = 1;
        for (String item : menuItems) {
            System.out.println(menuItemIndex++ + ". " + item);
        }
    }

    public static String getBasePizzaChoice() throws IOException {
        List<String> basePizzasList = dao.getBasePizzasList();

        displayMenuItems(basePizzasList);

        String basePizzaChoiceInput = scanner.nextLine();
        String basePizzaChoice = basePizzasList.get(Integer.parseInt(basePizzaChoiceInput) - 1);

        return basePizzaChoice;
    }

    public static List<String> getAdditionalToppings() {
        List<String> toppingsChoiceList = new ArrayList<String>();
        List<String> toppingsMenuList = dao.getToppingsList();

        displayMenuItems(toppingsMenuList);

        String toppingChoiceInput;

        while(true) {
            toppingChoiceInput = scanner.nextLine();

            if (toppingChoiceInput.equals("0")) {
                break;
            }

            if (!inputValidator.isNumericAndWithinRange(toppingChoiceInput, toppingsMenuList.size())) {
                System.out.println("Invalid Input. Please try again");
            }
            else {
                toppingsChoiceList.add(toppingsMenuList.get(Integer.parseInt(toppingChoiceInput) - 1));
            }
        }

        return toppingsChoiceList;
    }

    public static Pizza getPizzaOrder()  throws IOException {
        List<String> toppingsChoice = new ArrayList<String>();

        // gets base pizza
        System.out.println("Please select your type of pizza");
        String basePizzaChoice = getBasePizzaChoice();

        // gets the toppings for the chosen base pizza, and adds it to the toppings list
        toppingsChoice.addAll(dao.getToppingsByPizzaType(basePizzaChoice));


        // get and add additional toppings to the toppings list
        System.out.println("Please select any additional toppings or press '0' to confirm!");
        toppingsChoice.addAll(getAdditionalToppings());


        String pizzaSizeChoice = getSize();

        // Create pizza object
        Pizza myPizza = new Pizza(toppingsChoice, pizzaSizeChoice, basePizzaChoice);

        return myPizza;
    }

    public static String getSize(){
        System.out.println("Please select the size of pizza:");

        List<String> pizzaSizesList = dao.getPizzaSizes();
        displayMenuItems(pizzaSizesList);

        String pizzaChoiceInput = readSelection(pizzaSizesList.size());

        return pizzaSizesList.get(Integer.parseInt(pizzaChoiceInput) - 1);
    }

    public static List<String> getDrinks(){
        List<String> drinksMenuList = dao.getDrinksList();
        List<String> drinksChoiceList = new ArrayList<String>();

        System.out.println("Please add drinks or enter 0 to confirm:");
        displayMenuItems(drinksMenuList);

        String drinksChoiceInput = "";


        while(true) {
            drinksChoiceInput = scanner.nextLine();

            if (drinksChoiceInput.equals("0")) {
                break;
            }

            if (!InputValidator.isNumericAndWithinRange(drinksChoiceInput, drinksMenuList.size())) {
                System.out.println("Invalid Input. Please try again");
            }
            else {
                drinksChoiceList.add(drinksMenuList.get(Integer.parseInt(drinksChoiceInput) - 1));
            }
        }

        return drinksChoiceList;
    }

    public static void updateOrder(){
        System.out.println("Please enter the order # for the order to update:");

        int orderId;
        String tempOrderId = scanner.nextLine();
        Order orderToUpdate;
        try{
            orderId = Integer.parseInt(tempOrderId);
            orderToUpdate = orderManager.getOrderById(orderId);
            System.out.println(orderToUpdate.toString());
            if(orderToUpdate == null){
                System.out.println("Sorry. No orders found with that order id.");
            }
            else{
                Pizza updatedPizza = getPizzaOrder();
                List<String> updatedDrinks = getDrinks();

                orderToUpdate.updatePizza(updatedPizza);
                orderToUpdate.updateDrinks(updatedDrinks);

                deliveryManager.prepareDelivery(orderToUpdate);

                System.out.println("Order updated successfully.");
            }
        }catch(Exception e){
            System.out.println("Sorry. Invalid order #. Please try again later.");
        }
    }

    public static void deleteOrder(){
        System.out.println("Please enter the order # for the order to remove:");

        int orderId;
        String tempOrderId = scanner.nextLine();

        try{
            orderId = Integer.parseInt(tempOrderId);
            Order orderToRemove = orderManager.getOrderById(orderId);
            if(orderToRemove == null){
                System.out.println("Sorry. No orders found with that order id.");

            }
            else{
                System.out.println(orderToRemove.toString());
                System.out.println("Are you sure you want to remove this order? Enter 1 to confirm or any other key to cancel.");
                String choice = scanner.nextLine();
                if(choice.equals("1")){
                    orderManager.removeOrderByOrderId(orderId);
                    System.out.println("Order Removed Successfully!");
                }
            }
        }catch(Exception e){
            System.out.println("Sorry. Invalid order #. Please try again later.");
        }
    }
}
