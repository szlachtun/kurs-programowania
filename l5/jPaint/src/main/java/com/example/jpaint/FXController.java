package com.example.jpaint;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class FXController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private Spinner<Integer> sideCountSpinner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 10, 6, 1);
        sideCountSpinner.setValueFactory(valueFactory);
    }

    @FXML
    private ToggleButton circleButton;
    @FXML
    protected void toggleCircleButton(ActionEvent actionEvent) {
        if (circleButton.isSelected()) {
            unselectButtons();
            circleButton.setSelected(true);
        }
        else
            circleButton.setSelected(false);
    }
    @FXML
    private ToggleButton rectangleButton;
    @FXML
    protected void toggleRectangleButton(ActionEvent actionEvent) {
        if (rectangleButton.isSelected()) {
            unselectButtons();
            rectangleButton.setSelected(true);
        }
        else
            rectangleButton.setSelected(false);
    }
    @FXML
    private ToggleButton polygonButton;
    @FXML
    protected void togglePolygonButton(ActionEvent actionEvent) {
        if (polygonButton.isSelected()) {
            unselectButtons();
            polygonButton.setSelected(true);
            sideCountSpinner.setDisable(false);
        }
        else {
            polygonButton.setSelected(false);
            sideCountSpinner.setDisable(true);
        }
    }

    @FXML
    protected void getPanePoint(MouseEvent mouseEvent) {}

    protected void unselectButtons() {
        circleButton.setSelected(false);
        rectangleButton.setSelected(false);
        polygonButton.setSelected(false);
        sideCountSpinner.setDisable(true);
    }

}
