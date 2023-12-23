package com.example.nova_marketproject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import static javafx.application.Application.STYLESHEET_CASPIAN;

public class Seller_Dashboard implements dataloading {

    LinearGradient lgrad = new LinearGradient(
            0, 0, 1280, 720, false,                      //sizing
            CycleMethod.NO_CYCLE,                  //cycling
            new Stop(0, Color.web("#000000"/*"#81c483"*/)),     //colors
            new Stop(1, Color.web("#FF5733"/*"#fcc200")*/))
    );

    Label wel_meg = new Label("Welcome, ");
    Label wel_msg2;

    Label dsb = new Label("Dashboard");
    AnchorPane a1 = new AnchorPane();
    VBox vb1 = new VBox();
    Label no_of_orders;
    Label text1 = new Label("No of items");
    Label no_of_items ;
    Label text2 = new Label("No of orders");
    private List<Product> productList;
    private ListView<Product> productListView;
    Button b1 = new Button("General");
    Button b2 = new Button("Add Items");
    Button b3 = new Button("Orders");
    Button logoutButton = new Button("Logout");


    String button_normal_style ="-fx-background-color: #E74D2C; -fx-text-fill:WHITE";
    String hover_button_style = "-fx-background-color: black; -fx-text-fill:#E74D2C";
    VBox vb2 = new VBox();
    static String selname, DATA_FILE_PATH;
    String temp1,temp2;

    static void nameset (String nam)
    {
        selname=nam;
        DATA_FILE_PATH=selname+"_items.txt";
    }

    public static long countLineNumberReader(String fileName) {

        File file = new File(fileName);
        long lines = 0;
        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {
            while (lnr.readLine() != null) ;
            lines = lnr.getLineNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void start2(Stage stage) throws IOException {
        temp1 = String.valueOf(countLineNumberReader(DATA_FILE_PATH));
        no_of_items=new Label(temp1);
        temp2 = String.valueOf((countLineNumberReader(selname+"_ordereditems.txt")));
        no_of_orders=new Label(temp2);

        b1.setStyle(button_normal_style);
        b1.setTextFill(Color.WHITE);
        b1.setFont(Font.font(STYLESHEET_CASPIAN, 25));
        b1.setPrefHeight(100);
        b1.setPrefSize(300,63 );
        b1.setTranslateX(0);
        b1.setTranslateY(120);
        b1.setOnMouseEntered(e -> b1.setStyle(hover_button_style));
        b1.setOnMouseExited(e -> b1.setStyle(button_normal_style));
        KeyFrame startFadeOut = new KeyFrame(Duration.seconds(0), new KeyValue(b1.opacityProperty(), 0.0));
        KeyFrame endFadeOut = new KeyFrame(Duration.seconds(1.8), new KeyValue(b1.opacityProperty(),1.0));
        
        b2.setStyle(button_normal_style);
        b2.setTextFill(Color.WHITE);
        b2.setFont(Font.font(STYLESHEET_CASPIAN, 25));
        b2.setPrefHeight(100);
        b2.setPrefSize(300,63 );
        b2.setTranslateX(0);
        b2.setTranslateY(120);
        b2.setOnMouseEntered(e -> b2.setStyle(hover_button_style));
        b2.setOnMouseExited(e -> b2.setStyle(button_normal_style));
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Add_items a1 = new Add_items();
                a1.starter(stage);
            }
        });
        KeyFrame startFadeOut2 = new KeyFrame(Duration.seconds(0), new KeyValue(b2.opacityProperty(), 0.0));
        KeyFrame endFadeOut2 = new KeyFrame(Duration.seconds(1.8), new KeyValue(b2.opacityProperty(),1.0));

        b3.setStyle(button_normal_style);
        b3.setTextFill(Color.WHITE);
        b3.setFont(Font.font(STYLESHEET_CASPIAN, 25));
        b3.setPrefHeight(100);
        b3.setPrefSize(300,63 );
        b3.setTranslateX(0);
        b3.setTranslateY(120);
        b3.setOnMouseEntered(e -> b3.setStyle(hover_button_style));
        b3.setOnMouseExited(e -> b3.setStyle(button_normal_style));
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Orders o1 = new Orders();
                o1.starter(stage);

            }
        });
        KeyFrame startFadeOut3 = new KeyFrame(Duration.seconds(0), new KeyValue(b3.opacityProperty(), 0.0));
        KeyFrame endFadeOut3 = new KeyFrame(Duration.seconds(1.8), new KeyValue(b3.opacityProperty(), 1.0));
        
        logoutButton.setStyle(button_normal_style);
        logoutButton.setTextFill(Color.WHITE);
        logoutButton.setFont(Font.font(STYLESHEET_CASPIAN, 25));
        logoutButton.setPrefHeight(100);
        logoutButton.setPrefSize(300,63 );
        logoutButton.setTranslateX(0);
        logoutButton.setTranslateY(120);
        logoutButton.setOnMouseEntered(e -> logoutButton.setStyle(hover_button_style));
        logoutButton.setOnMouseExited(e -> logoutButton.setStyle(button_normal_style));
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main_interface m1 = new Main_interface();
                try {
                    m1.start(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        KeyFrame startFadeOut4 = new KeyFrame(Duration.seconds(0), new KeyValue(logoutButton.opacityProperty(), 0.0));
        KeyFrame endFadeOut4 = new KeyFrame(Duration.seconds(1.8), new KeyValue(logoutButton.opacityProperty(), 1.0));


        wel_meg.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        wel_meg.setTextFill(Color.WHITE);
        wel_msg2=new Label(selname);
        wel_msg2.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        wel_msg2.setTextFill(Color.WHITE);
        KeyFrame startFadeOut5 = new KeyFrame(Duration.seconds(0), new KeyValue(wel_msg2.opacityProperty(), 0.0));
        KeyFrame endFadeOut5 = new KeyFrame(Duration.seconds(1.8), new KeyValue(wel_msg2.opacityProperty(), 1.0));


        vb1.setStyle("-fx-background-color:#FF5733;");
        vb1.setPrefWidth(280);
        vb1.setPrefHeight(1280);
        vb1.setAlignment(Pos.BASELINE_LEFT);
        vb1.getChildren().addAll(wel_meg,wel_msg2,b1, b2,b3 ,logoutButton);
        KeyFrame startFadeOut6 = new KeyFrame(Duration.seconds(0), new KeyValue(vb1.opacityProperty(), 0.0));
        KeyFrame endFadeOut6 = new KeyFrame(Duration.seconds(1.8), new KeyValue(vb1.opacityProperty(), 1.0));
        
        VBox hold1 = new VBox(5);
        VBox hold2 = new VBox(5);

        dsb.setFont(Font.font(STYLESHEET_CASPIAN, 50));
        dsb.setTextFill(Color.WHITE);

        no_of_items.setFont(Font.font(STYLESHEET_CASPIAN,35));
        no_of_items.setTranslateX(20);
        no_of_items.setTranslateY(100);
        no_of_items.setTextFill(Color.WHITE);
        KeyFrame startFadeOut7 = new KeyFrame(Duration.seconds(0), new KeyValue(no_of_items.opacityProperty(), 0.0));
        KeyFrame endFadeOut7 = new KeyFrame(Duration.seconds(1.8), new KeyValue(no_of_items.opacityProperty(), 1.0));

        text2.setFont(Font.font(STYLESHEET_CASPIAN, 20));
        text2.setTranslateX(20);
        text2.setTranslateY(100);
        text2.setTextFill(Color.WHITE);
        KeyFrame startFadeOut8 = new KeyFrame(Duration.seconds(0), new KeyValue(text2.opacityProperty(), 0.0));
        KeyFrame endFadeOut8 = new KeyFrame(Duration.seconds(1.8), new KeyValue(text2.opacityProperty(), 1.0));
        hold1.getChildren().addAll(no_of_items,text1);

        no_of_orders.setFont(Font.font(STYLESHEET_CASPIAN,35));
        no_of_orders.setTranslateX(20);
        no_of_orders.setTranslateY(100);
        no_of_orders.setTextFill(Color.WHITE);
        KeyFrame startFadeOut9 = new KeyFrame(Duration.seconds(0), new KeyValue(no_of_orders.opacityProperty(), 0.0));
        KeyFrame endFadeOut9 = new KeyFrame(Duration.seconds(1.8), new KeyValue(no_of_orders.opacityProperty(), 1.0));

        text1.setFont(Font.font(STYLESHEET_CASPIAN, 20));
        text1.setTranslateX(20);
        text1.setTranslateY(100);
        text1.setTextFill(Color.WHITE);
        KeyFrame startFadeOut10 = new KeyFrame(Duration.seconds(0), new KeyValue(text1.opacityProperty(), 0.0));
        KeyFrame endFadeOut10 = new KeyFrame(Duration.seconds(1.8), new KeyValue(text1.opacityProperty(), 1.0));

        hold2.getChildren().addAll(no_of_orders, text2);

        HBox hb1 = new HBox(400);
        HBox hb2 = new HBox();
        hb1.getChildren().addAll(hold1, hold2);

        productList = loadProductData();

        productListView = new ListView<>();
        productListView.getItems().addAll(productList);
//        productListView.setBackground(new Background(new BackgroundFill(lgrad, null, null)));
        productListView.setTranslateX(18);
        productListView.setTranslateY(100);
        KeyFrame startFadeOut11 = new KeyFrame(Duration.seconds(0), new KeyValue(productListView.opacityProperty(), 0.0));
        KeyFrame endFadeOut11 = new KeyFrame(Duration.seconds(1.8), new KeyValue(productListView.opacityProperty(), 1.0));
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
        productListView.setPrefHeight(400);
        productListView.setPrefWidth(300);
        productListView.setTranslateX(10);
        productListView.setTranslateY(100);


        vb2.setBackground(new Background(new BackgroundFill(lgrad, null, null)));
        vb2.setPrefWidth(1000);
        vb2.setPrefHeight(1280);
        vb2.setTranslateX(280);

        hb2.getChildren().addAll(productListView, selectedProductImage);

        vb2.getChildren().addAll(hb1, hb2);
        a1.setStyle("-fx-background-color: black");
        a1.getChildren().addAll(vb1,vb2);
        Scene scene = new Scene(a1, 1280, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        Timeline timeline = new Timeline(startFadeOut,startFadeOut2,startFadeOut3,startFadeOut4,startFadeOut5,startFadeOut6,startFadeOut7,startFadeOut8,startFadeOut9,startFadeOut10,startFadeOut11,endFadeOut,endFadeOut2,endFadeOut3,endFadeOut4,endFadeOut5,endFadeOut6,endFadeOut7,endFadeOut8,endFadeOut9,endFadeOut10,endFadeOut11);
        timeline.play();

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
    @Override
    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void starter() throws IOException {
        Stage stage = new Stage();
        start2(stage);
    }
}


