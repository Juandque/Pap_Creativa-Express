package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.HelloApplication;
import com.example.papcreativaexpress.Model.*;
import com.example.papcreativaexpress.Persistencia.Persistencia;
import com.example.papcreativaexpress.Utils.GenerarCodigoBarras;
import com.example.papcreativaexpress.Utils.MensajeUtil;
import com.example.papcreativaexpress.Utils.TextFormatterUtil;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.swing.text.DefaultEditorKit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.papcreativaexpress.Utils.GenerarCodigoBarras.generarCodigoDeBarras;


public class InventarioController implements Initializable {
    ModelFactoryController modelFactoryController;
    @FXML
    private Label lblContador;
    private int contador =0;
    @FXML
    private ImageView imageCodigoBarras;

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
    private Button btnVerInfoVentas;
    @FXML
    private Button btnCodigoBrras;
    @FXML
    private ComboBox<String> cbBoxFiltro;
    @FXML
    private ComboBox<Cargo> cbCargoEmpleado;

    @FXML
    private ComboBox<EstadoCargo> cbEstadoCargo;

    @FXML
    private ComboBox<Estado> cbEstadoEmpleado;
    @FXML
    private ComboBox<EstadoProveedor> cbEstadoProveedor;
    @FXML
    private ComboBox<Proveedor> cbProveedorLote;

    @FXML
    private TableColumn<Cargo, Integer> colCantidadEmpleadosCargo;

    @FXML
    private TableColumn<Lote, Integer> colCantidadLote;

    @FXML
    private TableColumn<Lote, Integer> colCantidadProductos;

    @FXML
    private TableColumn<Lote, Double> colCostoTotalLote;

    @FXML
    private TableColumn<Usuario, String> colDireccionEmpleado;

    @FXML
    private TableColumn<Proveedor, String> colDireccionProveedor;

    @FXML
    private TableColumn<Usuario, String> colEmailEmpleado;

    @FXML
    private TableColumn<Factura, String> colEmpleadoFacturas;

    @FXML
    private TableColumn<Cargo, String> colEstadoCargo;

    @FXML
    private TableColumn<Proveedor, String> colEstadoProveedor;

    @FXML
    private TableColumn<Lote, Date> colFechaEntradaLote;

    @FXML
    private TableColumn<Factura, Date> colFechaFacturas;

    @FXML
    private TableColumn<Usuario, String> colIdEmpleado;

    @FXML
    private TableColumn<Factura, Integer> colIdFacturas;

    @FXML
    private TableColumn<Factura, Double> colImpuestoFacturas;
    @FXML
    private TableColumn<Lote, String> colMarca;

    @FXML
    private TableColumn<Cargo, String> colNombreCargo;

    @FXML
    private TableColumn<Proveedor, String> colNombreContactoProveedor;

    @FXML
    private TableColumn<Usuario, String> colNombreEmpleado;

    @FXML
    private TableColumn<Proveedor, String> colNombreEmpresaProveedor;

    @FXML
    private TableColumn<Lote, String> colNombreProductoLote;

    @FXML
    private TableColumn<Lote, Double> colPrecioUnitarioLote;

    @FXML
    private TableColumn<Lote, String> colProductos;

    @FXML
    private TableColumn<Lote, String> colProveedor;

    @FXML
    private TableColumn<Cargo, Double> colSalarioCargo;

    @FXML
    private TableColumn<Usuario, String> colTelefonoEmpleado;

    @FXML
    private TableColumn<Proveedor, String> colTelefonoProveedor;

    @FXML
    private TableColumn<Factura, Double> colTotalFacturas;
    @FXML
    private TableColumn<DetalleVenta, String> colProductoVentas;
    @FXML
    private TableColumn<DetalleVenta, Integer> colCantidadVentas;
    @FXML
    private TableColumn<DetalleVenta, Double> colPrecioUnidadVentas;
    @FXML
    private TableColumn<DetalleVenta, Double> colSubTotalVentas;
    @FXML
    private TableColumn<DetalleVenta, Double> colDescuentoVentas;
    @FXML
    private TableColumn<DetalleVenta, String> colProductoDevoluciones;
    @FXML
    private TableColumn<DetalleVenta, Integer> colCantidadDevoluciones;
    @FXML
    private TableColumn<DetalleVenta, String> colMotivoDevoluciones;

    @FXML
    private DatePicker dpFechaCaducidadProductoLote;

    @FXML
    private Pane paneAdmin;
    @FXML
    private Pane paneVentas;


    @FXML
    private Pane paneCargos;

    @FXML
    private Pane paneLotes;

    @FXML
    private Pane paneProductos;
    @FXML
    private Pane paneDevoluciones;

    @FXML
    private Pane paneProveedores;

    @FXML
    private TableView<Cargo> tableCargos;
    @FXML
    private TableView<Factura>tableRegistroVentas;

    @FXML
    private TableView<Usuario> tableEmpleados;

    @FXML
    private TableView<Lote> tableLotes;

    @FXML
    private TableView<Proveedor> tableProveedores;
    @FXML
    private TableView<DetalleVenta> tableDevoluciones;

    @FXML
    private TableView<Lote> tblPoductos;

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
    private TextArea txtDescripcionCargo;

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
    private Image imagenSeleccionada;
    private Image imagenLote;
    private Cargo cargoSeleccionado;
    private Lote loteSeleccionado;
    private DetalleVenta devolucionSeleccionada;

    private Proveedor proveedorSeleccionado;
    private Usuario empleadoSeleccionado;
    private Factura ventaSeleccionada;
    private Factura facturaActual;
    private ObservableList<Usuario> empleados = FXCollections.observableArrayList();
    private ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();
    private ObservableList<Cargo> cargos = FXCollections.observableArrayList();
    private ObservableList<Lote> lotes = FXCollections.observableArrayList();
    private ObservableList<Factura> ventas = FXCollections.observableArrayList();
    private ObservableList<DetalleVenta> devoluciones = FXCollections.observableArrayList();

    private ObservableList<DetalleVenta> ventasVolatiles = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelFactoryController = ModelFactoryController.getInstance();
        Image imagenRegistro = new Image(getClass().getResourceAsStream("/Imagenes/icons8-error-64.png"));
        imageEmpleadoRegistro.setImage(imagenRegistro);
        imageCodigoBarras.setImage(imagenRegistro);
        imageEmpleado.setImage(modelFactoryController.cargarImagenEmpleado(modelFactoryController.getUsuarioActual().getNombre()));
        System.out.println(modelFactoryController.getUsuarioActual().getNombre());
        modelFactoryController = ModelFactoryController.getInstance();
        ArrayList<Usuario> empleadosArrayList = modelFactoryController.getEmpleados();
        ArrayList<Proveedor> proveedoresArrayList = modelFactoryController.getProveedores();
        ArrayList<Cargo> cargosArrayList = modelFactoryController.getCargos();
        ArrayList<Lote> lotesArrayList = modelFactoryController.getLotes();
        ArrayList<Factura> facturas = modelFactoryController.getPapCreativaExpress().getListaFacturas();
        ArrayList<DetalleVenta> devolucionesArrayList = modelFactoryController.getPapCreativaExpress().getListaProductosDevoluciones();


        empleados.addAll(empleadosArrayList);
        proveedores.addAll(proveedoresArrayList);
        cargos.addAll(cargosArrayList);
        lotes.addAll(lotesArrayList);
        ventas.addAll(facturas);
        devoluciones.addAll(devolucionesArrayList);
        initializePaneAdmin();
        initializePaneProductos();
        initializePaneCargos();
        initializePaneLotes();
        initializePaneProveedores();
        initializePaneVentas();
        initializePaneDevoluciones();
        txtCantidadLote.textProperty().addListener((observable, oldValue, newValue) -> {
            actualizarCostoTotal();
        });
        txtPrecioUnitarioLote.textProperty().addListener((observable, oldValue, newValue) -> {
            actualizarCostoTotal();
        });
        mostrarProductosCantidadBaja(modelFactoryController.getLotes());
        lblContador.setText(Integer.toString(contador));

    }


    public void initializePaneAdmin() {
        this.colNombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colTelefonoEmpleado.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.colEmailEmpleado.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.colIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colDireccionEmpleado.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tableEmpleados.setItems(empleados);
        tableEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            empleadoSeleccionado = newSelection;
            if (empleadoSeleccionado != null) {
                mostrarInformacionEmpleado(empleadoSeleccionado);
            }
        });
        this.cbCargoEmpleado.setConverter(new StringConverter<Cargo>() {
            @Override
            public String toString(Cargo cargo) {
                return (cargo != null) ? cargo.getNombre() : "";
            }

            @Override
            public Cargo fromString(String s) {
                return null;
            }
        });
        this.cbCargoEmpleado.setItems(cargos);
        this.cbEstadoEmpleado.getItems().addAll(Estado.values());
        this.txtTelefonoEmpleado.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        this.txtIdEmpleado.setTextFormatter(new TextFormatter<Object>(TextFormatterUtil::integerFormat));
    }

    public void initializePaneProveedores() {
        this.colDireccionProveedor.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.colEstadoProveedor.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colTelefonoProveedor.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.colNombreContactoProveedor.setCellValueFactory(new PropertyValueFactory<>("nombreContacto"));
        this.colNombreEmpresaProveedor.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresa"));
        tableProveedores.setItems(proveedores);
        tableProveedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            proveedorSeleccionado = newSelection;
            if (proveedorSeleccionado != null) {
                mostrarInformacionProveedor(proveedorSeleccionado);
            }
        });
        this.txtTelefonoProveedor.setTextFormatter(new TextFormatter<Object>(TextFormatterUtil::integerFormat));
        this.cbEstadoProveedor.getItems().addAll(EstadoProveedor.values());
    }

    public void initializePaneCargos() {
        this.colNombreCargo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colSalarioCargo.setCellValueFactory(new PropertyValueFactory<>("salario"));
        this.colEstadoCargo.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colCantidadEmpleadosCargo.setCellValueFactory(new PropertyValueFactory<>("empleadosRequeridos"));
        tableCargos.setItems(cargos);
        tableCargos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            cargoSeleccionado = newSelection;
            if (cargoSeleccionado != null) {
                mostrarInformacionCargo(cargoSeleccionado);
            }
        });
        txtEmpleadosRequeridosCargo.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        txtSalarioCargo.setTextFormatter(new TextFormatter<>(TextFormatterUtil::doubleFormat));
        this.cbEstadoCargo.getItems().addAll(EstadoCargo.values());
    }
    public void initializePaneDevoluciones(){
        this.colProductoDevoluciones.setCellValueFactory(((cellData -> {
            Producto p = cellData.getValue().getProducto();
            if (p != null) {
                String nombre = p.getNombre();
                return new SimpleStringProperty(nombre);
            } else {
                return new SimpleStringProperty("");
            }
        })));
        colCantidadDevoluciones.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.colMotivoDevoluciones.setCellValueFactory(((cellData -> {
            Producto p = cellData.getValue().getProducto();
            if (p != null) {
                String detalle = p.getDescripcionDetallada();
                return new SimpleStringProperty(detalle);
            } else {
                return new SimpleStringProperty("");
            }
        })));
        tableDevoluciones.setItems(devoluciones);
        tableDevoluciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            devolucionSeleccionada = newSelection;});
    }

    public void initializePaneLotes() {
        this.colNombreProductoLote.setCellValueFactory(((cellData -> {
            Producto p = cellData.getValue().getListaProductosLote().get(0);
            if (p != null) {
                String nombre = p.getNombre();
                return new SimpleStringProperty(nombre);
            } else {
                return new SimpleStringProperty("");
            }
        })));
        this.colCantidadLote.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.colFechaEntradaLote.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));
        this.colPrecioUnitarioLote.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        this.colCostoTotalLote.setCellValueFactory(new PropertyValueFactory<>("costoTotalLote"));
        tableLotes.setItems(lotes);
        tableLotes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            loteSeleccionado = newSelection;
            if (loteSeleccionado != null) {
                mostrarInformacionLote(loteSeleccionado);
            }
        });
        txtCantidadLote.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        txtPrecioUnitarioLote.setTextFormatter(new TextFormatter<>(TextFormatterUtil::doubleFormat));
        txtCostoTotalLote.setTextFormatter(new TextFormatter<>(TextFormatterUtil::doubleFormat));
        txtCostoProductoLote.setTextFormatter(new TextFormatter<>(TextFormatterUtil::doubleFormat));
        txtPrecioVentaProductoLote.setTextFormatter(new TextFormatter<>(TextFormatterUtil::doubleFormat));
        this.cbProveedorLote.setConverter(new StringConverter<Proveedor>() {
            @Override
            public String toString(Proveedor proveedor) {
                return (proveedor != null) ? proveedor.getNombreEmpresa() : "";
            }

            @Override
            public Proveedor fromString(String s) {
                return null;
            }
        });
        this.cbProveedorLote.setItems(proveedores);
    }

    public void initializePaneProductos() {
        this.colProductos.setCellValueFactory(((cellData -> {
            Producto p = cellData.getValue().getListaProductosLote().get(0);
            if (p != null) {
                String nombre = p.getNombre();
                return new SimpleStringProperty(nombre);
            } else {
                return new SimpleStringProperty("");
            }
        })));
        this.colCantidadProductos.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.colProveedor.setCellValueFactory(((cellData -> {
            Proveedor p = cellData.getValue().getProveedor();
            if (p != null) {
                String nombre = p.getNombreEmpresa();
                return new SimpleStringProperty(nombre);
            } else {
                return new SimpleStringProperty("");
            }
        })));
        this.colMarca.setCellValueFactory(((cellData -> {
            Producto p = cellData.getValue().getListaProductosLote().get(0);
            if (p != null) {
                String marca = p.getMarca();
                return new SimpleStringProperty(marca);
            } else {
                return new SimpleStringProperty("");
            }
        })));
        cbBoxFiltro.getItems().addAll("Mayor Precio", "Menor Precio");
        cbBoxFiltro.setValue("Mayor Precio");
        cbBoxFiltro.setOnAction(event -> {
            String selectedItem = cbBoxFiltro.getValue();
            if ("Mayor Precio".equals(selectedItem)) {
                lotes.sort(Comparator.comparing(Lote::getCostoTotalLote).reversed());
            } else if ("Menor Precio".equals(selectedItem)) {
                lotes.sort(Comparator.comparing(Lote::getCostoTotalLote));
            }
            tableLotes.refresh();
        });

        tblPoductos.setItems(lotes);
        tblPoductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            loteSeleccionado = newSelection;
            if (loteSeleccionado != null) {
                mostrarInformacionLote(loteSeleccionado);
            }
        });
    }
    public void initializePaneVentas(){
        this.colIdFacturas.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colFechaFacturas.setCellValueFactory(new PropertyValueFactory<>("fechaFactura"));
        this.colTotalFacturas.setCellValueFactory(new PropertyValueFactory<>("totalFactura"));
        this.colEmpleadoFacturas.setCellValueFactory(((cellData -> {
            Usuario u = cellData.getValue().getEmpleadoEncargadoFactura();
            if (u != null) {
                String nombre = u.getNombre();
                return new SimpleStringProperty(nombre);
            } else {
                return new SimpleStringProperty("");
            }
        })));
        this.colImpuestoFacturas.setCellValueFactory(new PropertyValueFactory<>("impuesto"));

        for(Factura factura: ventas){
            for(DetalleVenta detalleVenta: factura.getListaDetallesVenta()){
                if(detalleVenta.getCantidad()<=0){
                    ventas.remove(detalleVenta);
                }
            }
        }
            tableRegistroVentas.setItems(ventas);
            tableRegistroVentas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            ventaSeleccionada = newSelection;});
    }

    public ObservableList<DetalleVenta> getVentasVolatiles() {
        return ventasVolatiles;
    }

    @FXML
    void OnActionEmpleados(ActionEvent event) {
        paneAdmin.setVisible(true);
        paneCargos.setVisible(false);
        paneLotes.setVisible(false);
        paneProductos.setVisible(false);
        paneProveedores.setVisible(false);
        paneVentas.setVisible(false);
        paneDevoluciones.setVisible(false);


    }

    @FXML
    void OnActionProductos(ActionEvent event) {
        paneAdmin.setVisible(false);
        paneCargos.setVisible(false);
        paneLotes.setVisible(false);
        paneProductos.setVisible(true);
        paneProveedores.setVisible(false);
        paneVentas.setVisible(false);
        paneDevoluciones.setVisible(false);


    }

    @FXML
    void OnActionProveedores(ActionEvent event) {
        paneAdmin.setVisible(false);
        paneCargos.setVisible(false);
        paneLotes.setVisible(false);
        paneProductos.setVisible(false);
        paneProveedores.setVisible(true);
        paneVentas.setVisible(false);
        paneDevoluciones.setVisible(false);


    }
    @FXML
    void onActionVentas(ActionEvent event) {
        paneAdmin.setVisible(false);
        paneCargos.setVisible(false);
        paneLotes.setVisible(false);
        paneProductos.setVisible(false);
        paneProveedores.setVisible(false);
        paneVentas.setVisible(true);
        paneDevoluciones.setVisible(false);


    }

    public Factura getFacturaActual() {
        return facturaActual;
    }
    @FXML
    void OnActionRefrescarRegistroVentas(ActionEvent event){
        ventas.removeIf(factura -> factura.getListaDetallesVenta().stream().anyMatch(detalle -> detalle.getCantidad() <= 0));
        tableRegistroVentas.refresh();    }
    @FXML
    void OnActionVerInfo(ActionEvent event) {
        Lote loteActual;
        if (loteSeleccionado != null) {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("VentanaDetalles.fxml"));
            Parent root;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            loteActual = modelFactoryController.buscarLotePorId(loteSeleccionado.getId());
            modelFactoryController.asignarLoteActual(loteActual.getId());
            VentanaDetallesController detallesController = loader.getController();
            detallesController.mostrarInformacionProducto(loteActual);
            Stage detallesStage = new Stage();
            detallesStage.setScene(new Scene(root));
            detallesStage.setTitle("Detalles del Producto");
            detallesStage.show();
            tableLotes.refresh();
        } else {
            MensajeUtil.mostrarMensaje("Error", "Selección", "Debe seleccionar un producto", Alert.AlertType.ERROR);
        }
    }

    private void mostrarProductosCantidadBaja(List<Lote> lotes) {
        StringBuilder mensajeProductosBajos = new StringBuilder("Productos con cantidad baja:\n");

        for (Lote lote : lotes) {
            if (lote.getCantidad() <= 2) {
                mensajeProductosBajos.append(lote.getNombre()).append(" (").append(lote.getCantidad()).append(")\n");
            }
        }

        // Crear una alerta si hay productos con cantidades bajas
        if (mensajeProductosBajos.length() > "Productos con cantidad baja:\n".length()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventario con Productos Bajos");
            alert.setHeaderText(null);
            alert.setContentText(mensajeProductosBajos.toString());
            alert.showAndWait();
        }
    }


    @FXML
    void onActionActualizarCargo(ActionEvent event) throws IOException {
        String nombre = txtNombreCargo.getText();
        double salario = Double.parseDouble(txtSalarioCargo.getText());
        EstadoCargo estado = cbEstadoCargo.getValue();
        int cantidadEmpleados = Integer.parseInt(txtEmpleadosRequeridosCargo.getText());
        String descripcion = txtDescripcionCargo.getText();

        if (datosValidosCargo(nombre, String.valueOf(salario), String.valueOf(cantidadEmpleados), descripcion)) {
            boolean actualizado = modelFactoryController.actualizarCargo(cargoSeleccionado.getId(), nombre, descripcion, salario, estado, cantidadEmpleados);

            if (actualizado) {
                mostrarMensaje("Notificacion Cargo", "Cargo actualizado", "El cargo se ha actualizado con éxito", Alert.AlertType.INFORMATION);

                limpiarCamposCargo();
                tableCargos.refresh();
            } else {
                mostrarMensaje("Notificacion Cargo", "Cargo no actualizado", "El cargo no se ha actualizado con éxito", Alert.AlertType.ERROR);
                limpiarCamposCargo();
            }
        } else {
            mostrarMensaje("Notificacion Cargo", "Datos inválidos", "Los datos ingresados son inválidos", Alert.AlertType.WARNING);
            limpiarCamposCargo();
        }
    }


    @FXML
    void onActionActualizarEmpleado(ActionEvent event) throws IOException {
        String nombreUsuario = txtNombreUsuario.getText();
        String contrasena = txtContraseniaEmpleado.getText();
        String nombre = txtNombreEmpleado.getText();
        String email = txtEmailEmpleado.getText();
        String id = txtIdEmpleado.getText();
        String telefono = txtTelefonoEmpleado.getText();
        String direccion = txtDireccionEmpleado.getText();
        Estado estado = cbEstadoEmpleado.getValue();
        Cargo cargo = cbCargoEmpleado.getValue();
        if (datosValidosEmpleado(nombreUsuario, contrasena, nombre, email, id, telefono, direccion, estado, cargo)) {
            boolean actualizado = false;
            actualizado = modelFactoryController.actualizarEmpleado(empleadoSeleccionado.getNombreUsuario(), nombreUsuario, contrasena, nombre, telefono, id, email, direccion, estado, cargo);
            if (actualizado) {
                mostrarMensaje("Notificacion Empleado", "Empleado actualizado", "El empleado se ha actualizado con exito", Alert.AlertType.INFORMATION);
                limpiarCamposEmpleado();
                tableEmpleados.refresh();
            } else {
                mostrarMensaje("Notificacion Empleado", "Empleado no actualizado", "El empleado no se ha actualizado con exito", Alert.AlertType.ERROR);
                limpiarCamposEmpleado();
            }
        } else {
            mostrarMensaje("Notificacion Empleado", "Datos invalidos", "Los datos ingresados son invalidos", Alert.AlertType.WARNING);
            limpiarCamposEmpleado();
        }
    }

    @FXML
    void onActionActualizarLote(ActionEvent event) throws IOException {
        int cantidadProductos = Integer.parseInt(txtCantidadLote.getText());
        double precioUnitario = Double.parseDouble(txtPrecioUnitarioLote.getText());
        Proveedor proveedor = cbProveedorLote.getValue();
        double costoTotalLote = Double.parseDouble(txtCostoTotalLote.getText());
        String nombreProducto = txtNombreProductoLote.getText();
        double precioVenta = Double.parseDouble(txtPrecioVentaProductoLote.getText());
        double costoProducto = Double.parseDouble(txtCostoProductoLote.getText());
        String marca = txtMarcaProductoLote.getText();
        String descripcion = txtDescripcionProductoLote.getText();
        LocalDate selectedLocalDate = dpFechaCaducidadProductoLote.getValue();
        Date fechaCaducidad = java.util.Date.from(selectedLocalDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        if (datosValidosLote(String.valueOf(cantidadProductos), String.valueOf(precioUnitario), String.valueOf(costoTotalLote), nombreProducto, String.valueOf(precioVenta), String.valueOf(costoProducto), marca, descripcion)) {
            boolean actualizado = false;
            actualizado = modelFactoryController.actualizarLote(loteSeleccionado.getId(), cantidadProductos, precioUnitario, costoTotalLote, proveedor, nombreProducto, precioVenta, fechaCaducidad, costoProducto, marca, descripcion);
            if (actualizado) {
                mostrarMensaje("Notificacion Lote", "Lote actualizado", "El Lote se ha actualizado con exito", Alert.AlertType.INFORMATION);
                limpiarCamposLote();
                tableLotes.refresh();
            } else {
                mostrarMensaje("Notificacion Lote", "Lote no actualizado", "El Lote no se ha actualizado con exito", Alert.AlertType.ERROR);
                limpiarCamposLote();
            }
        } else {
            mostrarMensaje("Notificacion Lote", "Datos invalidos", "Los datos ingresados son invalidos", Alert.AlertType.WARNING);
            limpiarCamposLote();
        }
    }

    @FXML
    void onActionActualizarProveedor(ActionEvent event) throws IOException {
        String nombreEmpresa = txtNombreEmpresaProveedor.getText();
        String direccion = txtDireccionProveedor.getText();
        String telefono = txtTelefonoProveedor.getText();
        String nombreContacto = txtNombreContactoProveedor.getText();
        String comentarios = txtComentariosProveedor.getText();
        EstadoProveedor estado = cbEstadoProveedor.getValue();
        if (datosValidosProveedor(nombreEmpresa, direccion, telefono, nombreContacto, comentarios)) {
            boolean actualizado = false;
            actualizado = modelFactoryController.actualizarProveedor(proveedorSeleccionado.getId(), nombreEmpresa, direccion, telefono, nombreContacto, comentarios, estado);
            if (actualizado) {
                mostrarMensaje("Notificacion Proveedor", "Proveedor actualizado", "El Proveedor se ha actualizado con exito", Alert.AlertType.INFORMATION);
                limpiarCamposProveedores();
                tableProveedores.refresh();
            } else {
                mostrarMensaje("Notificacion Proveedor", "Proveedor no actualizado", "El Proveedor no se ha actualizado con exito", Alert.AlertType.ERROR);
                limpiarCamposProveedores();
            }
        } else {
            mostrarMensaje("Notificacion Proveedor", "Datos invalidos", "Los datos ingresados son invalidos", Alert.AlertType.WARNING);
            limpiarCamposProveedores();
        }
    }

    @FXML
    void onActionAnadirCargo(ActionEvent event) throws IOException {
        String nombre = txtNombreCargo.getText();
        double salario = Double.parseDouble(txtSalarioCargo.getText());
        EstadoCargo estado = cbEstadoCargo.getValue();
        int cantidadEmpleados = Integer.parseInt(txtEmpleadosRequeridosCargo.getText());
        String descripcion = txtDescripcionCargo.getText();
        if (datosValidosCargo(nombre, String.valueOf(salario), String.valueOf(cantidadEmpleados), descripcion)) {
            Cargo nuevo = null;
            nuevo = modelFactoryController.crearCargo(nombre, descripcion, salario, estado, cantidadEmpleados);
            if (nuevo != null) {
                cargos.add(nuevo);
                mostrarMensaje("Notificacion Cargo", "Cargo creado", "El cargo se ha creado con exito", Alert.AlertType.INFORMATION);
                limpiarCamposCargo();
            } else {
                mostrarMensaje("Notificacion Cargo", "Cargo no creado", "El cargo no se ha creado con exito", Alert.AlertType.ERROR);
                limpiarCamposCargo();
            }
        } else {
            mostrarMensaje("Notificacion Cargo", "Datos invalidos", "Los datos ingresados son invalidos", Alert.AlertType.WARNING);
            limpiarCamposCargo();
        }

    }

    @FXML
    void onActionAnadirEmpleado(ActionEvent event) throws IOException {
        Image imagenRegistroSett = new Image(getClass().getResourceAsStream("/Imagenes/icons8-error-64.png"));
        String nombreUsuario = txtNombreUsuario.getText();
        String contrasena = txtContraseniaEmpleado.getText();
        String nombre = txtNombreEmpleado.getText();
        String email = txtEmailEmpleado.getText();
        String id = txtIdEmpleado.getText();
        String telefono = txtTelefonoEmpleado.getText();
        String direccion = txtDireccionEmpleado.getText();
        Estado estado = cbEstadoEmpleado.getValue();
        Cargo cargo = cbCargoEmpleado.getValue();
        if (datosValidosEmpleado(nombreUsuario, contrasena, nombre, email, id, telefono, direccion, estado, cargo)) {
            Usuario nuevo = null;
            nuevo = modelFactoryController.crearEmpleado(nombre, nombreUsuario, contrasena, email, id, telefono, direccion, estado, cargo);
            nuevo.setFotoUsuario(imagenSeleccionada);
            imageEmpleadoRegistro.setImage(imagenRegistroSett);
            if (nuevo != null) {
                empleados.add(nuevo);
                mostrarMensaje("Notificacion Empleado", "Empleado creado", "El empleado se ha creado con exito", Alert.AlertType.INFORMATION);
                limpiarCamposEmpleado();
            } else {
                mostrarMensaje("Notificacion Empleado", "Empleado no creado", "El empleado no se ha creado con exito", Alert.AlertType.ERROR);
                limpiarCamposEmpleado();
            }
        } else {
            mostrarMensaje("Notificacion Empleado", "Datos invalidos", "Los datos ingresados son invalidos", Alert.AlertType.WARNING);
            limpiarCamposEmpleado();
        }
    }

    @FXML
    void onActionAnadirLote(ActionEvent event) throws IOException {
        Image imagenSett = new Image(getClass().getResourceAsStream("/Imagenes/icons8-error-64.png"));
        int cantidadProductos = Integer.parseInt(txtCantidadLote.getText());
        double precioUnitario = Double.parseDouble(txtPrecioUnitarioLote.getText());
        Proveedor proveedor = cbProveedorLote.getValue();
        double costoTotalLote = Double.parseDouble(txtCostoTotalLote.getText());
        String nombreProducto = txtNombreProductoLote.getText();
        double precioVenta = Double.parseDouble(txtPrecioVentaProductoLote.getText());
        double costoProducto = Double.parseDouble(txtCostoProductoLote.getText());
        String marca = txtMarcaProductoLote.getText();
        String descripcion = txtDescripcionProductoLote.getText();
        LocalDate selectedLocalDate = dpFechaCaducidadProductoLote.getValue();
        Date fechaCaducidad = java.util.Date.from(selectedLocalDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        if (datosValidosLote(String.valueOf(cantidadProductos), String.valueOf(precioUnitario), String.valueOf(costoTotalLote), nombreProducto, String.valueOf(precioVenta), String.valueOf(costoProducto), marca, descripcion)) {
            Lote nuevo = null;
            nuevo = modelFactoryController.crearLote(cantidadProductos, precioUnitario, costoTotalLote, proveedor, nombreProducto, precioVenta, fechaCaducidad, costoProducto, marca, descripcion);
            nuevo.setCodigoBarrasImage(imagenLote);
            String uniqueId = UUID.randomUUID().toString();
            nuevo.setId(uniqueId);
            imageCodigoBarras.setImage(imagenSett);
            if (nuevo != null) {
                lotes.add(nuevo);
                mostrarMensaje("Notificacion Lote", "Lote creado", "El Lote se ha creado con exito", Alert.AlertType.INFORMATION);
                limpiarCamposLote();
            } else {
                mostrarMensaje("Notificacion Lote", "Lote no creado", "El Lote no se ha creado con exito", Alert.AlertType.ERROR);
                limpiarCamposLote();
            }
        } else {
            mostrarMensaje("Notificacion Lote", "Datos invalidos", "Los datos ingresados son invalidos", Alert.AlertType.WARNING);
            limpiarCamposLote();
        }
    }

    private void actualizarCostoTotal() {
        try {
            int cantidadProductos = Integer.parseInt(txtCantidadLote.getText());
            double precioUnitario = Double.parseDouble(txtPrecioUnitarioLote.getText());
            double costoTotalLote = cantidadProductos * precioUnitario;
            txtCostoTotalLote.setText(String.valueOf(costoTotalLote));
        } catch (NumberFormatException e) {
            // Manejar la excepción si los valores no son numéricos
            txtCostoTotalLote.setText("0"); // Puedes mostrar un valor predeterminado o un mensaje de error
        }
    }

    @FXML
    void onActionAnadirProveedor(ActionEvent event) throws IOException {
        String nombreEmpresa = txtNombreEmpresaProveedor.getText();
        String direccion = txtDireccionProveedor.getText();
        String telefono = txtTelefonoProveedor.getText();
        String nombreContacto = txtNombreContactoProveedor.getText();
        String comentarios = txtComentariosProveedor.getText();
        EstadoProveedor estado = cbEstadoProveedor.getValue();
        if (datosValidosProveedor(nombreEmpresa, direccion, telefono, nombreContacto, comentarios)) {
            Proveedor nuevo = null;
            nuevo = modelFactoryController.anadirProveedor(nombreEmpresa, direccion, telefono, nombreContacto, comentarios, estado);
            if (nuevo != null) {
                proveedores.add(nuevo);
                mostrarMensaje("Notificacion Proveedor", "Proveedor creado", "El Proveedor se ha creado con exito", Alert.AlertType.INFORMATION);
                limpiarCamposProveedores();
            } else {
                mostrarMensaje("Notificacion Proveedor", "Proveedor no creado", "El Proveedor no se ha creado con exito", Alert.AlertType.ERROR);
                limpiarCamposProveedores();
            }
        } else {
            mostrarMensaje("Notificacion Proveedor", "Datos invalidos", "Los datos ingresados son invalidos", Alert.AlertType.WARNING);
            limpiarCamposProveedores();
        }
    }

    @FXML
    void onActionCancelarCargo(ActionEvent event) {
        limpiarCamposCargo();
        tableCargos.getSelectionModel().clearSelection();
    }

    @FXML
    void onActionCancelarEmpleado(ActionEvent event) {
        limpiarCamposEmpleado();
        tableEmpleados.getSelectionModel().clearSelection();
    }

    @FXML
    void onActionCancelarLote(ActionEvent event) {
        limpiarCamposLote();
        tableLotes.getSelectionModel().clearSelection();
    }

    @FXML
    void onActionCancelarProveedor(ActionEvent event) {
        limpiarCamposProveedores();
        tableProveedores.getSelectionModel().clearSelection();
    }

    @FXML
    void onActionCargos(ActionEvent event) {
        paneAdmin.setVisible(false);
        paneCargos.setVisible(true);
        paneLotes.setVisible(false);
        paneProductos.setVisible(false);
        paneProveedores.setVisible(false);
        paneVentas.setVisible(false);
        paneDevoluciones.setVisible(false);

    }
    @FXML
    void onActionDevoluciones(ActionEvent event){
        paneAdmin.setVisible(false);
        paneCargos.setVisible(false);
        paneLotes.setVisible(false);
        paneProductos.setVisible(false);
        paneProveedores.setVisible(false);
        paneVentas.setVisible(false);
        paneDevoluciones.setVisible(true);
    }

    @FXML
    void onActionEliminarCargo(ActionEvent event) throws IOException {
        boolean eliminado = false;
        if (cargoSeleccionado != null) {
            if (mostrarMensajeConfirmacion("Desea eliminar este Cargo?") == true) {
                eliminado = modelFactoryController.eliminarCargo(cargoSeleccionado);
                if (eliminado) {
                    cargos.remove(cargoSeleccionado);
                    mostrarMensaje("Notificacion Cargo", "Cargo eliminado", "El cargo se ha eliminado con exito", Alert.AlertType.INFORMATION);
                    limpiarCamposCargo();
                } else {
                    mostrarMensaje("Notificacion Cargo", "Cargo no eliminado", "El cargo no se ha eliminado con exito", Alert.AlertType.ERROR);
                    limpiarCamposCargo();
                }
            }
        } else {
            mostrarMensaje("Notificacion Cargo", "Cargo no seleccionado", "No ha seleccionado ningun elemento", Alert.AlertType.WARNING);
            limpiarCamposCargo();
        }
    }

    @FXML
    void onActionEliminarEmpleado(ActionEvent event) throws IOException {
        boolean eliminado = false;
        if (empleadoSeleccionado != null) {
            if (mostrarMensajeConfirmacion("Desea eliminar este Empleado?") == true) {
                eliminado = modelFactoryController.eliminarEmpleado(empleadoSeleccionado);
                if (eliminado) {
                    empleados.remove(empleadoSeleccionado);
                    mostrarMensaje("Notificacion Empleado", "Empleado eliminado", "El Empleado se ha eliminado con exito", Alert.AlertType.INFORMATION);
                    limpiarCamposEmpleado();
                } else {
                    mostrarMensaje("Notificacion Empleado", "Empleado no eliminado", "El Empleado no se ha eliminado con exito", Alert.AlertType.ERROR);
                    limpiarCamposEmpleado();
                }
            }
        } else {
            mostrarMensaje("Notificacion Empleado", "Empleado no seleccionado", "No ha seleccionado ningun elemento", Alert.AlertType.WARNING);
            limpiarCamposEmpleado();
        }
    }

    @FXML
    void onActionEliminarLote(ActionEvent event) throws IOException {
        boolean eliminado = false;
        if (loteSeleccionado != null) {
            if (mostrarMensajeConfirmacion("Desea eliminar este lote?") == true) {
                eliminado = modelFactoryController.eliminarLote(loteSeleccionado);
                if (eliminado) {
                    lotes.remove(loteSeleccionado);
                    mostrarMensaje("Notificacion Lote", "Lote eliminado", "El Lote se ha eliminado con exito", Alert.AlertType.INFORMATION);
                    limpiarCamposLote();
                } else {
                    mostrarMensaje("Notificacion Lote", "Lote no eliminado", "El Lote no se ha eliminado con exito", Alert.AlertType.ERROR);
                    limpiarCamposLote();
                }
            }
        } else {
            mostrarMensaje("Notificacion Lote", "Lote no seleccionado", "No ha seleccionado ningun elemento", Alert.AlertType.WARNING);
            limpiarCamposLote();
        }
    }

    @FXML
    void onActionEliminarProveedor(ActionEvent event) throws IOException {
        boolean eliminado = false;
        if (proveedorSeleccionado != null) {
            if (mostrarMensajeConfirmacion("Desea eliminar este proveedor?") == true) {
                eliminado = modelFactoryController.eliminarProveedor(proveedorSeleccionado);
                if (eliminado) {
                    proveedores.remove(proveedorSeleccionado);
                    mostrarMensaje("Notificacion Proveedor", "Proveedor eliminado", "El Proveedor se ha eliminado con exito", Alert.AlertType.INFORMATION);
                    limpiarCamposProveedores();
                } else {
                    mostrarMensaje("Notificacion Proveedor", "Proveedor no eliminado", "El Proveedor no se ha eliminado con exito", Alert.AlertType.ERROR);
                    limpiarCamposProveedores();
                }
            }
        } else {
            mostrarMensaje("Notificacion Proveedor", "Proveedor no seleccionado", "No ha seleccionado ningun elemento", Alert.AlertType.WARNING);
            limpiarCamposProveedores();
        }
    }

    @FXML
    void OnActionBuscar(ActionEvent event) {
        String buscar = tfBuscarProducto.getText().trim().toLowerCase();
        List<Lote> lotesEncontrados = lotes.stream()
                .filter(lote -> lote.getListaProductosLote().get(0).getNombre().toLowerCase().contains(buscar))
                .toList();

        if (lotesEncontrados.isEmpty()) {
            MensajeUtil.mostrarMensaje("Información","Sin resultados","No se encontraron lotes según el criterio de búsqueda.",Alert.AlertType.INFORMATION);
            tblPoductos.setItems(FXCollections.observableArrayList(lotes));
        } else {
            tblPoductos.setItems(FXCollections.observableArrayList(lotesEncontrados));
        }

    }
    @FXML
    void OnActionRefrescar(ActionEvent event) {
        tfBuscarProducto.clear();

        tblPoductos.setItems(FXCollections.observableArrayList(lotes));

        tblPoductos.refresh();
        tableRegistroVentas.setItems(FXCollections.observableArrayList(modelFactoryController.getPapCreativaExpress().getListaFacturas()));
        tableRegistroVentas.refresh();
    }

    @FXML
    void OnActionVerVenta(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("detallesVentas.fxml"));
            Parent root = loader.load();
            DetallesVentasController detallesVentasController = loader.getController();
            detallesVentasController.setControladorPrincipal(this);
            detallesVentasController.agregarDetallesVenta(ventasVolatiles);
            contador = 0;
            lblContador.setText(Integer.toString(contador));

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionAgregar(ActionEvent event) {
        if (loteSeleccionado != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Venta de Productos");
            dialog.setHeaderText("Ingrese la cantidad a vender:");
            dialog.setContentText("Cantidad:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(cantidadStr -> {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    venderProductos(loteSeleccionado, cantidad);
                    tableLotes.refresh();
                } catch (NumberFormatException | IOException e) {
                    MensajeUtil.mostrarMensaje("Error", "Número no válido.","Ingrese por favor una cantidad válida", Alert.AlertType.ERROR);
                }
            });
        }
    }
    private void venderProductos(Lote lote, int cantidad) throws IOException {
        Producto producto= lote.getListaProductosLote().get(0);
        double precioUnitario= lote.getListaProductosLote().get(0).getPrecioVenta();
        double porcentajeDescuento=0;
        if(cantidad>20){
            porcentajeDescuento=0.10;
        }
        if (cantidad <= 0) {
            MensajeUtil.mostrarMensaje("Error", "Número no válido.", "Ingrese por favor una cantidad válida", Alert.AlertType.ERROR);
            return;
        }

        if (cantidad > lote.getCantidad()) {
            MensajeUtil.mostrarMensaje("Error", "Cantidad no válida.", "La cantidad es mayor a los productos que hay en stock", Alert.AlertType.ERROR);
            return;
        }
        lote.setCantidad(lote.getCantidad() - cantidad);

        DetalleVenta detalleVenta = modelFactoryController.venderProducto(producto,precioUnitario,cantidad,porcentajeDescuento);

        if (lote.getCantidad() == 0) {
            modelFactoryController.eliminarLote(lote);
        }
        ventasVolatiles.add(detalleVenta);
        contador++;
        lblContador.setText(Integer.toString(contador));
        if (facturaActual == null) {
            Factura nuevaFactura = modelFactoryController.crearFactura(modelFactoryController.getUsuarioActual(), new ArrayList<>(ventasVolatiles));
            modelFactoryController.setFacturaActual(nuevaFactura);
            detalleVenta.setFactura(nuevaFactura);
            ventas.add(nuevaFactura);
        }
        tableRegistroVentas.refresh();
    }

    @FXML
    void onVerInfoVentasAction(ActionEvent event) {
        if(ventaSeleccionada!=null) {
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("InformacionFactura.fxml"));
                Parent root = loader.load();
                InformacionFacturaController informacionFacturaController = loader.getController();
                informacionFacturaController.setControladorPrincipal(this);
                informacionFacturaController.agregarDetallesVenta(ventaSeleccionada.getListaDetallesVenta());

                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onActionLotes(ActionEvent event) {
        paneAdmin.setVisible(false);
        paneCargos.setVisible(false);
        paneLotes.setVisible(true);
        paneProductos.setVisible(false);
        paneProveedores.setVisible(false);
        paneVentas.setVisible(false);
        paneDevoluciones.setVisible(false);
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmacion");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    private void mostrarInformacionLote(Lote l){
        txtCantidadLote.setText(String.valueOf(l.getCantidad()));
        txtPrecioUnitarioLote.setText(String.valueOf(l.getPrecioUnitario()));
        txtCostoProductoLote.setText(String.valueOf(l.getListaProductosLote().get(0).getPrecioCosto()));
        txtCostoTotalLote.setText(String.valueOf(l.getCostoTotalLote()));
        txtMarcaProductoLote.setText(l.getListaProductosLote().get(0).getMarca());
        txtDescripcionProductoLote.setText(l.getListaProductosLote().get(0).getDescripcionDetallada());
        txtNombreProductoLote.setText(l.getListaProductosLote().get(0).getNombre());
        txtPrecioVentaProductoLote.setText(String.valueOf(l.getListaProductosLote().get(0).getPrecioVenta()));
        cbProveedorLote.setValue(l.getProveedor());
        Date fechaC= l.getListaProductosLote().get(0).getFechaCaducidad();
        Instant instant=fechaC.toInstant();
        LocalDate fechaCaducidad=instant.atZone(ZoneId.systemDefault()).toLocalDate();
        dpFechaCaducidadProductoLote.setValue(fechaCaducidad);
    }

    private void  mostrarInformacionEmpleado(Usuario u){
        txtIdEmpleado.setText(u.getId());
        txtContraseniaEmpleado.setText(u.getContrasenia());
        txtEmailEmpleado.setText(u.getEmail());
        txtDireccionEmpleado.setText(u.getDireccion());
        txtNombreEmpleado.setText(u.getNombre());
        txtTelefonoEmpleado.setText(u.getTelefono());
        txtNombreUsuario.setText(u.getNombreUsuario());
        cbEstadoEmpleado.setValue(u.getEstado());
        cbCargoEmpleado.setValue(u.getCargo());
    }

    private void mostrarInformacionProveedor(Proveedor p){
        txtComentariosProveedor.setText(p.getComentarios());
        txtDireccionProveedor.setText(p.getDireccion());
        cbEstadoProveedor.setValue(p.getEstado());
        txtTelefonoProveedor.setText(p.getTelefono());
        txtNombreContactoProveedor.setText(p.getNombreContacto());
        txtNombreEmpresaProveedor.setText(p.getNombreEmpresa());
    }

    private void mostrarInformacionCargo(Cargo c){
        cbEstadoCargo.setValue(c.getEstado());
        txtNombreCargo.setText(c.getNombre());
        txtSalarioCargo.setText(String.valueOf(c.getSalario()));
        txtEmpleadosRequeridosCargo.setText(String.valueOf(c.getEmpleadosRequeridos()));
        txtDescripcionCargo.setText(c.getDescripcion());
    }

    private void limpiarCamposLote() {
        txtCantidadLote.setText("");
        txtPrecioUnitarioLote.setText("");
        txtCostoProductoLote.setText("");
        txtCostoTotalLote.setText("");
        txtMarcaProductoLote.setText("");
        txtDescripcionProductoLote.setText("");
        txtNombreProductoLote.setText("");
        txtPrecioVentaProductoLote.setText("");
        cbProveedorLote.setValue(null);
        dpFechaCaducidadProductoLote.setValue(null);
    }

    private  void limpiarCamposEmpleado(){
        txtIdEmpleado.setText("");
        txtContraseniaEmpleado.setText("");
        txtEmailEmpleado.setText("");
        txtDireccionEmpleado.setText("");
        txtNombreEmpleado.setText("");
        txtTelefonoEmpleado.setText("");
        txtNombreUsuario.setText("");
        cbEstadoEmpleado.setValue(null);
        cbCargoEmpleado.setValue(null);
    }

    private void limpiarCamposCargo(){
        cbEstadoCargo.setValue(null);
        txtNombreCargo.setText("");
        txtSalarioCargo.setText("");
        txtEmpleadosRequeridosCargo.setText("");
        txtDescripcionCargo.setText("");
    }

    private void limpiarCamposProveedores(){
        txtComentariosProveedor.setText("");
        txtDireccionProveedor.setText("");
        cbEstadoProveedor.setValue(null);
        txtTelefonoProveedor.setText("");
        txtNombreContactoProveedor.setText("");
        txtNombreEmpresaProveedor.setText("");
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean datosValidosLote(String cantidad, String precioUnitario, String costoTotal, String nombreProducto, String precioVenta, String costo, String marca, String descripcion) {
        String mensaje= "";
        if(cantidad == null || cantidad.equals("")) {
            mensaje += "La cantidad es invalida \n" ;
        }
        if(precioUnitario == null || precioUnitario.equals("")) {
            mensaje += "El precio unitario es invalido \n" ;
        }
        if(costoTotal == null || costoTotal.equals("")){
            mensaje += "El costo total es invalido \n" ;
        }
        if(nombreProducto==null || nombreProducto.equals("")){
            mensaje+="El nombre del Producto es invalido\n";
        }
        if(precioVenta==null || precioVenta.equals("")){
            mensaje+="El precio de Venta del Producto es invalido\n";
        }
        if(costo==null || costo.equals("")){
            mensaje+="El costo del Producto es invalido\n";
        }
        if(marca==null || marca.equals("")){
            mensaje+="La marca del Producto es invalida\n";
        }
        if(descripcion==null || descripcion.equals("")){
            mensaje+="La descripcion del Producto es invalida\n";
        }
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificacion Usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private boolean datosValidosProveedor(String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios) {
        String mensaje= "";
        if(nombreEmpresa == null || nombreEmpresa.equals("")) {
            mensaje += "El nombre de laEmpresa es invalido \n" ;
        }
        if(direccion == null || direccion.equals("")) {
            mensaje += "La direccion es invalida \n" ;
        }
        if(telefono == null || telefono.equals("")){
            mensaje += "El telefono es invalido \n" ;
        }
        if(nombreContacto==null || nombreContacto.equals("")){
            mensaje+="El nombre del Contacto es invalido\n";
        }
        if(comentarios==null || comentarios.equals("")){
            mensaje+="Los comentarios son invalidos\n";
        }
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificacion Usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private boolean datosValidosCargo(String nombreCargo, String salario, String cantidadEmpleados, String descripcion) {
        String mensaje= "";
        if(nombreCargo == null || nombreCargo.equals("")) {
            mensaje += "El nombre del cargo es invalido \n" ;
        }
        if(salario == null || salario.equals("")) {
            mensaje += "El salario es invalido \n" ;
        }
        if(cantidadEmpleados == null || cantidadEmpleados.equals("")){
            mensaje += "La cantidad de Empleados es invalida \n" ;
        }
        if(descripcion==null || descripcion.equals("")){
            mensaje+="La descripcion es invalida\n";
        }
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificacion Usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private boolean datosValidosEmpleado(String nombreUsuario, String contrasena, String nombre, String email, String nDocumento, String telefono, String direccion, Estado estado, Cargo cargo) {
        String mensaje= "";
        if(nombreUsuario == null || nombreUsuario.equals("")) {
            mensaje += "El nombre de Usuario es invalido \n" ;
        }
        if(direccion == null || direccion.equals("")) {
            mensaje += "La direccion unitario es invalida \n" ;
        }
        if(telefono == null || telefono.equals("")){
            mensaje += "El telefono es invalido \n" ;
        }
        if(!validarContrasenia(contrasena)){
            mostrarMensaje("Contraseña","Requisitos contraseña","La contraseña no cumple con los requisitos: \nLetra mayuscula \nLetra minuscula \nNumero \nCaracter especial \nMas de 8 caracteres", Alert.AlertType.WARNING);
            mensaje+="La contraseña es invalida\n";
        }
        if(nombre==null || nombre.equals("")){
            mensaje+="El nombre es invalido\n";
        }
        if(email==null || email.equals("")){
            mensaje+="El email es invalido\n";
        }
        if(nDocumento==null || nDocumento.equals("")){
            mensaje+="El numero de Documento es invalido\n";
        }
        if(cargo==null){
            mensaje+="El cargo no fue seleccionado correctamente";
        }
        if(estado==null){
            mensaje+="El estado no fue seleccionado correctamente";
        }
        return mensaje.equals("");

    }

    private boolean validarContrasenia(String contrasenia) {
        boolean uppercase = contrasenia.matches(".*[A-Z].*");
        boolean lowercase = contrasenia.matches(".*[a-z].*");
        boolean digit = contrasenia.matches(".*\\d.*");
        boolean specialChar = contrasenia.matches(".*[^A-Za-z0-9].*");
        boolean length = contrasenia.length()>=8;
        return uppercase && lowercase && digit && specialChar && length;
    }

    @FXML
    void OnActionCodigoBarras(ActionEvent event) {
        String uniqueId = txtCostoTotalLote.getText();
        Image imagenCodigoBarras = GenerarCodigoBarras.generarCodigoDeBarras(uniqueId);
        imageCodigoBarras.setImage(imagenCodigoBarras);
        MensajeUtil.mensajeInformacion("Código de barras generado con éxito y guardado en la carpeta de recursos.");
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
            String nombreArchivo = txtNombreUsuario.getText() ;

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
    public TableView<DetalleVenta> getTableDevoluciones() {
        return tableDevoluciones;
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

