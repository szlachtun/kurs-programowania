package com.example.jpaint;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class FXPolygon extends Polygon {
    public FXPolygon(double[] convertedPoints) {
        super(convertedPoints);
        setFill(Color.BLUE);
    }

    static double[] convertPoints(ArrayList<double[]> input) {
        ArrayList<Double> oneDimension = new ArrayList<>();
        for (var pair : input) {
            oneDimension.add(pair[0]);
            oneDimension.add(pair[1]);
        }

        double[] result = new double[oneDimension.size()];
        for (int i = 0; i < oneDimension.size(); i++) {
            result[i] = oneDimension.get(i);
        }
        return result;
    }

    public boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x, y);
    }

    public double[] addX(double[] points, double dx) {
        for (int i = 0; i < points.length; i+=2) {
            points[i] += dx;
        }
        return points;
    }

    public double[] addY(double[] points, double dy) {
        for (int i = 1; i < points.length; i+=2) {
            points[i] += dy;
        }
        return points;
    }
}
