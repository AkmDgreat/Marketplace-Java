package model;

import java.util.ArrayList;

// Represents a buyer having a list of bought products
public class Buyer {
    private ArrayList<Product> orderList;

    // EFFECTS: creates an empty orderList
    public Buyer() {
        orderList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the product to the orderList
    public void buyProduct(Product product) {
        orderList.add(product);
        product.incrementNumProductSold();
    }

    // EFFECTS: returns the orderList
    public ArrayList<Product> getBoughtProducts() {
        return this.orderList;
    }
}
