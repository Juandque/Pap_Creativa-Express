package com.example.papcreativaexpress.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
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
