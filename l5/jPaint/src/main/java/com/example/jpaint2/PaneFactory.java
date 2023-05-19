package com.example.jpaint2;

import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;

import java.util.Objects;

/*
 * Handles drawing pane creation
 *
 * @author Roman Szlachtun
 */
public class PaneFactory {
    static Pane create(Logic controller, ColorPicker picker) {
        Pane pane = new Pane();
        pane.setPrefSize(600, 600);
        HBox.setHgrow(pane, Priority.ALWAYS);

        EventHandler<MouseEvent> mousePaneClickDown = mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                System.out.println("Click");
                // clear mouse clicked position array if figure type was changed
                if (!Objects.equals(controller.getLastClickedButton(), controller.getSelectedFigure())) {
                    controller.setLastClickedButton(controller.getSelectedFigure());
                    controller.resetClickedPos();
                }

                // add hook position for moving or rotating figure
                controller.setLastClickedPos(mouseEvent.getX(), mouseEvent.getY());

                switch (controller.getSelectedFigure()) {
                    case "nothing" -> {
                        if (!mouseEvent.isShiftDown() && !mouseEvent.isControlDown()) {
                            int lockedFigureIndex = controller.getHitFigureIndex(mouseEvent.getX(), mouseEvent.getY());
                            controller.setLockedFigureIndex(lockedFigureIndex);
                        }

                    }
                    case "circle" -> {
                        controller.addClickedPos(mouseEvent.getX(), mouseEvent.getY());
                        if (controller.getClickedPos().size() == 4) {
                            if (controller.getClickedPos().get(0) >= CircleFactory.distance(controller.getClickedPos()) &&
                                controller.getClickedPos().get(1) >= CircleFactory.distance(controller.getClickedPos())) {
                                CircleFactory circle = new CircleFactory(controller.getClickedPos(), controller.getSelectedColor());
                                controller.addCreatedFigures(circle);
                                controller.setLockedFigureIndex(controller.getCreatedFigures().size() - 1);
                                pane.getChildren().add(circle);
                            }
                            controller.resetClickedPos();
                        }
                    }
                    case "rectangle" -> {
                        controller.addClickedPos(mouseEvent.getX(), mouseEvent.getY());
                        if (controller.getClickedPos().size() == 4) {
                            RectangleFactory rectangle = new RectangleFactory(controller.getClickedPos(), controller.getSelectedColor());
                            controller.addCreatedFigures(rectangle);
                            controller.setLockedFigureIndex(controller.getCreatedFigures().size()- 1);
                            pane.getChildren().add(rectangle);
                            controller.resetClickedPos();
                        }
                    }
                    case "polygon" -> {
                        controller.addClickedPos(mouseEvent.getX(), mouseEvent.getY());
                        if (controller.getClickedPos().size() == 2 * controller.getSpinnerCount()) {
                            PolygonFactory polygon = new PolygonFactory(controller.getClickedPos(), controller.getSelectedColor());
                            controller.addCreatedFigures(polygon);
                            controller.setLockedFigureIndex(controller.getCreatedFigures().size() - 1);
                            pane.getChildren().add(polygon);
                            controller.resetClickedPos();
                        }
                    }
                }
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                picker.show();
            }
        };

        EventHandler<MouseEvent> mouseDragEventEventHandler = mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (controller.getLockedFigureIndex() != -1) {
                    if (!mouseEvent.isShiftDown()) {
                        Draw.moveFigure(mouseEvent, controller);
                    } else {
                        Draw.rotateFigure(mouseEvent, controller);
                    }
                }
            }
        };
        EventHandler<MouseEvent> mousePaneClickUp = mouseEvent -> controller.resetLastClickedPos();
        EventHandler<ScrollEvent> mousePaneScroll = scrollEvent -> Draw.scaleFigure(scrollEvent, controller);

        pane.setOnMousePressed(mousePaneClickDown);
        pane.setOnMouseReleased(mousePaneClickUp);
        pane.setOnMouseDragged(mouseDragEventEventHandler);
        pane.setOnScroll(mousePaneScroll);
        return pane;
    }
}
