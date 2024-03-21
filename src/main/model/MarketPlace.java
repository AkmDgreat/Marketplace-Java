package model;

import persistence.Writable;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

// Represents the marketplace with the list of listed products
public class MarketPlace implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("products", productsToJson());
        return json;
    }

    // EFFECTS: returns products in this marketPlace as a JSON array
    private JSONArray productsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product product : this.listOfProductsAvailable) {
            jsonArray.put(product.toJson());
        }

        return jsonArray;
    }

    // GETTERS:
    public ArrayList<Product> getListOfProductsAvailable() {
        return this.listOfProductsAvailable;
    }

}
