package com.example.papcreativaexpress.Model;

import java.util.Date;
import java.util.List;

public class Inventario {
    private Date fechaActualizacion;
    private double valorTotalInventario;
    private String nivelAlertaStock;
    private List<Producto> listaProductosInventario;
    private List<Caja> listaCajasInventario;
    public Inventario(){

    }
}
