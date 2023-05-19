package com.example.jpaint2;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

/*
* Handles general figure operations in functions
* Determines figure type and run appropriate method
*
* @author Roman Szlachtun
 */
public class Draw {
    static void scaleFigure(ScrollEvent scrollEvent, Logic controller) {
        int index = controller.getLockedFigureIndex();
        if (index != -1) {
            Shape figure = controller.getCreatedFigures().get(index);

            if (figure instanceof CircleFactory circle) {
                circle.scale(1 + scrollEvent.getDeltaY() / 80.0);
            } else if (figure instanceof RectangleFactory rectangle) {
                rectangle.scale(1 + scrollEvent.getDeltaY() / 80.0);
            } else if (figure instanceof PolygonFactory polygon) {
                polygon.scale(1 + scrollEvent.getDeltaY() / 80);
            }
        }
    }

    static void moveFigure(MouseEvent mouseEvent, Logic controller) {
        Shape figure = controller.getCreatedFigures().get(controller.getLockedFigureIndex());
        ArrayList<Double> lastClickedPos = controller.getLastClickedPos();
        double dx = mouseEvent.getX() - lastClickedPos.get(0);
        double dy = mouseEvent.getY() - lastClickedPos.get(1);
        controller.setLastClickedPos(lastClickedPos.get(0) + dx, lastClickedPos.get(1) + dy);

        if (figure instanceof CircleFactory circle) {
            circle.addX(dx);
            circle.addY(dy);
        } else if (figure instanceof RectangleFactory rectangle) {
            rectangle.addX(dx);
            rectangle.addY(dy);
        } else if (figure instanceof PolygonFactory polygon) {
            polygon.addX(dx);
            polygon.addY(dy);
        } else
            throw new RuntimeException();
    }

    static void rotateFigure(MouseEvent mouseEvent, Logic controller) {

        Shape figure = controller.getCreatedFigures().get(controller.getLockedFigureIndex());
        ArrayList<Double> lastClickedPos = controller.getLastClickedPos();

        if (figure instanceof PolygonFactory polygon) {
            Double[] center = polygon.getCenter();
            double ax = lastClickedPos.get(0) - center[0];
            double ay = lastClickedPos.get(1) - center[1];
            double ak = ay / ax;

            double bx = mouseEvent.getX() - center[0];
            double by = mouseEvent.getY() - center[1];
            double bk = by / bx;

            double num = ax * bx + ay * by;
            double den = Math.sqrt(ax * ax + ay * ay) * Math.sqrt(bx * bx + by * by);

            if ((ay / ax) / (by / bx) > 1.000001 || (ay / ax) / (by / bx) < 0.999999) {
                double radianAngle = Math.acos(num / den);
                if (ak > bk)
                    polygon.rotate(-radianAngle);
                else
                    polygon.rotate(radianAngle);

                controller.setLastClickedPos(mouseEvent.getX(), mouseEvent.getY());
            }
        }
    }
}
