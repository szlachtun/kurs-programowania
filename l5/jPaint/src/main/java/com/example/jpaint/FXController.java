package com.example.jpaint;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Math.abs;


public class FXController implements Initializable {
    private int lockedFigureIndex = -1;
    private final ArrayList<Shape> createdFigures = new ArrayList<>();
    private ArrayList<double[]> clickedPos = new ArrayList<>();
    private double[] lastFigureHitPos;

    public double[] getLastFigureHitPos() {
        return lastFigureHitPos;
    }

    public void resetClickedPos() {
        this.clickedPos = new ArrayList<>();
    }

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


            if (!Objects.equals(getSelectedButton(), "nothing")) {

                clickedPos.add(new double[]{mouseEvent.getX(), mouseEvent.getY()});
                System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
            } else if (getHitFigureIndex(mouseEvent.getX(), mouseEvent.getY()) != -1) {
                setLockedFigureIndex(getHitFigureIndex(mouseEvent.getX(), mouseEvent.getY()));
                System.out.println("Locked figure with index: " + getLockedFigureIndex());
            } else {
                resetLockedFigureIndex();
                System.out.println("Locked figure index reset!");
            }

            if (Objects.equals(getSelectedButton(), "circle") && (clickedPos.size() == 2)) {
                drawCircle();

            } else if (Objects.equals(getSelectedButton(), "rectangle") && (clickedPos.size() == 2)) {
                drawRectangle();

            } else if (Objects.equals(getSelectedButton(), "polygon") && (clickedPos.size() == sideCountSpinner.getValue())) {
                drawPoly();
            }
        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            System.out.println("context menu");
        }
    }

    @FXML
    protected void handleMouseMovePane(MouseEvent mouseEvent) {
        if (Objects.equals(getSelectedButton(), "nothing")) {
            int hitFigureIndex = getHitFigureIndex(mouseEvent.getX(), mouseEvent.getY());
            if (hitFigureIndex != -1) {
                System.out.println("Hit figure!");
                lastFigureHitPos = new double[]{mouseEvent.getX(), mouseEvent.getY()};
            }
        }
    }

    @FXML
    protected void handleMouseDragPane(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.isPrimaryButtonDown() &&
                Objects.equals(getSelectedButton(), "nothing") && mouseEvent.isShiftDown()) {
            int hitFigureIndex = getHitFigureIndex(mouseEvent.getX(), mouseEvent.getY());
            if (hitFigureIndex != -1) {
                System.out.println("figure moving");
                moveFigure(hitFigureIndex, mouseEvent, getLastFigureHitPos());
            }
        } else if (mouseEvent.getButton() == MouseButton.SECONDARY && mouseEvent.isShiftDown() &&
                Objects.equals(getSelectedButton(), "nothing")) {

            if (getHitFigureIndex(mouseEvent.getX(), mouseEvent.getY()) != -1) {
                System.out.println("rotating");
            }
        }
    }

    @FXML
    protected void handleMouseScrollPane(ScrollEvent scrollEvent) {
        if (scrollEvent.getEventType() == ScrollEvent.SCROLL) {
            int hitFigureIndex = getHitFigureIndex(scrollEvent.getX(), scrollEvent.getY());
            if (hitFigureIndex != -1) {
                System.out.println("scrolling");
                scaleFigure(hitFigureIndex, scrollEvent);
            }
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

    protected String getSelectedButton() {
        if (circleButton.isSelected())
            return "circle";
        else if (rectangleButton.isSelected())
            return "rectangle";
        else if (polygonButton.isSelected())
            return "polygon";
        else
            return "nothing";
    }

    protected int getHitFigureIndex(double x, double y) {
        for (int i = createdFigures.size() - 1; i >= 0; i--) {
            Shape figure = createdFigures.get(i);

            if (figure instanceof FXCircle tempCircle) {
                if (tempCircle.isHit(x, y)) {
                    return i;
                }
            } else if (figure instanceof FXRectangle tempRectangle) {
                if (tempRectangle.isHit(x, y)) {
                    return i;
                }
            } else if (figure instanceof FXPolygon tempPolygon) {
                if (tempPolygon.isHit(x, y)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getLockedFigureIndex(){
        return this.lockedFigureIndex;
    }

    public void setLockedFigureIndex(int index){
        this.lockedFigureIndex = index;
    }

    public void resetLockedFigureIndex(){
        this.lockedFigureIndex = -1;
    }

    protected void drawCircle() {
        FXCircle figure = new FXCircle(clickedPos.get(0), clickedPos.get(1));
        System.out.println("created circle");
        createdFigures.add(figure);
        pane.getChildren().add(figure);
        resetClickedPos();
    }

    protected void drawRectangle() {
        FXRectangle rect = new FXRectangle(FXRectangle.convertPoints(clickedPos.get(0), clickedPos.get(1)));
        System.out.println("created rectangle");
        createdFigures.add(rect);
        pane.getChildren().add(rect);
        resetClickedPos();
    }

    protected void drawPoly() {
        FXPolygon poly = new FXPolygon(FXPolygon.convertPoints(clickedPos));
        System.out.println("created polygon");
        createdFigures.add(poly);
        pane.getChildren().add(poly);
        resetClickedPos();
    }

    protected void moveFigure(int index, MouseEvent mouseEvent, double[] lastFigureHitPos) {

        double dx = mouseEvent.getX() - lastFigureHitPos[0];
        double dy = mouseEvent.getY() - lastFigureHitPos[1];

        if (createdFigures.get(index) instanceof FXCircle circle) {

            circle.addX(dx);
            circle.addY(dy);

            lastFigureHitPos[0] += dx;
            lastFigureHitPos[1] += dy;
        } else if (createdFigures.get(index) instanceof FXRectangle rectangle) {

            rectangle.addX(dx);
            rectangle.addY(dy);

            lastFigureHitPos[0] += dx;
            lastFigureHitPos[1] += dy;
        } else if (createdFigures.get(index) instanceof FXPolygon polygon) {

            ObservableList<Double> points = polygon.getPoints();
            ArrayList<Double> arrayListPoints = new ArrayList<>(points);
            Double[] arrayObjectPoints = arrayListPoints.toArray(new Double[0]);
            double[] arrayPoints = Arrays.stream(arrayObjectPoints).mapToDouble(Double::doubleValue).toArray();

            arrayPoints = polygon.addX(arrayPoints, dx);
            arrayPoints = polygon.addY(arrayPoints, dy);

            pane.getChildren().remove(polygon);
            polygon = new FXPolygon(arrayPoints);
            createdFigures.set(index, polygon);
            pane.getChildren().add(polygon);

            lastFigureHitPos[0] += dx;
            lastFigureHitPos[1] += dy;
        }
    }

    protected void scaleFigure(int index, ScrollEvent scrollEvent) {
        Scale scale = new Scale();
        scale.setX(1.2);
        scale.setY(1.2);

        if (createdFigures.get(index) instanceof FXCircle circle) {
            circle.setRadius(circle.getRadius() * (1 + scrollEvent.getDeltaY() / 80.0));
            System.out.println();

        } else if (createdFigures.get(index) instanceof FXRectangle rectangle) {
            rectangle.setWidth(rectangle.getWidth() * (1 + scrollEvent.getDeltaY() / 80));
            rectangle.setHeight(rectangle.getHeight() * (1 + scrollEvent.getDeltaY() / 80));
        } else if (createdFigures.get(index) instanceof FXPolygon polygon) {

            ObservableList<Double> points = polygon.getPoints();
            ArrayList<Double> arrayListPoints = new ArrayList<>(points);
            Double[] arrayObjectPoints = arrayListPoints.toArray(new Double[0]);
            double[] arrayPoints = Arrays.stream(arrayObjectPoints).mapToDouble(Double::doubleValue).toArray();

            for (int i = 0; i < arrayPoints.length; i++) {
                arrayPoints[i] *= (1 + scrollEvent.getDeltaY() / 80.0);
            }

            pane.getChildren().remove(polygon);
            polygon = new FXPolygon(arrayPoints);
            createdFigures.set(index, polygon);
            pane.getChildren().add(polygon);
        }
    }

    protected void rotateFigure(int index, MouseEvent mouseEvent) {
        Rotate rotate = new Rotate();
    }
}