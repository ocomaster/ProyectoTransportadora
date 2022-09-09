package com.example.Crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "tipoVehiculo")
public class TipoVehiculo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    public TipoVehiculo() {
    }

    public TipoVehiculo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
