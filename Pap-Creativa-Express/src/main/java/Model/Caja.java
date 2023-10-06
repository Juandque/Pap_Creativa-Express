package Model;

import java.util.Date;
import java.util.List;

public class Caja {
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
