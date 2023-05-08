package com.example.pascaltriangle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FXController {
    @FXML
    private TextArea triangleOutput;
    @FXML
    private TextField countInput;
    private boolean countInputValid = false;
    int parsedCountInteger;

    @FXML
    private Button drawButton;

    @FXML
    private Label stdErr;

    @FXML
    protected void updateCountInput(KeyEvent keyEvent) {
        try {
            parsedCountInteger = Integer.parseInt(countInput.getCharacters().toString());
            if (parsedCountInteger >= 1) {
                stdErr.setText("");
                countInputValid = true;
            }
        } catch (NumberFormatException ex) {
            stdErr.setText("Wrong number!");
            countInputValid = false;
            parsedCountInteger = 0;
        }
    }

    @FXML
    protected void onDrawButtonClick(ActionEvent event) {
        triangleOutput.autosize();
        event.consume();
        if (countInputValid && parsedCountInteger >= 1)
        {
            triangleOutput.setText("");
            PascalTriangle pascalTriangle = new PascalTriangle(parsedCountInteger);
            for(String line : pascalTriangle.getLineStringList())
            {
                int lineLength = line.length();
                triangleOutput.appendText(" ".repeat((pascalTriangle.getMaxLineLength() - lineLength) / 2));
                triangleOutput.appendText(line);
                triangleOutput.appendText(" ".repeat((pascalTriangle.getMaxLineLength() - lineLength) / 2));
                triangleOutput.appendText("\n");
            }
            // triangleOutput.setText(String.join("\n" ,pascalTriangle.getLineStringList()));
        }
    }
}
