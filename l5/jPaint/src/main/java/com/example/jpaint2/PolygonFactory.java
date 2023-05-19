package com.example.jpaint2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

/*
 * Handles polygon creation and managing polygon properties
 *
 * @author Roman Szlachtun
 */
public class PolygonFactory extends Polygon {
    public PolygonFactory(ArrayList<Double> points, Color color) {
        super(points.stream().mapToDouble(i -> i).toArray());
        setFill(color);
        setStroke(Color.BLACK);
        setStrokeWidth(3);
    }

    public boolean isHit(Double x, Double y) {
        return getBoundsInLocal().contains(x, y);
    }

    public void addX(Double dx) {
        var points = getPoints();
        var pointsBackup = new ArrayList<>(points);

        for (int i = 0; i < getPoints().size(); i += 2) {
            if (getPoints().get(i) + dx < getStrokeWidth() / 2) {
                points.setAll(pointsBackup);
                return;
            }
            points.set(i, getPoints().get(i) + dx);
        }
    }

    public void addY(Double dy) {
        var points = getPoints();
        var pointsBackup = new ArrayList<>(points);

        for (int i = 1; i < getPoints().size(); i += 2) {
            if (getPoints().get(i) + dy < getStrokeWidth() / 2) {
                points.setAll(pointsBackup);
                return;
            }
            points.set(i, getPoints().get(i) + dy);
        }
    }

    public Double[] getCenter() {
        double xSum = 0, ySum = 0;
        var points = getPoints();
        for (int i = 0; i < points.size(); i += 2) {
            xSum += points.get(i);
            ySum += points.get(i + 1);
        }
        return new Double[]{xSum / (getPoints().size() / 2.0), ySum / (getPoints().size() / 2.0)};
    }

    public void rotate(Double radianAngle) {
        Double[] centerPoint = getCenter();
        var points = getPoints();
        var pointsBackup = new ArrayList<>(points);
        for (int i = 0; i < points.size(); i += 2) {
            double x1 = points.get(i) - centerPoint[0];
            double y1 = (points.get(i + 1) - centerPoint[1]);

            double x2 = x1 * Math.cos(radianAngle) - y1 * Math.sin(radianAngle);
            double y2 = x1 * Math.sin(radianAngle) + y1 * Math.cos(radianAngle);

            if (x2 + centerPoint[0] < getStrokeWidth() / 2 || y2 + centerPoint[1] < getStrokeWidth() / 2) {
                points.setAll(pointsBackup);
                return;
            }

            points.set(i, x2 + centerPoint[0]);
            points.set(i + 1, y2 + centerPoint[1]);
        }
    }

    public void scale(double k) {
        Double[] centerPoint = getCenter();
        var points = getPoints();
        var pointsBackup = new ArrayList<>(points);
        for (int i = 0; i < points.size(); i += 2) {
            double dx = points.get(i) - centerPoint[0];
            double xScaled = centerPoint[0] + dx * k;

            double dy =  points.get(i + 1) - centerPoint[1];
            double yScaled = centerPoint[1] + dy * k ;
            if (xScaled < 0 || yScaled < 0) {
                points.setAll(pointsBackup);
                return;
            }
            points.set(i, xScaled);
            points.set(i + 1, yScaled);
        }
    }
}
