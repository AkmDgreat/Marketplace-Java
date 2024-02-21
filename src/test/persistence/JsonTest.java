package persistence;

import model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProduct(String name, int price, Product product) {
        assertEquals(name, product.getProductName());
        assertEquals(price, product.getProductPrice());
    }
}