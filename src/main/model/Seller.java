package model;

import java.util.*;

public class Seller {
    private HashSet<Product> productsListedByTheSeller;

    public Seller() {
        productsListedByTheSeller = new HashSet<>();
    }

//    public void sell(Product product) {
//        listOfProductsSold.add(product);
//    }

    public HashSet<Product> getProductsListedByTheSeller() {
        return this.productsListedByTheSeller;
    }

}
