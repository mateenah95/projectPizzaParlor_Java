package a2;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PizzaParlourTest {
    static DataAccessObject dao = new DataAccessObject();
    static PriceCalculator priceCalculator = new PriceCalculator(dao);

    @Test
    public void fakeTest() {
        assert(true);
    }

    @Test
    public void testFileReader() {
        BufferedReader basePizzaFile = dao.getBufferedReader("./data/basePizzas.dat");

        assert(basePizzaFile != null);
    }

    @Test
    public void testDaoGetBasePizzas() throws IOException {
        DataAccessObject dao = new DataAccessObject();
        List<String> basePizzasList = dao.getBasePizzasList();
        assert(basePizzasList.size() == 5);
    }

    @Test
    public void testDaoGetPizzaToppings() throws IOException{
        DataAccessObject dao = new DataAccessObject();
        List<String> pizzaToppings = dao.getToppingsList();
        assert(pizzaToppings.size() > 0);
    }

    @Test
    public void testGetSizePrices() throws IOException{
        DataAccessObject dao = new DataAccessObject();
        Map<String, Integer> itemPrices = dao.getItemPricesFromFile("./data/prices.dat");
        assert(itemPrices.size() > 0);

        Map<String, Integer> itemPricesExpected = new LinkedHashMap<String, Integer>();
        itemPricesExpected.put("small", 3);
        itemPricesExpected.put("medium", 5);
        itemPricesExpected.put("large", 7);
        itemPricesExpected.put("xl", 9);

        assert(itemPricesExpected.equals(itemPrices));
    }

//    @Test
//    public void testGetToppingsPrices() throws IOException{
//        DataAccessObject dao = new DataAccessObject();
//        Map<String, Integer> itemPrices = dao.getItemPricesFromFile("./data/toppings.dat");
//        assert(itemPrices.size() > 0);
//
//
//
//    }

    @Test
    public void testGetPriceBySizeAndTopping() {
        DataAccessObject dao = new DataAccessObject();
        assert(dao.getPriceByPizzaSize("small") == 3);
        assert(dao.getPriceByPizzaSize("xl") == 9);

        assert(dao.getPriceByTopping("mushroom") == 1);
        assert(dao.getPriceByTopping("ham") == 2);

    }


    /*** Price calculator tests **/
    @Test
    public void testGetPizzaCost() {
        List<String> mockToppings = new ArrayList<String>();
        String mockType = "";
        mockToppings.add("ham");
        mockToppings.add("pepperoni");
        String mockSize = "small";
        Pizza mockPizza = new Pizza(mockToppings, mockSize, mockType);

        assert(priceCalculator.getPizzaCost(mockPizza) == 7);

    }

}

