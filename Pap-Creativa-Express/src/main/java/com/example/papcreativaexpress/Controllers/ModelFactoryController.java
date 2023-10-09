package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Excepciones.CorreoNoExisteException;
import com.example.papcreativaexpress.Model.*;
import com.example.papcreativaexpress.Persistencia.Persistencia;
import com.example.papcreativaexpress.Utils.EnviarCorreo;
import com.example.papcreativaexpress.Utils.MensajeUtil;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class ModelFactoryController {
    private PapCreativaExpress papCreativaExpress;

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        inicializarDatos();
        guardarResourceXML();
        guardarResourceBinario();
        cargarRecursoBinario();
        cargarRecursoXML();


    }

    public void inicializarDatos(){
        papCreativaExpress = new PapCreativaExpress();

        Usuario usr= new Usuario();
        usr.setNombreUsuario("Juanse");
        usr.setDireccion("Cra x N° xx-xx");
        usr.setId("10963490");
        usr.setTelefono("31948303");
        usr.setNombre("Juan");
        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes_usuarios/Falcao.png")));
        usr.setFotoUsuario(imagen);
        usr.setEmail("juans.orozcoa@uqvirtual.edu.co");
        usr.setContrasenia("10973");
        LocalDate fecha= LocalDate.now();
        usr.setFechaRegistro(fecha);
        usr.setEstado(Estado.DISPONIBLE);
        papCreativaExpress.getListaEmpleados().add(usr);
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
                                 String id, String telefono, String direccion, Estado estado, Cargo cargo) {
        Usuario usuario = papCreativaExpress.crearEmpleado(nombre, nombreUsuario, contrasenia, correo, id, telefono, direccion,estado,cargo);
        return usuario;
    }

    public boolean eliminarEmpleado(Usuario usr) {
        boolean flagEliminado = papCreativaExpress.eliminarEmpleado(usr);
        return flagEliminado;
    }

    public boolean actualizarEmpleado(String nombreUsuario, String nuevoNombreUsuario, String contrasenia, String nombre, String telefono, String id, String email, String direccion, Estado estado, Cargo cargo) {
        boolean flagActualizado = papCreativaExpress.actualizarEmpleado(nombreUsuario, nuevoNombreUsuario, contrasenia, nombre, telefono, id, email, direccion, estado, cargo);
        return flagActualizado;
    }

    public Cargo crearCargo(String nombre, String descripcion, double salario, String estado, int empleadosRequeridos) {
        Cargo cargo = papCreativaExpress.anadirCargo(nombre, descripcion, salario, estado, empleadosRequeridos);
        return cargo;
    }

    public boolean eliminarCargo(Cargo cargo) {
        boolean flagEliminado = papCreativaExpress.eliminarCargo(cargo);
        return flagEliminado;
    }

    public boolean actualizarCargo(String idCargo, String nombre, String descripcion, double salario, String estado, int empleadosRequeridos) {
        boolean flagActualizado = papCreativaExpress.actualizarCargo(idCargo, nombre, descripcion, salario, estado, empleadosRequeridos);
        return flagActualizado;
    }

    public Lote crearLote(int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion) {
        Lote lote = papCreativaExpress.anadirLote(cantidad, precioUnitario, precioTotal, proveedor, nombre, precioVenta, fechaCaducidad, costo, marca, descripcion);
        return lote;
    }

    public boolean eliminarLote(Lote lote) {
        boolean flagEliminado = papCreativaExpress.eliminarLote(lote);
        return flagEliminado;
    }

    public boolean actualizarLote(String idLote, int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion) {
        boolean flagActualizado = papCreativaExpress.actualizarLote(idLote, cantidad, precioUnitario, precioTotal, proveedor, nombre, precioVenta, fechaCaducidad, costo, marca, descripcion);
        return flagActualizado;
    }

    public Proveedor anadirProveedor(String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado) {
        Proveedor proveedor = papCreativaExpress.anadirProveedor(nombreEmpresa, direccion, telefono, nombreContacto, comentarios, estado);
        return proveedor;
    }

    public boolean eliminarProveedor(Proveedor proveedor) {
        boolean flagEliminado = papCreativaExpress.eliminarProveedor(proveedor);
        return flagEliminado;
    }

    public boolean actualizarProveedor(String idProveedor, String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado) {
        boolean flagActualizado = papCreativaExpress.actualizarProveedor(idProveedor, nombreEmpresa, direccion, telefono, nombreContacto, comentarios, estado);
        return flagActualizado;
    }

    public void actualizarContrasenia(Usuario usuario, String contrasenia) {
        papCreativaExpress.actualizarContrasenaUsuario(usuario, contrasenia);
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

    public void mostrarRuta() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            String rutaDestino = selectedFile.getAbsolutePath();
            Image imagenFX = new Image(rutaDestino);
            guardarImagen(imagenFX, rutaDestino);
            Usuario usuarioActual = getUsuarioActual();
            usuarioActual.setFotoUsuario(imagenFX);
        } else {
            MensajeUtil.mensajeInformacion("Error");
        }
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
    public void guardarResourceXML() {
        Persistencia.guardarRecursoXML(papCreativaExpress);
    }
    public void guardarResourceBinario() {
        Persistencia.guardarRecursoBancoBinario(papCreativaExpress);
    }
    public static PapCreativaExpress cargarRecursoBinario(){
        return Persistencia.cargarRecursoBancoBinario();

    }
    public static PapCreativaExpress cargarRecursoXML(){
        return Persistencia.cargarRecursoXML();

    }

}
