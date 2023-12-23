package com.example.nova_marketproject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.*;
import java.util.*;


public class Cart_System{
    private List<Product> productList;
    private ListView<Product> productListView;
    private ImageView selectedProductImage;
    Font hf1 = Font.loadFont("file:src/main/resources/inkfree.ttf", 29);
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; -fx-border-color: white";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color:black; -fx-opacity: 50";
    File myObj = new File("Cartitems.txt");

    LinearGradient lgrad = new LinearGradient(
            0, 0, 1280, 720, false,                      //sizing
            CycleMethod.NO_CYCLE,                  //cycling
            new Stop(0, Color.web("#000000"/*"#81c483"*/)),     //colors
            new Stop(1, Color.web("#FF5733"/*"#fcc200")*/))
    );
    FileReader fl1;
    Stage stage = new Stage();
    void crtfile() throws IOException
    {
        fl1 = new FileReader("Cartitems.txt");
    }
    VBox main_holder = new VBox();


    public void start4() throws IOException
    {
        crtfile();

        HBox buyBox = new HBox(10);

        productList = cartReader();
        productListView = new ListView<>();
        productListView.getItems().addAll(productList);
        productListView.setStyle("-fx-background-color: orange;");
        productListView.setTranslateX(18);
        productListView.setTranslateY(100);
        KeyFrame startFadeOut3 = new KeyFrame(Duration.seconds(0), new KeyValue(productListView.opacityProperty(), 0.0));
        KeyFrame endFadeOut4 = new KeyFrame(Duration.seconds(1.8), new KeyValue(productListView.opacityProperty(), 1.0));


        selectedProductImage = new ImageView();
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
        Label lnm1 = new Label("Products in Cart");
        lnm1.setFont(hf1);
        lnm1.setTextFill(Color.WHITE);
        lnm1.setTranslateX(18);
        lnm1.setTranslateY(100);

        Button buyButton = new Button("Buy now");
        buyButton.setTextFill(Color.WHITE);
        KeyFrame startFadeOut1 = new KeyFrame(Duration.seconds(0), new KeyValue(buyButton.opacityProperty(), 0.0));
        KeyFrame endFadeOut2 = new KeyFrame(Duration.seconds(1.8), new KeyValue(buyButton.opacityProperty(), 1.0));
        buyButton.setStyle(IDLE_BUTTON_STYLE);
        buyButton.setOnMouseEntered(e -> buyButton.setStyle(HOVERED_BUTTON_STYLE));
        buyButton.setOnMouseExited(e -> buyButton.setStyle(IDLE_BUTTON_STYLE));
        buyButton.setTranslateY(300);
        buyButton.setTranslateX(500);
        buyButton.setOnAction(e -> {
            try {
                soldProduct();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Button back = new Button("Close Cart");
        back.setTextFill(Color.WHITE);
        KeyFrame startFadeOut2 = new KeyFrame(Duration.seconds(0), new KeyValue(back.opacityProperty(), 0.0));
        KeyFrame endFadeOut3 = new KeyFrame(Duration.seconds(1.8), new KeyValue(back.opacityProperty(), 1.0));
        back.setStyle(IDLE_BUTTON_STYLE);
        back.setOnMouseEntered(e -> back.setStyle(HOVERED_BUTTON_STYLE));
        back.setOnMouseExited(e -> back.setStyle(IDLE_BUTTON_STYLE));
        back.setTranslateY(300);
        back.setTranslateX(500);
        back.setOnAction(e -> {
            stage.close();
        });

        buyBox.getChildren().addAll(productListView, selectedProductImage, buyButton, back);

        main_holder.setBackground(new Background(new BackgroundFill(lgrad, null, null)));

        main_holder.getChildren().addAll(lnm1, buyBox);

        Scene logged = new Scene(main_holder, 1280, 720);
        stage.setTitle("Nova Marketplace - Cart");
        stage.setScene(logged);
        stage.setMaximized(true);
        stage.show();
        Timeline timelineOn = new Timeline(startFadeOut1, startFadeOut2, startFadeOut3, endFadeOut4, endFadeOut3, endFadeOut3);
        timelineOn.play();
    }

    private LinkedList<Product> cartReader() {
        //List<Product> products = new ArrayList<>();
        LinkedList<Product> products = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(fl1)) {
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

    private void soldProduct() throws IOException {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a product to buy.");
            return;
        }
        FileWriter myWriter = new FileWriter(selectedProduct.getSeller()+"_ordereditems.txt",true);
        FileWriter myWriter2 = new FileWriter("ordered_items.txt",true);
        myWriter.write("\n"+selectedProduct.getName() + "," + selectedProduct.getPrice() + "," + selectedProduct.getSeller() + "," + selectedProduct.getImagePath());
        myWriter2.write("\n"+selectedProduct.getName() + "," + selectedProduct.getPrice() + "," +selectedProduct.getSeller() + "," + selectedProduct.getImagePath());
        myWriter.close();
        myWriter2.close();
        System.out.println(productListView.getSelectionModel().getSelectedItem());
        productList.remove(selectedProduct);
        productListView.getItems().remove(selectedProduct);
        selectedProductImage.setImage(null);
//        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE_PATH))) {
//            for (Product product : productList) {
//                writer.println(product.getName() + "," + product.getPrice()+ "," + product.getSeller() + "," + product.getImagePath());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        showAlert(Alert.AlertType.INFORMATION, "Success", "Product bought successfully!.");

    }




    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
