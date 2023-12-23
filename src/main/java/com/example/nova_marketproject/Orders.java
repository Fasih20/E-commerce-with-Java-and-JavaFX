package com.example.nova_marketproject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Orders extends Seller_Dashboard{
    LinearGradient lgrad1 = new LinearGradient(
            0, 0, 1280, 720, false,                      //sizing
            CycleMethod.NO_CYCLE,                  //cycling
            new Stop(0, Color.web("#FFFFFF"/*"#81c483"*/)),     //colors
            new Stop(1, Color.web("#FF5733"/*"#fcc200")*/))
    );
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #FF5733; -fx-background-radius: 50";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color:white; -fx-text-fill: #FF5733; -fx-background-radius: 50";
    Button back = new Button("back");
    private List<Product> orderedproductList;
    private ListView<Product> orderedListView;

    void starter(Stage stage)
    {

        VBox vbox1 = new VBox();
        vbox1.setSpacing(10);
        vbox1.setPadding(new Insets(20));
        vbox1.setStyle("fx-background-color: black");


        PieChart pieChart = new PieChart();
        pieChart.setData(FXCollections.observableArrayList(
                new PieChart.Data("Total Items", Double.valueOf(countLineNumberReader(DATA_FILE_PATH))),
                new PieChart.Data("Total Orders", Double.valueOf(countLineNumberReader(selname+"_ordereditems.txt")))));
        pieChart.setTitle("Order Distribution");
        pieChart.setStyle("-fx-text-fill:white");
        KeyFrame startFadeOut = new KeyFrame(Duration.seconds(0), new KeyValue(pieChart.opacityProperty(), 0.0));
        KeyFrame endFadeOut = new KeyFrame(Duration.seconds(1.8), new KeyValue(pieChart.opacityProperty(), 1.0));

        Label selectedOrderLabel = new Label();


        pieChart.getData().forEach(data -> {data.getNode().setOnMousePressed(e -> {
            selectedOrderLabel.setText(String.format("Order: %s, Value: %.2f%%",
                    data.getName(), data.getPieValue()));
                    });
                });
        KeyFrame startFadeOut5 = new KeyFrame(Duration.seconds(0), new KeyValue(selectedOrderLabel.opacityProperty(), 0.0));
        KeyFrame endFadeOut6 = new KeyFrame(Duration.seconds(1.8), new KeyValue(selectedOrderLabel.opacityProperty(), 1.0));

        orderedproductList = loadProductData();

        orderedListView = new ListView<>();
        orderedListView.getItems().addAll(orderedproductList);
        //productListView.setBackground(new Background(new BackgroundFill(lgrad, null, null)));
        orderedListView.setStyle("-fx-background-color: orange;");
        orderedListView.setTranslateX(18);
        orderedListView.setTranslateY(100);
        KeyFrame startFadeOut3 = new KeyFrame(Duration.seconds(0), new KeyValue(orderedListView.opacityProperty(), 0.0));
        KeyFrame endFadeOut3 = new KeyFrame(Duration.seconds(1.8), new KeyValue(orderedListView.opacityProperty(), 1.0));

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
        KeyFrame startFadeOut4 = new KeyFrame(Duration.seconds(0), new KeyValue(back.opacityProperty(), 0.0));
        KeyFrame endFadeOut4 = new KeyFrame(Duration.seconds(1.8), new KeyValue(back.opacityProperty(), 1.0));

        vbox1.getChildren().addAll(back,pieChart, selectedOrderLabel, orderedListView);
        //vbox1.setStyle("-fx-background-color: grey");
        vbox1.setBackground(new Background(new BackgroundFill(lgrad1, null, null)));
        Scene scene = new Scene(vbox1, 1280, 720);
        stage.setTitle("Nova Marketplace - Seller Orders");
        stage.setScene(scene);
        stage.show();
        Timeline timeline = new Timeline(startFadeOut,startFadeOut3,startFadeOut4,startFadeOut5,endFadeOut,endFadeOut4,endFadeOut3,endFadeOut6);

        timeline.play();
    }
    @Override
    public List<Product> loadProductData() {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(selname+"_ordereditems.txt"))) {
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
