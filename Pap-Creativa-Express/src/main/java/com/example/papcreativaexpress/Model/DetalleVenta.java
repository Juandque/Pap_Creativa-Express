package com.example.papcreativaexpress.Model;

import java.io.Serializable;

public class DetalleVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int cantidad;
    private double precioUnitario;
    private double subTotalDetalleVenta;
    private double descuentoDetalleVenta;
    private double totalDetalleVenta;
    private Factura factura;
    private Producto producto;

    public DetalleVenta(){

    }

    public DetalleVenta(int id, int cantidad, double precioUnitario, double subTotalDetalleVenta, double descuentoDetalleVenta, double totalDetalleVenta, Factura factura, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotalDetalleVenta = subTotalDetalleVenta;
        this.descuentoDetalleVenta = descuentoDetalleVenta;
        this.totalDetalleVenta = totalDetalleVenta;
        this.factura = factura;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubTotalDetalleVenta() {
        return subTotalDetalleVenta;
    }

    public void setSubTotalDetalleVenta(double subTotalDetalleVenta) {
        this.subTotalDetalleVenta = subTotalDetalleVenta;
    }

    public double getDescuentoDetalleVenta() {
        return descuentoDetalleVenta;
    }

    public void setDescuentoDetalleVenta(double descuentoDetalleVenta) {
        this.descuentoDetalleVenta = descuentoDetalleVenta;
    }

    public double getTotalDetalleVenta() {
        return totalDetalleVenta;
    }

    public void setTotalDetalleVenta(double totalDetalleVenta) {
        this.totalDetalleVenta = totalDetalleVenta;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void realizarDevolucion(int cantidadDevuelta){
        if(cantidadDevuelta>cantidad){
            throw new IllegalArgumentException("La cantidad a devolver es mayor que la cantidad vendida originalmente.");
        }
        cantidad-=cantidadDevuelta;
    }
}
