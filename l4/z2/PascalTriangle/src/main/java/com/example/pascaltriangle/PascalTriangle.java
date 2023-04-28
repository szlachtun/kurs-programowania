package com.example.pascaltriangle;

import java.util.ArrayList;
import java.io.*;

public class PascalTriangle {
    private final int lineCount;
    private int maxLineLength;
    private final ArrayList<String> lineStringList = new ArrayList<String>();

    PascalTriangle(int lineCount) throws IOException {
        this.lineCount = lineCount;

        for (int i = 0; i <= lineCount; i++) {
            String path = "/home/romka/Desktop/pwr/kp/tasklist/l4/z2/PascalTriangle/src/main/java/com/example/pascaltriangle/z2/z2 ".concat(Integer.toString(i));
            Process p = Runtime.getRuntime().exec(path);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                lineStringList.add(line);
            }
            maxLineLength = lineStringList.get(lineStringList.size() - 1).length();
        }
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
