package com.example.jpaint2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

/*
 * Handles menu bar creation
 *
 * @author Roman Szlachtun
 */
public class MenuFactory {
    static MenuBar create(Stage stage, Logic controller, Pane pane) {
        MenuBar bar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");
        Menu aboutMenu = new Menu("About");

        MenuItem help = new MenuItem("Usage tutorial");
        MenuItem aboutProgram = new MenuItem("About program");
        MenuItem fileSave = new MenuItem("Save");
        MenuItem fileLoad = new MenuItem("Load");
        MenuItem programExit = new MenuItem("Exit");

        fileMenu.getItems().add(fileSave);
        fileMenu.getItems().add(fileLoad);
        fileMenu.getItems().add(programExit);

        helpMenu.getItems().add(help);

        aboutMenu.getItems().add(aboutProgram);

        bar.getMenus().add(fileMenu);
        bar.getMenus().add(helpMenu);
        bar.getMenus().add(aboutMenu);

        EventHandler<ActionEvent> programExitEventHandler = event -> System.exit(0);

        EventHandler<ActionEvent> saveEventHandler = event -> {
            FileChooser fileSaveChooser = new FileChooser();
            fileSaveChooser.setTitle("Save");
            fileSaveChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV files", "*.csv"));

            var createdFiguresExported = controller.getCreatedFigures();
            var file = fileSaveChooser.showSaveDialog(stage);
            if (file != null) {
                System.out.println("file serialization");
                try {
                    FileIO.serialize(createdFiguresExported, file.getAbsolutePath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(file.getAbsolutePath());
            }
        };

        EventHandler<ActionEvent> loadEventHandler = event -> {
            FileChooser fileLoadChooser = new FileChooser();
            fileLoadChooser.setTitle("Load");
            fileLoadChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
            var file = fileLoadChooser.showOpenDialog(stage);
            if (file != null) {
                System.out.println("file deserialization");
                controller.setCreatedFigures(FileIO.deserialize(file.getAbsolutePath()));
                pane.getChildren().clear();
                pane.getChildren().addAll(controller.getCreatedFigures());
            }
        };

        EventHandler<ActionEvent> helpHandler = event -> {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Help");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.setContentText("""
                    Create the figure: click figure icon and click on pane
                    Move the figure: Uncheck figure icon and drag figure
                    Rotate the figure: Uncheck figure icon and drag with ctrl key pressed
                    Scale figure: Select figure and scroll mouse""");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        };

        helpMenu.setOnAction(helpHandler);

        EventHandler<ActionEvent> aboutHandler = event -> {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("About");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.setContentText("""
                    jPaint (javaPaint) v 2.0
                    Roman Szlachtun PWr, 2023
                    
                    Draw figures and manipulate them with comfort""");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        };

        aboutMenu.setOnAction(aboutHandler);
        fileSave.setOnAction(saveEventHandler);
        fileLoad.setOnAction(loadEventHandler);
        programExit.setOnAction(programExitEventHandler);

        return bar;
    }
}