package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads marketPlace from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    public Buyer readBuyer2() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        Buyer by = new Buyer();

        JSONArray buyerProducts = jsonObject.getJSONArray("byProducts");

        for (Object json : buyerProducts) {
            JSONObject nextProduct = (JSONObject) json;
            String name = nextProduct.getString("name");
            int price = nextProduct.getInt("price");
            int rating = nextProduct.getInt("rating");
            //int id = nextProduct.getInt("id");

            Product product = new Product(name, price);
            product.setProductRating(rating);
            //product.setId(id);
            by.buyProduct(product);
        }

        return by;
    }

    public MarketPlace readMp2() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        MarketPlace mp = new MarketPlace();

        JSONArray buyerProducts = jsonObject.getJSONArray("mpProducts");

        for (Object json : buyerProducts) {
            JSONObject nextProduct = (JSONObject) json;
            String name = nextProduct.getString("name");
            int price = nextProduct.getInt("price");
            int rating = nextProduct.getInt("rating");
            //int id = nextProduct.getInt("id");

            Product product = new Product(name, price);
            product.setProductRating(rating);
            //product.setId(id);

            mp.addProductToMP(product);
        }

        return mp;
    }

    public Seller readSeller2() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        Seller sl = new Seller();

        JSONArray sellerProducts = jsonObject.getJSONArray("slProducts");

        for (Object json : sellerProducts) {
            JSONObject nextProduct = (JSONObject) json;
            String name = nextProduct.getString("name");
            int price = nextProduct.getInt("price");
            int rating = nextProduct.getInt("rating");
            int buys = nextProduct.getInt("buys");
            //int id = nextProduct.getInt("id");

            Product product = new Product(name, price);
            product.setProductRating(rating);
            product.setNumProductsSold(buys);
            //product.setId(id);

            sl.addProductToSellerList(product);
        }

        return sl;
    }

    // EFFECTS: reads marketplace from file and returns it;
    // throws IOException if an error occurs reading data from file
//    public MarketPlace readMarketPlace() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseMpJson(jsonObject);
//    }

    // EFFECTS: parses marketPlace from JSON object and returns it
//    private MarketPlace parseMpJson(JSONObject jsonObject) {
//        MarketPlace mp = new MarketPlace();
//        addMarketPlaceProducts(mp, jsonObject);
//        return mp;
//    }

    // MODIFIES: mp
    // EFFECTS: parses products from JSON object and adds them to marketPlace
//    private void addMarketPlaceProducts(MarketPlace mp, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("mpProducts");
//
//        for (Object json : jsonArray) {
//            JSONObject nextProduct = (JSONObject) json;
//            addMpProduct(mp, nextProduct);
//        }
//    }

    // MODIFIES: mp
    // EFFECTS: parses product from JSON object and adds it to marketPlace
//    private void addMpProduct(MarketPlace mp, JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//        int price = jsonObject.getInt("price");
//        Product product = new Product(name, price);
//        mp.addProductToMP(product);
//    }

    // EFFECTS: reads buyer from file and returns it;
    // throws IOException if an error occurs reading data from file
//    public Buyer readBuyer() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseBuyerJson(jsonObject);
//    }

    // EFFECTS: parses buyer from JSON object and returns it
//    private Buyer parseBuyerJson(JSONObject jsonObject) {
//        Buyer by = new Buyer();
//        addBuyerProducts(by, jsonObject);
//        return by;
//    }

    // MODIFIES: mp
    // EFFECTS: parses buyer from JSON object and adds bought products to the list
//    private void addBuyerProducts(Buyer by, JSONObject jsonObject) {
//        JSONArray buyerProducts = jsonObject.getJSONArray("byProducts");
//
//        for (Object json : buyerProducts) {
//            JSONObject nextProduct = (JSONObject) json;
//            addBuyerProduct(by, nextProduct);
//        }
//    }

    // MODIFIES: mp
    // EFFECTS: parses product from JSON object and adds it to buyer
//    private void addBuyerProduct(Buyer by, JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//        int price = jsonObject.getInt("price");
//        Product product = new Product(name, price);
//        by.buyProduct(product);
//    }

    // EFFECTS: reads seller from file and returns it;
    // throws IOException if an error occurs reading data from file
//    public Seller readSeller() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseSellerJson(jsonObject);
//    }

    // EFFECTS: parses seller from JSON object and returns it
//    private Seller parseSellerJson(JSONObject jsonObject) {
//        Seller sl = new Seller();
//        addSellerProducts(sl, jsonObject);
//        return sl;
//    }

    // MODIFIES: mp
    // EFFECTS: parses seller from JSON object and adds bought products to the list
//    private void addSellerProducts(Seller sl, JSONObject jsonObject) {
//        JSONArray buyerProducts = jsonObject.getJSONArray("slProducts");
//
//        for (Object json : buyerProducts) {
//            JSONObject nextProduct = (JSONObject) json;
//            addSellerProduct(sl, nextProduct);
//        }
//    }

    // MODIFIES: mp
    // EFFECTS: parses product from JSON object and adds it to seller
//    private void addSellerProduct(Seller sl, JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//        int price = jsonObject.getInt("price");
//        Product product = new Product(name, price);
//        sl.addProductToSellerList(product);
//    }



}
