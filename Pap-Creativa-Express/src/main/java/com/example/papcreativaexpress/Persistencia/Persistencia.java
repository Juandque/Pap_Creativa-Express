package com.example.papcreativaexpress.Persistencia;//package co.edu.uniquindio.proyectohotel.Persistencia;

import com.example.papcreativaexpress.Model.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

public class Persistencia {

	public static final String RUTA_ARCHIVO_VENDEDORES = "src/main/resources/persistencia/archivos/archivoVendedores" +
			".txt";
	public static final String RUTA_ARCHIVO_PRODUCTOS = "src/main/resources/persistencia/archivos/archivoProductos.txt";
	public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/archivos/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_LOG = "src/main/resources/persistencia/log/MarketPlaceLog.txt";
	public static final String RUTA_ARCHIVO_OBJETOS = "src/main/resources/persistencia/archivos/archivoObjetos.txt";
	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO = "src/main/resources/persistencia/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_MARQUETPLACE_XML = "src/main/resources/persistencia/model.xml";
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

	public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
		String contenido = "";

		for(Usuario usuario:listaUsuarios) {
				contenido+= usuario.getNombreUsuario()+"@@"+usuario.getEmail()+"@@"+usuario.getContrasenia()+"@@"+usuario.getNombre()+"@@"+usuario.getEstado()+"@@"+usuario.getDireccion()+"@@"+
						usuario.getId()+"@@"+usuario.getTelefono()+"@@"+usuario.getCargo()+"@@"+usuario.getFechaRegistro()+"@@"+usuario.getUltimoInicioSesion()+"@@"+usuario.getFotoUsuario()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
	}
	public static void guardarCargos(ArrayList<Cargo> listaCargos) throws IOException {
		String contenido = "";

		for(Cargo cargos:listaCargos) {
			contenido+= cargos.getId()+"@@"+cargos.getNombre()+"@@"+cargos.getDescripcion()+"@@"+cargos.getEstado()+"@@"+cargos.getDescripcion()+"@@"+
					cargos.getEmpleadosRequeridos()+"@@"+cargos.getSalario()+"@@"+cargos.getFechaCreacion()+"@@"+cargos.getFechaModificacion()+"@@"+cargos.getListaEmpleadosCargo()+"@@"+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
	}
	public static void guardarProveedores(ArrayList<Proveedor>listaProveedores){
		String contenido = "";
		for(Proveedor proveedor: listaProveedores){
			contenido+= proveedor.getId()+"@@"+proveedor.getDireccion()+"@@"+proveedor.getEstado()+"@@"+proveedor.getTelefono()+"@@"+proveedor.getNombreEmpresa()+"@@"+
					proveedor.getComentarios()+"@@"+proveedor.getNombreContacto()+"@@"+proveedor.getFechaModificacion()+"@@"+proveedor.getFechaRegistro()+"@@"+proveedor.getListaProductosProveedor()+"@@"+"\n";
		}
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

	public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException {
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
			usuario.setFechaRegistro(LocalDate.parse((linea.split("@@")[9])));
			usuario.setUltimoInicioSesion(LocalDate.parse((linea.split("@@")[10])));
			String imagenBase64 = linea.split("@@")[11]; // Supongamos que esto es una cadena en formato base64
			Image imagen = base64ToImage(imagenBase64);
			usuario.setFotoUsuario(imagen);
			usuarios.add(usuario);
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

	public static void guardarCopiaXML(PapCreativaExpress marketplace) {
		String nombreArchivo = "CopiaXML_";
		LocalDate hoy = LocalDate.now();
		LocalTime hora = LocalTime.now();
		int hh = hora.getHour();
		int mm = hora.getMinute();
		int ss = hora.getSecond();
		String fecha = hoy.toString();
		String aa = fecha.split("-")[0];
		String MM = fecha.split("-")[1];
		String dd = fecha.split("-")[2];
		String fechaFinal = dd+MM+aa+"_"+hh+"_"+mm+"_"+ss;

		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_COPIA_XML+nombreArchivo+fechaFinal, marketplace);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static Image base64ToImage(String base64) {
		byte[] bytes = Base64.getDecoder().decode(base64);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SwingFXUtils.toFXImage(bufferedImage, null);
	}
}
