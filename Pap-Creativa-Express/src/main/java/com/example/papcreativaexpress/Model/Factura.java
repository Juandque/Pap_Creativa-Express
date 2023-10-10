package com.example.papcreativaexpress.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Date fechaFactura;
    private double subtotalFactura;
    private  double descuentoTotalFactura;
    private  double totalFactura;
    private List<DetalleVenta> listaDetallesVenta;
    private Usuario empleadoEncargadoFactura;

    public Factura(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public double getDescuentoTotalFactura() {
        return descuentoTotalFactura;
    }

    public void setDescuentoTotalFactura(double descuentoTotalFactura) {
        this.descuentoTotalFactura = descuentoTotalFactura;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public List<DetalleVenta> getListaDetallesVenta() {
        return listaDetallesVenta;
    }

    public void setListaDetallesVenta(List<DetalleVenta> listaDetallesVenta) {
        this.listaDetallesVenta = listaDetallesVenta;
    }

    public Usuario getEmpleadoEncargadoFactura() {
        return empleadoEncargadoFactura;
    }

    public void setEmpleadoEncargadoFactura(Usuario empleadoEncargadoFactura) {
        this.empleadoEncargadoFactura = empleadoEncargadoFactura;
    }
}
