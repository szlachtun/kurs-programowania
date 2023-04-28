package com.example.pascaltriangle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI {
    public GUI(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("fxml.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Pascal Triangle");
        stage.setScene(scene);
        stage.show();
    }
}
