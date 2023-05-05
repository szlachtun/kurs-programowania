package com.example.jpaint;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI{
    public GUI(Stage stage) throws IOException {
        Scene scene;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("jPaint.fxml"));
            scene = new Scene(fxmlLoader.load());
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
