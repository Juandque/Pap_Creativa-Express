package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Model.DetalleVenta;
import com.example.papcreativaexpress.Model.Producto;
import com.example.papcreativaexpress.Utils.MensajeUtil;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
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
    private ComboBox<String> motivoComboBox;

    @FXML
    private TextField motivoEspecificoTextField;
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

    private ObservableList<DetalleVenta> detallesVentasList = FXCollections.observableArrayList();

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
            detallesVentaSeleccionado = newSelection;
        });
    }

    @FXML
    void OnActionGenerarDevolucion(ActionEvent event) {
        int cantidadAux = detallesVentaSeleccionado.getCantidad();
        if (detallesVentaSeleccionado != null) {
            // Crea un diálogo de entrada de cantidad
            TextInputDialog cantidadDialog = new TextInputDialog();
            cantidadDialog.setTitle("Devolucion de producto");
            cantidadDialog.setHeaderText("Ingrese la cantidad a devolver:");
            cantidadDialog.setContentText("Cantidad:");

            // Muestra el diálogo y espera por la entrada del usuario
            Optional<String> cantidadResult = cantidadDialog.showAndWait();

            cantidadResult.ifPresent(cantidadStr -> {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);

                    // Crea un nuevo diálogo para el motivo de devolución
                    ChoiceDialog<String> motivoDialog = new ChoiceDialog<>();
                    motivoDialog.getItems().addAll("Producto defectuoso", "Cambio de producto", "Otro");
                    motivoDialog.setTitle("Motivo de devolución");
                    motivoDialog.setHeaderText("Seleccione el motivo de la devolución:");
                    motivoDialog.setContentText("Motivo:");

                    // Muestra el diálogo y espera por la selección del usuario
                    Optional<String> motivoResult = motivoDialog.showAndWait();

                    // Si se selecciona un motivo, procesa la devolución
                    motivoResult.ifPresent(motivo -> {
                        if ("Otro".equals(motivo)) {
                            // Si el motivo es "Otro", muestra un diálogo para ingresar el motivo específico
                            TextInputDialog motivoEspecificoDialog = new TextInputDialog();
                            motivoEspecificoDialog.setTitle("Motivo específico");
                            motivoEspecificoDialog.setHeaderText("Ingrese el motivo específico:");
                            motivoEspecificoDialog.setContentText("Motivo específico:");

                            Optional<String> motivoEspecificoResult = motivoEspecificoDialog.showAndWait();

                            // Si se proporciona un motivo específico, procesa la devolución
                            motivoEspecificoResult.ifPresent(motivoEspecifico -> {
                                try {
                                    generarDevolucion(cantidad);
                                    asignarDetalles(detallesVentaSeleccionado);
                                    modelFactoryController.getDetalleVentaActual().getProducto().setDescripcionDetallada(motivoEspecifico);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        } else {
                            try {
                                generarDevolucion(cantidad);
                                asignarDetalles(detallesVentaSeleccionado);
                                modelFactoryController.getDetalleVentaActual().getProducto().setDescripcionDetallada(motivo);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (NumberFormatException e) {
                    MensajeUtil.mostrarMensaje("Error", "Número no válido.", "Ingrese por favor una cantidad válida", Alert.AlertType.ERROR);
                }
            });
            if (detallesVentaSeleccionado.getCantidad() <= 0) {
                detallesVentasList.remove(detallesVentaSeleccionado);
            }
            modelFactoryController.getDetalleVentaActual().setCantidad(cantidadAux);
            inventarioController.getTableDevoluciones().getItems().add(modelFactoryController.getDetalleVentaActual());
        }
    }

    @FXML
    void OnActionRefrescar(ActionEvent event) {
        tableDetallesVenta.refresh();
    }

    private void generarDevolucion(int cantidad) throws IOException {
        boolean devolucionRalizada = modelFactoryController.procesarDevolucion(detallesVentaSeleccionado, cantidad);
        if (!devolucionRalizada) {
            mostrarMensaje("Devolucion", "Devolucion Fallida", "La devolucion no se pudo realizar", Alert.AlertType.ERROR);
        } else {
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

    public void agregarDetallesVenta(ArrayList<DetalleVenta> detallesVentas) {
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
    private void asignarDetalles(DetalleVenta detalleVenta){
        modelFactoryController.asignarDetalleVenta(detalleVenta);
    }
}
