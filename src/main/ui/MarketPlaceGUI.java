package ui;

import model.Buyer;
import model.MarketPlace;
import model.Product;
import model.Seller;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// represents the marketplaceGUI
public class MarketPlaceGUI extends JFrame {
    private MarketPlace marketPlace;
    private Buyer buyer;
    private Seller seller;

    private JPanel southPanel;
    private JPanel centerPanel;
    private JPanel buyerPanel;
    private JPanel sellerPanel;

    //private final JButton eastButton = new JButton("East Button");
    private final JButton loadButton = new JButton("Load");
    private final JButton saveButton = new JButton("Save");
    //private SaveBtn saveButton; // !!!

    private final JButton listAProductButton = new JButton("List a product");
    //listAProductButton.setActionCommand()

    private final JButton viewListedProductButton = new JButton("view listed products");
    private final JButton removeListingBtn = new JButton("remove a listing");

    private final JButton buyProductButton = new JButton("Buy");
    private final JButton previousOrdersButton = new JButton("Previous orders");
    private final JButton rateProductsButton = new JButton("Rate product");

    // saving functionality
    private static final String JSON_STORE = "./data/marketPlace.json";
    private JsonWriter jsonWriter; //!!!
    private JsonReader jsonReader;

    // EFFECTS: creates the marketplace GUI
    MarketPlaceGUI() {
        seller = new Seller();
        marketPlace = new MarketPlace();
        buyer = new Buyer();
        jsonWriter = new JsonWriter(JSON_STORE); //!!!
        jsonReader = new JsonReader(JSON_STORE);


        //saveButton = new SaveBtn(this.marketPlace, this.seller, this.buyer); //!!!
        setFrame();
        setLayout();
        //setBuyButton();
    }

    // MODIFIES: this
    // EFFECTS: sets the JFRAME
    private void setFrame() {
        this.setTitle("MarketPlace");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setBackground(Color.BLUE);
        this.setResizable(true);
        //this.setSize(500, 500);
        //this.setBounds(, y, width, height);
        this.setBounds(500, 150, 500, 500);
        this.pack();
        this.setVisible(true);
        this.setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: adds the components to the frame
    @SuppressWarnings("methodlength")
    private void setLayout() {
        southPanel = new JPanel(new FlowLayout());
        //centerPanel = new JPanel(new GridBagLayout());
        centerPanel = new JPanel(new GridLayout(15, 0));
//        Box centerBox = Box.createVerticalBox();
//        centerPanel.add(centerBox);

        southPanel.add(loadButton);
        southPanel.add(saveButton);
        southPanel.add(saveButton); //!!!


        southPanel.setBorder(BorderFactory.createTitledBorder("Load and Save"));

        //centerGridBagLayoutPanel.add(centerButton);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Main screen"));

        Box buyerBox = Box.createVerticalBox();
        buyerPanel = new JPanel();
        buyerBox.add(buyProductButton);
        buyerBox.add(previousOrdersButton);
        buyerBox.add(rateProductsButton);
        buyerPanel.add(buyerBox);
        buyerPanel.setBorder(BorderFactory.createTitledBorder("Buyer options"));

        Box sellerBox = Box.createVerticalBox();
        sellerPanel = new JPanel();
        sellerBox.add(listAProductButton);
        sellerBox.add(viewListedProductButton);
        sellerBox.add(removeListingBtn);
        sellerPanel.add(sellerBox);
        sellerPanel.setBorder(BorderFactory.createTitledBorder("Seller options"));

        setSellButton();
        setViewListingButton();
        setRemoveListingButton();
        setBuyButton();
        setViewOrderBtn();
        setRateOrderBtn();
        setSaveBtn(); //!!!
        setLoadBtn();

        this.add(southPanel, BorderLayout.PAGE_END);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buyerPanel, BorderLayout.LINE_START);
        this.add(sellerPanel, BorderLayout.LINE_END);
    }

    // MODIFIES: this
    // EFFECTS: adds the action listener to loadButton
    private void setLoadBtn() {
        loadButton.addActionListener(e -> {
            loadState();
        });
    }

    // MODIFIES: this
    // EFFECTS: adds the action listener to saveButton
    private void setSaveBtn() {
        saveButton.addActionListener(e -> {
            saveState();
        });
    }

    // MODIFIES: this
    // EFFECTS: adds the action listener to sellButton
    private void setSellButton() {
        listAProductButton.addActionListener(e -> {
            sellProduct();
        });
    }

    // MODIFIES: this
    // EFFECTS: adds the action listener to viewListedProductButton
    private void setViewListingButton() {
        viewListedProductButton.addActionListener(e -> {
            viewListedProducts();
        });
    }

    // MODIFIES: this
    // EFFECTS: adds the action listener to removeListingBtn
    private void setRemoveListingButton() {
        removeListingBtn.addActionListener(e -> {
            removeAListedProduct();
        });
    }

    // MODIFIES: this
    // EFFECTS: adds the action listener to buyButton
    private void setBuyButton() {
        buyProductButton.addActionListener(e -> {
            buyProduct();
        });
    }

    // MODIFIES: this
    // EFFECTS: loads the marketplace from the jsonFile
    private void loadState() {
        try {
            this.marketPlace = jsonReader.readMp2();
            this.seller = jsonReader.readSeller2();
            this.buyer = jsonReader.readBuyer2();
            System.out.println("Loaded marketPlace from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        this.centerPanel.removeAll();
        this.centerPanel.repaint();

        JLabel loadMessage = new JLabel("Successfully loaded!");
        this.centerPanel.add(loadMessage);
    }

    // MODIFIES: this
    // EFFECTS: saves the marketplace to the jsonFile
    private void saveState() {
        // !!!
        try {
            jsonWriter.open();
            jsonWriter.write(marketPlace, seller, buyer);
            jsonWriter.close();
            System.out.println("Saved marketPlace to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

        //saveButton.saveState(this.marketPlace, this.seller, this.buyer);
        this.centerPanel.removeAll();
        this.centerPanel.repaint();

        JLabel savedMessage = new JLabel("Successfully saved!");
        this.centerPanel.add(savedMessage);
    }


    // MODIFIES: this
    // EFFECTS: adds the action listener to rateButton
    private void setRateOrderBtn() {
        rateProductsButton.addActionListener(e -> {
            rateOrder();
        });
    }

    // MODIFIES: this
    // EFFECTS: adds the rating to a product
    @SuppressWarnings("methodlength")
    private void rateOrder() {
        this.centerPanel.removeAll();
        this.centerPanel.repaint();
        viewPreviousOrders();

        JLabel pickProduct = new JLabel("Pick the ID of the product you want to rate:");
        JLabel pickRating = new JLabel("Add the rating between 1 to 5:");

        JTextField idField = new JTextField();
        idField.setColumns(5);
        JButton rateProductBtn = new JButton("Rate");

        JTextField rateField = new JTextField();
        rateField.setColumns(5);

        this.centerPanel.add(pickProduct);
        this.centerPanel.add(idField);
        this.centerPanel.add(pickRating);
        this.centerPanel.add(rateField);

        this.centerPanel.add(rateProductBtn);
        this.centerPanel.revalidate();
        this.centerPanel.validate();

        rateProductBtn.addActionListener(e -> {
            int productId = Integer.parseInt(idField.getText().trim());
            int rating = Integer.parseInt(rateField.getText().trim());

            for (Product product: this.buyer.getBoughtProducts()) {
                if (product.getProductId() == productId) {
                    product.setProductRating(rating);
                }
            }

            this.centerPanel.removeAll();
            this.centerPanel.repaint();
        });
    }

    // MODIFIES: this
    // EFFECTS: adds the action listener to viewPreviousOrdersBtn
    private void setViewOrderBtn() {
        previousOrdersButton.addActionListener(e -> {
            viewPreviousOrders();
        });
    }

    // MODIFIES: this
    // EFFECTS: displays the previous orders of the buyer
    private void viewPreviousOrders() {
        this.centerPanel.removeAll();
        this.centerPanel.repaint();

        JLabel columnLabel = new JLabel("ID " + "Name " + "Price " + "Rating " + "Buys ");
        this.centerPanel.add(columnLabel);
        this.centerPanel.revalidate();
        this.centerPanel.validate();

        for (Product product: this.buyer.getBoughtProducts()) {
//            JLabel nameLabel = new JLabel(product.getProductName());
//            JLabel priceLabel = new JLabel(String.valueOf(product.getProductPrice()));
//            JLabel idLabel = new JLabel(String.valueOf(product.getProductId()));
            String productString = product.getProductId() + " "
                    + product.getProductName() + " " + product.getProductPrice() + " "
                    + product.getProductRating() + " " + product.getNumProductsSold();
            JLabel productLabel = new JLabel(productString);
            this.centerPanel.add(productLabel);
//            this.centerPanel.add(idLabel);
//            this.centerPanel.add(nameLabel);
//            this.centerPanel.add(priceLabel);
        }
        this.centerPanel.revalidate();
        this.centerPanel.validate();
    }

    // MODIFIES: this
    // EFFECTS: adds the product to buyer items
    @SuppressWarnings("methodlength")
    private void buyProduct() {
        viewListedProducts();

        JLabel pickProduct = new JLabel("Pick the ID of the product you want to buy:");
        this.centerPanel.add(pickProduct);
        this.centerPanel.revalidate();
        this.centerPanel.validate();

        JTextField idField = new JTextField();
        idField.setColumns(5);
        JButton buyProductBtn = new JButton("Buy");
        this.centerPanel.add(idField);
        this.centerPanel.add(buyProductBtn);
        this.centerPanel.revalidate();
        this.centerPanel.validate();

        buyProductBtn.addActionListener(e -> {
            int productId = Integer.parseInt(idField.getText().trim());
            ArrayList<Product> listCopy = this.marketPlace.getListOfProductsAvailable();

            for (Product product: listCopy) {
                if (product.getProductId() == productId) {
                    Product product1 = this.marketPlace.getProduct(productId);
                    this.buyer.buyProduct(product1);
                }
            }

            this.centerPanel.removeAll();
            this.centerPanel.repaint();
        });
    }

    // MODIFIES: this
    // EFFECTS: removes an order from the mp
    @SuppressWarnings("methodlength")
    private void removeAListedProduct() {
        this.centerPanel.removeAll();
        this.centerPanel.repaint();
        viewListedProducts();

        JLabel pickProduct = new JLabel("Pick the ID of the product you want to remove:");
        this.centerPanel.add(pickProduct);
        this.centerPanel.revalidate();
        this.centerPanel.validate();

        JTextField idField = new JTextField();
        idField.setColumns(5);
        JButton removeProductBtn = new JButton("Remove");
        this.centerPanel.add(idField);
        this.centerPanel.add(removeProductBtn);
        this.centerPanel.revalidate();
        this.centerPanel.validate();

        removeProductBtn.addActionListener(e -> {
            int productId = Integer.parseInt(idField.getText().trim());
            // ArrayList<Product> listCopy = this.marketPlace.getListOfProductsAvailable();

//            for (Product product: this.marketPlace.getListOfProductsAvailable()) {
//                if (product.getProductId() == productId) {
//                    this.marketPlace.removeProduct(productId);
//                    this.seller.removeProduct(productId);
//                }
//            }
//            for (Product product: listCopy) {
//                if (product.getProductId() == productId) {
//                    this.marketPlace.removeProduct(productId);
//                    this.seller.removeProduct(productId);
//                }
//            }

            for (int i = 0; i < this.marketPlace.getListOfProductsAvailable().size(); i++) {
                Product product = this.marketPlace.getListOfProductsAvailable().get(i);
                if (product.getProductId() == productId) {
                    this.marketPlace.removeProduct(productId);
                    this.seller.removeProduct(productId);
                }
            }

            this.centerPanel.removeAll();
            this.centerPanel.repaint();
        });
    }

    // MODIFIES: this
    // EFFECTS: displays the orders in the mp
    private void viewListedProducts() {
        this.centerPanel.removeAll();
        this.centerPanel.repaint();

//        ArrayList<ArrayList<String>> data = new ArrayList<>();
//        ArrayList<String> columNames = new ArrayList<>();
//        columNames.add("ID");
//        columNames.add("Name");
//        columNames.add("Price");
//        columNames.add("Rating");
//        columNames.add("products sold");
//        JTable table = new JTable((TableModel) data, (TableColumnModel) columNames);
        //JLabel columnLabel = new JLabel("ID " + "Name " + "Price " + "Rating " + "ProductsSold");
        JLabel columnLabel = new JLabel("ID " + "Name " + "Price " + "Rating " + "Buys ");
        this.centerPanel.add(columnLabel);
        this.centerPanel.revalidate();
        this.centerPanel.validate();

        for (Product product: this.marketPlace.getListOfProductsAvailable()) {
            String productString = product.getProductId() + " "
                    + product.getProductName() + " " + product.getProductPrice() + " "
                    + product.getProductRating() + " " + product.getNumProductsSold();
            JLabel productLabel = new JLabel(productString);
            this.centerPanel.add(productLabel);
        }

//        for (int i = 0; i < this.marketPlace.getListOfProductsAvailable().size(); i++) {
//            Product product = this.marketPlace.getListOfProductsAvailable().get(i);
//
//            String productId = String.valueOf(product.getProductId());
//            String productName = product.getProductName();
//            String productPrice = String.valueOf(product.getProductPrice());
//            String productRating = String.valueOf(product.getProductRating());
//            String numProdSold = String.valueOf(product.getNumProductsSold());
//
//            data.get(i).set(0, productId);
//            data.get(i).set(1, productName);
//            data.get(i).set(2, productPrice);
//            data.get(i).set(3, productRating);
//            data.get(i).set(4, numProdSold);
//        }
//        this.centerPanel.add(table);
        this.centerPanel.revalidate();
        this.centerPanel.validate();
    }

    // MODIFIES: this
    // EFFECTS: adds a product to the mp
    @SuppressWarnings("methodlength")
    private void sellProduct() {
        this.centerPanel.removeAll();
        this.centerPanel.repaint();
        JLabel nameLabel = new JLabel("Enter product name:");
        JTextField nameField = new JTextField();
        nameField.setColumns(15);

        JLabel priceLabel = new JLabel("Enter product price:");
        JTextField priceField = new JTextField();
        priceField.setColumns(5);

        JButton addProductButton = new JButton("Add the product");

        this.centerPanel.add(nameLabel);
        this.centerPanel.add(nameField);
        this.centerPanel.add(priceLabel);
        this.centerPanel.add(priceField);
        this.centerPanel.add(addProductButton);
        this.centerPanel.revalidate();
        this.centerPanel.validate();

        addProductButton.addActionListener(e -> {
            Product product = new Product(nameField.getText(), Integer.parseInt(priceField.getText().trim()));
            this.seller.addProductToSellerList(product);
            this.marketPlace.addProductToMP(product);
            System.out.println("The product was added");

            this.centerPanel.removeAll();
            this.centerPanel.repaint();
        });
    }
}
