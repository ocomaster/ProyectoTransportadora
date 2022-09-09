package com.example.Crud.dto;

import com.sun.istack.NotNull;

public class TorreDTO {

    //Notacion para especificar que el campo no puede venir vacio
    @NotNull
    private String nombreTorre;
    //Notacion para indicar que el tamaño minibo debe ser 0
   //Pendiente
    private int cantidadAptos;

    //Creacion de constructor
    public TorreDTO() {
    }

    public TorreDTO(String nombreTorre,int cantidadAptos){
        this.nombreTorre =nombreTorre;
        this.cantidadAptos = cantidadAptos;
    }

    public String getNombreTorre() {
        return nombreTorre;
    }

    public void setNombreTorre(String nombreTorre) {
        this.nombreTorre = nombreTorre;
    }

    public int getCantidadAptos() {
        return cantidadAptos;
    }

    public void setCantidadAptos(int cantidadAptos) {
        this.cantidadAptos = cantidadAptos;
    }
}
