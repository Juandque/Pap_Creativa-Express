package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Model.*;
import com.example.papcreativaexpress.Utils.TextFormatterUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventarioController implements Initializable {

    ModelFactoryController modelFactoryController;

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
    private ComboBox<Cargo> cbCargoEmpleado;

    @FXML
    private ComboBox<Estado> cbEstadoEmpleado;

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
    private TableColumn<Cargo, String> colEstadoCargo;

    @FXML
    private TableColumn<Proveedor, String> colEstadoProveedor;

    @FXML
    private TableColumn<Lote, Date> colFechaEntradaLote;

    @FXML
    private TableColumn<Usuario, String> colIdEmpleado;

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
    private TableView<Cargo> tableCargos;

    @FXML
    private TableView<Usuario> tableEmpleados;

    @FXML
    private TableView<Lote> tableLotes;

    @FXML
    private TableView<Proveedor> tableProveedores;

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

    private Cargo cargoSeleccionado;
    private Lote loteSeleccionado;
    private Proveedor proveedorSeleccionado;
    private Usuario empleadoSeleccionado;
    private ObservableList<Usuario> empleados= FXCollections.observableArrayList();
    private ObservableList<Proveedor> proveedores= FXCollections.observableArrayList();
    private ObservableList<Cargo> cargos=FXCollections.observableArrayList();
    private ObservableList<Lote> lotes=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelFactoryController= ModelFactoryController.getInstance();
        ArrayList<Usuario> empleadosArrayList = modelFactoryController.getEmpleados();
        ArrayList<Proveedor> proveedoresArrayList = modelFactoryController.getProveedores();
        ArrayList<Cargo> cargosArrayList = modelFactoryController.getCargos();
        ArrayList<Lote> lotesArrayList = modelFactoryController.getLotes();

        empleados.addAll(empleadosArrayList);
        proveedores.addAll(proveedoresArrayList);
        cargos.addAll(cargosArrayList);
        lotes.addAll(lotesArrayList);

        initializePaneAdmin();
        initializePaneProductos();
        initializePaneCargos();
        initializePaneLotes();
        initializePaneProveedores();
    }

    public void initializePaneAdmin(){
        this.colNombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colTelefonoEmpleado.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.colEmailEmpleado.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.colIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colDireccionEmpleado.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tableEmpleados.setItems(empleados);
    }

    public void initializePaneProveedores(){
        this.colDireccionProveedor.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.colEstadoProveedor.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colTelefonoProveedor.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.colNombreContactoProveedor.setCellValueFactory(new PropertyValueFactory<>("nombreContacto"));
        this.colNombreEmpresaProveedor.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresa"));
        tableProveedores.setItems(proveedores);
    }

    public void initializePaneCargos(){
        this.colNombreCargo.setCellValueFactory(new PropertyValueFactory<>(""));
        this.colSalarioCargo.setCellValueFactory(new PropertyValueFactory<>(""));
        this.colEstadoCargo.setCellValueFactory(new PropertyValueFactory<>(""));
        this.colCantidadEmpleadosCargo.setCellValueFactory(new PropertyValueFactory<>(""));
        tableCargos.setItems(cargos);
        txtEmpleadosRequeridosCargo.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        txtSalarioCargo.setTextFormatter(new TextFormatter<>(TextFormatterUtil::doubleFormat));
    }

    public void initializePaneLotes(){
        this.colNombreProductoLote.setCellValueFactory(new PropertyValueFactory<>(""));
        this.colCantidadLote.setCellValueFactory(new PropertyValueFactory<>(""));
        this.colFechaEntradaLote.setCellValueFactory(new PropertyValueFactory<>(""));
        this.colPrecioUnitarioLote.setCellValueFactory(new PropertyValueFactory<>(""));
        this.colCostoTotalLote.setCellValueFactory(new PropertyValueFactory<>(""));
        tableLotes.setItems(lotes);
        txtCantidadLote.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        txtPrecioUnitarioLote.setTextFormatter(new TextFormatter<>(TextFormatterUtil::doubleFormat));
        txtCostoTotalLote.setTextFormatter(new TextFormatter<>(TextFormatterUtil::doubleFormat));
        txtCostoProductoLote.setTextFormatter(new TextFormatter<>(TextFormatterUtil::doubleFormat));
        txtPrecioVentaProductoLote.setTextFormatter(new TextFormatter<>(TextFormatterUtil::doubleFormat));
    }

    public void initializePaneProductos(){
        this.colProductos.setCellValueFactory(new PropertyValueFactory<>(""));
        this.colCantidadProductos.setCellValueFactory(new PropertyValueFactory<>(""));
        this.colProveedor.setCellValueFactory(new PropertyValueFactory<>(""));
        this.colMarca.setCellValueFactory(new PropertyValueFactory<>(""));
        tblPoductos.setItems(lotes);
    }
    @FXML
    void OnActionEmpleados(ActionEvent event) {
        paneAdmin.setVisible(true);
        paneCargos.setVisible(false);
        paneLotes.setVisible(false);
        paneProductos.setVisible(false);
        paneProveedores.setVisible(false);
    }

    @FXML
    void OnActionProductos(ActionEvent event) {
        paneAdmin.setVisible(false);
        paneCargos.setVisible(false);
        paneLotes.setVisible(false);
        paneProductos.setVisible(true);
        paneProveedores.setVisible(false);
    }

    @FXML
    void OnActionProveedores(ActionEvent event) {
        paneAdmin.setVisible(false);
        paneCargos.setVisible(false);
        paneLotes.setVisible(false);
        paneProductos.setVisible(false);
        paneProveedores.setVisible(true);
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
        paneAdmin.setVisible(false);
        paneCargos.setVisible(true);
        paneLotes.setVisible(false);
        paneProductos.setVisible(false);
        paneProveedores.setVisible(false);
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
        paneAdmin.setVisible(false);
        paneCargos.setVisible(false);
        paneLotes.setVisible(true);
        paneProductos.setVisible(false);
        paneProveedores.setVisible(false);
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
        //cbProveedorLote.setValue(l.getProveedor().getNombreEmpresa());
        //dpFechaCaducidadProductoLote.setValue(l.getListaProductosLote().get(0).getFechaCaducidad());
    }

    private void  mostrarInformacionEmpleado(Usuario u){
        txtIdEmpleado.setText(u.getId());
        txtContraseniaEmpleado.setText(u.getContrasenia());
        txtEmailEmpleado.setText(u.getEmail());
        txtDireccionEmpleado.setText(u.getDireccion());
        txtNombreEmpleado.setText(u.getNombre());
        txtTelefonoEmpleado.setText(u.getTelefono());
        txtNombreUsuario.setText(u.getNombreUsuario());
        //cbEstadoEmpleado.setValue(u.getEstado());
        //cbCargoEmpleado.setValue(u.getCargo());
    }

    private void mostrarInformacionProveedor(Proveedor p){
        txtComentariosProveedor.setText(p.getComentarios());
        txtDireccionProveedor.setText(p.getDireccion());
        txtEstadoProveedor.setText(p.getEstado());
        txtTelefonoProveedor.setText(p.getTelefono());
        txtNombreContactoProveedor.setText(p.getNombreContacto());
        txtNombreEmpresaProveedor.setText(p.getNombreEmpresa());
    }

    private void mostrarInformacionCargo(Cargo c){
        txtEstadoCargo.setText(c.getEstado());
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
        txtEstadoCargo.setText("");
        txtNombreCargo.setText("");
        txtSalarioCargo.setText("");
        txtEmpleadosRequeridosCargo.setText("");
        txtDescripcionCargo.setText("");
    }

    private void limpiarCamposProveedores(){
        txtComentariosProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtEstadoProveedor.setText("");
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

    private boolean datosValidosProveedor(String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado) {
        String mensaje= "";
        if(nombreEmpresa == null || nombreEmpresa.equals("")) {
            mensaje += "El nombre de laEmpresa es invalido \n" ;
        }
        if(direccion == null || direccion.equals("")) {
            mensaje += "La direccion unitario es invalida \n" ;
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
        if(estado==null || estado.equals("")){
            mensaje+="El estado es invalido\n";
        }
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificacion Usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private boolean datosValidosCargo(String nombreCargo, String salario, String cantidadEmpleados, String descripcion,String estadoCargo) {
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
        if(estadoCargo==null || estadoCargo.equals("")){
            mensaje+="El estado es invalido\n";
        }
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificacion Usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private boolean datosValidosEmpleado(String nombreUsuario, String contrasena, String nombre, String email, String nDocumento, String telefono, String direccion) {
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
        if(contrasena==null || contrasena.equals("")){
            mensaje+="La contrasena es invalida\n";
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
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificacion Usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }



}
