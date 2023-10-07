package com.example.papcreativaexpress.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MensajeUtil {

    public static void mensajeInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
    public static boolean mostrarMensajeConfirmacion(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmaci�n");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}

