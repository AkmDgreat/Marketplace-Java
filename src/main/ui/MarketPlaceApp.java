package ui;

import model.*;

import java.util.*;

public class MarketPlaceApp {
    private Scanner input;
    private MarketPlace marketPlace;
    private Buyer buyer;
    private Seller seller;
    //private Cart cart;

    // EFFECTS: runs the marketplace application
    public MarketPlaceApp() {
        init();
        runMarketPlace();
    }

    private void init() {
        marketPlace = new MarketPlace();

        marketPlace.addProductToMP(new Product("iPhone10", 600));
        marketPlace.addProductToMP(new Product("MacBook M2", 1800));
        marketPlace.addProductToMP(new Product("shitty Windows laptop", 100));

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
            sellerUi();
        } else {
            System.out.println("Selection not valid, please select again:");
        }
    }

    private void buyerUi() {
        buyer = new Buyer();
        //cart = new Cart();
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
            System.out.println("Previously bought items: ");
            displayProducts(ordersOfBuyer);
        }
    }

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

    private static void displayProduct(Product product) {
        System.out.print("\tID: " + product.getProductId());
        System.out.print("\tPrice: " + product.getProductPrice());
        System.out.print("\tName: " + product.getProductName());
        System.out.print("\tRating: " + product.getProductRating());
        System.out.println();
    }



    // SELLER:
    private void sellerUi() {
        seller = new Seller();
        runMarketPlaceSeller();
    }

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

    private void sellerScreen() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\ts -> sell a product");
        System.out.println("\tv -> view my products and their rating and number of times they were sold");
        System.out.println("\tq -> quit application");
    }

    private void processSellerCommand(String command) {
        if (command.equals("s")) {
            sellProduct();
        } else if (command.equals("v")) {
            viewProductsListedBySeller();
        } else {
            System.out.println("Selection not valid, please select again:");
        }
    }

    private void sellProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the product's name and price");

        String productName = scanner.nextLine();
        int productPrice = scanner.nextInt();

        Product product = new Product(productName, productPrice);
        marketPlace.addProductToMP(product);
        seller.getProductsListedByTheSeller().add(product);

        System.out.println("The following product was listed in the market place!");
        displayProduct(product);
        //product.incrementNumProductSold();
    }

    private void viewProductsListedBySeller() {
        if (seller.getProductsListedByTheSeller().size() == 0) {
            System.out.println("No products listed! Start selling today!");
        } else {
            System.out.println("These are the products that were listed: ");
            displayProductsForSeller(seller.getProductsListedByTheSeller());
        }
    }
}
