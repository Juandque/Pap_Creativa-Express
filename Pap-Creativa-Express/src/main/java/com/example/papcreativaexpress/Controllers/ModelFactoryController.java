package com.example.papcreativaexpress.Controllers;

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

    }
    public boolean verificarInicioSesion(String correo, String contrasena) {
        return papCreativaExpress.verificarCredenciales(correo,contrasena);
    }
    public Usuario ObtenerUsuario(String correo){
        return papCreativaExpress.buscarUsuarioPorCorreo(correo);
    }
    public int obtenerIntentosFallidos(String correo){
        return papCreativaExpress.obtenerIntentosFallidos(correo);
    }
    public void bloquearUsuario(String correo){
        papCreativaExpress.bloquearUsuario(correo);
    }

    public Usuario crearEmpleado(){
        Usuario usr= new Usuario();
        return usr;
    }

    public boolean eliminarEmpleado(Usuario usr){
        return true;
    }

    public boolean actualizarEmpleado(String nombreUsuario, String nuevoNombreUsuario, String contrasenia, String nombre, String telefono,String id,String email,String direccion,String  estado, Cargo cargo){
        return true;
    }

    public Cargo crearCargo(String nombre, String descripcion,double salario,String estado, int empleadosRequeridos){
        Cargo cargo= new Cargo();
        return cargo;
    }

    public boolean eliminarCargo(Cargo cargo){
        return true;
    }

    public boolean actualizarCargo(String nombre, String descripcion,double salario,String estado, int empleadosRequeridos){
        return true;
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
        return true;
    }

    public Proveedor anadirProveedor(String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado){
        Proveedor proveedor= new Proveedor();
        return proveedor;
    }

    public boolean eliminarProveedor(Proveedor proveedor){
        return true;
    }

    public boolean actualizarProveedor(String idProveedor, String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado){
        return true;
    }
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }
    public void actualizarContrasenia(Usuario usuario){
        papCreativaExpress.actualizarUsuario(usuario);
    }

}
