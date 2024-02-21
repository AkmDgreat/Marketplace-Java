package persistence;


import model.MarketPlace;

import model.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            MarketPlace mp = new MarketPlace();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MarketPlace mp = new MarketPlace();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMarketplace.json");
            writer.open();
            writer.write(mp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMarketplace.json");
            mp = reader.read();
            //assertEquals("My work room", wr.getName());
            assertEquals(0, mp.getListOfProductsAvailable().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MarketPlace mp = new MarketPlace();
            mp.addProductToMP(new Product("Fridge", 700));
            mp.addProductToMP(new Product("Table", 900));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMarketplace.json");
            writer.open();
            writer.write(mp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMarketplace.json");
            mp = reader.read();
            List<Product> products = mp.getListOfProductsAvailable();

            assertEquals(2, products.size());
            checkProduct("Fridge", 700, products.get(0));
            checkProduct("Table", 900, products.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}