package com.example.jpaint;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Math.abs;


public class FXController implements Initializable {
    private ArrayList<Shape> createdFigures = new ArrayList<>();
    private ArrayList<double[]> clickedPos= new ArrayList<>();
    public void resetClickedPos() {
        this.clickedPos = new ArrayList<>();
    }
    @FXML
    private AnchorPane pane;
    @FXML
    private Spinner<Integer> sideCountSpinner;

    @FXML
    public void initialize(){
        System.out.println("tridwaras");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 20, 6, 1);
        sideCountSpinner.setValueFactory(valueFactory);
    }
    @FXML
    private ToggleButton circleButton;

    @FXML
    protected void toggleCircleButton(ActionEvent actionEvent) {
        selectFigureButton(circleButton);
    }

    @FXML
    private ToggleButton rectangleButton;
    @FXML
    protected void toggleRectangleButton(ActionEvent actionEvent) {
        selectFigureButton(rectangleButton);
    }

    @FXML
    private ToggleButton polygonButton;
    @FXML
    protected void togglePolygonButton(ActionEvent actionEvent) {
        selectFigureButton(polygonButton);
    }

    @FXML
    protected void handleMouseClickPane(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY & mouseEvent.isStillSincePress()) {
            clickedPos.add(new double[]{ mouseEvent.getX(), mouseEvent.getY()});
            System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());

            if (Objects.equals(getSelectedButton(), "circle") && (clickedPos.size() == 2)) {
                System.out.println("create circle");

                FXCircle circ = new FXCircle(clickedPos.get(0), clickedPos.get(1));
                createdFigures.add(circ);
                pane.getChildren().add(circ);
                resetClickedPos();

            }
            else if (Objects.equals(getSelectedButton(), "rectangle") && (clickedPos.size() == 2)) {
                System.out.println("create rectangle");

                FXRectangle rect = new FXRectangle(FXRectangle.convertPoints(clickedPos.get(0), clickedPos.get(1)));

                createdFigures.add(rect);
                pane.getChildren().add(rect);
                resetClickedPos();
            }
            /*
            switch (getSelectedButton()) {
                case "circle" -> {
                    System.out.println("create circle");

                }
                case "rectangle" -> {
                    System.out.println("create rectangle");
                    FXRectangle rect = new FXRectangle(clickedX, clickedY, 100, 100);
                    createdFigures.add(rect);
                    pane.getChildren().add(rect);
                }

                case "polygon" -> System.out.println("create polygon");
            }
             */
        }
        else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            System.out.println("context menu");
        }
    }
    @FXML
    protected void handleMouseDragPane(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY & mouseEvent.isDragDetect()) {
            System.out.println("dragging");
        }
    }
    @FXML
    protected void handleMouseScrollPane(ScrollEvent scrollEvent) {
        if (scrollEvent.getEventType() == ScrollEvent.SCROLL) {
            System.out.println("scrolling");
        }
    }


    protected void unselectButtons() {
        circleButton.setSelected(false);
        rectangleButton.setSelected(false);
        polygonButton.setSelected(false);
        sideCountSpinner.setDisable(true);
    }

    protected void selectFigureButton(ToggleButton selectedFigureButton) {
        resetClickedPos();
        if (selectedFigureButton.isSelected()) {
            unselectButtons();
            selectedFigureButton.setSelected(true);

            if (selectedFigureButton == polygonButton)
                sideCountSpinner.setDisable(false);
        } else {
            unselectButtons();
        }
    }

    protected String getSelectedButton(){
        if (circleButton.isSelected())
            return "circle";
        else if (rectangleButton.isSelected())
            return "rectangle";
        else if (polygonButton.isSelected())
            return "polygon";
        else
            throw new RuntimeException();
    }
    /*

    private ArrayList<Shape> figures = new ArrayList<>();

    public ArrayList<Shape> getFigures() {
        return figures;
    }

    public void addFigure(Shape figures) {
        this.figures.add(figures);
    }

    @FXML
    protected void drawFigure(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
            if (circleButton.isSelected()) {
                System.out.println("circle chosen");
            } else if (rectangleButton.isSelected()) {
                System.out.println("rectangle chosen");
            } else if (polygonButton.isSelected()) {
                System.out.println("polygon chosen");
            }
        }
    }


    @FXML
    protected void moveFigure(MouseEvent mouseEvent) {
        System.out.println("dragging detected");
    }

    @FXML
    protected void scaleFigure(ScrollEvent scrollEvent) {
        System.out.println("scrolling detected");
    }


     */
}