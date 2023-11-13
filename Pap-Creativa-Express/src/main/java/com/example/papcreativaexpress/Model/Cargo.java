package com.example.papcreativaexpress.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String id;
    private String descripcion;
    private double salario;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private EstadoCargo estado;
    private int empleadosRequeridos;
    private List<Usuario> listaEmpleadosCargo;

    public Cargo(){

    }

    public Cargo(String nombre, String id, String descripcion, double salario, Date fechaCreacion, EstadoCargo estado, int empleadosRequeridos) {
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
        this.salario = salario;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.empleadosRequeridos = empleadosRequeridos;
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

    public EstadoCargo getEstado() {
        return estado;
    }

    public void setEstado(EstadoCargo estado) {
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

    public void setListaEmpleadosCargo(List<Usuario> listaEmpleadosCargo) {
        this.listaEmpleadosCargo = listaEmpleadosCargo;
    }
}
