package com.example.papcreativaexpress.Model;

import java.util.Date;
import java.util.List;

public class Categoria {
    private String id;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String estado;
    private List<Categoria> listaSubCategorias;
    private List<Producto> listaProductosCategoria;

    public Categoria(){

    }

}
