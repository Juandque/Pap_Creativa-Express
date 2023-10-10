package com.example.papcreativaexpress.Model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Usuario implements Serializable {
    private String nombreUsuario;
    private String contrasenia;
    private String nombre;
    private String telefono;
    private String id;
    private String email;
    private String direccion;
    private Date fechaRegistro;
    private Date ultimoInicioSesion;
    private Estado  estado;
    private Cargo cargo;
    private transient Image fotoUsuario;

    public Usuario(){

    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public Image getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(Image fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getUltimoInicioSesion() {
        return ultimoInicioSesion;
    }

    public void setUltimoInicioSesion(Date ultimoInicioSesion) {
        this.ultimoInicioSesion = ultimoInicioSesion;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public void copiarAtributos(Usuario otroUsuario) {
        this.setId(otroUsuario.getId());
        this.setNombre(otroUsuario.getNombre());
        this.setNombreUsuario(otroUsuario.getNombreUsuario());
        this.setEmail(otroUsuario.getEmail());
        this.setContrasenia(otroUsuario.getContrasenia());
        this.setDireccion(otroUsuario.getDireccion());
        this.setTelefono(otroUsuario.getTelefono());
        this.setCargo(otroUsuario.getCargo());
        this.setEstado(otroUsuario.getEstado());
        this.setUltimoInicioSesion(otroUsuario.getUltimoInicioSesion());
        this.setFechaRegistro(otroUsuario.getFechaRegistro());
    }
}