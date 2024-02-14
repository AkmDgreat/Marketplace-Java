package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SellerTest {
    private Seller seller;
    private Product macBook;
    private Product fridge;

    @BeforeEach
    void setup() {
        seller = new Seller();
        macBook = new Product("Macbook", 2000);
        fridge = new Product("LG x500", 500);
    }

    @Test
    void constructorTest() {
        assertEquals(seller.getProductsListedByTheSeller().size(), 0);
    }

    @Test
    void removeProductTest() {
        seller.addProduct(macBook);
        assertEquals(seller.getNumProductsListedByTheSeller(), 1);
        assertEquals(seller.removeProduct(macBook.getProductId()), macBook);
        assertEquals(seller.getNumProductsListedByTheSeller(), 0);
    }

    @Test
    void removeProductTestMultiple() {
        seller.addProduct(macBook);
        seller.addProduct(fridge);
        assertEquals(seller.getNumProductsListedByTheSeller(), 2);

        assertEquals(seller.removeProduct(macBook.getProductId()), macBook);
        assertEquals(seller.getNumProductsListedByTheSeller(), 1);

        assertEquals(seller.removeProduct(fridge.getProductId()), fridge);
        assertEquals(seller.getNumProductsListedByTheSeller(), 0);
    }

    @Test
    void removeProductTestNull() {
        // no product is removed, because the product id is incorrect
        seller.addProduct(macBook);
        assertEquals(seller.getNumProductsListedByTheSeller(), 1);
        assertEquals(seller.removeProduct(200), null);
        assertEquals(seller.getNumProductsListedByTheSeller(), 1);
    }
}
