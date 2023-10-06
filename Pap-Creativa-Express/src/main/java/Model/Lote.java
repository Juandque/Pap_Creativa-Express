package Model;

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
}
