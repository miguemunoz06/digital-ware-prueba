
package com.digitalware.dto;

public class ProductoRequestDTO {
    private String nombre;
    private float precio;
    private short inventario;

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
