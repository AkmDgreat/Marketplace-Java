package persistence;


import model.MarketPlace;
import model.Product;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MarketPlace mp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMarketPlace() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMarketplace.json");
        try {
            MarketPlace mp = reader.read();
            //assertEquals("My work room", mp.getName());
            assertEquals(0, mp.getListOfProductsAvailable().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMarketPlace() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMarketplace.json");
        try {
            MarketPlace mp = reader.read();
            //assertEquals("My work room", wr.getName());
            List<Product> listedProducts = mp.getListOfProductsAvailable();
            assertEquals(2, listedProducts.size());
            checkProduct("Macbook", 1800, listedProducts.get(0));
            checkProduct("iPhone", 800, listedProducts.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}