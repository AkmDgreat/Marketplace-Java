package persistence;

import model.Buyer;
import model.MarketPlace;
import model.Product;
import model.Seller;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.util.Map;

// Represents a writer that writes JSON representation of marketplace to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // Map<String,>

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of marketplace to file
    public void write(MarketPlace mp, Seller sl,  Buyer by) {
        JSONObject completeJson = new JSONObject();

        completeJson.put("mpProducts", mpProductsToJsonArray(mp));
        completeJson.put("byProducts", buyerProductsToJsonArray(by));
        completeJson.put("slProducts", sellerProductsToJsonArray(sl));

        saveToFile(completeJson.toString());
    }

    // EFFECTS: converts marketplace to JSON
    private JSONArray mpProductsToJsonArray(MarketPlace mp) {
        JSONArray mpJsonArray = new JSONArray();

        for (Product product: mp.getListOfProductsAvailable()) {
            JSONObject mpProductJson = new JSONObject();
            mpProductJson.put("name", product.getProductName());
            mpProductJson.put("price", product.getProductPrice());
            mpProductJson.put("rating", product.getProductRating());
            //mpProductJson.put("id", product.getProductId());
            mpJsonArray.put(mpProductJson);
        }

        return mpJsonArray;
    }

    // EFFECTS: converts buyer to JSON
    private JSONArray buyerProductsToJsonArray(Buyer by) {
        JSONArray byJsonArray = new JSONArray();

        for (Product product: by.getBoughtProducts()) {
            JSONObject mpBuyerJson = new JSONObject();
            mpBuyerJson.put("name", product.getProductName());
            mpBuyerJson.put("price", product.getProductPrice());
            mpBuyerJson.put("rating", product.getProductRating());
            //mpBuyerJson.put("id", product.getProductId());
            byJsonArray.put(mpBuyerJson);
        }

        return byJsonArray;
    }

    // EFFECTS: converts buyer to JSON
    private JSONArray sellerProductsToJsonArray(Seller sl) {
        JSONArray byJsonArray = new JSONArray();

        for (Product product: sl.getProductsListedByTheSeller()) {
            JSONObject mpBuyerJson = new JSONObject();
            mpBuyerJson.put("name", product.getProductName());
            mpBuyerJson.put("price", product.getProductPrice());
            mpBuyerJson.put("rating", product.getProductRating());
            mpBuyerJson.put("buys", product.getNumProductsSold());
            mpBuyerJson.put("id", product.getProductId());
            byJsonArray.put(mpBuyerJson);
        }

        return byJsonArray;
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
