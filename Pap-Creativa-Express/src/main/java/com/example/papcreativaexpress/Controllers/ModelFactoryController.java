package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Excepciones.CorreoNoExisteException;
import com.example.papcreativaexpress.Model.*;
import com.example.papcreativaexpress.Persistencia.Persistencia;
import com.example.papcreativaexpress.Utils.EnviarCorreo;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class ModelFactoryController {
    PapCreativaExpress papCreativaExpress;


    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        cargarResourceBinario();
    }

    public PapCreativaExpress getPapCreativaExpress(){
        return papCreativaExpress;
    }

    public boolean verificarInicioSesion(String correo, String contrasena) throws CorreoNoExisteException {
        return papCreativaExpress.verificarCredenciales(correo, contrasena);
    }

    public Usuario ObtenerUsuario(String correo) {
        return papCreativaExpress.buscarUsuarioPorCorreo(correo);
    }

    public int obtenerIntentosFallidos(String correo) {
        return papCreativaExpress.obtenerIntentosFallidos(correo);
    }

    public void bloquearUsuario(String correo) throws CorreoNoExisteException {
        papCreativaExpress.bloquearUsuario(correo);
    }

    public Usuario crearEmpleado(String nombre, String nombreUsuario, String contrasenia, String correo,
                                 String id, String telefono, String direccion, Estado estado, Cargo cargo) throws IOException {
        Usuario usuario = papCreativaExpress.crearEmpleado(nombre, nombreUsuario, contrasenia, correo, id, telefono, direccion,estado,cargo);
        guardarResourceBinario();
        return usuario;
    }

    public boolean eliminarEmpleado(Usuario usr) throws IOException {
        boolean flagEliminado = papCreativaExpress.eliminarEmpleado(usr);
        guardarResourceBinario();
        return flagEliminado;
    }

    public boolean actualizarEmpleado(String nombreUsuario, String nuevoNombreUsuario, String contrasenia, String nombre, String telefono, String id, String email, String direccion, Estado estado, Cargo cargo) throws IOException {
        boolean flagActualizado = papCreativaExpress.actualizarEmpleado(nombreUsuario, nuevoNombreUsuario, contrasenia, nombre, telefono, id, email, direccion, estado, cargo);
        guardarResourceBinario();
        return flagActualizado;
    }

    public Cargo crearCargo(String nombre, String descripcion, double salario, EstadoCargo estado, int empleadosRequeridos) throws IOException {
        Cargo cargo = papCreativaExpress.anadirCargo(nombre, descripcion, salario, estado, empleadosRequeridos);
        guardarResourceBinario();
        return cargo;
    }

    public boolean eliminarCargo(Cargo cargo) throws IOException {
        boolean flagEliminado = papCreativaExpress.eliminarCargo(cargo);
        guardarResourceBinario();
        return flagEliminado;
    }

    public boolean actualizarCargo(String idCargo, String nombre, String descripcion, double salario, EstadoCargo estado, int empleadosRequeridos) throws IOException {
        boolean flagActualizado = papCreativaExpress.actualizarCargo(idCargo, nombre, descripcion, salario, estado, empleadosRequeridos);
        guardarResourceBinario();

        return flagActualizado;
    }

    public Lote crearLote(int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion) throws IOException {
        Lote lote = papCreativaExpress.anadirLote(cantidad, precioUnitario, precioTotal, proveedor, nombre, precioVenta, fechaCaducidad, costo, marca, descripcion);
        guardarResourceBinario();
        return lote;
    }

    public boolean eliminarLote(Lote lote) throws IOException {
        boolean flagEliminado = papCreativaExpress.eliminarLote(lote);
        guardarResourceBinario();

        return flagEliminado;
    }

    public boolean actualizarLote(String idLote, int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion) throws IOException {
        boolean flagActualizado = papCreativaExpress.actualizarLote(idLote, cantidad, precioUnitario, precioTotal, proveedor, nombre, precioVenta, fechaCaducidad, costo, marca, descripcion);
        guardarResourceBinario();
        return flagActualizado;
    }

    public Proveedor anadirProveedor(String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, EstadoProveedor estado) throws IOException {
        Proveedor proveedor = papCreativaExpress.anadirProveedor(nombreEmpresa, direccion, telefono, nombreContacto, comentarios, estado);
        guardarResourceBinario();
        return proveedor;
    }

    public boolean eliminarProveedor(Proveedor proveedor) throws IOException {
        boolean flagEliminado = papCreativaExpress.eliminarProveedor(proveedor);
        guardarResourceBinario();
        return flagEliminado;
    }

    public boolean actualizarProveedor(String idProveedor, String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, EstadoProveedor estado) throws IOException {
        boolean flagActualizado = papCreativaExpress.actualizarProveedor(idProveedor, nombreEmpresa, direccion, telefono, nombreContacto, comentarios, estado);
        guardarResourceBinario();
        return flagActualizado;
    }

    public void actualizarContrasenia(Usuario usuario, String contrasenia) throws IOException {
        papCreativaExpress.actualizarContrasenaUsuario(usuario, contrasenia);
        guardarResourceBinario();
    }

    public String generarCodigo() {
        Random random = new Random();
        int codigoGenerado = 10000 + random.nextInt(90000);
        String codigoGeneradoStr = String.valueOf(codigoGenerado);
        return codigoGeneradoStr;
    }

    public void enviarCorreo(String remitente, String destinatario, String asunto, String cuerpo) {
        EnviarCorreo Correo = new EnviarCorreo();
        Correo.enviarCorreo(remitente, destinatario, asunto, cuerpo);
    }

    public void asignarUsuarioActual(String correo) {
        papCreativaExpress.asignarUsuarioActual(correo);
    }

    public Usuario getUsuarioActual() {
        return papCreativaExpress.getUsuarioActual();
    }

    public void setUsuarioActual(Usuario usuario) {
        papCreativaExpress.setUsuarioActual(usuario);
    }

    void guardarImagen(Image imagen, String rutaDestino) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imagen, null);
        try {
            File archivoDestino = new File(rutaDestino);
            ImageIO.write(bufferedImage, "png", archivoDestino);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Image getImagenSeleccionada() {
        return papCreativaExpress.getImagenActual();
    }

    public void setImagenSeleccionada(Image imagenSeleccionada) {
        papCreativaExpress.setImagenActual(imagenSeleccionada);
    }
    public Lote getLoteActual() {
        return papCreativaExpress.getLoteActual();
    }
    public Factura getFacturaActual() {
        return papCreativaExpress.getFacturaActual();
    }public void setFacturaActual(Factura facturaActual) {
         papCreativaExpress.setFacturaActual(facturaActual);
    }


    public void setLoteActual(Lote loteActual) {
        papCreativaExpress.setLoteActual(loteActual);
    }

    public ArrayList<Usuario> getEmpleados(){
        return  papCreativaExpress.getListaEmpleados();
    }

    public ArrayList<Lote> getLotes(){
        ArrayList<Lote> listaLotes= papCreativaExpress.getListaLotes();
        return listaLotes;
    }

    public ArrayList<Cargo> getCargos(){
        return  papCreativaExpress.getListaCargos();
    }

    public ArrayList<DetalleVenta> getDetallesVenta(){
        return papCreativaExpress.getListaDetalleVentas();
    }

    public ArrayList<Proveedor> getProveedores(){
        return  papCreativaExpress.getListaProveedores();
    }
   public ArrayList<Factura>getFacturas(){return  papCreativaExpress.getListaFacturas();}
    public void cargarResourceBinario() {
        papCreativaExpress = (PapCreativaExpress) Persistencia.cargar().orElse(new PapCreativaExpress());
    }

    public void guardarResourceBinario() {
        Persistencia.guardar(papCreativaExpress);
    }

    public void cargarResourceXML() {
        papCreativaExpress = Persistencia.cargarRecursoXML();
    }

    public void guardarResourceXML() {
        Persistencia.guardarRecursoXML(papCreativaExpress);
    }

    public Image cargarImagenEmpleado(String nombreEmpleado) {
        String rutaImagen = "Pap-Creativa-Express/src/main/resources/imagenes_usuarios" + File.separator+ nombreEmpleado + ".png";
        File archivoImagen = new File(rutaImagen);

        if (archivoImagen.exists()) {
            return new Image(archivoImagen.toURI().toString());
        } else {
            System.out.println("Imagen de empleado Nula");
            return null;
        }
    }
    public Image cargarImagenLote(String id) {
        String rutaImagen = "Pap-Creativa-Express/src/main/resources/Imagenes_Productos" + File.separator+ id + ".png";
        File archivoImagen = new File(rutaImagen);

        if (archivoImagen.exists()) {

            return new Image(archivoImagen.toURI().toString());
        } else {
            System.out.println("ES NULA");
            return null;
        }
    }
    public void asignarLoteActual(String id) {
        papCreativaExpress.asignarUsuarioActual(id);
    }
    public Lote buscarLotePorId(String id){
        return papCreativaExpress.buscarLotePorId(id);
    }

    public DetalleVenta venderProducto(Producto producto,double precioUnitario, int cantidad, double porcentajeDescuento){
        DetalleVenta detalleVenta= papCreativaExpress.crearDetalleVenta(producto,precioUnitario,cantidad,porcentajeDescuento);
        guardarResourceBinario();
        return detalleVenta;
    }
    public Factura crearFactura(Usuario empleado,ArrayList<DetalleVenta>detallesVentaList){
        Factura factura = papCreativaExpress.crearFactura(empleado,detallesVentaList);
        guardarResourceBinario();
        return factura;
    }

    public boolean procesarDevolucion(DetalleVenta detalleVenta, int cantidad){
        boolean devolucionRealizada= papCreativaExpress.procesarDevolucion(detalleVenta,cantidad);
        return devolucionRealizada;
    }
}

