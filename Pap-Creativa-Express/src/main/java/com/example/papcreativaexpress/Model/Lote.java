package com.example.papcreativaexpress.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    public Lote(Date fechaEntrada, int cantidad, String id, double precioUnitario, double costoTotalLote, Date fechaRegistro, Proveedor proveedor) {
        this.fechaEntrada = fechaEntrada;
        this.cantidad = cantidad;
        this.id = id;
        this.precioUnitario = precioUnitario;
        this.costoTotalLote = costoTotalLote;
        this.fechaRegistro = fechaRegistro;
        this.listaProductosLote = new ArrayList<>();
        this.proveedor = proveedor;
        this.codigoBarrasImage = null;
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

    public boolean crearProductosLote(String id,String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion, Proveedor proveedor){
        Producto aux= new Producto(id, nombre, precioVenta, fechaCaducidad, costo, marca, 0, descripcion, new Date(), proveedor);
        if(aux==null){
            return false;
        }
        listaProductosLote.add(aux);
        return true;
    }

    public void eliminarCantidadProductos(int cantidad){
        int productosEliminar=cantidad;
        Iterator<Producto> iterator= listaProductosLote.iterator();
        while (iterator.hasNext()&&productosEliminar>0){
            iterator.next();
            iterator.remove();
            productosEliminar--;
        }
    }

    public void actualizarProductosEnlistados(String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion, Proveedor proveedor){
        for(Producto p: listaProductosLote){
            p.setNombre(nombre);
            p.setProveedor(proveedor);
            p.setFechaModificacion(new Date());
            p.setDescripcionDetallada(descripcion);
            p.setFechaCaducidad(fechaCaducidad);
            p.setMarca(marca);
            p.setPrecioCosto(costo);
            p.setPrecioVenta(precioVenta);
        }
    }

}
