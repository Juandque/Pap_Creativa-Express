package com.example.papcreativaexpress.Utils;

import javafx.scene.control.TextFormatter.Change;


public class TextFormatterUtil {
    public static Change upperCaseFormat(Change change){
        change.setText(change.getText().toUpperCase());
        return change;
    }

    public static Change integerFormat(Change change){
        if (change.getText().matches("[0-9]*")) {
            return change;
        }
        return null;
    }

    public static Change doubleFormat(Change change) {
        String newText = change.getControlNewText();
        if (newText.matches("-?\\d*(\\.\\d*)?")) {
            return change;
        }
        return null;
    }
}
