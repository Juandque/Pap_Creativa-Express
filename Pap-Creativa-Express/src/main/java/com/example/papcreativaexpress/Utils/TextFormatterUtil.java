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

    public static Change doubleFormat(Change change){
        String newText = change.getControlNewText();
        double newValue = Double.parseDouble(newText);
        if (!Double.isNaN(newValue) && !Double.isInfinite(newValue)) {
            return change;
        }
        return null;
    }
}
