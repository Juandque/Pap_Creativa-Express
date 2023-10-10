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
        inicializarDatos();
       //guardarResourceXML();
       //cargarResourceXML();
        //guardarResourceBinario();
        cargarResourceBinario();


    }

    public void inicializarDatos(){

        papCreativaExpress = new PapCreativaExpress();
        try {
            Persistencia.guardarCargos(getPapCreativaExpress().getListaCargos());
            Persistencia.guardarProductos(getPapCreativaExpress().getListaProductos());
            Persistencia.guardarUsuarios(getPapCreativaExpress().getListaEmpleados());
            Persistencia.guardarLotes(getPapCreativaExpress().getListaLotes());
            Persistencia.guardarProveedores(getPapCreativaExpress().getListaProveedores());
            Persistencia.cargarDatosArchivos(getPapCreativaExpress());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Usuario usr= new Usuario();
        usr.setNombreUsuario("Juanse");
        usr.setDireccion("Cra x N° xx-xx");
        usr.setId("10963490");
        usr.setTelefono("31948303");
        usr.setNombre("Juan");
        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes_usuarios/Juan.png")));
        usr.setFotoUsuario(imagen);
        usr.setEmail("juans.orozcoa@uqvirtual.edu.co");
        usr.setContrasenia("10973");
        usr.setEstado(Estado.DISPONIBLE);
        papCreativaExpress.getListaEmpleados().add(usr);
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
        Persistencia.guardarUsuarios(papCreativaExpress.getListaEmpleados());
        return usuario;
    }

    public boolean eliminarEmpleado(Usuario usr) throws IOException {
        boolean flagEliminado = papCreativaExpress.eliminarEmpleado(usr);
        Persistencia.guardarUsuarios(papCreativaExpress.getListaEmpleados());
        return flagEliminado;
    }

    public boolean actualizarEmpleado(String nombreUsuario, String nuevoNombreUsuario, String contrasenia, String nombre, String telefono, String id, String email, String direccion, Estado estado, Cargo cargo) throws IOException {
        boolean flagActualizado = papCreativaExpress.actualizarEmpleado(nombreUsuario, nuevoNombreUsuario, contrasenia, nombre, telefono, id, email, direccion, estado, cargo);
        Persistencia.guardarUsuarios(papCreativaExpress.getListaEmpleados());
        return flagActualizado;
    }

    public Cargo crearCargo(String nombre, String descripcion, double salario, String estado, int empleadosRequeridos) throws IOException {
        Cargo cargo = papCreativaExpress.anadirCargo(nombre, descripcion, salario, estado, empleadosRequeridos);
        Persistencia.guardarCargos(papCreativaExpress.getListaCargos());
        return cargo;
    }

    public boolean eliminarCargo(Cargo cargo) throws IOException {
        boolean flagEliminado = papCreativaExpress.eliminarCargo(cargo);
        Persistencia.guardarCargos(papCreativaExpress.getListaCargos());
        return flagEliminado;
    }

    public boolean actualizarCargo(String idCargo, String nombre, String descripcion, double salario, String estado, int empleadosRequeridos) throws IOException {
        boolean flagActualizado = papCreativaExpress.actualizarCargo(idCargo, nombre, descripcion, salario, estado, empleadosRequeridos);
        Persistencia.guardarCargos(papCreativaExpress.getListaCargos());

        return flagActualizado;
    }

    public Lote crearLote(int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion) throws IOException {
        Lote lote = papCreativaExpress.anadirLote(cantidad, precioUnitario, precioTotal, proveedor, nombre, precioVenta, fechaCaducidad, costo, marca, descripcion);
        Persistencia.guardarLotes(papCreativaExpress.getListaLotes());

        return lote;
    }

    public boolean eliminarLote(Lote lote) throws IOException {
        boolean flagEliminado = papCreativaExpress.eliminarLote(lote);
        Persistencia.guardarLotes(papCreativaExpress.getListaLotes());

        return flagEliminado;
    }

    public boolean actualizarLote(String idLote, int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion) throws IOException {
        boolean flagActualizado = papCreativaExpress.actualizarLote(idLote, cantidad, precioUnitario, precioTotal, proveedor, nombre, precioVenta, fechaCaducidad, costo, marca, descripcion);
        Persistencia.guardarLotes(papCreativaExpress.getListaLotes());
        return flagActualizado;
    }

    public Proveedor anadirProveedor(String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado) throws IOException {
        Proveedor proveedor = papCreativaExpress.anadirProveedor(nombreEmpresa, direccion, telefono, nombreContacto, comentarios, estado);
        Persistencia.guardarProveedores(papCreativaExpress.getListaProveedores());
        return proveedor;
    }

    public boolean eliminarProveedor(Proveedor proveedor) throws IOException {
        boolean flagEliminado = papCreativaExpress.eliminarProveedor(proveedor);
        Persistencia.guardarProveedores(papCreativaExpress.getListaProveedores());
        return flagEliminado;
    }

    public boolean actualizarProveedor(String idProveedor, String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado) throws IOException {
        boolean flagActualizado = papCreativaExpress.actualizarProveedor(idProveedor, nombreEmpresa, direccion, telefono, nombreContacto, comentarios, estado);
        Persistencia.guardarProveedores(papCreativaExpress.getListaProveedores());
        return flagActualizado;
    }

    public void actualizarContrasenia(Usuario usuario, String contrasenia) throws IOException {
        papCreativaExpress.actualizarContrasenaUsuario(usuario, contrasenia);
        Persistencia.guardarUsuarios(papCreativaExpress.getListaEmpleados());
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

    public ArrayList<Proveedor> getProveedores(){
        return  papCreativaExpress.getListaProveedores();
    }
    public double SumaTotalPrecios(List<Producto>productoList){
        return papCreativaExpress.calcularPrecioTotal(productoList);
    }
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
            System.out.println("NO APARECE");

            return new Image(archivoImagen.toURI().toString());
        } else {
            System.out.println("ES NULLA");
            return null;
        }
    }
    public Image cargarImagenLote(String id) {
        String rutaImagen = "Pap-Creativa-Express/src/main/resources/imagenes_usuarios" + File.separator+ id + ".png";
        File archivoImagen = new File(rutaImagen);

        if (archivoImagen.exists()) {
            System.out.println("NO APARECE");

            return new Image(archivoImagen.toURI().toString());
        } else {
            System.out.println("ES NULLA");
            return null;
        }
    }
    public void asignarLoteActual(String id) {
        papCreativaExpress.asignarUsuarioActual(id);
    }
    public Lote buscarLotePorId(String id){
        return papCreativaExpress.buscarLotePorId(id);
    }



}

