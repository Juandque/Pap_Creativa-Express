package com.example.papcreativaexpress.Model;

import java.util.Date;
import java.util.List;

public class Cargo {
    private String nombre;
    private String id;
    private String descripcion;
    private double salario;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String estado;
    private int empleadosRequeridos;
    private List<Usuario> listaEmpleadosCargo;

    public Cargo(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getEmpleadosRequeridos() {
        return empleadosRequeridos;
    }

    public void setEmpleadosRequeridos(int empleadosRequeridos) {
        this.empleadosRequeridos = empleadosRequeridos;
    }

    public List<Usuario> getListaEmpleadosCargo() {
        return listaEmpleadosCargo;
    }
}
