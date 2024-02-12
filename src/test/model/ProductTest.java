package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    private Product macbook;
    private Product iPhone;

    @BeforeEach
    void setUp() {
        macbook = new Product("macbook air m2", 2000);
        iPhone = new Product("iphone 14", 1000);
    }

    @Test
    void constructorTest() {
        assertEquals(macbook.getProductName(), "macbook air m2");
        assertEquals(macbook.getProductPrice(), 2000);
        assertEquals(macbook.getProductRating(), 0);
        //assertEquals(1, macbook.getProductId());

        assertEquals(iPhone.getProductName(), "iphone 14");
        assertEquals(iPhone.getProductPrice(), 1000);
        assertEquals(iPhone.getProductRating(), 0);
        //assertEquals(iPhone.getProductId(), 2);
    }

    @Test
    void testIncrementNumProductSold() {
        assertEquals(macbook.getNumProductsSold(), 0);
        macbook.incrementNumProductSold();
        assertEquals(macbook.getNumProductsSold(), 1);
    }

    @Test
    void testIncrementNumProductSoldMultiple() {
        assertEquals(macbook.getNumProductsSold(), 0);
        macbook.incrementNumProductSold();
        assertEquals(macbook.getNumProductsSold(), 1);

        macbook.incrementNumProductSold();
        assertEquals(macbook.getNumProductsSold(), 2);
    }

    @Test
    void testSetProductRating() {
        macbook.setProductRating(3);
        assertEquals(macbook.getProductRating(), 3);
    }

    @Test
    void testSetProductRatingMultiple() {
        macbook.setProductRating(3);
        assertEquals(macbook.getProductRating(), 3);

        macbook.setProductRating(5);
        assertEquals(macbook.getProductRating(), 5);
    }
}
