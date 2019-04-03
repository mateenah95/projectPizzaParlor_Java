package a2;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private List<String> toppings;
    private String size;
    private String type;

    Pizza(List<String> toppings, String size, String type) {
        this.toppings = toppings;
        this.size = size;
        this.type = type;
    }

    List<String> getToppings() {

        return this.toppings;
    }

    String getSize() {
        return this.size;
    }

    String getType() {
        return this.type;
    }
}
