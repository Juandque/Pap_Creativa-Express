package com.example.papcreativaexpress.Excepciones;

public class UsuarioExisteException extends UsuarioExceptions{
    public UsuarioExisteException() {
        super("No puedes crear un usuario que ya existe");
    }
}
