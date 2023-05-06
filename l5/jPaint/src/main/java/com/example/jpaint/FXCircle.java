package com.example.jpaint;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.lang.Math;

public class FXCircle extends Circle {

    public FXCircle(double[] a, double[] b) {
        super(a[0], a[1], Math.sqrt(Math.pow(b[0] - a[0], 2) + Math.pow(b[1] - a[1], 2)));
        setFill(Color.VIOLET);
    }
}
