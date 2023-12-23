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
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Login {
    Label lb1 = new Label("Marketplace");


    Label lb2 = new Label("Sign In");
    Label result = new Label();
    Label lb3 = new Label("New here? click here..");
    Button b2 = new Button("Register");
    Label llb3 = new Label ("Username");
    Label llb4 = new Label ("Password");
    Font f1 = Font.loadFont("file:src/main/resources/inkfree.ttf", 30);
    Font hf1 = Font.loadFont("file:src/main/resources/inkfree.ttf", 29);
    Font f2 = Font.loadFont("file:src/main/resources/inkfree.ttf", 10);
    ImageView im1= new ImageView("logo1.png");
    PasswordField  password = new PasswordField();
    TextField username = new TextField();
    Button b1 = new Button("Sign In");
    VBox vb1 = new VBox(5); //this holds the objects on the left hand side of the screen
    VBox vb2 = new VBox(5); //this holds the objects on the right hand side of the screen

    Database_of_users d1 = new Database_of_users();

    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: black; -fx-background-radius: 50";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color:white; -fx-text-fill: #FF5733; -fx-background-radius: 50";
    LinearGradient lgrad = new LinearGradient(
            0, 0, 780, 460, false,                      //sizing
            CycleMethod.NO_CYCLE,                  //cycling
            new Stop(0, Color.web("#000000"  /*"#81c483"*/)),     //colors
            new Stop(1, Color.web("#FF5733"/*"#fcc200")*/))
        );
    boolean logged=false;
    boolean logger(String uname, String pswd) throws IOException
    {
        return (d1.readFormfile(uname,pswd));
    }

    Stage stage = new Stage();


    public void start() {

//Setting up the left side of the screen
        password.setId("Enter your password");
        im1.setFitHeight(200.0);
        im1.setFitWidth(200.0);
        im1.setTranslateX(50);
        im1.setTranslateY(100);


        //KeyFrame keyFrame1On = new KeyFrame(Duration.seconds(0), new KeyValue(im1.imageProperty(), new Image("logo1.png")));
        KeyFrame startFadeOut = new KeyFrame(Duration.seconds(0), new KeyValue(im1.opacityProperty(), 0.0));
        KeyFrame endFadeOut = new KeyFrame(Duration.seconds(1.8), new KeyValue(im1.opacityProperty(), 1.0));

        lb1.setFont(f1);
        lb1.setTranslateX(80);
        lb1.setTranslateY(70);
        lb1.setTextFill(Color.WHITE);
        KeyFrame startFadeOut1 = new KeyFrame(Duration.seconds(0), new KeyValue(lb1.opacityProperty(), 0.0));
        KeyFrame endFadeOut2 = new KeyFrame(Duration.seconds(1.8), new KeyValue(lb1.opacityProperty(), 1.0));

        lb3.setFont(f2);
        lb3.setTranslateX(100);
        lb3.setTranslateY(120);
        lb3.setTextFill(Color.WHITE);
        KeyFrame startFadeOut9 = new KeyFrame(Duration.seconds(0), new KeyValue(lb3.opacityProperty(), 0.0));
        KeyFrame endFadeOut10 = new KeyFrame(Duration.seconds(1.8), new KeyValue(lb3.opacityProperty(), 1.0));

        result.setFont(f2);
        result.setTranslateX(80);
        result.setTranslateY(100);
        result.setTextFill(Color.WHITE);

        b2.setTranslateX(120);
        b2.setTranslateY(120);
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
                Registration r1 = new Registration();
                r1.registration_Start();
                stage.close();
            }
        });
        vb1.getChildren().addAll(im1,lb1,result,lb3,b2);


//Setting up the right side of the screen
        lb2.setFont(hf1);
        lb2.setTranslateX(80);
        lb2.setTranslateY(100);
        lb2.setTextFill(Color.WHITE);
        KeyFrame startFadeOut2 = new KeyFrame(Duration.seconds(0), new KeyValue(lb2.opacityProperty(), 0.0));
        KeyFrame endFadeOut3 = new KeyFrame(Duration.seconds(1.8), new KeyValue(lb2.opacityProperty(), 1.0));

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
        //b1.setBackground(new Background(new BackgroundFill(lgrad, null, null)));


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
            public void handle(ActionEvent event) {
                try {
                    logged=logger(username.getText(), password.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if(logged)
                {
                    Products p1 = new Products();
                    //p1.name_setter(username.getText());
                    p1.nameset(username.getText());
                    try {
                        p1.crtfile();
                        p1.filecreation();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Seller_Dashboard sd1 = new Seller_Dashboard();
                    sd1.nameset(username.getText());
                    try {
                        sd1.starter();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.close();
                }
                else if (username.getText().isEmpty()||password.getText().isEmpty()) {
                    result.setText("Please enter Username or Password");

                }
                else {
                    result.setText("Wrong USername or Password");
                }
            }
        });

        vb2.getChildren().addAll(lb2, llb3, username, llb4, password, b1);


//adding VBoxes to the HBox as to organise and display them in the scene

        HBox hb1 = new HBox(250);
        hb1.getChildren().addAll(vb1,vb2);
        hb1.setBackground(new Background(new BackgroundFill(lgrad, null, null)));


        Scene scene = new Scene( hb1,780, 460);
        stage.setTitle("Nova Marketplace - Seller Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Timeline timelineOn = new Timeline( startFadeOut, endFadeOut,startFadeOut1, endFadeOut2, startFadeOut2, endFadeOut3, startFadeOut3, endFadeOut4, startFadeOut4, endFadeOut5, startFadeOut5, endFadeOut6, startFadeOut6, endFadeOut7,startFadeOut7, endFadeOut8, startFadeOut8, endFadeOut9, startFadeOut9, endFadeOut10);
        timelineOn.play();
    }


}