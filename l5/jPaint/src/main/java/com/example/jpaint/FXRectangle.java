package com.example.jpaint;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class FXRectangle extends Rectangle {

    public FXRectangle(ArrayList<double[]> points) {
        super(points.get(0)[0], points.get(0)[1], abs(points.get(1)[0] - points.get(0)[0]), abs(points.get(1)[1] - points.get(0)[1]));
        setFill(Color.DARKGOLDENROD);
    }

    static ArrayList<double[]> convertPoints(double[] a, double[] b) {
        ArrayList<double[]> result = new ArrayList<>();
        if (a[0] >= b[0] & a[1] >= b[1]) {
            result.add(new double[]{b[0], b[1]});
            result.add(new double[]{a[0], a[1]});
        } else if (a[0] >= b[0] & a[1] < b[1]) {
            result.add(new double[]{b[0], a[1]});
            result.add(new double[]{a[0], b[1]});
        } else if (a[0] < b[0] & a[1] >= b[1]) {
            result.add(new double[]{a[0], b[1]});
            result.add(new double[]{b[0], a[1]});
        } else {
            result.add(new double[]{a[0], a[1]});
            result.add(new double[]{b[0], b[1]});
        }
        return result;
    }
}
