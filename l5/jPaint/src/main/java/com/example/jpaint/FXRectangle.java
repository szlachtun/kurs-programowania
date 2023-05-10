package com.example.jpaint;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.abs;

public class FXRectangle extends Rectangle {

    public FXRectangle(double[][] pointArray) {
        super(pointArray[0][0], pointArray[0][1], abs(pointArray[1][0] - pointArray[0][0]), abs(pointArray[1][1] - pointArray[0][1]));
        setFill(Color.GREEN);
    }

    static double[][] convertPoints(double[] a, double[] b) {
        double[][] result = new double[2][2];

        if (a[0] >= b[0] & a[1] >= b[1]) {
            result[0] = new double[]{b[0], b[1]};
            result[1] = new double[]{a[0], a[1]};
        } else if (a[0] >= b[0] & a[1] < b[1]) {
            result[0] = new double[]{b[0], a[1]};
            result[1] = new double[]{a[0], b[1]};
        } else if (a[1] >= b[1]) {
            result[0] = new double[]{a[0], b[1]};
            result[1] = new double[]{b[0], a[1]};
        } else {
            result[0] = new double[]{a[0], a[1]};
            result[1] = new double[]{b[0], b[1]};
        }

        return result;
    }

    public void addX(double x) {
        setX(getX() + x);
    }

    public void addY(double y) {
        setY(getY() + y);
    }

    public boolean isHit(double x, double y) {return getBoundsInLocal().contains(x, y);}
}
