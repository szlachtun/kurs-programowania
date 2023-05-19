package com.example.jpaint;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controllers implements Initializable {
    @FXML
    private Pane pane;

    @FXML
    private Spinner<Integer> sideCountSpinner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 20, 6, 1);
        sideCountSpinner.setValueFactory(valueFactory);
    }

    @FXML
    private ToggleButton circleButton;

    @FXML
    private ToggleButton polygonButton;
    public Controllers(Parent root) {

    }
}
