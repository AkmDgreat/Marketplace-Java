package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Random;

// Represents a product having a product-ID, name, price, and rating
public class Product implements Writable {
    private String productName;
    private int productPrice;
    private int productRating;
    private int numProductsSold;
    private int productId;
    //private static int counter = 0;


    // EFFECTS: creates a product with given name, price, 0 rating,
    // unique product ID and 0 number of buys
    public Product(String productName, int productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.numProductsSold = 0;
        this.productRating = 0;
        //incrementCounter();
        int randomNum = new Random().nextInt(100000);
        this.productId = randomNum;
    }

    // MODIFIES: this
    // EFFECTS: increments the counter by 1
//    public void incrementCounter() {
//        counter++;
//    }

    // MODIFIES: this
    // EFFECTS: adds 1 to number of products sold
    public void incrementNumProductSold() {
        this.numProductsSold++;
    }

    // REQUIRES: 1 <= productRating <= 5
    // MODIFIES: this
    // EFFECTS: sets the product rating
    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.productName);
        json.put("price", this.productPrice);
        json.put("rating", this.productRating);
        json.put("rating", this.numProductsSold);
        return json;
    }

    // GETTERS:
    public String getProductName() {
        return this.productName;
    }

    public int getProductPrice() {
        return this.productPrice;
    }

    public int getProductRating() {
        return this.productRating;
    }

    public int getProductId() {
        return productId;
    }

    public int getNumProductsSold() {
        return numProductsSold;
    }

    // the following two functions are for data persistence
    public void setNumProductsSold(int numProductsSold) {
        this.numProductsSold = numProductsSold;
    }

    public void setId(int productId) {
        this.productId = productId;
    }

//    public void setCounter(int value) {
//        counter = value;
//    }
}
