package com.example.Crud.entity;

import javax.persistence.*;

//Notacion pata indicar que es una entidad
@Entity
//Tabla que corresponde a esta  entidad
@Table(name = "torre")
public class Torre {

    //Llave  primaria de la tabla
    @Id
    //Se le indica que el campo ID es Autonumerico
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTorre;
    private String nombreTorre;
    private int cantidadAptos;

    //Constructor sin parametros
    public Torre(){

    }
    public Torre(String nombreTorre,int cantidadAptos){
        this.nombreTorre = nombreTorre;
        this.cantidadAptos = cantidadAptos;
    }

    public int getIdTorre() {
        return idTorre;
    }

    public void setIdTorre(int idTorre) {
        this.idTorre = idTorre;
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
