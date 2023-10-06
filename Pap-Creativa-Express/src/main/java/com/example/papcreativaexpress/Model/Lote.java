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
    public Image getCodigoBarrasImage() {
        return codigoBarrasImage;
    }

    public void setCodigoBarrasImage(Image codigoBarrasImage) {
        this.codigoBarrasImage = codigoBarrasImage;
    }
}
