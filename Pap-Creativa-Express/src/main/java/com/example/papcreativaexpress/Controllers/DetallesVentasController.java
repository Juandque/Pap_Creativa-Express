package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.HelloApplication;
import com.example.papcreativaexpress.Model.DetalleVenta;
import com.example.papcreativaexpress.Model.Producto;
import com.example.papcreativaexpress.Utils.MensajeUtil;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DetallesVentasController implements Initializable {
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
    void OnActionGenerarFactura(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("factura.fxml"));
            Parent root = loader.load();
            FacturaController facturaController = loader.getController();
            facturaController.agregarFactura(detallesVentasList);

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            inventarioController.getVentasVolatiles().clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void agregarDetallesVenta(ObservableList<DetalleVenta> detallesVentas) {
        detallesVentasList.addAll(detallesVentas);
        tableDetallesVenta.refresh();
    }
    public void setControladorPrincipal(InventarioController inventarioController) {
        this.inventarioController = inventarioController;
    }
    @FXML
    void OnActionVolver(ActionEvent event){
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionEliminar(ActionEvent event){
        if (detallesVentaSeleccionado != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Devolver productos");
            dialog.setHeaderText("Ingrese la cantidad a devolver:");
            dialog.setContentText("Cantidad:");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(cantidadStr -> {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);

                    if (cantidad > 0) {
                        detallesVentaSeleccionado.setCantidad(detallesVentaSeleccionado.getCantidad() - cantidad);

                        if (detallesVentaSeleccionado.getCantidad() <= 0) {
                            detallesVentaSeleccionado.setCantidad(0);
                        }
                        tableDetallesVenta.refresh();
                    }
                } catch (NumberFormatException e) {
                    MensajeUtil.mostrarMensaje("Error", "Número no válido.", "Ingrese por favor una cantidad válida", Alert.AlertType.ERROR);
                }
            });
        }
    }
}
