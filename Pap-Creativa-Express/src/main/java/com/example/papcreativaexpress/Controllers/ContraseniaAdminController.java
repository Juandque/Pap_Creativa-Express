package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.HelloApplication;
import com.example.papcreativaexpress.Model.Usuario;
import com.example.papcreativaexpress.Model.ValidarContrasenia;
import com.example.papcreativaexpress.Utils.MensajeUtil;
import javafx.application.Platform;
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
import javafx.scene.text.Text;
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
    @FXML
    private SplitPane splitPlane;
    private String codigoGenerado;
    @FXML
    private Text uppercaseText;
    @FXML
    private Text lowercaseText;
    @FXML
    private Text digitText;
    @FXML
    private Text specialCharText;
    @FXML
    private Text tamanio;


    @FXML
    void OnActionActualizar(ActionEvent event) throws IOException {
        if (!tfContrasenia.getText().isEmpty() || !tfContrasenia2.getText().isEmpty()) {
            if (ValidarContrasenia.validate(tfContrasenia.getText())) {
                if (ValidarContrasenia.validate(tfContrasenia2.getText())) {
                    if (tfContrasenia.getText().equals(tfContrasenia2.getText())) {
                        String correo = tfCorreo.getText();
                        if (correo != null) {
                            Usuario usuario = modelFactoryController.ObtenerUsuario(correo);
                            if (validarContrasenia(tfContrasenia.getText())&&validarContrasenia(tfContrasenia2.getText())) {
                                modelFactoryController.actualizarContrasenia(usuario, tfContrasenia.getText());
                                if (MensajeUtil.mostrarMensajeConfirmacion("¿Deseas cambiar tu contraseña?")) {
                                    MensajeUtil.mensajeInformacion("Su contraseña se ha cambiado exitosamente");
                                    cambiarVentana("Login.fxml", "Login", 630, 420);
                                }
                            } else {
                                MensajeUtil.mensajeInformacion("Su contraseña no cumple con los parámetros");
                            }
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
        Usuario correoNoExistente = modelFactoryController.ObtenerUsuario(tfCorreo.getText());
        if (correoNoExistente != null) {
            String codigo = modelFactoryController.generarCodigo();
            String asunto = "Código recuperación de contraseña";
            String destinatario = tfCorreo.getText();
            String remitente = "papcreativaexpress@gmail.com";
            String cuerpo = "Cordial salud, su código de recuperación de cuenta es: " + codigo;
            modelFactoryController.enviarCorreo(remitente, destinatario, asunto, cuerpo);
            MensajeUtil.mensajeInformacion("Hemos enviado el código a tu correo");

            this.codigoGenerado = codigo;
        } else {
            MensajeUtil.mensajeInformacion("No se ha encontrado una cuenta asociada con ese correo");
        }
    }

    // En el método OnActionIngresar:
    @FXML
    void OnActionIngresar(ActionEvent event) {
        if (!tfCodigo.getText().isEmpty()) {
            if (tfCodigo.getText().equals(codigoGenerado)) {
                splitPlane.getItems().get(1).setDisable(false);
            } else {
                splitPlane.getItems().get(1).setDisable(true);
            }
        } else {
            MensajeUtil.mensajeInformacion("Por favor digite el código");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelFactoryController = ModelFactoryController.getInstance();
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
        lowercaseText.getStyleClass().add("texto-rojo");
        uppercaseText.getStyleClass().add("texto-rojo");
        digitText.getStyleClass().add("texto-rojo");
        specialCharText.getStyleClass().add("texto-rojo");
        tamanio.getStyleClass().add("texto-rojo");


        tfContrasenia.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = validarContrasenia(newValue);
            aplicarEstilosNodoTexto(uppercaseText, valid);
            aplicarEstilosNodoTexto(lowercaseText, valid);
            aplicarEstilosNodoTexto(digitText, valid);
            aplicarEstilosNodoTexto(specialCharText, valid);
            aplicarEstilosNodoTexto(tamanio,valid);

        });
        tfContrasenia2.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = validarContrasenia(newValue);
            aplicarEstilosNodoTexto(uppercaseText, valid);
            aplicarEstilosNodoTexto(lowercaseText, valid);
            aplicarEstilosNodoTexto(digitText, valid);
            aplicarEstilosNodoTexto(specialCharText, valid);
            aplicarEstilosNodoTexto(tamanio,valid);

        });


    }

    private void cambiarVentana(String fxml, String titulo, int ancho, int largo) throws IOException {
        Parent parent = FXMLLoader.load(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(parent, ancho, largo);
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagenes/Papelería.png")));
        scene.getStylesheets().add(getClass().getResource("/Css/Estilos.css").toExternalForm());


        Stage ventanaActual = (Stage) btnActualizar.getScene().getWindow();

        ventanaActual.close();

        stage.show();
    }

    private boolean validarContrasenia(String contrasenia) {
        boolean uppercase = contrasenia.matches(".*[A-Z].*");
        boolean lowercase = contrasenia.matches(".*[a-z].*");
        boolean digit = contrasenia.matches(".*\\d.*");
        boolean specialChar = contrasenia.matches(".*[^A-Za-z0-9].*");
        boolean length = contrasenia.length()==8;

        aplicarEstilosNodoTexto(uppercaseText, uppercase);
        aplicarEstilosNodoTexto(lowercaseText, lowercase);
        aplicarEstilosNodoTexto(digitText, digit);
        aplicarEstilosNodoTexto(specialCharText, specialChar);
        aplicarEstilosNodoTexto(tamanio,length);

        return uppercase && lowercase && digit && specialChar && contrasenia.length() >= 8;
    }

    private void aplicarEstilosNodoTexto(Text nodoTexto, boolean condicion) {
        if (condicion) {
            nodoTexto.getStyleClass().clear(); // Limpiar clases de estilo previas
            nodoTexto.getStyleClass().add("texto-verde"); // Agregar clase de estilo para texto verde
        }
    }
}


