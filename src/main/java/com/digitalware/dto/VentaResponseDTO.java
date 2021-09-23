
package com.digitalware.dto;

import java.util.Date;
import java.util.List;

public class VentaResponseDTO {
    private Long id;
    private Date fecha;
    private ClienteResponseDTO cliente;
    private List<ProductoCompradoResponseDTO> productosComprados;

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

    public ClienteResponseDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
    }

    public List<ProductoCompradoResponseDTO> getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(List<ProductoCompradoResponseDTO> productosComprados) {
        this.productosComprados = productosComprados;
    }
}
