package com.example.jpaint;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class FXRectangle extends Polygon {

    public FXRectangle(double[] pointArray) {
        super(pointArray);
        setFill(Color.GREEN);
        setStrokeWidth(3);
    }

    public FXRectangle(ArrayList<Double> serialArray) {
        super(serialArray.get(0), serialArray.get(1), serialArray.get(2), serialArray.get(3));
        setFill(Color.GREEN);
        setStrokeWidth(3);
    }


    public void addX(double dx) {
        var points = this.getPoints();
        for (int i = 0; i < points.size(); i += 2) {
            points.set(i, points.get(i) + dx);
        }
    }

    public void addY(double dy) {
        var points = this.getPoints();
        for (int i = 1; i < points.size(); i += 2) {
            points.set(i, points.get(i) + dy);
        }
    }

    public boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x, y);
    }

    public double center(char axis) {
        int offset;

        if (axis == 'y') offset = 1;
        else if (axis == 'x') offset = 0;
        else throw new RuntimeException();

        double result = 0;
        var points = this.getPoints();

        for (int i = offset; i < points.size(); i += 2) {
            result += points.get(i);
        }

        return result / (points.size() / 2.0);
    }

    public void rotate(double angle) {
        double centerX = center('x');
        double centerY = center('y');

        var points = this.getPoints();

        for (int i = 0; i < points.size(); i += 2) {
            double x1 = points.get(i) - centerX;
            double y1 = (points.get(i + 1) - centerY);

            double x2 = x1 * Math.cos(Math.toRadians(angle)) - y1 * Math.sin(Math.toRadians(angle));
            double y2 = x1 * Math.sin(Math.toRadians(angle)) + y1 * Math.cos(Math.toRadians(angle));

            points.set(i, x2 + centerX);
            points.set(i + 1, y2 + centerY);
        }
    }

    public void scale(double k) {
        double centerX = center('x');
        double centerY = center('y');

        var points = this.getPoints();

        for (int i = 0; i < points.size(); i += 2) {
            double dx = centerX - points.get(i);
            double xScaled = centerX + dx * k;

            double dy = centerY - points.get(i + 1);
            double yScaled = centerY + dy * k;

            points.set(i, xScaled);
            points.set(i + 1, yScaled);
        }
    }
}
