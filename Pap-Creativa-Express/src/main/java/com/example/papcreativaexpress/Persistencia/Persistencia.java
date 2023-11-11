package com.example.papcreativaexpress.Persistencia;//package co.edu.uniquindio.proyectohotel.Persistencia;

import com.example.papcreativaexpress.Model.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class Persistencia {

	public static final String RUTA_ARCHIVO_PRODUCTOS = "Pap-Creativa-Express/src/main/resources/persistencia/archivos/archivoProductos.txt";
	public static final String RUTA_ARCHIVO_USUARIOS = "Pap-Creativa-Express/src/main/resources/persistencia/archivos/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_LOTES = "Pap-Creativa-Express/src/main/resources/persistencia/archivos/archivoLotes.txt";
	public static final String RUTA_ARCHIVO_CARGOS = "Pap-Creativa-Express/src/main/resources/persistencia/archivos/archivoCargos.txt";
	public static final String RUTA_ARCHIVO_PROVEEDORES = "Pap-Creativa-Express/src/main/resources/persistencia/archivos/archivoProveedores.txt";

	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO = "Pap-Creativa-Express/src/main/resources/persistencia/model.dat";
	//C:/Users/Usuario/Workspace eclipse/Ing soft/Pap_Creativa-Express/Pap-Creativa-Express/src/main/resources/persistencia/model.dat
	//C:\Users\Usuario\Workspace eclipse\Ing soft\Pap_Creativa-Express\Pap-Creativa-Express\src\main\java\com\example\papcreativaexpress\Persistencia
	public static final String RUTA_ARCHIVO_MODELO_MARQUETPLACE_XML = "Pap-Creativa-Express/src/main/resources/persistencia/model.xml";
	public static final String RUTA_ARCHIVO_COPIA_XML = "src/main/resources/persistencia/respaldo/";

	public static void cargarDatosArchivos(PapCreativaExpress marketplace) throws FileNotFoundException, IOException {

		ArrayList<Usuario> usuariosCargados = cargarUsuarios();
		if(usuariosCargados.size() > 0)
			marketplace.getListaEmpleados().addAll(usuariosCargados);

		ArrayList<Producto> productosCargados = cargarProductos();
		if(productosCargados.size() > 0)
			marketplace.getListaProductos().addAll(productosCargados);

		ArrayList<Lote> lotesCargados = cargarLotes();
		if(lotesCargados.size() > 0)
			marketplace.getListaLotes().addAll(lotesCargados);

		ArrayList<Cargo> cargosCargados = cargarCargos();
		if(cargosCargados.size() > 0)
			marketplace.getListaCargos().addAll(cargosCargados);

		ArrayList<Proveedor> proveedoresCargados = cargarProveedores();
		if(proveedoresCargados.size() > 0)
			marketplace.getListaProveedores().addAll(proveedoresCargados);

	}

	public static void guardarProductos(ArrayList<Producto> listaProductos) throws IOException {
		String contenido = "";

		for(Producto producto:listaProductos) {
			contenido+= producto.getNombre()+"@@"+producto.getId()+"@@"+producto.getMarca()+"@@"+producto.getProveedor()+"@@"+producto.getDescripcionDetallada()+"@@"+producto.getFechaCaducidad()+"@@"+producto.getFechaModificacion()+"@@"+producto.getNivelStockMinimo()
					+ "@@"+producto.getPrecioVenta()+"@@"+producto.getPrecioCosto()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, false);
	}
	public static void guardarLotes(ArrayList<Lote> lisaLotes) throws IOException {
		String contenido = "";

		for(Lote lote:lisaLotes) {
			contenido+= lote.getId()+"@@"+lote.getPrecioUnitario()+"@@"+lote.getCostoTotalLote()+"@@"+lote.getProveedor()+"@@"+lote.getCantidad()+"@@"+lote.getFechaRegistro()+"@@"+lote.getFechaEntrada()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_LOTES, contenido, false);
	}

	public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
		String contenido = "";

		for(Usuario usuario:listaUsuarios) {
				contenido+= usuario.getNombreUsuario()+"@@"+usuario.getEmail()+"@@"+usuario.getContrasenia()+"@@"+usuario.getNombre()+"@@"+usuario.getEstado()+"@@"+usuario.getDireccion()+"@@"+
						usuario.getId()+"@@"+usuario.getTelefono()+"@@"+usuario.getCargo()+"@@"+usuario.getFechaRegistro()+"@@"+usuario.getUltimoInicioSesion()+"@@"+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
	}
	public static void guardarCargos(ArrayList<Cargo> listaCargos) throws IOException {
		String contenido = "";

		for(Cargo cargos:listaCargos) {
			contenido+= cargos.getId()+"@@"+cargos.getNombre()+"@@"+cargos.getDescripcion()+"@@"+cargos.getEstado()+"@@"+
					cargos.getEmpleadosRequeridos()+"@@"+cargos.getSalario()+"@@"+cargos.getFechaCreacion()+"@@"+cargos.getFechaModificacion()+"@@"+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CARGOS, contenido, false);
	}
	public static void guardarProveedores(ArrayList<Proveedor>listaProveedores)throws IOException{
		String contenido = "";
		for(Proveedor proveedor: listaProveedores){
			contenido+= proveedor.getId()+"@@"+proveedor.getDireccion()+"@@"+proveedor.getEstado()+"@@"+proveedor.getTelefono()+"@@"+proveedor.getNombreEmpresa()+"@@"+
					proveedor.getComentarios()+"@@"+proveedor.getNombreContacto()+"@@"+proveedor.getFechaModificacion()+"@@"+proveedor.getFechaRegistro()+"@@"+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PROVEEDORES,contenido,false);
	}
//	----------------------LOADS------------------------

	private static ArrayList<Producto> cargarProductos() throws IOException {

		ArrayList<Producto> productos =new ArrayList<Producto>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
		String linea="";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Producto producto = new Producto();
			producto.setNombre(linea.split("@@")[0]);
			producto.setId(linea.split("@@")[1]);
			producto.setMarca(linea.split("@@")[2]);
			String proveedorInfo = linea.split("@@")[3];
			String[] proveedorData = proveedorInfo.split("@@");
			if (proveedorData.length >= 1) {
				String nombreProveedor = proveedorData[0];
				Proveedor proveedor = new Proveedor();
				proveedor.setNombreContacto(nombreProveedor);
				producto.setProveedor(proveedor);
			} else {
				System.out.println("Error al procesar la información del proveedor.");
			}
			producto.setDescripcionDetallada(linea.split("@@")[4]);
			String fechaCaducidadStr = linea.split("@@")[5];
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu necesidad.
				Date fechaCaducidad = dateFormat.parse(fechaCaducidadStr);
				producto.setFechaCaducidad(fechaCaducidad);
			} catch (ParseException e) {
				System.out.println("Error al analizar la fecha de caducidad: " + e.getMessage());
			}
			String fechaModificacionStr= linea.split("@@")[6];
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu necesidad.
				Date fechaModificacion = dateFormat.parse(fechaModificacionStr);
				producto.setFechaCaducidad(fechaModificacion);
			} catch (ParseException e) {
				System.out.println("Error al analizar la fecha de caducidad: " + e.getMessage());
			}
			producto.setNivelStockMinimo((int) Double.parseDouble(linea.split("@@")[7]));
			producto.setPrecioVenta(Double.parseDouble(linea.split("@@")[8]));
			producto.setPrecioCosto(Double.parseDouble(linea.split("@@")[9]));

			productos.add(producto);
		}
		return productos;
	}
	private static ArrayList<Lote> cargarLotes() throws IOException {

		ArrayList<Lote> lotes =new ArrayList<Lote>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
		String linea="";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Lote lote = new Lote();
			lote.setId(linea.split("@@")[0]);
			lote.setPrecioUnitario(Double.parseDouble(linea.split("@@")[1]));
			lote.setCostoTotalLote(Double.parseDouble(linea.split("@@")[2]));
			String proveedorInfo = linea.split("@@")[3];
			String[] proveedorData = proveedorInfo.split("@@");
			if (proveedorData.length >= 1) {
				String nombreProveedor = proveedorData[0];
				Proveedor proveedor = new Proveedor();
				proveedor.setNombreContacto(nombreProveedor);
				lote.setProveedor(proveedor);
			} else {
				System.out.println("Error al procesar la información del proveedor.");
			}
			lote.setCantidad(Integer.parseInt(linea.split("@@")[4]));
			String fechaRegistrostr = linea.split("@@")[6];
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu necesidad.
				Date fechaRegistro = dateFormat.parse(fechaRegistrostr);
				lote.setFechaRegistro(fechaRegistro);
			} catch (ParseException e) {
				System.out.println("Error al analizar la fecha de caducidad: " + e.getMessage());
			}
			String fechaEntradastr= linea.split("@@")[7];
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu necesidad.
				Date fechaEntrada = dateFormat.parse(fechaEntradastr);
				lote.setFechaEntrada(fechaEntrada);
			} catch (ParseException e) {
				System.out.println("Error al analizar la fecha de caducidad: " + e.getMessage());
			}
		}
		return lotes;
	}
	private static ArrayList<Cargo> cargarCargos() throws IOException {

		ArrayList<Cargo> cargos =new ArrayList<Cargo>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
		String linea="";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Cargo cargo = new Cargo();
			cargo.setId(linea.split("@@")[0]);
			cargo.setNombre((linea.split("@@")[1]));
			cargo.setDescripcion((linea.split("@@")[2]));
			cargo.setEstado((linea.split("@@")[3]));
			cargo.setEmpleadosRequeridos(Integer.parseInt((linea.split("@@")[4])));
			cargo.setSalario(Double.parseDouble(linea.split("@@")[5]));
			String fechaCreacionstr = linea.split("@@")[6];
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu necesidad.
				Date fechaCreacion = dateFormat.parse(fechaCreacionstr);
				cargo.setFechaCreacion(fechaCreacion);
			} catch (ParseException e) {
				System.out.println("Error al analizar la fecha de caducidad: " + e.getMessage());
			}
			String fechaModificacionstr= linea.split("@@")[7];
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu necesidad.
				Date fechaModificacion = dateFormat.parse(fechaModificacionstr);
				cargo.setFechaModificacion(fechaModificacion);
			} catch (ParseException e) {
				System.out.println("Error al analizar la fecha de caducidad: " + e.getMessage());
			}
		}
		return cargos;
	}

	public static ArrayList<Proveedor> cargarProveedores() throws FileNotFoundException, IOException {
		ArrayList<Proveedor> proveedores =new ArrayList<Proveedor>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
		String linea="";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);

			Proveedor proveedor = new Proveedor();
			proveedor.setId(linea.split("@@")[0]);
			proveedor.setDireccion(linea.split("@@")[1]);
			proveedor.setEstado(String.valueOf(Estado.valueOf(linea.split("@@")[2])));
			proveedor.setTelefono(linea.split("@@")[3]);
			proveedor.setNombreEmpresa(linea.split("@@")[4]);
			proveedor.setComentarios(linea.split("@@")[5]);
			proveedor.setNombreContacto(linea.split("@@")[6]);
			String fechaModificacionStr= linea.split("@@")[7];
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu necesidad.
				Date fechaModificacion = dateFormat.parse(fechaModificacionStr);
				proveedor.setFechaModificacion(fechaModificacion);
			} catch (ParseException e) {
				System.out.println("Error al analizar la fecha de caducidad: " + e.getMessage());
			}

			String fechaRegistroStr= linea.split("@@")[8];
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu necesidad.
				Date fechaRegistro = dateFormat.parse(fechaRegistroStr);
				proveedor.setFechaRegistro(fechaRegistro);
			} catch (ParseException e) {
				System.out.println("Error al analizar la fecha de caducidad: " + e.getMessage());
			}
		}
		return proveedores;
	}public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException {
		ArrayList<Usuario> usuarios =new ArrayList<Usuario>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
		String linea="";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);

			Usuario usuario = new Usuario();
			usuario.setNombreUsuario(linea.split("@@")[0]);
			usuario.setEmail(linea.split("@@")[1]);
			usuario.setContrasenia(linea.split("@@")[2]);
			usuario.setNombre(linea.split("@@")[3]);
			usuario.setEstado(Estado.valueOf(linea.split("@@")[4]));
			usuario.setDireccion(linea.split("@@")[5]);
			usuario.setId(linea.split("@@")[6]);
			usuario.setTelefono(linea.split("@@")[7]);
			String cargo = linea.split("@@")[8];
			String[] proveedorData = cargo.split("@@");
			if (proveedorData.length >= 1) {
				String nombreCargo = proveedorData[0];
				Cargo cargo1 = new Cargo();
				cargo1.setNombre(nombreCargo);
				usuario.setCargo(cargo1);
			} else {
				System.out.println("Error al procesar la información del proveedor.");
			}
			String fechaRegistroStr= linea.split("@@")[9];
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu necesidad.
				Date fechaRegistro = dateFormat.parse(fechaRegistroStr);
				usuario.setFechaRegistro(fechaRegistro);
			} catch (ParseException e) {
				System.out.println("Error al analizar la fecha de caducidad: " + e.getMessage());
			}
			String fechaSesionStr= linea.split("@@")[10];
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu necesidad.
				Date fechaSesion = dateFormat.parse(fechaSesionStr);
				usuario.setUltimoInicioSesion(fechaSesion);
			} catch (ParseException e) {
				System.out.println("Error al analizar la fecha de caducidad: " + e.getMessage());
			}			usuarios.add(usuario);
		}
		return usuarios;
	}


	//	----------------------SAVES------------------------

	//------------------------------------SERIALIZACI�N  y XML
	public static void guardar(Serializable objeto){
		try(ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO))) {
			archivo.writeObject(objeto);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static Optional cargar(){
		Object objeto = null;
		try(ObjectInputStream archivo = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO))) {
			objeto = archivo.readObject();
		}catch (Exception e){
			e.printStackTrace();
		}
		return Optional.ofNullable(objeto);
	}

	public static PapCreativaExpress cargarRecursoXML() {

		PapCreativaExpress marketplace = null;

		try {
			marketplace = (PapCreativaExpress) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARQUETPLACE_XML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marketplace;
	}

	public static void guardarRecursoXML(PapCreativaExpress marketplace) {

		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARQUETPLACE_XML, marketplace);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
