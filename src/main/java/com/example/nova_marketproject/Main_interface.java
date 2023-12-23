package com.example.nova_marketproject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Main_interface extends Application {

    private TextField searchField = new TextField();
    private Button searchButton = new Button("Search");

    private List<Product> productList;
    private TilePane productTilePane;
    private ImageView selectedProductImage;

    Database_of_users d1 = new Database_of_users();
    Font f1 = Font.loadFont("file:src/main/resources/inkfree.ttf", 30);
    Font hf1 = Font.loadFont("file:src/main/resources/inkfree.ttf", 29);
    Font f2 = Font.loadFont("file:src/main/resources/inkfree.ttf", 10);
    LinearGradient lgrad = new LinearGradient(
            0, 0, 1280, 720, false,                      //sizing
            CycleMethod.NO_CYCLE,                  //cycling
            new Stop(0, Color.web("#000000"/*"#81c483"*/)),     //colors
            new Stop(1, Color.web("#FF5733"/*"#fcc200")*/)
            ));

    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; -fx-border-color: white";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color:black; -fx-opacity: 50";

    private static final String DATA_FILE_PATH = "product_data.txt";

    VBox mainHolder = new VBox();
    ImageView logoImage = new ImageView("E:\\Coding files\\OOP_Project\\Logo\\logo1.png");

    Button signInButton = new Button("Sign In for Sellers");
    Button registerButton = new Button("Create an Account");

    HBox buttonHolder = new HBox(10);

    @Override
    public void start(Stage stage) throws IOException {
        logoImage.setFitHeight(200.0);
        logoImage.setFitWidth(200.0);
        logoImage.setTranslateX(20);
        logoImage.setTranslateY(2);
        KeyFrame startFadeOut6 = new KeyFrame(Duration.seconds(0), new KeyValue(logoImage.opacityProperty(), 0.0));
        KeyFrame endFadeOut7 = new KeyFrame(Duration.seconds(1.8), new KeyValue(logoImage.opacityProperty(), 1.0));

        signInButton.setTranslateX(550);
        signInButton.setTranslateY(20);
        signInButton.setTextFill(Color.WHITE);
        KeyFrame startFadeOut8 = new KeyFrame(Duration.seconds(0), new KeyValue(signInButton.opacityProperty(), 0.0));
        KeyFrame endFadeOut9 = new KeyFrame(Duration.seconds(1.8), new KeyValue(signInButton.opacityProperty(), 1.0));
        signInButton.setStyle(IDLE_BUTTON_STYLE);
        signInButton.setOnMouseEntered(e -> signInButton.setStyle(HOVERED_BUTTON_STYLE));
        signInButton.setOnMouseExited(e -> signInButton.setStyle(IDLE_BUTTON_STYLE));
        signInButton.setOnAction(event -> {
            Login login = new Login();
            login.start();
            stage.close();
        });

        registerButton.setTranslateX(550);
        registerButton.setTranslateY(20);
        registerButton.setTextFill(Color.WHITE);
        KeyFrame startFadeOut7 = new KeyFrame(Duration.seconds(0), new KeyValue(registerButton.opacityProperty(), 0.0));
        KeyFrame endFadeOut8 = new KeyFrame(Duration.seconds(1.8), new KeyValue(registerButton.opacityProperty(), 1.0));
        registerButton.setStyle(IDLE_BUTTON_STYLE);
        registerButton.setOnMouseEntered(e -> registerButton.setStyle(HOVERED_BUTTON_STYLE));
        registerButton.setOnMouseExited(e -> registerButton.setStyle(IDLE_BUTTON_STYLE));
        registerButton.setOnAction(event -> {
            Registration registration = new Registration();
            registration.registration_Start();
            stage.close();
        });




        mainHolder.setBackground(new Background(new BackgroundFill(lgrad, null, null)));

        productList = loadProductData();

        productTilePane = new TilePane();
        productTilePane.setPrefColumns(4);  // Set the number of columns as per your requirement
        productTilePane.setHgap(20);
        productTilePane.setVgap(20);

        for (Product product : productList) {
            productTilePane.getChildren().add(createProductTile(product));
        }

        HBox buyBox = new HBox(10);
        Button cartButton = new Button("View Cart");
        cartButton.setTextFill(Color.WHITE);
        KeyFrame startFadeOut11 = new KeyFrame(Duration.seconds(0), new KeyValue(cartButton.opacityProperty(), 0.0));
        KeyFrame endFadeOut12 = new KeyFrame(Duration.seconds(1.8), new KeyValue(cartButton.opacityProperty(), 1.0));
        cartButton.setStyle(IDLE_BUTTON_STYLE);
        cartButton.setOnMouseEntered(e -> cartButton.setStyle(HOVERED_BUTTON_STYLE));
        cartButton.setOnMouseExited(e -> cartButton.setStyle(IDLE_BUTTON_STYLE));
        cartButton.setTranslateY(550);
        cartButton.setTranslateX(550);
        cartButton.setOnAction(e -> {
            try {
                Cart_System c1 = new Cart_System();
                c1.start4();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        searchField.setPromptText("Enter product name");
        searchButton.setOnAction(event -> searchProduct(searchField.getText()));

        //HBox searchBox = new HBox(10);
        searchButton.setTextFill(Color.WHITE);
        KeyFrame startFadeOut13 = new KeyFrame(Duration.seconds(0), new KeyValue(searchButton.opacityProperty(), 0.0));
        KeyFrame endFadeOut14 = new KeyFrame(Duration.seconds(1.8), new KeyValue(searchButton.opacityProperty(), 1.0));
        searchButton.setStyle(IDLE_BUTTON_STYLE);
        searchButton.setOnMouseEntered(e -> searchButton.setStyle(HOVERED_BUTTON_STYLE));
        searchButton.setOnMouseExited(e -> searchButton.setStyle(IDLE_BUTTON_STYLE));
        searchButton.setTranslateY(20);
        searchField.setTranslateY(20);
        searchField.setTranslateX(100);
        searchButton.setTranslateX(100);
//        searchBox.getChildren().addAll(searchField, searchButton);
//        searchBox.setTranslateX(800);
//        searchBox.setTranslateY(20);
        buttonHolder.getChildren().addAll(logoImage,searchField,searchButton, signInButton, registerButton, cartButton);


        // ... (other code)
        //buttonHolder.getChildren().addAll(logoImage, signInButton, registerButton, cartButton);

        mainHolder.getChildren().addAll(buttonHolder, productTilePane);

        Scene logged = new Scene(mainHolder, 1280, 720);
        stage.setTitle("Nova Marketplace - Seller Dashboard");
        stage.setScene(logged);
        stage.setMaximized(true);
        stage.show();
        Timeline timelineOn = new Timeline(startFadeOut11,endFadeOut12,startFadeOut6, endFadeOut7, startFadeOut7, startFadeOut8, endFadeOut9, endFadeOut8);
        timelineOn.play();
    }

    // ... (other methods)

    private Button createProductTile(Product product) {
        Button productButton = new Button();
        productButton.setGraphic(createProductGraphic(product));
        productButton.setStyle("-fx-background-color: transparent; -fx-border-color: white");
        productButton.setOnMouseEntered(e -> productButton.setStyle("-fx-background-color:black; -fx-opacity: 50"));
        productButton.setOnMouseExited(e -> productButton.setStyle("-fx-background-color: transparent; -fx-border-color: white"));
        productButton.setOnAction(event -> displayProductDetails(product));

        return productButton;
    }

    private VBox createProductGraphic(Product product) {
        VBox productGraphic = new VBox();
        productGraphic.setAlignment(javafx.geometry.Pos.CENTER);

        ImageView productImage = new ImageView(new Image("file:" + product.getImagePath()));
        productImage.setFitWidth(150);
        productImage.setFitHeight(150);

        Label productNameLabel = new Label(product.getName());
        productNameLabel.setFont(Font.loadFont("file:src/main/resources/inkfree.ttf", 14));
        productNameLabel.setTextFill(Color.WHITE);

        Label productPriceLabel = new Label("$" + product.getPrice());
        productPriceLabel.setFont(Font.loadFont("file:src/main/resources/inkfree.ttf", 12));
        productPriceLabel.setTextFill(Color.WHITE);

        productGraphic.getChildren().addAll(productImage, productNameLabel, productPriceLabel);

        return productGraphic;
    }

    private void searchProduct(String productName) {
        // Sort the product list based on names before searching
        productList.sort(Comparator.comparing(Product::getName));

        int index = binarySearch(productName);

        // Check if the index is valid and if the product at that index has the correct name
        if (index >= 0 && index < productList.size() && productList.get(index).getName().equals(productName)) {
            displayProductDetails(productList.get(index));
        } else {
            // Product not found
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "Product not found.");
        }
        }


    private int binarySearch(String productName) {
        int low = 0;
        int high = productList.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Product midProduct = productList.get(mid);
            int comparisonResult = midProduct.getName().compareTo(productName);

            if (comparisonResult == 0) {
                // Product found at mid index
                return mid;
            } else if (comparisonResult < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Product not found, return the insertion point
        return -(low + 1);
    }

//    private Product binarySearch(String productName) {
//        // Assuming productList is sorted by name for binary search to work
//        int low = 0;
//        int temp = productList.size();
//        int high = temp- 1;
//
//        while (low <= high) {
//            int mid = (low + high) / 2;
//            Product midProduct = productList.get(mid);
//            int comparisonResult = midProduct.getName().toLowerCase().compareTo(productName);
//            //Product psd1 = productList.get(mid);
//            if (midProduct.getName().toLowerCase().equals(productName)) {
//                // Product found at mid index
//                return midProduct;
//            }
//            else if (comparisonResult < 0) {
//                low = mid + 1;
//            }
//            else {
//                high = mid - 1;
//            }
//        }
//
//        // Product not found
//        return null;
//    }

    private void displayProductDetails(Product product) {
        // Implement logic to display product details, if needed
        // For example, you can update selectedProductImage or show a popup with details
        Dialog<Void> detailsDialog = new Dialog<>();
        Window window = detailsDialog.getDialogPane().getScene().getWindow();
        detailsDialog.setTitle("Product Details");

        VBox detailsVBox = new VBox(10);

        Label nameLabel = new Label("Name: " + product.getName());
        Label priceLabel = new Label("Price: $" + product.getPrice());
        Label sellerLabel = new Label("Seller: " + product.getSeller());

        ImageView productImage = new ImageView(new Image("file:" + product.getImagePath()));
        productImage.setFitWidth(200);
        productImage.setFitHeight(200);
        window.setOnCloseRequest(event -> window.hide());

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(event -> {
            try {
                detailsDialog.close();
                window.hide();
                buyProduct(product);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        detailsVBox.getChildren().addAll(nameLabel, priceLabel, sellerLabel, productImage, addToCartButton);

        detailsDialog.getDialogPane().setContent(detailsVBox);

        detailsDialog.showAndWait();
    }

    // ... (other methods)
    private void buyProduct(Product selectedProduct) throws IOException {
        if (selectedProduct == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a product to buy.");
            return;
        }

        FileWriter myWriter2 = new FileWriter("Cartitems.txt", true);
        myWriter2.write("\n" + selectedProduct.getName() + "," + selectedProduct.getPrice() + "," + selectedProduct.getSeller() + "," + selectedProduct.getImagePath());
        myWriter2.close();

        //productList.remove(selectedProduct);
        //productTilePane.getChildren().removeIf(node -> node instanceof Button && ((Button) node).getGraphic() == createProductGraphic(selectedProduct));

//        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE_PATH))) {
//            for (Product product : productList) {
//                writer.println(product.getName() + "," + product.getPrice() + "," + product.getSeller() + "," + product.getImagePath());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        showAlert(Alert.AlertType.INFORMATION, "Success", "Product added to cart successfully!");
    }

    private List<Product> loadProductData() {
        List<Product> products = new ArrayList<>();
        Stack<Product> pd1 = new Stack<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String price = parts[1];
                    String seller = parts[2];
                    String imagePath = parts[3];
                    pd1.push(new Product(name, price, seller, new File(imagePath)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(!pd1.isEmpty())
        {
            products.add(pd1.pop());
        }

        return products;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void progStart() throws IOException {
        Stage stage1 = new Stage();
        start(stage1);
    }

    public static void main(String[] args) {
        launch();
    }
}

