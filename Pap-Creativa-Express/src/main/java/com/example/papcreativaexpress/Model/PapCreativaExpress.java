package com.example.papcreativaexpress.Model;

import com.example.papcreativaexpress.Excepciones.CorreoNoExisteException;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PapCreativaExpress implements Serializable {
    private static final long serialVersionUID = 1L;

    ArrayList<Caja> listaCajeros;
    ArrayList<Cargo> listaCargos;
    ArrayList<Categoria> listaCategorias;
    ArrayList<Compra> listaComprasAbastecimiento;
    ArrayList<DetalleVenta> listaDetalleVentas;
    ArrayList<Factura> listaFacturas;
    Inventario iventario;
    ArrayList<Lote> listaLotes;
    ArrayList<Producto> listaProductos;
    ArrayList<Proveedor> listaProveedores;
    ArrayList<Usuario> listaEmpleados;
    ArrayList<String> listaEmpleadosBloqueados;
    ArrayList<String> intentosFallidos = new ArrayList<>();
    private Usuario usuarioActual;
    private transient Image imagenActual;
    private Lote loteActual;
    private Factura facturaActual;


    int idProductos;
    int idLotes;
    int idCajeros;
    int idCargos;
    int idCategorias;
    int idCompras;
    int idDetallesVenta;
    int idFacturas;
    int idProveedores;
    int idEmpleados;

    public PapCreativaExpress() {
        listaCajeros = new ArrayList<>();
        listaCargos = new ArrayList<>();
        listaCategorias = new ArrayList<>();
        listaComprasAbastecimiento = new ArrayList<>();
        listaDetalleVentas = new ArrayList<>();
        listaFacturas = new ArrayList<>();
        iventario = new Inventario();
        listaLotes = new ArrayList<>();
        listaProductos = new ArrayList<>();
        listaProveedores = new ArrayList<>();
        listaEmpleados = new ArrayList<>();
        intentosFallidos = new ArrayList<>();
        listaEmpleadosBloqueados = new ArrayList<String>();
        idEmpleados = 1;
        idProveedores = 1;
        idCajeros = 1;
        idCargos = 1;
        idCategorias = 1;
        idCompras = 1;
        idDetallesVenta = 1;
        idFacturas = 1;
        idLotes = 1;
        idProductos = 1;
    }

    public List<Caja> getListaCajeros() {
        return listaCajeros;
    }

    public void setListaCajeros(ArrayList<Caja> listaCajeros) {
        this.listaCajeros = listaCajeros;
    }

    public ArrayList<Cargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(ArrayList<Cargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public ArrayList<Compra> getListaComprasAbastecimiento() {
        return listaComprasAbastecimiento;
    }

    public void setListaComprasAbastecimiento(ArrayList<Compra> listaComprasAbastecimiento) {
        this.listaComprasAbastecimiento = listaComprasAbastecimiento;
    }

    public ArrayList<DetalleVenta> getListaDetalleVentas() {
        return listaDetalleVentas;
    }

    public void setListaDetalleVentas(ArrayList<DetalleVenta> listaDetalleVentas) {
        this.listaDetalleVentas = listaDetalleVentas;
    }

    public ArrayList<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(ArrayList<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public Inventario getIventario() {
        return iventario;
    }

    public void setIventario(Inventario iventario) {
        this.iventario = iventario;
    }

    public ArrayList<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(ArrayList<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(ArrayList<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public ArrayList<Usuario> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Usuario> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Lote anadirLote(int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion) {
        String id = crearId(idLotes);
        idLotes++;
        String idProducto = null;
        Lote loteAux = new Lote(new Date(), cantidad, id, precioUnitario, precioTotal, new Date(), proveedor);
        loteAux.setNombre(nombre);
        for (int i = 1; i <= cantidad; i++) {
            idProducto = crearId(idProductos);
            idProductos++;
            loteAux.crearProductosLote(idProducto, nombre, precioVenta, fechaCaducidad, costo, marca, descripcion, proveedor,loteAux);
        }
        listaProductos.addAll(loteAux.getListaProductosLote());
        listaLotes.add(loteAux);
        return loteAux;
    }

    public boolean actualizarLote(String idLote, int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion) {
        Lote aux = buscarLoteId(idLote);
        if (aux == null) {
            return false;
        }
        aux.setCantidad(cantidad);
        aux.setPrecioUnitario(precioUnitario);
        aux.setCostoTotalLote(costo);
        aux.setProveedor(proveedor);
        aux.actualizarProductosEnlistados(nombre, precioVenta, fechaCaducidad, costo, marca, descripcion, proveedor);
        if (cantidad < aux.getListaProductosLote().size()) {
            aux.eliminarCantidadProductos(aux.getListaProductosLote().size() - cantidad);
        }
        if (cantidad > aux.getListaProductosLote().size()) {
            String idProducto = null;
            for (int i = 1; i <= cantidad - aux.getListaProductosLote().size(); i++) {
                idProducto = crearId(idProductos);
                idProductos++;
                aux.crearProductosLote(idProducto, nombre, precioVenta, fechaCaducidad, costo, marca, descripcion, proveedor,aux);
            }
        }
        return true;
    }

    public boolean eliminarLote(Lote lote) {
        if (lote == null || !listaLotes.contains(lote)) {
            return false;
        }
        listaLotes.remove(lote);
        return true;
    }

    public Cargo anadirCargo(String nombre, String descripcion, double salario, String estado, int empleadosRequeridos) {
        String id = crearId(idCargos);
        idCargos++;
        Cargo aux = new Cargo(nombre, id, descripcion, salario, new Date(), estado, empleadosRequeridos);
        listaCargos.add(aux);
        return aux;
    }

    public boolean actualizarCargo(String id, String nombre, String descripcion, double salario, String estado, int empleadosRequeridos) {
        Cargo aux = buscarCargoId(id);
        if (aux == null) {
            return false;
        }
        aux.setDescripcion(descripcion);
        aux.setEmpleadosRequeridos(empleadosRequeridos);
        aux.setEstado(estado);
        aux.setFechaModificacion(new Date());
        aux.setNombre(nombre);
        aux.setSalario(salario);
        return true;
    }

    public boolean eliminarCargo(Cargo cargo) {
        if (cargo == null || !listaCargos.contains(cargo)) {
            return false;
        }
        listaCargos.remove(cargo);
        return true;
    }

    public Proveedor anadirProveedor(String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado) {
        String id = crearId(idProveedores);
        idProveedores++;
        Proveedor aux = new Proveedor(id, nombreEmpresa, direccion, telefono, nombreContacto, comentarios, estado, new Date());
        listaProveedores.add(aux);
        return aux;
    }

    public boolean actualizarProveedor(String idProveedor, String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado) {
        Proveedor aux = buscarProveedorId(idProveedor);
        if (aux == null) {
            return false;
        }
        aux.setComentarios(comentarios);
        aux.setDireccion(direccion);
        aux.setEstado(estado);
        aux.setFechaModificacion(new Date());
        aux.setNombreContacto(nombreContacto);
        aux.setNombreEmpresa(nombreEmpresa);
        aux.setTelefono(telefono);
        return true;
    }

    public boolean eliminarProveedor(Proveedor proveedor) {
        if (proveedor == null || !listaProveedores.contains(proveedor)) {
            return false;
        }
        listaProveedores.remove(proveedor);
        return true;
    }

    public Lote buscarLoteId(String id) {
        Lote aux = null;
        for (Lote l : listaLotes) {
            if (l.getId().equals(id)) {
                aux = l;
            }
        }
        return aux;
    }

    public Usuario buscarEmpleadoNombreUsuario(String nombreUsuario) {
        Usuario aux = null;
        for (Usuario usr : listaEmpleados) {
            if (usr.getNombreUsuario().equals(nombreUsuario)) {
                aux = usr;
            }
        }
        return aux;
    }

    public Cargo buscarCargoId(String id) {
        Cargo aux = null;
        for (Cargo cargo : listaCargos) {
            if (cargo.getId().equals(id)) {
                aux = cargo;
            }
        }
        return aux;
    }

    public Proveedor buscarProveedorId(String id) {
        Proveedor aux = null;
        for (Proveedor prov : listaProveedores) {
            if (prov.getId().equals(id)) {
                aux = prov;
            }
        }
        return aux;
    }

    public Producto buscarProducto(String id) {
        Producto aux = null;
        for (Producto prod : listaProductos) {
            if (prod.getId().equals(id)) {
                aux = prod;
            }
        }
        return aux;
    }

    public String crearId(int id) {
        String idAux = String.valueOf(id);
        for (int i = 10 - idAux.length(); i >= 0; i--) {
            idAux = "0" + idAux;
        }
        return idAux;
    }


    public boolean verificarCredenciales(String correo, String contrasena) throws CorreoNoExisteException {
        if (obtenerIntentosFallidos(correo) >= 3) {
            bloquearUsuario(correo);
            return false;
        }
        for (Usuario usuario : listaEmpleados) {
            if (usuario.getEmail().equals(correo) && usuario.getContrasenia().equals(contrasena)) {
                intentosFallidos.clear();
                return true;
            }
        }
        intentosFallidos.add(correo);
        return false;
    }

    public void bloquearUsuario(String correo) throws CorreoNoExisteException {
        Usuario bloquearUsuario = buscarUsuarioPorCorreo(correo);
        if (bloquearUsuario != null) {
            bloquearUsuario.setEstado(Estado.BLOQUEADO);
            listaEmpleadosBloqueados.add(correo);
        } else {
            throw new CorreoNoExisteException();
        }
    }

    public int obtenerIntentosFallidos(String correo) {
        int intentos = 0;
        for (String intentoFallido : intentosFallidos) {
            if (intentoFallido.equals(correo)) {
                intentos++;
            }
        }
        return intentos;
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        List<Usuario> aux = getListaEmpleados();
        for (Usuario usuario : aux) {
            if (usuario.getEmail().equals(correo)) {
                return usuario;
            }
        }
        return null;
    }

    public void actualizarContrasenaUsuario(Usuario usuario, String contrasenia) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            Usuario usuarioExistente = listaEmpleados.get(i);
            usuarioExistente.setContrasenia(contrasenia);
            break;
        }

    }

    public Usuario crearEmpleado(String nombre, String nombreUsuario, String contrasenia, String correo,
                                 String id, String telefono, String direccion, Estado estado, Cargo cargo) {
        Usuario usuarioExiste = buscarUsuarioPorCorreo(correo);
        if (usuarioExiste != null) {
            return null;
        }
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setNombreUsuario(nombreUsuario);
        usuarioNuevo.setContrasenia(contrasenia);
        usuarioNuevo.setEmail(correo);
        usuarioNuevo.setEstado(estado);
        usuarioNuevo.setNombre(nombre);
        usuarioNuevo.setDireccion(direccion);
        usuarioNuevo.setTelefono(telefono);
        usuarioNuevo.setCargo(cargo);
        usuarioNuevo.setId(id);
        LocalDate fechaLocal = LocalDate.now();
        Date fechaDate = java.sql.Date.valueOf(fechaLocal.atStartOfDay().toLocalDate());
        usuarioNuevo.setFechaRegistro(fechaDate);
        usuarioNuevo.setUltimoInicioSesion(fechaDate);
        listaEmpleados.add(usuarioNuevo);
        return usuarioNuevo;
    }

    public boolean actualizarEmpleado(String nombreUsuario, String nuevoNombreUsuario, String contrasenia, String nombre, String telefono, String id, String email, String direccion, Estado estado, Cargo cargo) {
        Usuario aux = buscarEmpleadoNombreUsuario(nombreUsuario);
        if (aux == null) {
            return false;
        }
        aux.setNombreUsuario(nuevoNombreUsuario);
        aux.setContrasenia(contrasenia);
        aux.setNombre(nombre);
        aux.setTelefono(telefono);
        aux.setId(id);
        aux.setEmail(email);
        aux.setDireccion(direccion);
        aux.setEstado(estado);
        aux.setCargo(cargo);
        return true;
    }

    public boolean eliminarEmpleado(Usuario usuario) {
        if (usuario == null || !listaEmpleados.contains(usuario)) {
            return false;
        }
        listaEmpleados.remove(usuario);
        return true;
    }

    public void asignarUsuarioActual(String correo) {
        for (Usuario usuario : listaEmpleados) {
            if (usuario.getEmail().equals(correo)) {
                usuarioActual = new Usuario();
                usuarioActual.copiarAtributos(usuario);
                break;
            }
        }
    }

    public Usuario getUsuarioActual() {
        // Asegurarse de que usuarioActual no sea null antes de devolverlo
        if (usuarioActual == null) {
            usuarioActual = new Usuario(); // o inicializarlo de alguna otra manera
        }
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public void setImagenActual(Image imagenActual) {
        this.imagenActual = imagenActual;
    }

    public Image getImagenActual() {
        if (imagenActual == null) {
            imagenActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icons8-error-64.png"))); // o inicializarlo de alguna otra manera
        }
        return imagenActual;
    }

    public Lote getLoteActual() {
        if (loteActual == null) {
            loteActual = new Lote();
        }
        return loteActual;
    }

    public void setLoteActual(Lote loteActual) {
        this.loteActual = loteActual;
    }

    public double calcularPrecioTotal(List<Producto> listaProductos) {
        double precioTotal = 0.0;

        // Iterar a través de la lista de productos y sumar sus precios
        for (Producto producto : listaProductos) {
            precioTotal += producto.getPrecioVenta();
        }

        return precioTotal;
    }

    public void asignarLoteActual(String id) {
        for (Lote lote : listaLotes) {
            if (lote.getId().equals(id)) {
                loteActual = new Lote();
                loteActual.copiarAtributos(lote);
                break;
            }
        }

    }
    public Lote buscarLotePorId(String id) {
        List<Lote> aux = getListaLotes();
        for (Lote lote : aux) {
            if (lote.getId().equals(id)) {
                return lote;
            }
        }
        return null;
    }
    public Factura getFacturaActual() {
        if (facturaActual == null) {
            facturaActual = new Factura();
        }
        return facturaActual;
    }

    public void setFacturaActual(Factura factura) {
        this.facturaActual = factura;
    }

    public DetalleVenta crearDetalleVenta(Producto producto,double precioUnitario, int cantidad, double porcentajeDescuento){
        double subTotal=precioUnitario*cantidad;
        double descuento= subTotal*porcentajeDescuento;
        double total=subTotal-descuento;
        DetalleVenta detalleVenta= new DetalleVenta(idDetallesVenta,cantidad,precioUnitario,subTotal,descuento,total,null,producto);
        idDetallesVenta++;
        listaDetalleVentas.add(detalleVenta);
        return detalleVenta;
    }
    public Factura crearFactura(Usuario empleado,List<DetalleVenta>detallesVentaList){
        double precio = 0;
        for(DetalleVenta detalleVenta: detallesVentaList){
            precio += detalleVenta.getSubTotalDetalleVenta();
        }
        double impuesto = precio/1.19;
        impuesto = impuesto-precio;
        double total = precio+impuesto;
        Factura factura = new Factura(idFacturas,new Date(),precio,total,detallesVentaList,empleado,impuesto);
        idFacturas++;
        listaFacturas.add(factura);
        return factura;
    }

    public void procesarDevolucion(DetalleVenta detalleVenta, int cantidadDevuelta){
        detalleVenta.realizarDevolucion(cantidadDevuelta);
        Producto productoDevuelto= detalleVenta.getProducto();
        Lote loteDevuelto= null;
        for (Lote l: listaLotes) {
            if(l.getId().equals(productoDevuelto.getLote().getId())){
                loteDevuelto=l;
                break;
            }
        }
        for(int i=0;i<cantidadDevuelta;i++ ) {
            loteDevuelto.anadirProducto(productoDevuelto);
        }
    }

}
