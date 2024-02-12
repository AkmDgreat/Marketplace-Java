package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SellerTest {
    private Seller seller;

    @BeforeEach
    void setup() {
        seller = new Seller();
    }

    @Test
    void constructorTest() {
        assertEquals(seller.getProductsListedByTheSeller().size(), 0);
    }
}
