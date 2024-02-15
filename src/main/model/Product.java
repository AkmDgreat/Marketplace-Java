package model;

// Represents a product having a product-ID, name, price, and rating
public class Product {
    private String productName;
    private int productPrice;
    private int productRating;
    private int numProductsSold;
    private int productId;
    private static int counter = 0;


    // EFFECTS: creates a product with given name, price, 0 rating,
    // unique product ID and 0 number of buys
    public Product(String productName, int productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productRating = 0;
        incrementCounter();
        this.productId = counter;
    }

    // MODIFIES: this
    // EFFECTS: increments the counter by 1
    public void incrementCounter() {
        counter++;
    }

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
}
