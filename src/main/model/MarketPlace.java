package model;

import java.util.ArrayList;

public class MarketPlace {
    private ArrayList<Product> listOfProductsAvailable;

    public MarketPlace() {
        listOfProductsAvailable = new ArrayList<>();
    }

    public ArrayList<Product> getListOfProductsAvailable() {
        return this.listOfProductsAvailable;
    }

    public void addProductToMP(Product product) {
        listOfProductsAvailable.add(product);
    }

//    public boolean isEmpty() {
//        return listOfProductsAvailable.size() == 0;
//    }

//    public void buyProduct() {
//
//    }

//    public void viewProduct() {
//
//    }
}
