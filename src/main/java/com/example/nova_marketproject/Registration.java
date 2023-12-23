package com.example.nova_marketproject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

import java.io.IOException;

public class Registration {

    Stage stage=new Stage();
    Database_of_users d1 = new Database_of_users();
    LinearGradient lgrad = new LinearGradient(
            0, 0, 780, 460, false,                      //sizing
            CycleMethod.NO_CYCLE,                  //cycling
            new Stop(0, Color.web("#000000"  /*"#81c483"*/)),     //colors
            new Stop(1, Color.web("#FF5733"/*"#fcc200")*/))

    );

    Label llb3 = new Label ("Enter your name here");
    Label llb4 = new Label ("Enter password for your account");
    Font f1 = Font.loadFont("file:src/main/resources/inkfree.ttf", 30);
    Font hf1 = Font.loadFont("file:src/main/resources/inkfree.ttf", 29);
    Font f2 = Font.loadFont("file:src/main/resources/inkfree.ttf", 10);
    ImageView im1= new ImageView("logo1.png");
    PasswordField password = new PasswordField();
    TextField username = new TextField();
    Button b1 = new Button("Sign Up");

    Button b2 = new Button("Go Back");
    VBox vb1 = new VBox(5); //this holds the objects on the left hand side of the screen
    VBox vb2 = new VBox(5);
    Label lb2 = new Label("Sign Up");
    Label result = new Label();

    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: black; -fx-background-radius: 50";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color:white; -fx-text-fill: #FF5733; -fx-background-radius: 50";
    void registration_Start()
    {
        im1.setFitHeight(200.0);
        im1.setFitWidth(200.0);
        im1.setTranslateX(50);
        im1.setTranslateY(100);

        //KeyFrame keyFrame1On = new KeyFrame(Duration.seconds(0), new KeyValue(im1.imageProperty(), new Image("logo1.png")));
        KeyFrame startFadeOut = new KeyFrame(Duration.seconds(0), new KeyValue(im1.opacityProperty(), 0.0));
        KeyFrame endFadeOut = new KeyFrame(Duration.seconds(1.8), new KeyValue(im1.opacityProperty(), 1.0));

        result.setFont(f2);
        result.setTranslateX(90);
        result.setTranslateY(100);
        result.setTextFill(Color.WHITE);
        KeyFrame startFadeOut9 = new KeyFrame(Duration.seconds(0), new KeyValue(result.opacityProperty(), 0.0));
        KeyFrame endFadeOut10 = new KeyFrame(Duration.seconds(1.8), new KeyValue(result.opacityProperty(), 1.0));

        b2.setTranslateX(110);
        b2.setTranslateY(150);
        b2.setTextFill(Color.WHITE);
        b2.setFont(f2);
        KeyFrame startFadeOut8 = new KeyFrame(Duration.seconds(0), new KeyValue(b2.opacityProperty(), 0.0));
        KeyFrame endFadeOut9 = new KeyFrame(Duration.seconds(1.8), new KeyValue(b2.opacityProperty(), 1.0));
        b2.setStyle(IDLE_BUTTON_STYLE);
        b2.setOnMouseEntered(e -> b2.setStyle(HOVERED_BUTTON_STYLE));
        b2.setOnMouseExited(e -> b2.setStyle(IDLE_BUTTON_STYLE));
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Login h1 = new Login();
                h1.start();
                stage.close();
            }
        });





        vb1.getChildren().addAll(im1,result,b2);


        llb3.setFont(f2);
        llb3.setTranslateX(80);
        llb3.setTranslateY(149);
        llb3.setTextFill(Color.WHITE);
        KeyFrame startFadeOut3 = new KeyFrame(Duration.seconds(0), new KeyValue(llb3.opacityProperty(), 0.0));
        KeyFrame endFadeOut4 = new KeyFrame(Duration.seconds(1.8), new KeyValue(llb3.opacityProperty(), 1.0));


        username.setTranslateX(80);
        username.setTranslateY(150);
        username.setStyle("-fx-background-radius: 50;");
        KeyFrame startFadeOut4 = new KeyFrame(Duration.seconds(0), new KeyValue(username.opacityProperty(), 0.0));
        KeyFrame endFadeOut5 = new KeyFrame(Duration.seconds(1.8), new KeyValue(username.opacityProperty(), 1.0));


        llb4.setFont(f2);
        llb4.setTranslateX(80);
        llb4.setTranslateY(149);
        llb4.setTextFill(Color.WHITE);
        KeyFrame startFadeOut5 = new KeyFrame(Duration.seconds(0), new KeyValue(llb4.opacityProperty(), 0.0));
        KeyFrame endFadeOut6 = new KeyFrame(Duration.seconds(1.8), new KeyValue(llb4.opacityProperty(), 1.0));


        password.setTranslateX(80);
        password.setTranslateY(150);
        password.setStyle("-fx-background-radius: 50;");
        KeyFrame startFadeOut6 = new KeyFrame(Duration.seconds(0), new KeyValue(password.opacityProperty(), 0.0));
        KeyFrame endFadeOut7 = new KeyFrame(Duration.seconds(1.8), new KeyValue(password.opacityProperty(), 1.0));

        b1.setTranslateX(130);
        b1.setTranslateY(150);
        b1.setTextFill(Color.WHITE);
        b1.setFont(f2);
        KeyFrame startFadeOut7 = new KeyFrame(Duration.seconds(0), new KeyValue(b1.opacityProperty(), 0.0));
        KeyFrame endFadeOut8 = new KeyFrame(Duration.seconds(1.8), new KeyValue(b1.opacityProperty(), 1.0));
        b1.setStyle(IDLE_BUTTON_STYLE);
        b1.setOnMouseEntered(e -> b1.setStyle(HOVERED_BUTTON_STYLE));
        b1.setOnMouseExited(e -> b1.setStyle(IDLE_BUTTON_STYLE));
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try {
                    d1.writeTOfile(username.getText(), password.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                result.setText("Registration Successful");
            }
        });




        vb2.getChildren().addAll(lb2, llb3, username, llb4, password, b1);

        HBox hb1 = new HBox(250);
        hb1.setBackground(new Background(new BackgroundFill(lgrad, null, null)));
        hb1.getChildren().addAll(vb1, vb2);



        Scene scene = new Scene(hb1,780, 460);
        stage.setTitle("Nova Marketplace - Seller Registration");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Timeline timelineOn = new Timeline( startFadeOut, endFadeOut, startFadeOut3, endFadeOut4, startFadeOut4, endFadeOut5, startFadeOut5, endFadeOut6, startFadeOut6, endFadeOut7,startFadeOut7, endFadeOut8, startFadeOut8, endFadeOut9);
        timelineOn.play();
    }
}
