package model;

import java.util.ArrayList;

// Represents the marketplace with the list of listed products
public class MarketPlace {
    private ArrayList<Product> listOfProductsAvailable;

    // creates a new marketplace with no products listed
    public MarketPlace() {
        listOfProductsAvailable = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a product to the listOfProductsAvailable
    public void addProductToMP(Product product) {
        listOfProductsAvailable.add(product);
    }

    // GETTERS:
    public ArrayList<Product> getListOfProductsAvailable() {
        return this.listOfProductsAvailable;
    }

}
