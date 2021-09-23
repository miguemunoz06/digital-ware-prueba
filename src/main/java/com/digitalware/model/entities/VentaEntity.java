
package com.digitalware.model.entities;

import java.util.Date;
import java.util.List;

public class VentaEntity {
    private Long id;
    private Date fecha;
    private ClienteEntity cliente;
    private List<ProductoCompradoEntity> productosComprados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<ProductoCompradoEntity> getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(List<ProductoCompradoEntity> productosComprados) {
        this.productosComprados = productosComprados;
    }
}
