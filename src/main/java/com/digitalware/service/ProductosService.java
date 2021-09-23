
package com.digitalware.service;

import java.util.List;

import com.digitalware.dto.ProductoRequestDTO;
import com.digitalware.dto.ProductoResponseDTO;

public interface ProductosService {

    public List<ProductoResponseDTO> listAll() throws Exception;

    public ProductoResponseDTO getById(Long id) throws Exception;

    public ProductoResponseDTO create(ProductoRequestDTO request) throws Exception;

    public ProductoResponseDTO update(Long id, ProductoRequestDTO request) throws Exception;

    public void delete(Long id) throws Exception;

}
