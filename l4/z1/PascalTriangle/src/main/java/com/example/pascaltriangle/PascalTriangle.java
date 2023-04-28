package com.example.pascaltriangle;

import java.util.ArrayList;

public class PascalTriangle {
    private final int lineCount;
    private int maxLineLength;
    private final ArrayList<String> lineStringList = new ArrayList<String>();

    PascalTriangle(int lineCount) {
        this.lineCount = lineCount;
        for (int i = 0; i < this.lineCount; i++) {
            PascalTriangleLine pascalLine = new PascalTriangleLine(i);

            String temp =String.join(" ", pascalLine.getLineNumbers());
            lineStringList.add(temp);
        }
        maxLineLength = lineStringList.get(lineStringList.size() - 1).length();
    }

    int getMaxLineLength() {
        return maxLineLength;
    }

    void setMaxLineLength(int maxLineLength) {
        this.maxLineLength = maxLineLength;
    }

    ArrayList<String> getLineStringList() {
        return lineStringList;
    }

}
