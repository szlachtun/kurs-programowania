package com.example.jpaint2;

import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

/*
* Manages figures, figure properties like size, colour and UI controllers
*
* @author Roman Szlachtun
 */
public class Logic {
    private ArrayList<Shape> createdFigures;
    private ArrayList<Double> lastClickedPos;
    private String lastClickedButton;
    private ArrayList<Double> clickedPos;
    private int lockedFigureIndex;
    private ToggleButton circleButton, rectangleButton, polygonButton;
    private Spinner<Integer> sideCountSpinner;
    private Color selectedColor;

    public Logic() {
        this.createdFigures = new ArrayList<>();
        this.lastClickedPos = new ArrayList<>();
        this.clickedPos = new ArrayList<>();
        this.lastClickedButton = "nothing";
        this.lockedFigureIndex = -1;
    }

    public void setControllers(ToggleButton circleButton, ToggleButton rectangleButton, ToggleButton polygonButton, Spinner<Integer> spinner) {
        this.circleButton = circleButton;
        this.rectangleButton = rectangleButton;
        this.polygonButton = polygonButton;
        this.sideCountSpinner = spinner;
        this.selectedColor = Color.GOLD;

        ToggleGroup group = new ToggleGroup();
        this.circleButton.setToggleGroup(group);
        this.rectangleButton.setToggleGroup(group);
        this.polygonButton.setToggleGroup(group);
    }

    public String getLastClickedButton() {
        return this.lastClickedButton;
    }

    public void setLastClickedButton(String lastClickedButton) {
        this.lastClickedButton = lastClickedButton;
    }

    public int getSpinnerCount() {
        return this.sideCountSpinner.getValue();
    }

    public ArrayList<Shape> getCreatedFigures() {
        return this.createdFigures;
    }

    public void addCreatedFigures(Shape figure) {
        this.createdFigures.add(figure);
    }

    public void setCreatedFigures(ArrayList<Shape> createdFigures) {
        this.createdFigures = createdFigures;
    }

    public ArrayList<Double> getClickedPos() {
        return this.clickedPos;
    }

    public void addClickedPos(Double x, Double y) {
        this.clickedPos.add(x);
        this.clickedPos.add(y);
    }

    public void resetClickedPos() {
        this.clickedPos = new ArrayList<>();
    }

    public String getSelectedFigure() {
        boolean cState = circleButton.isSelected();
        boolean rState = rectangleButton.isSelected();
        boolean pState = polygonButton.isSelected();

        if (cState && !rState && !pState)
            return "circle";
        else if (!cState && rState && !pState)
            return "rectangle";
        else if (!cState && !rState && pState)
            return "polygon";
        else
            return "nothing";
    }

    public int getHitFigureIndex(Double x, Double y) {
        for (int i = getCreatedFigures().size() - 1; i >= 0; i--) {
            Shape figure = getCreatedFigures().get(i);
            if (figure instanceof CircleFactory circle) {
                if (circle.isHit(x, y))
                    return i;
            } else if (figure instanceof RectangleFactory rectangle) {
                if (rectangle.isHit(x, y))
                    return i;
            } else if (figure instanceof PolygonFactory polygon) {
                if (polygon.isHit(x, y))
                    return i;
            }
        }
        return -1;
    }

    public int getLockedFigureIndex() {
        return this.lockedFigureIndex;
    }

    public void setLockedFigureIndex(int i) {
        if (i != -1) {  // creation
            if (getLockedFigureIndex() != -1) {
                Shape figure = getCreatedFigures().get(getLockedFigureIndex());
                figure.setStroke(Color.BLACK);
            }
            this.lockedFigureIndex = i;
            getCreatedFigures().get(i).setStroke(Color.RED);
        } else {  // deletion
            if (getLockedFigureIndex() != -1) {
                Shape figure = getCreatedFigures().get(getLockedFigureIndex());
                figure.setStroke(Color.BLACK);
                this.lockedFigureIndex = i;
            }
        }
    }

    public void setLastClickedPos(Double x, Double y) {
        ArrayList<Double> lastClickedPos = new ArrayList<>();
        lastClickedPos.add(x);
        lastClickedPos.add(y);
        this.lastClickedPos = lastClickedPos;
    }

    public ArrayList<Double> getLastClickedPos() {
        return lastClickedPos;
    }
    public void resetLastClickedPos() {
        this.lastClickedPos = new ArrayList<>();
    }
    public Color getSelectedColor(){
        return this.selectedColor;
    }
    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }
    public void setFigureColor(int lockedFigureIndex, Color selectedColor) {
        if (lockedFigureIndex != -1) {
            Shape figure = getCreatedFigures().get(lockedFigureIndex);
            figure.setFill(selectedColor);
            System.out.println("paints");
        }
    }
}
