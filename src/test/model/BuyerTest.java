package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuyerTest {
    private Buyer buyer;
    private Product iPhone;
    private Product macBook;

    @BeforeEach
    void setup() {
        buyer = new Buyer();
        iPhone = new Product("iPhone15", 1000);
        macBook = new Product("macbook air m2", 2000);
    }

    @Test
    void constructorTest() {
        assertTrue(buyer.getBoughtProducts().isEmpty());
    }

    @Test
    void testBuyProduct() {
        buyer.buyProduct(iPhone);
        assertEquals(buyer.getBoughtProducts().size(), 1);
        assertEquals(buyer.getBoughtProducts().get(0), iPhone);
    }

    @Test
    void testBuyProductMultiple() {
        buyer.buyProduct(iPhone);
        assertEquals(buyer.getBoughtProducts().size(), 1);
        assertEquals(buyer.getBoughtProducts().get(0), iPhone);

        buyer.buyProduct(macBook);
        assertEquals(buyer.getBoughtProducts().size(), 2);
        assertEquals(buyer.getBoughtProducts().get(1), macBook);
    }
}