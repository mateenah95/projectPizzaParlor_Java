package a2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataAccessObject {
	private Map<String, List<String>> basePizzasToToppings;
	private Map<String, Integer> pizzaSizePrices;
	private Map<String, Integer> toppingsPrices;
	private List<String> drinks;


	DataAccessObject(){
		try{
			this.basePizzasToToppings = getBasePizzasFromFile("./data/basePizzas.dat");
			this.pizzaSizePrices = getItemPricesFromFile("./data/prices.dat");
			this.toppingsPrices = getItemPricesFromFile("./data/toppingsPrices.dat");
			this.drinks = getDrinksFromFile("./data/drinks.dat");

		}catch(Exception ex){
			System.out.println("Exception happened!");
		}
	}

	/* All IO functions that get information from files start here  */
	public List<String> getDrinksFromFile(String filepath) throws IOException{
		BufferedReader drinksFile = getBufferedReader(filepath);
		drinks = new ArrayList<String>();

		String line = drinksFile.readLine();
		while(line != null){
			drinks.add(line);
			line = drinksFile.readLine();
		}
		return drinks;
	}

	public BufferedReader getBufferedReader(String filepath){
		try{
		    File newFile = new File(filepath);
			FileReader fileReader = new FileReader(filepath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			return bufferedReader;


		}catch(IOException e){
			System.out.println("Some error occured. File(s) not found!");

		}
		catch(Exception e){
			System.out.println("Some error occured. Please try again later!");
		}

		return null;

	}

	public Map<String, List<String>> getBasePizzasFromFile(String filepath) throws IOException {
		BufferedReader basePizzaFile = getBufferedReader(filepath);
		Map<String, List<String>> basePizzas = new LinkedHashMap<String, List<String>>();

		String line = basePizzaFile.readLine();
	    while (line != null) {
	    	String lineSplit[] = line.split(":");
	    	String basePizzaType = lineSplit[0];
	    	String toppingsArray[] = lineSplit[1].split(",");

	    	List<String> toppingsList = new ArrayList<String>();
	    	for (int i = 0; i < toppingsArray.length; i++) {
	    		toppingsList.add(toppingsArray[i]);
			}

			basePizzas.put(basePizzaType, toppingsList);
	    	line = basePizzaFile.readLine();
		}
		
        	return basePizzas;
	}

	public Map<String, Integer> getItemPricesFromFile(String filepath) throws IOException {
		BufferedReader file = getBufferedReader(filepath);
		Map<String, Integer> itemPrices = new LinkedHashMap<String, Integer>();

		String line = file.readLine();
		while(line != null){
			String lineSplit[] = line.split(":");
			String item = lineSplit[0];
			Integer price = Integer.parseInt(lineSplit[1]);
			itemPrices.put(item, price);

			line = file.readLine();
		}


		return itemPrices;
	}

	/* IO functions end here */


    public List<String> getToppingsList() {
		List <String> pizzaToppingsList = new ArrayList<String>();

		for (Map.Entry<String, Integer> entry : toppingsPrices.entrySet()) {
			pizzaToppingsList.add(entry.getKey());
		}

        return pizzaToppingsList;
    }

    public List<String> getBasePizzasList() {
	    List<String> basePizzasList = new ArrayList<String>();

		for (Map.Entry<String, List<String>> entry : basePizzasToToppings.entrySet()) {
			basePizzasList.add(entry.getKey());
		}

		return basePizzasList;
	}

    public Map<String, Integer> getPizzaSizePricing(){
		return this.pizzaSizePrices;
	}

	public Map<String, Integer> getToppingsPriceMaps(){
		return this.toppingsPrices;
	}

	public double getPriceByTopping(String myTopping){
		return toppingsPrices.get(myTopping);
	}

	public double getPriceByPizzaSize(String pizzaSize) {
        return pizzaSizePrices.get(pizzaSize);
	}

	public List<String> getToppingsByPizzaType(String pizzaType){
	    return basePizzasToToppings.get(pizzaType);
    }

	public List<String> getDrinksList(){
		return this.drinks;
	}

	public List<String> getPizzaSizes() {
		List<String> pizzaSizeList = new ArrayList<String>();

		for (Map.Entry<String, Integer> entry : pizzaSizePrices.entrySet()) {
			pizzaSizeList.add(entry.getKey());
		}

		return pizzaSizeList;
	}

}
