package com.example.jpaint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GUI extends FXController {
    static void serialize(ArrayList<Shape> createdFigures, String saveFilePath) throws IOException {
        try {
            FileWriter writer = new FileWriter(saveFilePath);

            for (Shape figure : createdFigures) {
                StringBuilder line = new StringBuilder();
                if (figure instanceof FXCircle) {
                    line.append("circle;");
                    line.append(((FXCircle) figure).getCenterX()).append(";");
                    line.append(((FXCircle) figure).getCenterY()).append(";");
                    line.append(((FXCircle) figure).getRadius()).append(";");
                    writer.write(String.valueOf(line));
                } else if ((figure instanceof FXRectangle) || (figure instanceof FXPolygon)) {

                    if (figure instanceof FXRectangle) {
                        line.append("rectangle");
                    } else {
                        line.append("polygon");
                    }

                    for (double point : ((Polygon) figure).getPoints()) {
                        line.append(point).append(";");
                    }
                    writer.write(String.valueOf(line));
                } else
                    throw new IOException();
            }
            writer.close();
        } catch (IOException exception) {
            System.err.println("Saving file error");
            throw exception;
        }
    }

    static ArrayList<Shape> deserialize(String loadFilePath) throws IOException {
        ArrayList<Shape> importedFigures = new ArrayList<>();
        try {
            File file = new File(loadFilePath);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String[] args = reader.nextLine().split(";");
                ArrayList<Double> points = new ArrayList<>();

                for (int i = 1; i < args.length; i++) {
                    try {
                        points.add(Double.parseDouble(args[i]));
                    } catch (NumberFormatException exception) {
                        System.err.println("Parsing values error");
                        throw exception;
                    }
                }

                if (Objects.equals(args[0], "circle")) {
                    if (points.size() != 3) {
                        System.err.println("Wrong circle argument count");
                        throw new NumberFormatException("Wrong circle arguments count");
                    } else {
                        FXCircle figure = new FXCircle(points);
                        importedFigures.add(figure);
                    }
                } else if (Objects.equals(args[0], "rectangle")) {
                   if (points.size() != 4) {
                       System.err.println("Wrong rectangle argument count");
                       throw new NumberFormatException("Wrong rectangle arguments count");
                   } else {
                       FXRectangle figure = new FXRectangle(points);
                       importedFigures.add(figure);
                   }
                } else if (Objects.equals(args[0], "polygon")) {
                    if (points.size() % 2 != 0) {
                        System.err.println("Wrong polygon argument count");
                        throw new NumberFormatException("Wrong polygon arguments count");
                    } else {
                        FXPolygon figure = new FXPolygon(points);
                        importedFigures.add(figure);
                    }
                }
            }

        } catch (IOException exception) {
            System.err.println("Reading file error");
            throw exception;
        }
        return importedFigures;

    }

    public GUI(Stage stage) throws IOException {
        Scene scene;
        try {
            var controller = new FXController();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("jPaint.fxml")));

            Parent root = loader.load();
            scene = new Scene(root);

            HBox hbox = (HBox) root.lookup("#hbox");

            MenuBar bar = (MenuBar) root.lookup("#bar");

            Menu fileMenu = new Menu("File");
            Menu helpMenu = new Menu("Help");
            Menu aboutMenu = new Menu("About");

            MenuItem help = new MenuItem("Usage tutorial");
            MenuItem aboutProgram = new MenuItem("About program");
            MenuItem fileSave = new MenuItem("Save");


            FileChooser fileSaveChooser = new FileChooser();
            fileSaveChooser.setTitle("Save");
            fileSaveChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV files", "*.csv"));

            EventHandler<ActionEvent> saveEventHandler = event -> {

                var createdFiguresExported = controller.getCreatedFigures();
                var file = fileSaveChooser.showSaveDialog(stage);
                if (file != null) {
                    try {
                        serialize(createdFiguresExported, file.getAbsolutePath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(file.getAbsolutePath());
                }
            };

            MenuItem fileLoad = new MenuItem("Load");

            FileChooser fileLoadChooser = new FileChooser();
            fileLoadChooser.setTitle("Load");
            fileLoadChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV files", "*.csv"));

            EventHandler<ActionEvent> loadEventHandler = event -> {

                var file = fileLoadChooser.showOpenDialog(stage);
                if (file != null) {
                    try {
                        Pane pane = (Pane) root.lookup("#pane");
                        Rectangle rectangle = new Rectangle();
                        rectangle.setX(0.f);
                        rectangle.setY(0.f);
                        rectangle.setWidth(10000.f);
                        rectangle.setHeight(10000.f);
                        rectangle.setFill(Color.WHITE);
                        pane.getChildren().add(rectangle);
                        controller.setCreatedFigures(deserialize(file.getAbsolutePath()), pane);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            MenuItem programExit = new MenuItem("Exit");
            EventHandler<ActionEvent> programExitEventHandler = event -> System.exit(0);

            fileSave.setOnAction(saveEventHandler);
            fileLoad.setOnAction(loadEventHandler);
            programExit.setOnAction(programExitEventHandler);

            fileMenu.getItems().add(fileSave);
            fileMenu.getItems().add(fileLoad);
            fileMenu.getItems().add(programExit);

            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Help");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.setContentText("Naciśnij przycisk figury po lewej stronie i kliknij myszą po planszy. Przesunięcie figury: naciśnij na figurę i zaciśnij lewy przycisk. Powiększenie figury: naciśnij na figurę ");
            dialog.getDialogPane().getButtonTypes().add(type);

            EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.showAndWait();
                }
            };
            help.setOnAction(eventHandler);

            helpMenu.getItems().add(help);
            aboutMenu.getItems().add(aboutProgram);

            bar.getMenus().add(fileMenu);
            bar.getMenus().add(helpMenu);
            bar.getMenus().add(aboutMenu);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error loading UI markup");
            throw ex;
        }

        stage.setTitle("jPaint");
        stage.setScene(scene);
        stage.show();

    }
}
