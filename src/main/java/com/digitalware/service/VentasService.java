
package com.digitalware.service;

import java.util.List;

import com.digitalware.dto.UltimaVentaResponseDTO;
import com.digitalware.dto.VentaRequestDTO;
import com.digitalware.dto.VentaResponseDTO;

public interface VentasService {

    public List<VentaResponseDTO> listAll() throws Exception;

    public VentaResponseDTO getById(Long id) throws Exception;

    public VentaResponseDTO create(VentaRequestDTO request) throws Exception;

    public List<UltimaVentaResponseDTO> ultimasCompras(Short numCompras) throws Exception;

}
