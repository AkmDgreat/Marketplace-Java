package ui;

import model.Buyer;
import model.MarketPlace;
import model.Product;
import model.Seller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MarketPlaceGUI extends JFrame {
    private MarketPlace marketPlace;
    private Buyer buyer;
    private Seller seller;

    private JPanel southPanel;
    private JPanel centerPanel;
    private JPanel buyerPanel;
    private JPanel sellerPanel;

    //private final JButton eastButton = new JButton("East Button");
    private final JButton exitButton = new JButton("Exit");
    private final JButton saveButton = new JButton("Save");

    private final JButton listAProductButton = new JButton("List a product");
    //listAProductButton.setActionCommand()

    private final JButton viewListedProductButton = new JButton("view listed products");
    private final JButton removeListingBtn = new JButton("remove a listing");

    private final JButton buyProductButton = new JButton("Buy");
    private final JButton previousOrdersButton = new JButton("Previous orders");
    private final JButton rateProductsButton = new JButton("Rate product");


    MarketPlaceGUI() {
        seller = new Seller();
        marketPlace = new MarketPlace();
        buyer = new Buyer();
        setFrame();
        setLayout();
        //setBuyButton();
    }

    private void setFrame() {
        this.setTitle("MarketPlace");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setBackground(Color.BLUE);
        this.setResizable(true);
        this.setSize(500, 500);
        //this.setBounds(, y, width, height);
        this.pack();
        this.setVisible(true);
        this.setLayout(new BorderLayout());
    }

    private void setLayout() {
        southPanel = new JPanel(new FlowLayout());
        //centerPanel = new JPanel(new GridBagLayout());
        centerPanel = new JPanel(new GridLayout(15, 0));
//        Box centerBox = Box.createVerticalBox();
//        centerPanel.add(centerBox);

        southPanel.add(exitButton);
        southPanel.add(saveButton);
        southPanel.setBorder(BorderFactory.createTitledBorder("Exit and save"));

        //centerGridBagLayoutPanel.add(centerButton);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Main screen"));

        Box buyerBox = Box.createVerticalBox();
        buyerPanel = new JPanel();
        buyerBox.add(buyProductButton);
        buyerBox.add(previousOrdersButton);
        buyerBox.add(rateProductsButton);
        buyerPanel.add(buyerBox);
        buyerPanel.setBorder(BorderFactory.createTitledBorder("Box Layout"));

        Box sellerBox = Box.createVerticalBox();
        sellerPanel = new JPanel();
        sellerBox.add(listAProductButton);
        sellerBox.add(viewListedProductButton);
        sellerBox.add(removeListingBtn);
        sellerPanel.add(sellerBox);
        sellerPanel.setBorder(BorderFactory.createTitledBorder("East Box Layout"));

        setSellButton();
        setViewListingButton();
        setRemoveListingButton();
        setBuyButton();
        setViewOrderBtn();
        setRateOrderBtn();

        this.add(southPanel, BorderLayout.PAGE_END);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buyerPanel, BorderLayout.LINE_START);
        this.add(sellerPanel, BorderLayout.LINE_END);
    }

    private void setSellButton() {
        listAProductButton.addActionListener(e -> {
            sellProduct();
        });
    }

    private void setViewListingButton() {
        viewListedProductButton.addActionListener(e -> {
            viewListedProducts();
        });
    }

    private void setRemoveListingButton() {
        removeListingBtn.addActionListener(e -> {
            removeAListedProduct();
        });
    }

    private void setBuyButton() {
        buyProductButton.addActionListener(e -> {
            buyProduct();
        });
    }

    private void setRateOrderBtn() {
        rateProductsButton.addActionListener(e -> {
            rateOrder();
        });
    }

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

    private void setViewOrderBtn() {
        previousOrdersButton.addActionListener(e -> {
            viewPreviousOrders();
        });
    }

    private void viewPreviousOrders() {
        this.centerPanel.removeAll();
        this.centerPanel.repaint();
        for (Product product: this.buyer.getBoughtProducts()) {
//            JLabel nameLabel = new JLabel(product.getProductName());
//            JLabel priceLabel = new JLabel(String.valueOf(product.getProductPrice()));
//            JLabel idLabel = new JLabel(String.valueOf(product.getProductId()));
            String productString = product.getProductId() + " "
                    + product.getProductName() + " " + product.getProductPrice();
            JLabel productLabel = new JLabel(productString);
            this.centerPanel.add(productLabel);
//            this.centerPanel.add(idLabel);
//            this.centerPanel.add(nameLabel);
//            this.centerPanel.add(priceLabel);
        }
        this.centerPanel.revalidate();
        this.centerPanel.validate();
    }


    private void buyProduct() {
        viewListedProducts();

        JLabel pickProduct = new JLabel("Pick the ID of the product you want to buy:");
        this.centerPanel.add(pickProduct);
        this.centerPanel.revalidate();
        this.centerPanel.validate();

        JTextField idField = new JTextField();
        idField.setColumns(5);
        JButton removeProductBtn = new JButton("Buy");
        this.centerPanel.add(idField);
        this.centerPanel.add(removeProductBtn);
        this.centerPanel.revalidate();
        this.centerPanel.validate();

        removeProductBtn.addActionListener(e -> {
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
            ArrayList<Product> listCopy = this.marketPlace.getListOfProductsAvailable();

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

    private void viewListedProducts() {
        this.centerPanel.removeAll();
        this.centerPanel.repaint();

        for (Product product: this.marketPlace.getListOfProductsAvailable()) {
            String productString = product.getProductId() + " "
                    + product.getProductName() + " " + product.getProductPrice();
            JLabel productLabel = new JLabel(productString);
            this.centerPanel.add(productLabel);
        }
        this.centerPanel.revalidate();
        this.centerPanel.validate();
    }

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
