package model;

import java.util.*;

// Represents a seller with the list of products listed in the marketplace
public class Seller {
    private HashSet<Product> productsListedByTheSeller;

    // EFFECTS: creates an empty productsListedByTheSeller
    public Seller() {
        productsListedByTheSeller = new HashSet<>();
    }

    public Product removeProduct(int productId) {
        for (Product product: this.productsListedByTheSeller) {
            if (productId == product.getProductId()) {
                this.productsListedByTheSeller.remove(product);
                return product;
            }
        }
        return null;
    }

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
