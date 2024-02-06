package model;

import java.util.*;

// Represents a cart having a list of products
public class Cart {
    private ArrayList<Product> listOfProduct;

    Cart() {
        listOfProduct = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the product to the cart
    public void addProduct(Product product) {
        listOfProduct.add(product);
    }

    // MODIFIES: this
    // EFFECTS: removes the product from the cart
    public void removeProduct(Product product) {
        listOfProduct.remove(product);
    }

    // I THINK THIS METHOD IS NOT REQUIRED HERE!!!
    // REQUIRES: product must exist
    // MODIFIES: this
    // EFFECTS: view the product
//    public Product viewProduct(Product product) {
//        for (Product ithProduct : listOfProduct) {
//            if (product.equals(ithProduct)) {
//                return ithProduct;
//            } else {
//                return null;
//            }
//        }
//        return product;
//    }
}
