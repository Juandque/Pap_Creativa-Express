package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Model.DetalleVenta;
import com.example.papcreativaexpress.Model.Factura;
import com.example.papcreativaexpress.Model.Producto;
import com.google.zxing.qrcode.decoder.Mode;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
        labelFechaFactura.setText("" + modelFactoryController.getFacturaActual().getFechaFactura()); // Asegúrate de tener un método en Factura que retorne la fecha formateada
        labelSubtotal.setText("$" + modelFactoryController.getFacturaActual().getSubtotalFactura());
        labelTotal.setText("$" + modelFactoryController.getFacturaActual().getTotalFactura());
        labelTotal1.setText("$"+ modelFactoryController.getFacturaActual().getImpuesto());
        labelSubtotal1.setText(""+ modelFactoryController.getFacturaActual().getEmpleadoEncargadoFactura());
    }
    public void agregarFactura(ObservableList<DetalleVenta> detallesVentas) {
        facturas.addAll(detallesVentas);
        tableFactura.refresh();
    }
    @FXML
    void OnActionImprimir(ActionEvent event){
        iniciarNuevaVenta();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        cerrarVentana(stage);
    }
    private void cerrarVentana(Stage ventana) {
        ventana.close();
    }
    private void iniciarNuevaVenta() {
        modelFactoryController.getFacturas().remove(modelFactoryController.getFacturaActual());
        Factura nuevaFactura = new Factura();
        modelFactoryController.setFacturaActual(nuevaFactura);
    }


}
