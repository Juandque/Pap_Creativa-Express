package com.example.papcreativaexpress.Model;

import java.io.Serializable;
import java.util.Date;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private double precioVenta;
    private Date fechaCaducidad;
    private double precioCosto;
    private String marca;
    private int nivelStockMinimo;
    private String descripcionDetallada;
    private Date fechaModificacion;
    private Proveedor proveedor;
    private Lote lote;
    public Producto(){
    }

    public Producto(String id, String nombre, double precioVenta, Date fechaCaducidad, double precioCosto, String marca, int nivelStockMinimo, String descripcionDetallada, Date fechaModificacion, Proveedor proveedor, Lote lote) {
        this.id = id;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.fechaCaducidad = fechaCaducidad;
        this.precioCosto = precioCosto;
        this.marca = marca;
        this.nivelStockMinimo = nivelStockMinimo;
        this.descripcionDetallada = descripcionDetallada;
        this.fechaModificacion = fechaModificacion;
        this.proveedor = proveedor;
        this.lote=lote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getNivelStockMinimo() {
        return nivelStockMinimo;
    }

    public void setNivelStockMinimo(int nivelStockMinimo) {
        this.nivelStockMinimo = nivelStockMinimo;
    }

    public String getDescripcionDetallada() {
        return descripcionDetallada;
    }

    public void setDescripcionDetallada(String descripcionDetallada) {
        this.descripcionDetallada = descripcionDetallada;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }
}
