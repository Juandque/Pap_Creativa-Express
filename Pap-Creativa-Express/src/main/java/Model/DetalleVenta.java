package Model;

public class DetalleVenta {
    private String id;
    private int cantidad;
    private double precioUnitario;
    private double subTotalDetalleVenta;
    private double descuentoDetalleVenta;
    private double totalDetalleVenta;
    private Factura factura;
    private Producto producto;

    public DetalleVenta(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubTotalDetalleVenta() {
        return subTotalDetalleVenta;
    }

    public void setSubTotalDetalleVenta(double subTotalDetalleVenta) {
        this.subTotalDetalleVenta = subTotalDetalleVenta;
    }

    public double getDescuentoDetalleVenta() {
        return descuentoDetalleVenta;
    }

    public void setDescuentoDetalleVenta(double descuentoDetalleVenta) {
        this.descuentoDetalleVenta = descuentoDetalleVenta;
    }

    public double getTotalDetalleVenta() {
        return totalDetalleVenta;
    }

    public void setTotalDetalleVenta(double totalDetalleVenta) {
        this.totalDetalleVenta = totalDetalleVenta;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
