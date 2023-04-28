package com.example.pascaltriangle;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class FXBorderPane extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        new GUI(stage);
    }
}