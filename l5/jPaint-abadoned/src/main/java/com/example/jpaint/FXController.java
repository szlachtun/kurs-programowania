package com.example.jpaint;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class FXController implements Initializable {
    public MenuBar bar;
    @FXML
    public Pane pane;
    @FXML
    private Label colorPicker;
    @FXML
    private ToggleButton circleButton;
    @FXML
    private ToggleButton rectangleButton;
    @FXML
    private ToggleButton polygonButton;
    @FXML
    private Spinner<Integer> sideCountSpinner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 20, 6, 1);
        sideCountSpinner.setValueFactory(valueFactory);
    }

    @FXML
    protected void toggleCircleButton() {
        selectFigureButton(circleButton);
    }

    @FXML
    protected void toggleRectangleButton() {
        selectFigureButton(rectangleButton);
    }

    @FXML
    protected void togglePolygonButton() {
        selectFigureButton(polygonButton);
    }

    public void setPane(Pane ImportPane) {
        pane = ImportPane;
    }

    static ArrayList<Shape> createdFigures = new ArrayList<>();

    public ArrayList<Shape> getCreatedFigures() {
        if (getLockedFigureIndex() != -1) {
            resetLockedFigureIndex();
        }
        return createdFigures;
    }

    public void setCreatedFigures(ArrayList<Shape> figures, Pane importPane) {
        pane = importPane;
        createdFigures = figures;
        for (Shape figure : createdFigures) {
            pane.getChildren().add(figure);
        }
    }

    private ArrayList<double[]> clickedPos = new ArrayList<>();

    public void resetClickedPos() {
        this.clickedPos = new ArrayList<>();
    }

    private double[] lastFigureHitPos;

    private int lockedFigureIndex = -1;

    public double[] getLastFigureHitPos() {
        return lastFigureHitPos;
    }

    public int getLockedFigureIndex() {
        return this.lockedFigureIndex;
    }

    public void setLockedFigureIndex(int index) {
        if (getLockedFigureIndex() != -1)
            resetLockedFigureIndex();
        this.lockedFigureIndex = index;
        createdFigures.get(index).setStroke(Color.RED);
    }

    public void resetLockedFigureIndex() {
        Paint fillColor = createdFigures.get(getLockedFigureIndex()).getFill();
        createdFigures.get(getLockedFigureIndex()).setStroke(fillColor);
        this.lockedFigureIndex = -1;
    }

    @FXML
    protected void handleMouseClickPane(MouseEvent mouseEvent) {
        // left mouse click
        if (mouseEvent.getButton() == MouseButton.PRIMARY & mouseEvent.isStillSincePress()) {

            // new figure will be created on added point
            if (!Objects.equals(getSelectedButton(), "nothing")) {

                clickedPos.add(new double[]{mouseEvent.getX(), mouseEvent.getY()});
                System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
            }

            // figure rotating
            if (Objects.equals(getSelectedButton(), "nothing") && mouseEvent.isControlDown()) {

                if (getLockedFigureIndex() != -1) {
                    System.out.println("rotating");
                    rotateFigure(getLockedFigureIndex(), mouseEvent);
                }
            }

            // figure selection
            if (Objects.equals(getSelectedButton(), "nothing") && !mouseEvent.isControlDown()) {
                if (HitFigureIndex(mouseEvent.getX(), mouseEvent.getY()) != -1) {
                    setLockedFigureIndex(HitFigureIndex(mouseEvent.getX(), mouseEvent.getY()));
                    System.out.println("Locked figure with index: " + getLockedFigureIndex());
                } else if (lockedFigureIndex != -1) {
                    resetLockedFigureIndex();
                    System.out.println("Locked figure index reset!");
                }
            }

            // draw figure if it has enough points
            // clear temporary click position array
            if (Objects.equals(getSelectedButton(), "circle") && (clickedPos.size() == 2)) {
                drawCircle();
                resetClickedPos();

            } else if (Objects.equals(getSelectedButton(), "rectangle") && (clickedPos.size() == 2)) {
                drawRectangle();
                resetClickedPos();

            } else if (Objects.equals(getSelectedButton(), "polygon") && (clickedPos.size() == sideCountSpinner.getValue())) {
                drawPoly();
                resetClickedPos();
            }

        }  // right mouse click
        else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            System.out.println("context menu");
        }
    }

    @FXML
    protected void handleMouseMovePane(MouseEvent mouseEvent) {
        // saves last mouse position when mouse touched figure
        if (Objects.equals(getSelectedButton(), "nothing")) {
            if ((HitFigureIndex(mouseEvent.getX(), mouseEvent.getY())) != -1) {
                System.out.println("Hit figure!");
                lastFigureHitPos = new double[]{mouseEvent.getX(), mouseEvent.getY()};
            }
        }
    }

    @FXML
    protected void handleMouseDragPane(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.isPrimaryButtonDown() && Objects.equals(getSelectedButton(), "nothing")) {

            int hitFigureIndex = HitFigureIndex(mouseEvent.getX(), mouseEvent.getY());
            if (hitFigureIndex != -1 && hitFigureIndex == getLockedFigureIndex()) {
                System.out.println("figure moving");
                moveFigure(hitFigureIndex, mouseEvent, getLastFigureHitPos());
            }
        }
    }

    @FXML
    protected void handleMouseScrollPane(ScrollEvent scrollEvent) {
        // scales figure
        if (scrollEvent.getEventType() == ScrollEvent.SCROLL) {
            if (getLockedFigureIndex() != -1) {
                System.out.println("scrolling");
                scaleFigure(getLockedFigureIndex(), scrollEvent);
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
        System.out.println(createdFigures.size());
        System.out.println(getLockedFigureIndex());

        if (getLockedFigureIndex() != -1)
            resetLockedFigureIndex();

        if (selectedFigureButton.isSelected()) {
            unselectButtons();
            selectedFigureButton.setSelected(true);
            if (selectedFigureButton == polygonButton) sideCountSpinner.setDisable(false);
        } else {
            unselectButtons();
        }
    }

    protected String getSelectedButton() {
        if (circleButton.isSelected()) return "circle";
        else if (rectangleButton.isSelected()) return "rectangle";
        else if (polygonButton.isSelected()) return "polygon";
        else return "nothing";
    }

    protected int HitFigureIndex(double x, double y) {
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

    protected void drawCircle() {
        FXCircle figure = new FXCircle(FXCircle.convertPoints(clickedPos));
        System.out.println("created circle");
        createdFigures.add(figure);
        pane.getChildren().add(figure);
        setLockedFigureIndex(createdFigures.size() - 1);
    }

    protected void drawRectangle() {
        FXRectangle rect = new FXRectangle(FXRectangle.convertPoints(clickedPos));
        System.out.println("created rectangle");
        createdFigures.add(rect);
        pane.getChildren().add(rect);
        setLockedFigureIndex(createdFigures.size() - 1);
    }

    protected void drawPoly() {
        FXPolygon poly = new FXPolygon(FXPolygon.convertPoints(clickedPos));
        System.out.println("created polygon");
        createdFigures.add(poly);
        pane.getChildren().add(poly);
        setLockedFigureIndex(createdFigures.size() - 1);
    }

    protected void moveFigure(int index, MouseEvent mouseEvent, double[] lastFigureHitPos) {

        double dx = mouseEvent.getX() - lastFigureHitPos[0];
        double dy = mouseEvent.getY() - lastFigureHitPos[1];

        if (createdFigures.get(index) instanceof FXCircle circle) {

            circle.addX(dx);
            circle.addY(dy);
        } else if (createdFigures.get(index) instanceof FXRectangle rectangle) {

            rectangle.addX(dx);
            rectangle.addY(dy);
        } else if (createdFigures.get(index) instanceof FXPolygon polygon) {

            polygon.addX(dx);
            polygon.addY(dy);
        }

        lastFigureHitPos[0] += dx;
        lastFigureHitPos[1] += dy;
    }

    protected void scaleFigure(int index, ScrollEvent scrollEvent) {

        if (createdFigures.get(index) instanceof FXCircle circle) {
            circle.scale(1 + scrollEvent.getDeltaY() / 80.0);
        } else if (createdFigures.get(index) instanceof FXRectangle rectangle) {
            rectangle.scale(1 + scrollEvent.getDeltaY() / 80);
        } else if (createdFigures.get(index) instanceof FXPolygon polygon) {
            polygon.scale(1 + scrollEvent.getDeltaY() / 80);
        }
    }

    protected void rotateFigure(int index, MouseEvent mouseEvent) {
        if (createdFigures.get(index) instanceof FXPolygon polygon) {
            polygon.rotate(10);
        } else if (createdFigures.get(index) instanceof FXRectangle rectangle) {
            rectangle.rotate(10);
        }
    }
}