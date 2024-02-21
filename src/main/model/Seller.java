package model;

import java.util.*;

// Represents a seller with the list of products listed in the marketplace
public class Seller {
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
                return product;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: adds the product to the seller list
    public void addProduct(Product product) {
        this.productsListedByTheSeller.add(product);
    }

    // GETTERS:
    public HashSet<Product> getProductsListedByTheSeller() {
        return this.productsListedByTheSeller;
    }

    public int getNumProductsListedByTheSeller() {
        return this.productsListedByTheSeller.size();
    }

}
