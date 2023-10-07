package com.example.papcreativaexpress.Excepciones;

public class CorreoNoExisteException extends UsuarioExceptions{
    public CorreoNoExisteException() {
        super("El correo no se encuentra registrado");
    }
}
