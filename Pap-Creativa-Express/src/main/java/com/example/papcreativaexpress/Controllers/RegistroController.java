package com.example.papcreativaexpress.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroController implements Initializable {

    @FXML
    private Button btnCargarImagen;
    @FXML
    private Button btnRegistrar;

    @FXML
    private ImageView imageUsuario;

    @FXML
    private TextField tfCargo;

    @FXML
    private TextField tfContrasenia;

    @FXML
    private TextField tfDireccion;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfNombreUsuario;

    @FXML
    private TextField tfTelefono;
    private final FileChooser fileChooser = new FileChooser();


    @FXML
    void OnActionCargar(ActionEvent event) {
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageUsuario.setImage(image);

        }
    }

    @FXML
    void OnActionRegistrar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Image fondoImage = new Image(getClass().getResourceAsStream("/Imagenes/Usuario.png"));
            imageUsuario.setImage(fondoImage);
        }
        catch (NullPointerException e){
            e.getMessage();
        }


            tfContrasenia.getStyleClass().add("text-field");
            tfCargo.getStyleClass().add("text-field");
            tfDireccion.getStyleClass().add("text-field");
            tfId.getStyleClass().add("text-field");
            tfEmail.getStyleClass().add("text-field");
            tfNombre.getStyleClass().add("text-field");
            tfNombreUsuario.getStyleClass().add("text-field");
            tfTelefono.getStyleClass().add("text-field");
            btnCargarImagen.getStyleClass().add("button");
            btnRegistrar.getStyleClass().add("button");


            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg")
            );

        }
    }

