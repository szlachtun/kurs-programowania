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
    private ArrayList<double[]> clickedPos = new ArrayList<>();

    public void resetClickedPos() {
        this.clickedPos = new ArrayList<>();
    }

    @FXML
    private AnchorPane pane;
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
            int hitFigureIndex = getHitFigureIndex(mouseEvent);
        }
    }

    @FXML
    protected void handleMouseDragPane(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.isDragDetect() &&
                Objects.equals(getSelectedButton(), "nothing")) {
            System.out.println("figure moving");
            int hitFigureIndex = getHitFigureIndex(mouseEvent);
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

    protected int getHitFigureIndex(MouseEvent mouseEvent) {
        for(int i = createdFigures.size() - 1; i >= 0; i--) {
            Shape figure = createdFigures.get(i);

            if (figure instanceof FXCircle tempCircle) {
                if (tempCircle.isHit(mouseEvent.getX(), mouseEvent.getY())) {
                    System.out.println("Hit circle!");
                    return i;
                }
            } else if (figure instanceof FXRectangle tempRectangle) {
                if (tempRectangle.isHit(mouseEvent.getX(), mouseEvent.getY())) {
                    System.out.println("Hit rectangle!");
                    return i;
                }
            } else if (figure instanceof FXPolygon tempPolygon) {
                if (tempPolygon.isHit(mouseEvent.getX(), mouseEvent.getY())) {
                    System.out.println("Hit polygon!");
                    return i;
                }
            }
        }
        return -1;
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

    protected void moveFigure(int index, double dx, double dy) {}
}