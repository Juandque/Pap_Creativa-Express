package com.example.papcreativaexpress.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Date fechaFactura;
    private double subtotalFactura;
    private double totalFactura;
    private double impuesto;
    private ArrayList<DetalleVenta> listaDetallesVenta;
    private Usuario empleadoEncargadoFactura;

    public Factura(){

    }

    public Factura(int id, Date fechaFactura, double subtotalFactura, double totalFactura, ArrayList<DetalleVenta> listaDetallesVenta, Usuario empleadoEncargadoFactura,double impuesto) {
        this.id = id;
        this.fechaFactura = fechaFactura;
        this.subtotalFactura = subtotalFactura;
        this.totalFactura = totalFactura;
        this.listaDetallesVenta = listaDetallesVenta;
        this.empleadoEncargadoFactura = empleadoEncargadoFactura;
        this.impuesto = impuesto;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getSubtotalFactura() {
        return subtotalFactura;
    }

    public void setSubtotalFactura(double subtotalFactura) {
        this.subtotalFactura = subtotalFactura;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public ArrayList<DetalleVenta> getListaDetallesVenta() {
        return listaDetallesVenta;
    }

    public void setListaDetallesVenta(ArrayList<DetalleVenta> listaDetallesVenta) {
        this.listaDetallesVenta = listaDetallesVenta;
    }

    public Usuario getEmpleadoEncargadoFactura() {
        return empleadoEncargadoFactura;
    }

    public void setEmpleadoEncargadoFactura(Usuario empleadoEncargadoFactura) {
        this.empleadoEncargadoFactura = empleadoEncargadoFactura;
    }
}
