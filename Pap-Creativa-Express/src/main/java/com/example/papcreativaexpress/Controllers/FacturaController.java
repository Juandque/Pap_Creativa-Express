package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Model.DetalleVenta;
import com.example.papcreativaexpress.Model.Factura;
import com.example.papcreativaexpress.Model.Producto;
import com.google.zxing.qrcode.decoder.Mode;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class FacturaController implements Initializable {
    ModelFactoryController modelFactoryController;

    @FXML
    private TableColumn<DetalleVenta, Integer> colPrecioUnitario;

    @FXML
    private TableColumn<DetalleVenta, String> colProducto;

    @FXML
    private Label labelFechaFactura;

    @FXML
    private Label labelSubtotal;

    @FXML
    private Label labelSubtotal1;

    @FXML
    private Label labelTotal;

    @FXML
    private Label labelTotal1;

    @FXML
    private TableView<DetalleVenta> tableFactura;
    private ObservableList<DetalleVenta> facturas = FXCollections.observableArrayList();
    private DetalleVenta facturaSeleccionada;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelFactoryController = ModelFactoryController.getInstance();
        this.colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        this.colProducto.setCellValueFactory(((cellData -> {
            Producto p = cellData.getValue().getProducto();
            if (p != null) {
                String nombre = p.getNombre();
                return new SimpleStringProperty(nombre);
            } else {
                return new SimpleStringProperty("");
            }
        })));

        tableFactura.setItems(facturas);
        tableFactura.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            facturaSeleccionada = newSelection;});
    }
    public void agregarFactura(ObservableList<DetalleVenta> detallesVentas) {
        facturas.addAll(detallesVentas);
        tableFactura.refresh();
    }
}
