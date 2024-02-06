package model;

import java.util.ArrayList;

public class Buyer {
    private ArrayList<Product> orderList;

    public Buyer() {
        orderList = new ArrayList<>();
    }

    public void buyProduct(Product product) {
        orderList.add(product);
    }

    public ArrayList<Product> getBoughtProducts() {
        return this.orderList;
    }

    // REQUIRES: product must be bought
    public void rateProduct(int rating, Product product) {
        product.setProductRating(rating);
//        for (Product ithProduct: orderList) {
//            if (ithProduct.equals(product)) {
//                product.setProductRating(rating);
//            }
//        }
    }








}
