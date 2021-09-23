
package com.digitalware.service;

import java.util.List;

import com.digitalware.dto.ClienteRequestDTO;
import com.digitalware.dto.ClienteResponseDTO;

public interface ClienteService {
    public List<ClienteResponseDTO> listAll() throws Exception;

    public ClienteResponseDTO getById(Long id) throws Exception;

    public ClienteResponseDTO create(ClienteRequestDTO request) throws Exception;

    public ClienteResponseDTO update(Long id, ClienteRequestDTO request) throws Exception;

    public void delete(Long id) throws Exception;
}
