package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a seller with the list of products listed in the marketplace
public class Seller implements Writable {
    private HashSet<Product> productsListedByTheSeller;
    // private String sellerName;

    // EFFECTS: creates an empty productsListedByTheSeller
    public Seller() {
        productsListedByTheSeller = new HashSet<>();
    }

    // MODIFIES: this
    // EFFECTS: removes the product from the seller list and returns it
    //          the function returns the product instead of void to increase testability
    public Product removeProduct(int productId) {
        for (Product product: this.productsListedByTheSeller) {
            if (productId == product.getProductId()) {
                this.productsListedByTheSeller.remove(product);
                EventLog.getInstance().logEvent(new Event(product.getProductName() + " was removed from marketplace"));
                return product;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: adds the product to the seller list
    public void addProductToSellerList(Product product) {
        this.productsListedByTheSeller.add(product);
        EventLog.getInstance().logEvent(new Event(product.getProductName() + " was added to the marketplace"));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("seller", sellerToJson());
        return json;
    }

    // EFFECTS: returns seller in this marketPlace as a JSON array
    private JSONArray sellerToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product product : this.productsListedByTheSeller) {
            jsonArray.put(product.toJson());
        }

        return jsonArray;
    }

    // GETTERS:
    public HashSet<Product> getProductsListedByTheSeller() {
        EventLog.getInstance().logEvent(new Event("displaying the products listed by the seller"));
        return this.productsListedByTheSeller;
    }

    public int getNumProductsListedByTheSeller() {
        return this.productsListedByTheSeller.size();
    }

}
