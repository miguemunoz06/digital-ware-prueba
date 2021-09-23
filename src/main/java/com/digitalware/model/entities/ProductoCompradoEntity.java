
package com.digitalware.model.entities;

public class ProductoCompradoEntity {
    private short cantidad;
    private ProductoEntity producto;

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
}
