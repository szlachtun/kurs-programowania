package com.example.jpaint2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Window {
    public GUI(Stage stage) throws IOException {
        Logic controller = new Logic();
        ColorPicker colorPicker = ControlsFactory.colorPickerCrate();
        Pane pane = PaneFactory.create(controller, colorPicker);
        MenuBar menuBar = MenuFactory.create(stage, controller, pane);

        VBox figure = new VBox();
        figure.setMaxWidth(60);

        ToggleButton circleButton = ControlsFactory.circleButtonCreate();
        ToggleButton rectangleButton = ControlsFactory.rectangleButtonCreate();
        ToggleButton polygonButton = ControlsFactory.polygonButtonCreate();
        Spinner<Integer> sideCountSpinner = ControlsFactory.sideCountSpinnerCreate();

        controller.setControllers(circleButton, rectangleButton, polygonButton, sideCountSpinner);

        EventHandler<ActionEvent> eventHandler = actionEvent -> {
            if (controller.getLockedFigureIndex() != -1)
                controller.setFigureColor(controller.getLockedFigureIndex(), colorPicker.getValue());
        };

        colorPicker.setOnAction(eventHandler);
        figure.getChildren().addAll(circleButton, rectangleButton, polygonButton, sideCountSpinner, colorPicker);

        HBox footer = new HBox();


        Separator separator = new Separator(Orientation.valueOf(String.valueOf(Orientation.VERTICAL)));
        footer.getChildren().addAll(figure, separator, pane);

        VBox window = new VBox(menuBar, footer);


        Scene scene = new Scene(window, 600, 400);
        stage.setScene(scene);
        stage.setTitle("jPaint v2");
        stage.show();
    }

}
