package model;

import java.awt.*;

// Represents a product having a product-ID, name, price, rating, and whether it's added to cart
// this is the product that user "sees" in the marketPlace
public class Product {
    private int productName;
    private int productPrice;
    private int productRating;
    private boolean addedToCart;
    private int productId;
    private static int counter = 0;
    // private Image productImage;

    // REQUIRES:
    // EFFECTS:
    Product(int productName, int productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productRating = 0;
        this.addedToCart = false;
        this.productId = counter;
        counter++;
    }

//    Product(int productRating, boolean addedToCart) {
//        this.productRating = -1;
//        this.addedToCart = false;
//    }

    // GETTERS:
    public int getProductName() {
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

    // REQUIRES: 1 <= productRating <= 5
    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    // MODIFIES: this
    // EFFECTS: adds the product to the cart
    public void addToCart() {
        this.addedToCart = true;
    }

}
