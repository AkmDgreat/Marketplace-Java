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

class JsonWriterTest extends JsonTest {
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
            Seller sl = new Seller();
            Buyer by = new Buyer();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMarketplace.json");
            writer.open();
            writer.write(mp, sl, by);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMarketplace.json");
            mp = reader.readMp2();
            assertEquals(0, mp.getListOfProductsAvailable().size());
            assertEquals(0, sl.getNumProductsListedByTheSeller());
            assertEquals(0, by.getBoughtProducts().size());
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

            Seller sl = new Seller();
            sl.addProductToSellerList(new Product("mac", 800));
            sl.addProductToSellerList(new Product("book", 100));

            Buyer by = new Buyer();
            by.buyProduct(new Product("bottle", 50));
            by.buyProduct(new Product("paper", 500));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMarketplace.json");
            writer.open();
            writer.write(mp, sl, by);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMarketplace.json");

            MarketPlace mpThatWasRead = reader.readMp2();
            List<Product> mpProductsThatWereRead = mpThatWasRead.getListOfProductsAvailable();
            assertEquals(2, mpProductsThatWereRead.size());
            checkProduct("Fridge", 700, mpProductsThatWereRead.get(0));
            checkProduct("Table", 900, mpProductsThatWereRead.get(1));

            Seller sellerThatWasRead = reader.readSeller2();
            HashSet<Product> sellerProductsThatWereRead = sellerThatWasRead.getProductsListedByTheSeller();
            assertEquals(2, sellerProductsThatWereRead.size());
            // !!!
//            checkProduct("Fridge", 700, sellerProductsThatWereRead.);
//            checkProduct("Table", 900, sellerProductsThatWereRead.get(1));

            Buyer buyerThatWasRead = reader.readBuyer2();
            List<Product> boughtProductsThatWereRead = buyerThatWasRead.getBoughtProducts();
            assertEquals(2, boughtProductsThatWereRead.size());
            checkProduct("bottle", 50, boughtProductsThatWereRead.get(0));
            checkProduct("paper", 500, boughtProductsThatWereRead.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}