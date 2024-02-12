package model;

import java.util.*;

// Represents a seller with the list of products listed in the marketplace
public class Seller {
    private HashSet<Product> productsListedByTheSeller;

    // EFFECTS: creates an empty productsListedByTheSeller
    public Seller() {
        productsListedByTheSeller = new HashSet<>();
    }

    // GETTERS:
    public HashSet<Product> getProductsListedByTheSeller() {
        return this.productsListedByTheSeller;
    }
}
