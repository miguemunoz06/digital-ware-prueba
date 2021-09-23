
package com.digitalware.dto;

public class ProductoCompradoResponseDTO {
    private short cantidad;
    private ProductoResponseDTO producto;

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoResponseDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoResponseDTO producto) {
        this.producto = producto;
    }
}
