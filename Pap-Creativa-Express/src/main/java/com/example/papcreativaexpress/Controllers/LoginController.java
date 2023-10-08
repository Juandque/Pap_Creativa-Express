package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Excepciones.CorreoNoExisteException;
import com.example.papcreativaexpress.HelloApplication;
import com.example.papcreativaexpress.Model.Usuario;
import com.example.papcreativaexpress.Utils.MensajeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController  implements Initializable{
    ModelFactoryController modelFactoryController;
    @FXML
    private ImageView fondoImageView;
    @FXML
    private ImageView usuarioImageView;

    @FXML
    private ImageView contraseniaImageView;

    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnRecuperarContrasenia;

    @FXML
    private TextField tfContrasenia;

    @FXML
    private TextField tfCorreoElectronico;

    @FXML
    void OnActionRegistrar(ActionEvent event) throws IOException {
        cambiarVentana("Registro.fxml","Registro",2,2);

    }

    @FXML
    void OnActionIngresar(ActionEvent event) throws IOException, CorreoNoExisteException {
        if(!tfContrasenia.getText().isEmpty()||!tfCorreoElectronico.getText().isEmpty()){
            if(modelFactoryController.verificarInicioSesion(tfCorreoElectronico.getText(), tfContrasenia.getText())){
                cambiarVentana("Inventario.fxml","Inventario",760,600);
            }else {
                int intentosFallidos = modelFactoryController.obtenerIntentosFallidos(tfCorreoElectronico.getText());
                if(intentosFallidos>=3){
                    modelFactoryController.bloquearUsuario(tfCorreoElectronico.getText());
                    MensajeUtil.mostrarMensaje("Bloqueado","Ha superado el número de intentos posibles, su cuenta ha sido bloqueada","Comuniquese con el administrador", Alert.AlertType.ERROR);
                }
                else {
                    MensajeUtil.mensajeInformacion("Credenciales incorrectas, número de intentos restantes:" + intentosFallidos);
                }
            }
        }else {
            MensajeUtil.mensajeInformacion("Por favor llene los campos");
        }
    }

    @FXML
    void OnActionRecuperarContrasenia(ActionEvent event) throws IOException {
        cambiarVentana("ContraseniaAdmin.fxml","Recuperación de contraseña",600,400);

    }
    private void cambiarVentana(String fxml, String titulo, int ancho, int largo) throws IOException {
        Parent parent = FXMLLoader.load(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(parent, ancho, largo);
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagenes/Papelería.png")));
        scene.getStylesheets().add(getClass().getResource("/Css/Estilos.css").toExternalForm());


        Stage ventanaActual = (Stage) btnIngresar.getScene().getWindow();

        ventanaActual.close();

        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelFactoryController = ModelFactoryController.getInstance();
        try {
            modelFactoryController=ModelFactoryController.getInstance();
            Image fondoImage = new Image(getClass().getResourceAsStream("/Imagenes/FondoLogin.jpg"));
            fondoImageView.setImage(fondoImage);
            Image usuarioImage = new Image(getClass().getResourceAsStream("/Imagenes/Usuario.png"));
            usuarioImageView.setImage(usuarioImage);
            Image contraseniaImage = new Image(getClass().getResourceAsStream("/Imagenes/Contrasenia.png"));
            contraseniaImageView.setImage(contraseniaImage);
        } catch (NullPointerException e) {
            System.err.println("No se pudo cargar la imagen" + e.getMessage());
        }
        tfCorreoElectronico.getStyleClass().add("text-field");
        tfContrasenia.getStyleClass().add("text-field");
        btnIngresar.getStyleClass().add("button");
        btnRegistrar.getStyleClass().add("button");

    }
}
