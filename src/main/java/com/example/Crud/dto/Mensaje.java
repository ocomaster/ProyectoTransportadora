package com.example.Crud.dto;

//Clase para mostrar mensajes por pantalla en el ciente
public class Mensaje {

    private String mensaje;

    public Mensaje(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMensaje(){
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
