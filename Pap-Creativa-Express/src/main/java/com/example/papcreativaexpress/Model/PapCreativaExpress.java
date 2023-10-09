package com.example.papcreativaexpress.Model;

import com.example.papcreativaexpress.Excepciones.CorreoNoExisteException;
import com.example.papcreativaexpress.Excepciones.UsuarioExisteException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PapCreativaExpress {
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

    public Lote anadirLote(int cantidad, double precioUnitario, double precioTotal, Proveedor proveedor, String nombre, double precioVenta, Date fechaCaducidad, double costo, String marca, String descripcion){
        String id= crearId(idLotes);
        idLotes++;
        String idProducto= null;
        Lote loteAux= new Lote(new Date(), cantidad,id, precioUnitario, precioTotal, new Date(), proveedor);
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
        aux.actualizarProductosEnlistados(nombre,precioVenta,fechaCaducidad,costo,marca,descripcion,proveedor);
        if(cantidad<aux.getListaProductosLote().size()){
            aux.eliminarCantidadProductos(aux.getListaProductosLote().size()-cantidad);
        }
        if(cantidad>aux.getListaProductosLote().size()){
            String idProducto= null;
            for(int i=1; i<=cantidad-aux.getListaProductosLote().size(); i++){
                idProducto= crearId(idProductos);
                idProductos++;
                aux.crearProductosLote(idProducto,nombre,precioVenta,fechaCaducidad,costo,marca,descripcion,proveedor);
            }
        }
        return true;
    }

    public boolean eliminarLote(Lote lote){
        if(lote==null || !listaLotes.contains(lote)){
            return false;
        }
        listaLotes.remove(lote);
        return true;
    }

    public Cargo anadirCargo(String nombre, String descripcion,double salario,String estado, int empleadosRequeridos){
        String id= crearId(idCargos);
        idCargos++;
        Cargo aux= new Cargo(nombre, id,descripcion,salario,new Date(),estado, empleadosRequeridos);
        listaCargos.add(aux);
        return aux;
    }

    public boolean actualizarCargo(String id,String nombre, String descripcion,double salario,String estado, int empleadosRequeridos){
        Cargo aux= buscarCargoId(id);
        if(aux==null){
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

    public boolean eliminarCargo(Cargo cargo){
        if(cargo==null || !listaCargos.contains(cargo)){
            return false;
        }
        listaCargos.remove(cargo);
        return true;
    }

    public Proveedor anadirProveedor(String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado){
        String id= crearId(idProveedores);
        idProveedores++;
        Proveedor aux= new Proveedor(id, nombreEmpresa,direccion,telefono,nombreContacto,comentarios,estado, new Date());
        listaProveedores.add(aux);
        return aux;
    }

    public boolean actualizarProveedor(String idProveedor, String nombreEmpresa, String direccion, String telefono, String nombreContacto, String comentarios, String estado){
        Proveedor aux= buscarProveedorId(idProveedor);
        if(aux==null){
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

    public boolean eliminarProveedor(Proveedor proveedor){
        if(proveedor==null || !listaProveedores.contains(proveedor)){
            return false;
        }
        listaProveedores.remove(proveedor);
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

    public Usuario buscarEmpleadoNombreUsuario(String nombreUsuario){
        Usuario aux= null;
        for(Usuario usr: listaEmpleados){
            if(usr.getNombreUsuario().equals(nombreUsuario)){
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
    public void actualizarContrasenaUsuario(Usuario usuario,String contrasenia){
        for(int i=0; i<listaEmpleados.size();i++){
            Usuario usuarioExistente = listaEmpleados.get(i);
            usuarioExistente.setContrasenia(contrasenia);
            break;
        }

    }
    public  Usuario crearEmpleado(String nombre, String nombreUsuario, String contrasenia, String correo,
                                 String id, String telefono, String direccion, Estado estado, Cargo cargo){
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
        LocalDate fechaActual = LocalDate.now();
        usuarioNuevo.setFechaRegistro(fechaActual);
        usuarioNuevo.setUltimoInicioSesion(fechaActual);
        listaEmpleados.add(usuarioNuevo);
        return usuarioNuevo;
    }

    public boolean actualizarEmpleado(String nombreUsuario, String nuevoNombreUsuario, String contrasenia, String nombre, String telefono,String id,String email,String direccion,Estado  estado, Cargo cargo){
        Usuario aux= buscarEmpleadoNombreUsuario(nombreUsuario);
        if(aux==null){
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

    public boolean eliminarEmpleado(Usuario usuario){
        if(usuario==null || !listaEmpleados.contains(usuario)){
            return  false;
        }
        listaEmpleados.remove(usuario);
        return true;
    }
}
