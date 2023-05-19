package com.example.jpaint;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class FXBorderPane extends Application {
    @Override
    public void start(Stage stage) {
        try {
            new GUI(stage);
        } catch (IOException ex) {
            System.err.println("Input/Output error in Application");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}