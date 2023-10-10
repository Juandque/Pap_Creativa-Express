package com.example.papcreativaexpress.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date fechaActualizacion;
    private double valorTotalInventario;
    private String nivelAlertaStock;
    private List<Producto> listaProductosInventario;
    private List<Caja> listaCajasInventario;
    public Inventario(){

    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public double getValorTotalInventario() {
        return valorTotalInventario;
    }

    public void setValorTotalInventario(double valorTotalInventario) {
        this.valorTotalInventario = valorTotalInventario;
    }

    public String getNivelAlertaStock() {
        return nivelAlertaStock;
    }

    public void setNivelAlertaStock(String nivelAlertaStock) {
        this.nivelAlertaStock = nivelAlertaStock;
    }

    public List<Producto> getListaProductosInventario() {
        return listaProductosInventario;
    }

    public void setListaProductosInventario(List<Producto> listaProductosInventario) {
        this.listaProductosInventario = listaProductosInventario;
    }

    public List<Caja> getListaCajasInventario() {
        return listaCajasInventario;
    }

    public void setListaCajasInventario(List<Caja> listaCajasInventario) {
        this.listaCajasInventario = listaCajasInventario;
    }
}
