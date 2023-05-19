package com.example.jpaint2;

import javafx.scene.paint.Color;
import java.util.ArrayList;

import static java.lang.Math.abs;

/*
 * Handles rectangle creation and managing rectangle properties
 *
 * @author Roman Szlachtun
 */
public class RectangleFactory extends PolygonFactory {
    public RectangleFactory(ArrayList<Double> points, Color color) {
        super(convertPoints(points), color);
    }

    static ArrayList<Double> convertPoints(ArrayList<Double> points) {
        double[][] point = new double[2][2];
        double[] a = new double[]{points.get(0), points.get(1)};
        double[] b = new double[]{points.get(2), points.get(3)};

        if (a[0] >= b[0] & a[1] >= b[1]) {
            point[0] = new double[]{b[0], b[1]};
            point[1] = new double[]{a[0], a[1]};
        } else if (a[0] >= b[0] & a[1] < b[1]) {
            point[0] = new double[]{b[0], a[1]};
            point[1] = new double[]{a[0], b[1]};
        } else if (a[1] >= b[1]) {
            point[0] = new double[]{a[0], b[1]};
            point[1] = new double[]{b[0], a[1]};
        } else {
            point[0] = new double[]{a[0], a[1]};
            point[1] = new double[]{b[0], b[1]};
        }

        double x = abs(point[1][0] - point[0][0]);
        double y = abs(point[1][1] - point[0][1]);

        ArrayList<Double> result = new ArrayList<>();
        result.add(point[0][0]);
        result.add(point[0][1]);

        result.add(point[0][0] + x);
        result.add(point[0][1]);

        result.add(point[1][0]);
        result.add(point[1][1]);

        result.add(point[0][0]);
        result.add(point[0][1] + y);
        return result;
    }
}
