package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Model.DetalleVenta;
import com.example.papcreativaexpress.Model.Producto;
import com.example.papcreativaexpress.Utils.MensajeUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class InformacionFacturaController implements Initializable {
    ModelFactoryController modelFactoryController;
    @FXML
    private TableColumn<DetalleVenta, Integer> colCantidad;

    @FXML
    private TableColumn<DetalleVenta, Double> colDescuento;

    @FXML
    private TableColumn<DetalleVenta, Integer> colPrecioUnidad;

    @FXML
    private TableColumn<DetalleVenta, String> colProducto;

    @FXML
    private TableColumn<DetalleVenta, Double> colSubTotal;

    @FXML
    private TableView<DetalleVenta> tableDetallesVenta;

    private ObservableList<DetalleVenta> detallesVentasList= FXCollections.observableArrayList();
    private DetalleVenta detallesVentaSeleccionado;
    private InventarioController inventarioController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelFactoryController = ModelFactoryController.getInstance();
        this.colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.colDescuento.setCellValueFactory(new PropertyValueFactory<>("descuentoDetalleVenta"));
        this.colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotalDetalleVenta"));
        this.colProducto.setCellValueFactory(((cellData -> {
            Producto p = cellData.getValue().getProducto();
            if (p != null) {
                String nombre = p.getNombre();
                return new SimpleStringProperty(nombre);
            } else {
                return new SimpleStringProperty("");
            }
        })));
        this.colPrecioUnidad.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));

        tableDetallesVenta.setItems(detallesVentasList);
        tableDetallesVenta.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            detallesVentaSeleccionado = newSelection;});
    }
    @FXML
    void OnActionGenerarDevolucion(ActionEvent event) {
        if(detallesVentaSeleccionado != null){
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Devolucion de producto");
            dialog.setHeaderText("Ingrese la cantidad a devolver:");
            dialog.setContentText("Cantidad:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(cantidadStr -> {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    generarDevolucion(cantidad);
                } catch (NumberFormatException | IOException e) {
                    MensajeUtil.mostrarMensaje("Error", "Número no válido.","Ingrese por favor una cantidad válida", Alert.AlertType.ERROR);
                }
            });
        }

    }

    private void generarDevolucion(int cantidad)throws IOException{
        boolean devolucionRalizada=modelFactoryController.procesarDevolucion(detallesVentaSeleccionado,cantidad);
        if(!devolucionRalizada){
            mostrarMensaje("Devolucion","Devolucion Fallida", "La devolucion no se pudo realizar", Alert.AlertType.ERROR);
        }else {
            mostrarMensaje("Devolucion", "Devolucion Realizada", "La devolucion se realizo con exito", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void OnActionVolver(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setControladorPrincipal(InventarioController inventarioController) {
        this.inventarioController = inventarioController;
    }
    public void agregarDetallesVenta(ArrayList<DetalleVenta>detallesVentas) {
        detallesVentasList.addAll(detallesVentas);
        tableDetallesVenta.refresh();
    }



    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}
