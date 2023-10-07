package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.HelloApplication;
import com.example.papcreativaexpress.Model.Usuario;
import com.example.papcreativaexpress.Model.ValidarContrasenia;
import com.example.papcreativaexpress.Utils.MensajeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContraseniaAdminController implements Initializable {
    ModelFactoryController modelFactoryController;

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnGenerarCodigo;

    @FXML
    private ImageView imagenSeguridad;

    @FXML
    private TextField tfContrasenia;

    @FXML
    private TextField tfContrasenia2;

    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfCorreo;
    private Usuario usuarioActual;
    @FXML
    private SplitPane splitPlane;


    @FXML
    void OnActionActualizar(ActionEvent event) {
        if (!tfContrasenia.getText().isEmpty() || !tfContrasenia2.getText().isEmpty()) {
            if (ValidarContrasenia.validate(tfContrasenia.getText())) {
                if (ValidarContrasenia.validate(tfContrasenia2.getText())) {
                    if (tfContrasenia.getText().equals(tfContrasenia2.getText())) {
                        String correo = usuarioActual.getEmail();
                        if (correo != null) {
                            Usuario usuario = modelFactoryController.ObtenerUsuario(correo);
                            modelFactoryController.actualizarContrasenia(usuario);
                        } else {
                            MensajeUtil.mensajeInformacion("Debe ingresar un correo válido");
                        }
                    } else {
                        MensajeUtil.mostrarMensaje("Error", "Cambiar contraseña", "Sus contraseñas no coinciden", Alert.AlertType.ERROR);
                    }
                } else {
                    MensajeUtil.mensajeInformacion("Su contraseña no cumple con los parámetros");
                }
            } else {
                MensajeUtil.mensajeInformacion("Su contraseña no cumple con los parámetros");

            }
        } else {
            MensajeUtil.mensajeInformacion("Por favor rellene los campos");
        }
    }


    @FXML
    void OnActionGenerarCodigo(ActionEvent event) {

    }

    @FXML
    void OnActionIngresar(ActionEvent event) {
        if (!tfCodigo.getText().isEmpty()) {
            usuarioActual = modelFactoryController.ObtenerUsuario(tfCorreo.getText());
            if (usuarioActual != null) {
                if (usuarioActual.getContrasenia().equals(tfCodigo.getText())) {
                    splitPlane.getItems().get(1).setDisable(false);
                } else splitPlane.getItems().get(1).setDisable(true);
            } else {
                MensajeUtil.mensajeInformacion("Su dirección de correo no ha sido encontrada");
            }

        } else {
            MensajeUtil.mensajeInformacion("Por favor digite el código");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Image seguridadImagen = new Image(getClass().getResourceAsStream("/Imagenes/Security.png"));
            imagenSeguridad.setImage(seguridadImagen);
        } catch (NullPointerException e) {
            System.err.println("No se pudo cargar la imagen" + e.getMessage());
        }
        tfContrasenia.getStyleClass().add("text-field");
        tfCorreo.getStyleClass().add("text-field");
        tfContrasenia2.getStyleClass().add("text-field");
        tfCodigo.getStyleClass().add("text-field");
        btnActualizar.getStyleClass().add("button");
        btnGenerarCodigo.getStyleClass().add("button");

    }

    private void cambiarVentana(String fxml, String titulo, int ancho, int largo) throws IOException {
        Parent parent = FXMLLoader.load(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(parent, ancho, largo);
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
    }

}


