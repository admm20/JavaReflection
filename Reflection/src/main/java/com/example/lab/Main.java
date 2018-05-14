package com.example.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox vbox = null;
        try {
            vbox = FXMLLoader.load(getClass().getResource("/Layout.fxml"));
        } catch (IOException e) {
            System.out.println("FXML file cannot be loaded.");
        }

        primaryStage.setScene(new Scene(vbox));
        primaryStage.setTitle("Select class");
        primaryStage.show();
    }
}
