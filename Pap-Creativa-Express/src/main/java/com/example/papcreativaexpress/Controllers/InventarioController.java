package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Model.Usuario;
import com.example.papcreativaexpress.Utils.MensajeUtil;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;





public class InventarioController implements Initializable {
    ModelFactoryController modelFactoryController;
    @FXML
    private ImageView IamgeCodigoBarras;
    
    @FXML
    private ImageView imageEmpleado;

    @FXML
    private ImageView imageEmpleadoRegistro;

    @FXML
    private Button btnActualizarCargo;

    @FXML
    private Button btnActualizarEmpleado;

    @FXML
    private Button btnActualizarLote;

    @FXML
    private Button btnActualizarProveedor;

    @FXML
    private Button btnAnadirCargo;

    @FXML
    private Button btnAnadirEmpleado;

    @FXML
    private Button btnAnadirLote;

    @FXML
    private Button btnAnadirProveedor;

    @FXML
    private Button btnCancelarCargo;

    @FXML
    private Button btnCancelarEmpleado;

    @FXML
    private Button btnCancelarLote;

    @FXML
    private Button btnCancelarProveedor;

    @FXML
    private Button btnCargos;

    @FXML
    private Button btnEliminarCargo;
    @FXML
    private Button btnIsertarImagen;

    @FXML
    private Button btnEliminarEmpleado;

    @FXML
    private Button btnEliminarLote;

    @FXML
    private Button btnEliminarProveedor;

    @FXML
    private Button btnEmpleados;

    @FXML
    private Button btnLotes;

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnProveedores;

    @FXML
    private Button btnVerInfo;
    @FXML
    private Button btnCodigoBrras;

    @FXML
    private ComboBox<?> cbCargoEmpleado;

    @FXML
    private ComboBox<?> cbEstadoEmpleado;

    @FXML
    private ComboBox<?> cbProveedorLote;

    @FXML
    private TableColumn<?, ?> colCantidadEmpleadosCargo;

    @FXML
    private TableColumn<?, ?> colCantidadLote;

    @FXML
    private TableColumn<?, ?> colCantidadProductos;

    @FXML
    private TableColumn<?, ?> colCostoTotalLote;

    @FXML
    private TableColumn<?, ?> colDireccionProveedor;

    @FXML
    private TableColumn<?, ?> colEstadoCargo;

    @FXML
    private TableColumn<?, ?> colEstadoProveedor;

    @FXML
    private TableColumn<?, ?> colFechaEntradaLote;

    @FXML
    private TableColumn<?, ?> colMarca;

    @FXML
    private TableColumn<?, ?> colNombreCargo;

    @FXML
    private TableColumn<?, ?> colNombreContactoProveedor;

    @FXML
    private TableColumn<?, ?> colNombreEmpresaProveedor;

    @FXML
    private TableColumn<?, ?> colNombreProductoLote;

    @FXML
    private TableColumn<?, ?> colPrecioUnitarioLote;

    @FXML
    private TableColumn<?, ?> colProductos;

    @FXML
    private TableColumn<?, ?> colProveedor;

    @FXML
    private TableColumn<?, ?> colSalarioCargo;

    @FXML
    private TableColumn<?, ?> colTelefonoProveedor;

    @FXML
    private DatePicker dpFechaCaducidadProductoLote;

    @FXML
    private Pane paneAdmin;

    @FXML
    private Pane paneCargos;

    @FXML
    private Pane paneLotes;

    @FXML
    private Pane paneProductos;

    @FXML
    private Pane paneProveedores;

    @FXML
    private TableView<?> tableCargos;

    @FXML
    private TableView<?> tableEmpleados;

    @FXML
    private TableView<?> tableLotes;

    @FXML
    private TableView<?> tableProveedores;

    @FXML
    private TableView<?> tblPoductos;

    @FXML
    private TextField tfBuscarProducto;

    @FXML
    private TextField txtCantidadLote;

    @FXML
    private TextField txtComentariosProveedor;

    @FXML
    private TextField txtContraseniaEmpleado;

    @FXML
    private TextField txtCostoProductoLote;

    @FXML
    private TextField txtCostoTotalLote;

    @FXML
    private TextField txtDescripcionProductoLote;

    @FXML
    private TextField txtDireccionEmpleado;

    @FXML
    private TextField txtDireccionProveedor;

    @FXML
    private TextField txtEmailEmpleado;

    @FXML
    private TextField txtEmpleadosRequeridosCargo;

    @FXML
    private TextField txtEstadoCargo;

    @FXML
    private TextField txtEstadoProveedor;

    @FXML
    private TextField txtIdEmpleado;

    @FXML
    private TextField txtMarcaProductoLote;

    @FXML
    private TextField txtNombreCargo;

    @FXML
    private TextField txtNombreContactoProveedor;

    @FXML
    private TextField txtNombreEmpleado;

    @FXML
    private TextField txtNombreEmpresaProveedor;

    @FXML
    private TextField txtNombreProductoLote;

    @FXML
    private TextField txtNombreUsuario;

    @FXML
    private TextField txtPrecioUnitarioLote;

    @FXML
    private TextField txtPrecioVentaProductoLote;

    @FXML
    private TextField txtSalarioCargo;

    @FXML
    private TextField txtTelefonoEmpleado;

    @FXML
    private TextField txtTelefonoProveedor;
    private final FileChooser fileChooser = new FileChooser();
    private Image imagenSeleccionada;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelFactoryController = ModelFactoryController.getInstance();
        Usuario usuarioActual = modelFactoryController.getUsuarioActual();
        if (usuarioActual != null) {
            if (modelFactoryController.getImagenSeleccionada() != null) {
                imageEmpleado.setImage(modelFactoryController.getImagenSeleccionada());
            }
            else {
                System.out.println("Imagen null");
            }
        }
        else {
            System.out.println("Usuario null");
        }
    }

    @FXML
    void OnActionEmpleados(ActionEvent event) {

    }

    @FXML
    void OnActionProductos(ActionEvent event) {

    }

    @FXML
    void OnActionProveedores(ActionEvent event) {

    }

    @FXML
    void OnActionVerInfo(ActionEvent event) {

    }

    @FXML
    void onActionActualizarCargo(ActionEvent event) {

    }

    @FXML
    void onActionActualizarEmpleado(ActionEvent event) {

    }

    @FXML
    void onActionActualizarLote(ActionEvent event) {

    }

    @FXML
    void onActionActualizarProveedor(ActionEvent event) {

    }

    @FXML
    void onActionAnadirCargo(ActionEvent event) {

    }

    @FXML
    void onActionAnadirEmpleado(ActionEvent event) {
        Usuario usuario = new Usuario();
        usuario.setFotoUsuario(imagenSeleccionada);

    }

    @FXML
    void onActionAnadirLote(ActionEvent event) {

    }

    @FXML
    void onActionAnadirProveedor(ActionEvent event) {

    }

    @FXML
    void onActionCancelarCargo(ActionEvent event) {

    }

    @FXML
    void onActionCancelarEmpleado(ActionEvent event) {

    }

    @FXML
    void onActionCancelarLote(ActionEvent event) {

    }

    @FXML
    void onActionCancelarProveedor(ActionEvent event) {

    }

    @FXML
    void onActionCargos(ActionEvent event) {

    }

    @FXML
    void onActionEliminarCargo(ActionEvent event) {

    }

    @FXML
    void onActionEliminarEmpleado(ActionEvent event) {

    }

    @FXML
    void onActionEliminarLote(ActionEvent event) {

    }

    @FXML
    void onActionEliminarProveedor(ActionEvent event) {

    }

    @FXML
    void onActionLotes(ActionEvent event) {

    }

    @FXML
    void OnActionCodigoBarras(ActionEvent event) {

    }
    @FXML
    void OnActionInsertarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String carpetaImagenes = "Pap-Creativa-Express/src/main/resources/imagenes_usuarios"; // Ruta deseada para la carpeta de imágenes

            File carpeta = new File(carpetaImagenes);
            if (!carpeta.exists()) {
                if (carpeta.mkdirs()) {
                    System.out.println("Carpeta creada con éxito.");
                } else {
                    System.err.println("No se pudo crear la carpeta.");
                }
            }
            String nombreArchivo = generarNombreUnico();

            String rutaImagen = selectedFile.toURI().toString();

            Image imagenFX = new Image(rutaImagen);
            String rutaDestino = carpetaImagenes + File.separator + nombreArchivo + ".png";

            modelFactoryController.guardarImagen(imagenFX, rutaDestino);

            Usuario usuarioActual = modelFactoryController.getUsuarioActual();
            usuarioActual.setFotoUsuario(imagenFX);
            imagenSeleccionada = imagenFX;
            imageEmpleadoRegistro.setImage(imagenFX);

            MensajeUtil.mensajeInformacion("Imagen guardada en la carpeta de recursos con el nombre: " + nombreArchivo + ".png");
        }
    }



    private String generarNombreUnico() {
        // Obtén la marca de tiempo actual en milisegundos
        long timeStamp = System.currentTimeMillis();

        // Genera un identificador único utilizando la marca de tiempo
        String identificadorUnico = Long.toString(timeStamp);

        return identificadorUnico;
    }

}










//    private void limpiarFormulario() {
//        // Limpia los campos del formulario y restablece la imagen del empleado
//        imageUsuario.setImage(null);
//        imagenSeleccionada.setImage(null);
//        tfNombre.clear();
//        tfCorreo.clear();
//        // Limpia otros campos del formulario
//    }

