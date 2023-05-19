package com.example.jpaint2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.lang.Math;
import java.util.ArrayList;

/*
* Handles circle creation and managing circle properties
*
* @author Roman Szlachtun
 */
public class CircleFactory extends Circle {
    public CircleFactory(ArrayList<Double> convertedPoints, Color color) {
        super(convertedPoints.get(0), convertedPoints.get(1), distance(convertedPoints));
        setFill(color);
        setStroke(Color.BLACK);
        setStrokeWidth(3);
    }

    static Double distance(ArrayList<Double> points) {
        Double[] a = {points.get(0), points.get(1)};
        Double[] b = {points.get(2), points.get(3)};
        return Math.sqrt(Math.pow(b[0] - a[0], 2) + Math.pow(b[1] - a[1], 2));
    }

    public boolean isHit(Double x, Double y) {
        return getBoundsInLocal().contains(x, y);
    }

    public void addX(Double x) {
        if (!(this.getCenterX() - this.getRadius() + x < getStrokeWidth()))
            this.setCenterX(this.getCenterX() + x);
    }

    public void addY(Double y) {
        if (!(this.getCenterY() - this.getRadius() + y < getStrokeWidth()))
            this.setCenterY(this.getCenterY() + y);
    }

    public void scale(Double k) {
        if (!(this.getCenterX() - this.getRadius() * k < getStrokeWidth() ||
                this.getCenterY() - this.getRadius() * k < getStrokeWidth()))
            this.setRadius(this.getRadius() * k);
    }
}
