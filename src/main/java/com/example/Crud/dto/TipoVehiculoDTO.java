package com.example.Crud.dto;

import com.sun.istack.NotNull;

public class TipoVehiculoDTO {
    @NotNull
    private int id;
    private String nombre;

    public TipoVehiculoDTO() {
    }

    public TipoVehiculoDTO(int id, String nombre) {
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
