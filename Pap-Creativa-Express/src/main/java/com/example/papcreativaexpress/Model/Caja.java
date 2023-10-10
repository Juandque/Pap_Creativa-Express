package com.example.papcreativaexpress.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Caja  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String contrasenia;
    private Date horaApertura;
    private Date HoraCierre;
    private String estado;
    private double totalEfectivo;
    private Usuario empleadoEncargadocaja;
    private List<Factura> listafacturasCaja;

    public Caja(){

    }

}
