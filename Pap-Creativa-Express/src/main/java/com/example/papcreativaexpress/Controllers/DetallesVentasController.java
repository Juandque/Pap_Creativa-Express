package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Model.DetalleVenta;
import com.example.papcreativaexpress.Model.Producto;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
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

    }
    public void agregarDetallesVenta(ObservableList<DetalleVenta> detallesVentas) {
        detallesVentasList.addAll(detallesVentas);
        tableDetallesVenta.refresh();
    }
    public void setControladorPrincipal(InventarioController inventarioController) {
        this.inventarioController = inventarioController;
    }



}
