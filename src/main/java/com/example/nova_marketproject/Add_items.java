package com.example.nova_marketproject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.STYLESHEET_CASPIAN;

public class Add_items extends Seller_Dashboard {

    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #FF5733; -fx-background-radius: 50";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color:white; -fx-text-fill: #FF5733; -fx-background-radius: 50";

    Button back = new Button("<-- back");

    Label heading = new Label("Add Items");
    private TextField nameField = new TextField();
    private TextField priceField= new TextField();
    Label nameLabel = new Label("Product Name:");



    private List<Product> productList;
    private ListView<Product> productListView;
    Label priceLabel = new Label("Product Price:");


    Button sellButton = new Button("Sell");
    void starter(Stage stage)
    {

        nameLabel.setTextFill(Color.WHITE);
        priceLabel.setTextFill(Color.WHITE);

        heading.setFont(Font.font(STYLESHEET_CASPIAN, 49));
        heading.setTranslateY(3);
        heading.setTranslateX(10);
        heading.setTextFill(Color.WHITE);
        KeyFrame startFadeOut = new KeyFrame(Duration.seconds(0), new KeyValue(heading.opacityProperty(), 0.0));
        KeyFrame endFadeOut = new KeyFrame(Duration.seconds(1.8), new KeyValue(heading.opacityProperty(), 1.0));


        back.setTranslateX(2);
        back.setTranslateY(3);
        back.setStyle(IDLE_BUTTON_STYLE);
        back.setOnMouseEntered(e -> back.setStyle(HOVERED_BUTTON_STYLE));
        back.setOnMouseExited(e -> back.setStyle(IDLE_BUTTON_STYLE));
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Seller_Dashboard s1 = new Seller_Dashboard();
                try {
                    s1.start2(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        KeyFrame startFadeOut2 = new KeyFrame(Duration.seconds(0), new KeyValue(back.opacityProperty(), 0.0));
        KeyFrame endFadeOut2 = new KeyFrame(Duration.seconds(1.8), new KeyValue(back.opacityProperty(), 1.0));

        productList = loadProductData();

        productListView = new ListView<>();
        productListView.getItems().addAll(productList);
        productListView.setTranslateX(18);
        productListView.setTranslateY(100);
        ImageView selectedProductImage = new ImageView();
        selectedProductImage.setFitHeight(300);
        selectedProductImage.setFitWidth(300);
        selectedProductImage.setTranslateY(150);
        selectedProductImage.setTranslateX(150);

        productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedProductImage.setImage(newValue.getImage());
            } else {
                selectedProductImage.setImage(null);
            }
        });

        productListView.setStyle("-fx-background-color:#FF5722;");
        productListView.setTranslateY(100);
        KeyFrame startFadeOut3 = new KeyFrame(Duration.seconds(0), new KeyValue(productListView.opacityProperty(), 0.0));
        KeyFrame endFadeOut3 = new KeyFrame(Duration.seconds(1.8), new KeyValue(productListView.opacityProperty(), 1.0));

        sellButton.setStyle(IDLE_BUTTON_STYLE);
        sellButton.setOnMouseEntered(e -> sellButton.setStyle(HOVERED_BUTTON_STYLE));
        sellButton.setOnMouseExited(e -> sellButton.setStyle(IDLE_BUTTON_STYLE));
        Button sellButton = new Button("Sell");
        sellButton.setOnAction(e -> {
            try {
                sellProduct();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        KeyFrame startFadeOut5 = new KeyFrame(Duration.seconds(0), new KeyValue(sellButton.opacityProperty(), 0.0));
        KeyFrame endFadeOut6 = new KeyFrame(Duration.seconds(1.8), new KeyValue(sellButton.opacityProperty(), 1.0));

        VBox sellBox = new VBox(10);
        sellBox.setPadding(new Insets(10));
        sellBox.getChildren().addAll(nameLabel, nameField, priceLabel, priceField, sellButton);

        HBox hbb1 = new HBox(10);
        hbb1.getChildren().addAll(back,heading);
        VBox b1 = new VBox(10);
        b1.getChildren().addAll(hbb1, sellBox, productListView);
        b1.setBackground(new Background(new BackgroundFill(lgrad, null, null)));
        //b1.setStyle("-fx-background-color: black");


        Scene scene = new Scene(b1, 1280, 720);
        stage.setTitle("Nova Marketplace - Seller (Add Items)");
        stage.setScene(scene);
        stage.show();
        Timeline timeline = new Timeline(startFadeOut,startFadeOut2,startFadeOut3,startFadeOut5,endFadeOut,endFadeOut2,endFadeOut3,endFadeOut6);
        timeline.play();
    }


    public void sellProduct() throws IOException {
        String name = nameField.getText();
        String price = priceField.getText();
        String seller = selname;

        if (name.isEmpty() || price.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter product name and price.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Product Image");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Product product = new Product(name, price, seller, selectedFile);
            FileWriter myWriter = new FileWriter("product_data.txt",true);
            FileWriter myWriter2 = new FileWriter(selname+"_items.txt", true);
            myWriter.write(product.getName() + "," + product.getPrice() + "," +product.getSeller()+","+ product.getImagePath()+"\n"+"");
            myWriter2.write(product.getName() + "," + product.getPrice() + ","+product.getSeller()+"," + product.getImagePath()+"\n"+"");
            myWriter.close();
            myWriter2.close();
            productList.add(product);
            productListView.getItems().add(product);

            nameField.clear();
            priceField.clear();
            nameField.clear();
            priceField.clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select an image for the product.");
        }
    }
    public List<Product> loadProductData() {
        List<Product> products = new ArrayList<>();
        System.out.println(DATA_FILE_PATH);
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String price = parts[1];
                    String seller = parts[2];
                    String imagePath = parts[3];
                    products.add(new Product(name, price, seller, new File(imagePath)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

}


