package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// MarketPlace application
public class MarketPlaceApp {
    private Scanner input;
    private MarketPlace marketPlace;
    private Buyer buyer;
    private Seller seller;

    private static final String JSON_STORE = "./data/marketPlace.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the marketplace application
    public MarketPlaceApp() {
        init();
        runMarketPlace();
    }

    // MODIFIES: this
    // EFFECTS: initialises marketPlace
    private void init() {
        marketPlace = new MarketPlace();
        //marketPlace = new MarketPlace("Alex's workroom");

//        marketPlace.addProductToMP(new Product("iPhone10", 600));
//        marketPlace.addProductToMP(new Product("MacBook M2", 1800));
//        marketPlace.addProductToMP(new Product("shitty Windows laptop", 100));

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMarketPlace() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            welcomeScreen();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void welcomeScreen() {
        System.out.println("\nDo you want to buy or sell?");
        System.out.println("\tb -> buy");
        System.out.println("\ts -> sell");
        System.out.println("\tsave -> save marketplace to file");
        System.out.println("\tload -> load marketplace from file");
        System.out.println("\tq -> quit application");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("b")) {
            buyerUi();
        } else if (command.equals("s")) {
            sellerUi();
        } else if (command.equals("save")) {
            saveMarketPlace();
        } else if (command.equals("load")) {
            loadMarketPlace();
        } else {
            System.out.println("Selection not valid, please select again:");
        }
    }

    // EFFECTS: saves the marketPlace to file
    private void saveMarketPlace() {
        try {
            jsonWriter.open();
            jsonWriter.write(marketPlace);
            jsonWriter.close();
            //System.out.println("Saved " + marketPlace.getName() + " to " + JSON_STORE);
            System.out.println("Saved marketPlace to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads marketplace from file
    private void loadMarketPlace() {
        try {
            marketPlace = jsonReader.read();
            // System.out.println("Loaded " + marketPlace.getName() + " from " + JSON_STORE);
            System.out.println("Loaded marketPlace from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: initialises buyer
    private void buyerUi() {
        buyer = new Buyer();
        runMarketPlaceBuyer();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMarketPlaceBuyer() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            buyerScreen();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processBuyerCommand(command);
            }
        }

        System.out.println("\nGoing back to the main menu");
    }

    // EFFECTS: displays options for buyers
    private void buyerScreen() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tb -> buy a product");
        System.out.println("\tr -> rate a previously bought product");
        System.out.println("\tv -> view past orders");
        System.out.println("\tq -> quit seller screen");
    }

    // MODIFIES: this
    // EFFECTS: processes buyer command
    private void processBuyerCommand(String command) {
        if (command.equals("b")) {
            listAllProducts();
        } else if (command.equals("r")) {
            rateProduct();
        } else if (command.equals("v")) {
            viewPastOrders();
        } else {
            System.out.println("Selection not valid, please select again:");
        }
    }

    // EFFECTS: displays previously bought products of the buyer
    private void viewPastOrders() {
        ArrayList<Product> ordersOfBuyer = buyer.getBoughtProducts();
        if (ordersOfBuyer.isEmpty()) {
            System.out.println("No previous orders! Buy stuff!!");
        } else {
            System.out.println("Previously bought items: ");
            displayProducts(ordersOfBuyer);
        }
    }

    // MODIFIES: this
    // EFFECTS: buys the product
    private void chooseAndBuyProducts() {
        int chosenId = input.nextInt();
        ArrayList<Product> productList = marketPlace.getListOfProductsAvailable();
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (product.getProductId() == chosenId) {
                System.out.println("Buying the product with ID " + chosenId + "...");
                buyer.buyProduct(product);
                System.out.println("The following product was bought:");
                displayProduct(product);
                return;
            }
        }
        System.out.println("product with ID " + chosenId + " does not exist");
    }

    // MODIFIES: this
    // EFFECTS: rates the product
    private void rateProduct() {
        ArrayList<Product> ordersOfBuyer = buyer.getBoughtProducts();
        if (ordersOfBuyer.isEmpty()) {
            System.out.println("No previous orders! Buy stuff!!");
        } else {
            System.out.println("Pick the product ID of the product you want to rate:");
            displayProducts(ordersOfBuyer);
            addRating(ordersOfBuyer);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the rating (a helper function for rateProduct)
    private void addRating(ArrayList<Product> ordersOfBuyer) {
        int chosenId = input.nextInt();
        for (int i = 0; i < ordersOfBuyer.size(); i++) {
            Product product = ordersOfBuyer.get(i);
            if (chosenId == product.getProductId()) {
                System.out.println("Rate the following product (insert a number between 1 to 5)");
                displayProduct(product);
                int userRating = input.nextInt();
                product.setProductRating(userRating);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: lists the products, and buys the product
    private void listAllProducts() {
        ArrayList<Product> productList = marketPlace.getListOfProductsAvailable();
        if (productList.isEmpty()) {
            System.out.println("No products available! check back later!");
        } else {
            System.out.println("Pick the product ID of the product you want to buy:");
            displayProducts(productList);
            chooseAndBuyProducts();
        }
    }

    // EFFECTS: shows the products in the list (a helper function)
    private static void displayProducts(ArrayList<Product> list) {
        for (int i = 0; i < list.size(); i++) {
            Product product = list.get(i);
            System.out.print("\tID: " + product.getProductId());
            System.out.print("\tPrice: " + product.getProductPrice());
            System.out.print("\tName: " + product.getProductName());
            System.out.print("\tRating: " + product.getProductRating());
            System.out.println();
        }
    }

    // EFFECTS: shows the products in the list (a helper function)
    private static void displayProductsForSeller(HashSet<Product> list) {
        Product[] productsArray = list.toArray(new Product[list.size()]);
        for (int i = 0; i < list.size(); i++) {
            Product product = productsArray[i];
            System.out.print("\tID: " + product.getProductId());
            System.out.print("\tPrice: " + product.getProductPrice());
            System.out.print("\tName: " + product.getProductName());
            System.out.print("\tRating: " + product.getProductRating());
            System.out.print("\tbuys: " + product.getNumProductsSold());
            System.out.println();
        }
    }

    // EFFECTS: displays a product (a helper function)
    private static void displayProduct(Product product) {
        System.out.print("\tID: " + product.getProductId());
        System.out.print("\tPrice: " + product.getProductPrice());
        System.out.print("\tName: " + product.getProductName());
        System.out.print("\tRating: " + product.getProductRating());
        System.out.println();
    }

    // SELLER METHODS:

    // EFFECTS: initialises buyer
    private void sellerUi() {
        seller = new Seller();
        runMarketPlaceSeller();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMarketPlaceSeller() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            sellerScreen();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processSellerCommand(command);
            }
        }

        System.out.println("\nGoing back to the main menu");
    }

    // EFFECTS: displays menu of options to the seller
    private void sellerScreen() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\ts -> sell a product");
        System.out.println("\tv -> view my products and their rating and number of times they were sold");
        System.out.println("\tr -> remove a listed product");
        System.out.println("\tq -> quit seller screen");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processSellerCommand(String command) {
        if (command.equals("s")) {
            sellProduct();
        } else if (command.equals("v")) {
            viewProductsListedBySeller();
        } else if (command.equals("r")) {
            removeProductListing();
        } else {
            System.out.println("Selection not valid, please select again:");
        }
    }

    // EFFECTS: displays a message
    private void noListingsMessage() {
        System.out.println("No products listed yet! Start selling today!");
    }

    // MODIFIES: this
    // EFFECTS: removes the product listing
    private void removeProductListing() {
        if (this.seller.getProductsListedByTheSeller().isEmpty()) {
            noListingsMessage();
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the product id");
        viewProductsListedBySeller();

        int productId = scanner.nextInt();
        Product product = marketPlace.getProduct(productId);
        if (product == null) {
            System.out.println("The product with id " + productId + "does not exist");
            return;
        }

        marketPlace.removeProduct(productId);
        seller.removeProduct(productId);

        System.out.println("The following product was removed from the market place:");
        displayProduct(product);
    }

    // MODIFIES: this
    // EFFECTS: sells the product
    private void sellProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the product's name and price");

        String productName = scanner.nextLine();
        int productPrice = scanner.nextInt();

        Product product = new Product(productName, productPrice);
        marketPlace.addProductToMP(product);
        seller.addProduct(product);

        System.out.println("The following product was listed in the market place!");
        displayProduct(product);
    }

    // EFFECTS: displays the products
    private void viewProductsListedBySeller() {
        if (seller.getProductsListedByTheSeller().size() == 0) {
            noListingsMessage();
        } else {
            System.out.println("These are the products that were listed: ");
            displayProductsForSeller(seller.getProductsListedByTheSeller());
        }
    }
}
