package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a buyer having a list of bought products
public class Buyer implements Writable {
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
        EventLog.getInstance().logEvent(new Event(product.getProductName() +  " was bought"));
    }

    // EFFECTS: returns the orderList
    public ArrayList<Product> getBoughtProducts() {
        EventLog.getInstance().logEvent(new Event("showing buyer products"));

        return this.orderList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("buyer", buyerToJson());
        return json;
    }

    // EFFECTS: returns buyer in this marketPlace as a JSON array
    private JSONArray buyerToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product product : this.orderList) {
            jsonArray.put(product.toJson());
        }

        return jsonArray;
    }
}
