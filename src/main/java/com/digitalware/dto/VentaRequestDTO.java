
package com.digitalware.dto;

import java.util.List;

public class VentaRequestDTO {
    private Long clientId;
    private List<ProductCompraRequestDTO> productosComprados;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<ProductCompraRequestDTO> getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(List<ProductCompraRequestDTO> productosComprados) {
        this.productosComprados = productosComprados;
    }
}
