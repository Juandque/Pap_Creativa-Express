package Model;

import java.util.Date;
import java.util.List;

public class Compra {
    private String id;
    private Date fechaRegistro;
    private double totalCompra;
    private String comentarios;
    private Proveedor proveedor;
    private Usuario empleadoResponsable;
    private List<Producto> productosCompra;

    public Compra(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Usuario getEmpleadoResponsable() {
        return empleadoResponsable;
    }

    public void setEmpleadoResponsable(Usuario empleadoResponsable) {
        this.empleadoResponsable = empleadoResponsable;
    }

    public List<Producto> getProductosCompra() {
        return productosCompra;
    }

    public void setProductosCompra(List<Producto> productosCompra) {
        this.productosCompra = productosCompra;
    }
}
