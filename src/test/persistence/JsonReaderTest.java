package persistence;


import model.Buyer;
import model.MarketPlace;
import model.Product;

import model.Seller;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MarketPlace mp = reader.readMp2();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMarketPlace() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMarketplace.json");
        try {
            MarketPlace mp = reader.readMp2();
            assertEquals(0, mp.getListOfProductsAvailable().size());
            Seller sl = reader.readSeller2();
            assertEquals(0, sl.getNumProductsListedByTheSeller());
            Buyer by = reader.readBuyer2();
            assertEquals(0, by.getBoughtProducts().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMarketPlace() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMarketplace.json");
        try {
            MarketPlace mp = reader.readMp2();
            List<Product> listedProducts = mp.getListOfProductsAvailable();
            assertEquals(2, listedProducts.size());
            checkProduct("Fridge", 700, listedProducts.get(0));
            checkProduct("Table", 900, listedProducts.get(1));

            Buyer buyerThatWasRead = reader.readBuyer2();
            List<Product> boughtProductsThatWereRead = buyerThatWasRead.getBoughtProducts();
            assertEquals(2, boughtProductsThatWereRead.size());
            checkProduct("bottle", 50, boughtProductsThatWereRead.get(0));
            checkProduct("paper", 500, boughtProductsThatWereRead.get(1));

            Seller sellerThatWasRead = reader.readSeller2();
            HashSet<Product> sellerProductsThatWereRead = sellerThatWasRead.getProductsListedByTheSeller();
            assertEquals(2, sellerProductsThatWereRead.size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}