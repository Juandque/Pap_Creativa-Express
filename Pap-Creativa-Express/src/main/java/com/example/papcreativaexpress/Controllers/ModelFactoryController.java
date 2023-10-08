package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Excepciones.CorreoNoExisteException;
import com.example.papcreativaexpress.Excepciones.UsuarioExisteException;
import com.example.papcreativaexpress.Model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelFactoryController {
    private PapCreativaExpress papCreativaExpress;
    private Usuario usuarioActual;

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        inicializarDatos();
    }

    public void inicializarDatos(){
        papCreativaExpress = new PapCreativaExpress();
        Usuario usr= new Usuario();
        usr.setEmail("aaa@gmail.com");
        usr.setContrasenia("10973");
        papCreativaExpress.getListaEmpleados().add(usr);
    }
    public boolean verificarInicioSesion(String correo, String contrasena) throws CorreoNoExisteException {
        return papCreativaExpress.verificarCredenciales(correo,contrasena);
    }
    public Usuario ObtenerUsuario(String correo){
        return papCreativaExpress.buscarUsuarioPorCorreo(correo);
    }
    public int obtenerIntentosFallidos(String correo){
        return papCreativaExpress.obtenerIntentosFallidos(correo);
    }
    public void bloquearUsuario(String correo) throws CorreoNoExisteException {
        papCreativaExpress.bloquearUsuario(correo);
    }
    public Usuario crearEmpleado(String nombre, String NombreUsuario, String contrasenia, String correo, String id, String telefono, String direccion){
        Usuario usuario =papCreativaExpress.crearEmpleado(nombre,NombreUsuario,contrasenia,correo,id,telefono,direccion) ;
        return usuario;
    }
    public boolean eliminarEmpleado(Usuario usr){
        boolean flagEliminado= papCreativaExpress.eliminarEmpleado(usr);
        return flagEliminado;
    }

    public boolean actualizarEmpleado(String nombreUsuario, String nuevoNombreUsuario, String contrasenia, String nombre, String telefono,String id,String email,String direccion,Estado  estado, Cargo cargo){
        boolean flagActualizado= papCreativaExpress.actualizarEmpleado(nombreUsuario,nuevoNombreUsuario,contrasenia,nombre,telefono,id,email,direccion,estado,cargo);
        return  flagActualizado;
    }

    public Cargo crearCargo(String nombre, String descripcion,double salario,String estado, int empleadosRequeridos){
        Cargo cargo= papCreativaExpress.anadirCargo(nombre,descripcion,salario,estado,empleadosRequeridos);
        return cargo;
    }

    public boolean eliminarCargo(Cargo cargo){
        boolean flagEliminado= papCreativaExpress.eliminarCargo(cargo);
        return flagEliminado;
    }

    public boolean actualizarCargo(String idCargo, String nombre, String descripcion,double salario,String estado, int empleadosRequeridos){
        boolean flagActualizado= papCreativaExpress.actualizarCargo(idCargo,nombre,descripcion,salario,estado,empleadosRequeridos);
        return flagActualizado;
    }

    public Lote crearLote(int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion){
        Lote lote= papCreativaExpress.anadirLote(cantidad,precioUnitario,precioTotal,proveedor,nombre,precioVenta,fechaCaducidad,costo,marca,descripcion);
        return lote;
    }

    public boolean eliminarLote(Lote lote){
        boolean flagEliminado= papCreativaExpress.eliminarLote(lote);
        return flagEliminado;
    }

    public boolean actualizarLote(String idLote,int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion){
        boolean flagActualizado= papCreativaExpress.actualizarLote(idLote,cantidad,precioUnitario,precioTotal,proveedor,nombre,precioVenta,fechaCaducidad,costo,marca,descripcion);
        return flagActualizado;
    }

    public Proveedor anadirProveedor(String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado){
        Proveedor proveedor= papCreativaExpress.anadirProveedor(nombreEmpresa,direccion,telefono,nombreContacto,comentarios,estado);
        return proveedor;
    }

    public boolean eliminarProveedor(Proveedor proveedor){
        boolean flagEliminado= papCreativaExpress.eliminarProveedor(proveedor);
        return flagEliminado;
    }

    public boolean actualizarProveedor(String idProveedor, String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado){
        boolean flagActualizado= papCreativaExpress.actualizarProveedor(idProveedor,nombreEmpresa,direccion,telefono,nombreContacto,comentarios,estado);
        return flagActualizado;
    }
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }
    public void actualizarContrasenia(Usuario usuario){
        papCreativaExpress.actualizarContrasenaUsuario(usuario);
    }


}
