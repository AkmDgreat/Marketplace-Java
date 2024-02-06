package ui;

import model.Buyer;
import model.MarketPlace;
import model.Product;

import java.util.*;

public class MarketPlaceApp {
    private Scanner input;
    private MarketPlace marketPlace;
    private Buyer buyer;

    // EFFECTS: runs the marketplace application
    public MarketPlaceApp() {
        init();
        runMarketPlace();
    }

    private void init() {
        marketPlace = new MarketPlace();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
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
        System.out.println("\tq -> quit application");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("b")) {
            buyerUi();
        } else if (command.equals("s")) {
            //sellerUi();
        } else {
            System.out.println("Selection not valid, please select again:");
        }
    }

    private void buyerUi() {
        buyer = new Buyer();

        runMarketPlaceBuyer();


    }

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

    private void buyerScreen() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tb -> buy a product");
        System.out.println("\tr -> rate a previously bought product");
        System.out.println("\tv -> view past orders");
        System.out.println("\tq -> quit application");
    }

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

    private void viewPastOrders() {
        ArrayList<Product> ordersOfBuyer = buyer.getBoughtProducts();
        if (ordersOfBuyer.isEmpty()) {
            System.out.println("No previous orders! Buy stuff!!");
        } else {
            viewProducts(ordersOfBuyer);
        }
    }

    private void chooseAndBuyProducts() {
        int chosenId = input.nextInt();
        ArrayList<Product> productList = marketPlace.getListOfProductsAvailable();
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (product.getProductId() == chosenId) {
                System.out.println("Choosing the product with ID " + chosenId);
                buyer.buyProduct(product);
            }
        }
        System.out.println("product with ID " + chosenId + " does not exist");
    }

    private void rateProduct() {
        ArrayList<Product> ordersOfBuyer = buyer.getBoughtProducts();
        if (ordersOfBuyer.isEmpty()) {
            System.out.println("No previous orders! Buy stuff!!");
        } else {
            System.out.println("Pick the product ID of the product you want to rate:");
            viewProducts(ordersOfBuyer);
        }
    }

    private void listAllProducts() {
        ArrayList<Product> productList = marketPlace.getListOfProductsAvailable();
        if (productList.isEmpty()) {
            System.out.println("No products available! check back later!");
        } else {
            System.out.println("Pick the product ID of the product you want to buy:");
            viewProducts(productList);
            chooseAndBuyProducts();
        }
    }

    private static void viewProducts(ArrayList<Product> list) {
        for (int i = 0; i < list.size(); i++) {
            Product product = list.get(i);
            System.out.print("ID: " + product.getProductId());
            System.out.print("Price: " + product.getProductPrice());
            System.out.print("Name: " + product.getProductName());
            System.out.print("Rating: " + product.getProductRating());
        }
    }


}
