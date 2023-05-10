package com.example.jpaint;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.lang.Math;

public class FXCircle extends Circle {

    public FXCircle(double[] a, double[] b) {
        super(a[0], a[1], distance(a, b));
        setFill(Color.RED);
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

}
