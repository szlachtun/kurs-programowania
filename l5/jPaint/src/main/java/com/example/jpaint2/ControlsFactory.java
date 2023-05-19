package com.example.jpaint2;

import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Objects;

/*
* Handles UI controllers creation
*
* @author Roman Szlachtun
 */
public class ControlsFactory {
    static ToggleButton circleButtonCreate() throws IOException {
        ToggleButton circleButton = new ToggleButton();
        circleButton.setPrefSize(30, 30);
        setImageFromURL(circleButton, "circle.png");
        VBox.setMargin(circleButton, new Insets(6, 6, 3, 6));
        return circleButton;
    }
    static ToggleButton rectangleButtonCreate() throws IOException {
        ToggleButton rectangleButton = new ToggleButton();
        rectangleButton.setPrefSize(30, 30);
        setImageFromURL(rectangleButton, "rectangle.png");
        VBox.setMargin(rectangleButton, new Insets(3, 6, 3, 5));
        return rectangleButton;
    }
    static ToggleButton polygonButtonCreate() throws IOException {
        ToggleButton polygonButton = new ToggleButton();
        polygonButton.setPrefSize(30, 30);
        setImageFromURL(polygonButton, "polygon.png");
        VBox.setMargin(polygonButton, new Insets(3, 6, 3, 5));
        return polygonButton;
    }

    static Spinner<Integer> sideCountSpinnerCreate() {
        Spinner<Integer> sideCountSpinner = new Spinner<>(3, 20 , 1);
        sideCountSpinner.getStyleClass().add("split-arrows-vertical");
        VBox.setMargin(sideCountSpinner, new Insets(3, 6, 3, 5));
        return sideCountSpinner;
    }

    static ColorPicker colorPickerCrate() {
        ColorPicker picker = new ColorPicker(Color.GOLD);
        VBox.setMargin(picker, new Insets(3, 6, 3, 5));
        return picker;
    }

    static void setImageFromURL(ToggleButton button, String fileName) throws IOException {
        Image image = new Image(Objects.requireNonNull(ControlsFactory.class.getResource(fileName)).openStream());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(28);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        button.setGraphic(imageView);
    }
}
