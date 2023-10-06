package com.example.papcreativaexpress.Model;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class Lote {
    private Date fechaEntrada;
    private int cantidad;
    private String id;
    private double precioUnitario;
    private double costoTotalLote;
    private Date fechaRegistro;
    private List<Producto> listaProductosLote;
    private Proveedor proveedor;
    private Factura factura;
    private Image codigoBarrasImage;

    public Lote(){

    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getCostoTotalLote() {
        return costoTotalLote;
    }

    public void setCostoTotalLote(double costoTotalLote) {
        this.costoTotalLote = costoTotalLote;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Producto> getListaProductosLote() {
        return listaProductosLote;
    }

    public void setListaProductosLote(List<Producto> listaProductosLote) {
        this.listaProductosLote = listaProductosLote;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Image getCodigoBarrasImage() {
        return codigoBarrasImage;
    }

    public void setCodigoBarrasImage(Image codigoBarrasImage) {
        this.codigoBarrasImage = codigoBarrasImage;
    }
}
