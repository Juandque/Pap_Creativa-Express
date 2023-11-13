package com.example.papcreativaexpress.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    private  String id;
    private String nombreEmpresa;
    private String direccion;
    private String telefono;
    private String nombreContacto;
    private String comentarios;
    private EstadoProveedor estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private List<Producto> listaProductosProveedor;

    public Proveedor(){

    }

    public Proveedor(String id, String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, EstadoProveedor estado, Date fechaRegistro) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nombreContacto = nombreContacto;
        this.comentarios = comentarios;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public EstadoProveedor getEstado() {
        return estado;
    }

    public void setEstado(EstadoProveedor estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<Producto> getListaProductosProveedor() {
        return listaProductosProveedor;
    }

    public void setListaProductosProveedor(List<Producto> listaProductosProveedor) {
        this.listaProductosProveedor = listaProductosProveedor;
    }
}
