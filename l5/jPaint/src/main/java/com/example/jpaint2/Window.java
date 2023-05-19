package com.example.jpaint2;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/*
* Entry point for application. Starts javafx GUI window
*
* @ author Roman Szlachtun
 */
public class Window extends Application {
    @Override
    public void start(Stage stage) {
        try {
            new GUI(stage);
        } catch (IOException ex) {
            System.err.println("Input/Output error in Application");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}