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

    // EFFECTS: reads marketplace from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MarketPlace read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMarketPlace(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses marketPlace from JSON object and returns it
    private MarketPlace parseMarketPlace(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        //MarketPlace mp = new MarketPlace(name);
        MarketPlace mp = new MarketPlace();
        addProducts(mp, jsonObject);
        return mp;
    }

    // MODIFIES: mp
    // EFFECTS: parses thingies from JSON object and adds them to marketPlace
    private void addProducts(MarketPlace mp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("products");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addProduct(mp, nextProduct);
        }
    }

    // MODIFIES: mp
    // EFFECTS: parses product from JSON object and adds it to marketPlace
    private void addProduct(MarketPlace mp, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int price = jsonObject.getInt("price");
        //Category category = Category.valueOf(jsonObject.getString("category"));
        Product product = new Product(name, price);
        mp.addProductToMP(product);
    }
}
