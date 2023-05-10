package com.example.jpaint;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI{
    public GUI(Stage stage) throws IOException {
        Scene scene;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("jPaint.fxml"));
            Parent root = fxmlLoader.load();

            scene = new Scene(root);

        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error loading UI markup");
            throw ex;
        }

        stage.setTitle("jPaint");
        stage.setScene(scene);
        stage.show();

    }
}
