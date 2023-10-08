package com.example.papcreativaexpress.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class InventarioController {

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

}
