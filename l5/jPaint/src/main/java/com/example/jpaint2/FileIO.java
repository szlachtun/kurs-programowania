package com.example.jpaint2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/*
* Handles figure serialization and deserialization to/from CSV files
*
* @author Roman Szlachtun
 */
public class FileIO {
    static void serialize(ArrayList<Shape> figures, String filePath) throws IOException {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (Shape figure : figures) {
                StringBuilder line = new StringBuilder();
                if (figure instanceof CircleFactory circle) {
                    line.append("circle;");
                    line.append(circle.getFill().toString()).append(";");
                    line.append(circle.getCenterX()).append(";");
                    line.append(circle.getCenterY()).append(";");
                    line.append(circle.getCenterX() + circle.getRadius()).append(";");
                    line.append(circle.getCenterY()).append(";");
                } else if (figure instanceof PolygonFactory polygon) {
                    line.append("polygon;");
                    line.append(polygon.getFill().toString()).append(";");
                    for (double point : polygon.getPoints()) {
                        line.append(point).append(";");
                    }
                } else
                    throw new IOException();
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException exception) {
            System.err.println("Saving file error");
            throw exception;
        }
    }

    static ArrayList<Shape> deserialize(String filePath) {
        ArrayList<Shape> figures = new ArrayList<>();

        Scanner reader;
        try {
            File file = new File(filePath);
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Loading file error");
            throw new RuntimeException(e);
        }

        while (reader.hasNextLine()) {
            String[] args = reader.nextLine().split(";");
            ArrayList<Double> points = new ArrayList<>();
            Color color = Color.valueOf(args[1]);

            for (int i = 2; i < args.length; i++) {
                try {
                    points.add(Double.parseDouble(args[i]));
                } catch (NumberFormatException exception) {
                    System.err.println("Parsing values error");
                }
            }

            if (Objects.equals(args[0], "circle")) {
                if (points.size() != 4) {
                    System.err.println("Wrong circle argument count");
                    throw new NumberFormatException("Wrong circle arguments count");
                } else {
                    CircleFactory figure = new CircleFactory(points, color);
                    figures.add(figure);
                }
            } else if (Objects.equals(args[0], "rectangle")) {
                if (points.size() != 4) {
                    System.err.println("Wrong rectangle argument count");
                    throw new NumberFormatException("Wrong rectangle argument count");
                } else {
                    PolygonFactory figure = new PolygonFactory(points, color);
                    figures.add(figure);
                }
            } else if (Objects.equals(args[0], "polygon")) {
                if (points.size() % 2 != 0) {
                    System.err.println("Wrong polygon argument count");
                    throw new NumberFormatException("Wrong polygon argument count");
                } else {
                    PolygonFactory figure = new PolygonFactory(points, color);
                    figures.add(figure);
                }
            } else
                throw new RuntimeException();
        }
        return figures;
    }
}
