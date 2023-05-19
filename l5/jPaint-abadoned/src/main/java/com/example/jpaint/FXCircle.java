package com.example.jpaint;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.lang.Math;
import java.util.ArrayList;

public class FXCircle extends Circle {

    public FXCircle(double[][] convertedPoints) {
        super(convertedPoints[0][0], convertedPoints[0][1], distance(convertedPoints[0], convertedPoints[1]));
        setFill(Color.YELLOW);
        setStrokeWidth(3);
    }

    public FXCircle(ArrayList<Double> serialArray) {
        super(serialArray.get(0), serialArray.get(1), serialArray.get(2));
        setFill(Color.YELLOW);
        setStrokeWidth(3);
    }
    static double[][] convertPoints(ArrayList<double[]> input) {
        return new double[][]{input.get(0), input.get(1)};
    }

    static double distance(double[] a, double[] b) {
        return Math.sqrt(Math.pow(b[0] - a[0], 2) + Math.pow(b[1] - a[1], 2));
    }

    public boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x, y);
    }

    public void addX(double x) {
        this.setCenterX(this.getCenterX() + x);
    }

    public void addY(double y) {
        this.setCenterY(this.getCenterY() + y);
    }

    public void scale(double k) {
        this.setRadius(this.getRadius() * k);
    }

}
