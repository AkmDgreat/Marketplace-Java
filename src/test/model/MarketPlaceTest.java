package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarketPlaceTest {
    private MarketPlace marketPlace;
    private Product macbook;
    private Product iPhone;

    @BeforeEach
    void setup() {
        marketPlace = new MarketPlace();
        macbook = new Product("macbook air m2", 2000);
        iPhone = new Product("iphone 14", 1000);
    }

    @Test
    void constructorTest() {
        assertEquals(marketPlace.getListOfProductsAvailable().size(), 0);
    }

    @Test
    void testAddProduct() {
        marketPlace.addProductToMP(macbook);
        assertEquals(marketPlace.getListOfProductsAvailable().size(), 1);
        assertEquals(marketPlace.getListOfProductsAvailable().get(0), macbook);
    }

    @Test
    void testAddProductMultiple() {
        marketPlace.addProductToMP(macbook);
        assertEquals(marketPlace.getListOfProductsAvailable().size(), 1);
        assertEquals(marketPlace.getProduct(macbook.getProductId()), macbook);

        marketPlace.addProductToMP(iPhone);
        assertEquals(marketPlace.getListOfProductsAvailable().size(), 2);
        assertEquals(marketPlace.getProduct(iPhone.getProductId()), iPhone);
    }

    @Test
    void testGetProduct() {
        marketPlace.addProductToMP(macbook);
        marketPlace.addProductToMP(iPhone);

        assertEquals(marketPlace.getProduct(macbook.getProductId()), macbook);
        assertEquals(marketPlace.getProduct(iPhone.getProductId()), iPhone);
        assertEquals(marketPlace.getProduct(10000), null);
    }

    @Test
    void testRemoveProduct() {
        int numProds = marketPlace.getListOfProductsAvailable().size();
        marketPlace.addProductToMP(macbook);
        marketPlace.addProductToMP(iPhone);
        numProds += 2;

        marketPlace.removeProduct(macbook.getProductId());
        assertEquals(numProds-1 ,marketPlace.getListOfProductsAvailable().size());

        marketPlace.removeProduct(iPhone.getProductId());
        assertEquals(numProds-1-1 ,marketPlace.getListOfProductsAvailable().size());
    }
}
