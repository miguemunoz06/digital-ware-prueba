
package com.digitalware.dto;

public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private float precio;
    private short inventario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public short getInventario() {
        return inventario;
    }

    public void setInventario(short inventario) {
        this.inventario = inventario;
    }
}
