package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Model.Lote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class VentanaDetallesController implements Initializable {
    ModelFactoryController modelFactoryController;

    @FXML
    private ImageView imageEmpleado;

    @FXML
    private Text textCantidad;

    @FXML
    private Text textFechaRegistro;

    @FXML
    private Text textId;

    @FXML
    private Text textNombre;

    @FXML
    private Text textPrecioTotal;

    @FXML
    private Text textPrecioUnitario;

    @FXML
    private Text textProveedores;

    @FXML
    private Button btnVolver;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelFactoryController = ModelFactoryController.getInstance();
        imageEmpleado.setImage(modelFactoryController.cargarImagenLote(String.valueOf(modelFactoryController.getLoteActual().getCostoTotalLote())));
    }
    public void mostrarInformacionProducto(Lote lote) {
        if (lote != null) {
            textNombre.setText("Nombre del producto:" + lote.getListaProductosLote().get(0).getNombre());
            textId.setText("Id del producto:" + lote.getId());
            textCantidad.setText("Cantidad del lote:" + lote.getCantidad());
            textPrecioUnitario.setText("El precio unitario del producto es de:"+ lote.getPrecioUnitario());
            textPrecioTotal.setText("El precio total del lote es de:" + lote.getCostoTotalLote());
            textProveedores.setText("El proveedor del producto es: " + lote.getProveedor().getNombreEmpresa());
        }
    }
    @FXML
    void OnActionVolver(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        cerrarVentana(stage);

    }

    // Método para cerrar la ventana de detalles
    private void cerrarVentana(Stage ventana) {
        ventana.close();
    }
}


