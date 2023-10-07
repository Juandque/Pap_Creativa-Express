package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Model.PapCreativaExpress;

import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController {
    private PapCreativaExpress papCreativaExpress;

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        inicializarDatos();
    }

    public void inicializarDatos(){
        papCreativaExpress = new PapCreativaExpress();

    }
    public boolean verificarInicioSesion(String correo, String contrasena) {
        return papCreativaExpress.verificarCredenciales(correo,contrasena);
    }
    public int obtenerIntentosFallidos(String correo){
        return papCreativaExpress.obtenerIntentosFallidos(correo);
    }
    public void bloquearUsuario(String correo){
        papCreativaExpress.bloquearUsuario(correo);
    }
}
