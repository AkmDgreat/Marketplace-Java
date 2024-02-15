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

    // MODIFIES: this
    // EFFECTS: removes a product from listOfProductsAvailable
    public void removeProduct(int productId) {
        for (Product product: this.listOfProductsAvailable) {
            if (product.getProductId() == productId) {
                listOfProductsAvailable.remove(product);
                return;
            }
        }
    }

    // EFFECTS: returns the product in the marketplace, and null if not found
    public Product getProduct(int productId) {
        for (Product product: this.listOfProductsAvailable) {
            if (productId == product.getProductId()) {
                return product;
            }
        }
        return null;
    }

    // GETTERS:
    public ArrayList<Product> getListOfProductsAvailable() {
        return this.listOfProductsAvailable;
    }

}
