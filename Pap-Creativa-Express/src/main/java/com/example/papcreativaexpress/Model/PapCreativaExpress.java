package com.example.papcreativaexpress.Model;

import com.example.papcreativaexpress.Excepciones.CorreoNoExisteException;
import com.example.papcreativaexpress.Excepciones.UsuarioExisteException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PapCreativaExpress {
    List<Caja> listaCajeros;
    List<Cargo> listaCargos;
    List<Categoria> listaCategorias;
    List<Compra> listaComprasAbastecimiento;
    List<DetalleVenta> listaDetalleVentas;
    List<Factura> listaFacturas;
    Inventario iventario;
    List<Lote> listaLotes;
    List<Producto> listaProductos;
    List<Proveedor> listaProveedores;
    List<Usuario> listaEmpleados;
    List<String> listaEmpleadosBloqueados;
    List<String> intentosFallidos = new ArrayList<>();


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
        listaCajeros= new ArrayList<>();
        listaCargos= new ArrayList<>();
        listaCategorias= new ArrayList<>();
        listaComprasAbastecimiento= new ArrayList<>();
        listaDetalleVentas= new ArrayList<>();
        listaFacturas= new ArrayList<>();
        iventario= new Inventario();
        listaLotes= new ArrayList<>();
        listaProductos= new ArrayList<>();
        listaProveedores= new ArrayList<>();
        listaEmpleados= new ArrayList<>();
        intentosFallidos = new ArrayList<>();
        listaEmpleadosBloqueados = new ArrayList<String>();
        idEmpleados=1;
        idProveedores=1;
        idCajeros=1;
        idCargos=1;
        idCategorias=1;
        idCompras=1;
        idDetallesVenta=1;
        idFacturas=1;
        idLotes=1;
        idProductos=1;
    }

    public List<Caja> getListaCajeros() {
        return listaCajeros;
    }

    public void setListaCajeros(List<Caja> listaCajeros) {
        this.listaCajeros = listaCajeros;
    }

    public List<Cargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<Cargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public List<Compra> getListaComprasAbastecimiento() {
        return listaComprasAbastecimiento;
    }

    public void setListaComprasAbastecimiento(List<Compra> listaComprasAbastecimiento) {
        this.listaComprasAbastecimiento = listaComprasAbastecimiento;
    }

    public List<DetalleVenta> getListaDetalleVentas() {
        return listaDetalleVentas;
    }

    public void setListaDetalleVentas(List<DetalleVenta> listaDetalleVentas) {
        this.listaDetalleVentas = listaDetalleVentas;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public Inventario getIventario() {
        return iventario;
    }

    public void setIventario(Inventario iventario) {
        this.iventario = iventario;
    }

    public List<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public List<Usuario> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Usuario> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Lote anadirLote(int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion){
        String id= crearId(idLotes);
        idLotes++;
        String idProducto= null;
        Lote loteAux= new Lote();
        for(int i=1; i<=cantidad; i++){
            idProducto= crearId(idProductos);
            idProductos++;
            loteAux.crearProductosLote(idProducto,nombre,precioVenta,fechaCaducidad,costo,marca,descripcion,proveedor);
        }
        listaProductos.addAll(loteAux.getListaProductosLote());
        listaLotes.add(loteAux);
        return loteAux;
    }

    public boolean actualizarLote(String idLote,int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion){
        Lote aux= buscarLoteId(idLote);
        if(aux==null){
            return false;
        }
        aux.setCantidad(cantidad);
        aux.setPrecioUnitario(precioUnitario);
        aux.setCostoTotalLote(costo);
        aux.setProveedor(proveedor);
        aux.getListaProductosLote().clear();
        String idProducto= null;
        for(int i=1; i<=cantidad; i++){
            idProducto= crearId(idProductos);
            idProductos++;
            aux.crearProductosLote(idProducto,nombre,precioVenta,fechaCaducidad,costo,marca,descripcion,proveedor);
        }
        return true;
    }

    public Lote buscarLoteId(String id){
        Lote aux=null;
        for(Lote l: listaLotes){
            if(l.getId().equals(id)){
                aux=l;
            }
        }
        return aux;
    }

    public Usuario buscarEmpleadoId(String id){
        Usuario aux= null;
        for(Usuario usr: listaEmpleados){
            if(usr.getId().equals(id)){
                aux=usr;
            }
        }
        return aux;
    }

    public Cargo buscarCargoId(String id){
        Cargo aux=null;
        for(Cargo cargo:listaCargos){
            if(cargo.getId().equals(id)){
                aux=cargo;
            }
        }
        return aux;
    }

    public Proveedor buscarProveedorId(String id){
        Proveedor aux=null;
        for(Proveedor prov: listaProveedores){
            if(prov.getId().equals(id)){
                aux=prov;
            }
        }
        return aux;
    }

    public Producto buscarProducto(String id){
        Producto aux= null;
        for(Producto prod: listaProductos){
            if(prod.getId().equals(id)){
                aux=prod;
            }
        }
        return aux;
    }

    public String crearId(int id){
        String idAux= String.valueOf(id);
        for(int i=10-idAux.length(); i>=0; i--){
            idAux="0"+idAux;
        }
        return idAux;
    }
    public boolean eliminarLote(Lote lote){
        if(lote==null){
            return false;
        }if(!listaLotes.contains(lote)) {
            return false;
        }
        listaLotes.remove(lote);
        return true;
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
    public void bloquearUsuario(String correo)  throws CorreoNoExisteException {
        Usuario bloquearUsuario = buscarUsuarioPorCorreo(correo);
        if(bloquearUsuario!=null){
            bloquearUsuario.setEstado(Estado.BLOQUEADO);
            listaEmpleadosBloqueados.add(correo);
        }
        else {
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
    public Usuario buscarUsuarioPorCorreo(String correo){
        List<Usuario>aux = getListaEmpleados();
        for(Usuario usuario: aux){
            if(usuario.getEmail().equals(correo)){
                return usuario;
            }
        }
        return null;
    }
    public void actualizarUsuario(Usuario usuario){
        for(int i=0; i<listaEmpleados.size();i++){
            Usuario usuarioExistente = listaEmpleados.get(i);
            usuarioExistente.setContrasenia(usuario.getContrasenia());
            break;
        }

    }
    public  Usuario crearUsuario(String nombre, String nombreUsuario, String contrasenia, String correo,
                                 String id, String telefono, String direccion)throws UsuarioExisteException {
        Usuario usuarioExiste = buscarUsuarioPorCorreo(correo);
        if (usuarioExiste != null) {
            throw new UsuarioExisteException();
        }
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setNombreUsuario(nombreUsuario);
        usuarioNuevo.setContrasenia(contrasenia);
        usuarioNuevo.setEmail(correo);
        usuarioNuevo.setEstado(Estado.DISPONIBLE);
        usuarioNuevo.setNombre(nombre);
        usuarioNuevo.setDireccion(direccion);
        usuarioNuevo.setTelefono(telefono);
        usuarioNuevo.setId(id);
        LocalDate fechaActual = LocalDate.now();
        usuarioNuevo.setFechaRegistro(fechaActual);
        usuarioNuevo.setUltimoInicioSesion(fechaActual);
        return usuarioNuevo;
    }
}
