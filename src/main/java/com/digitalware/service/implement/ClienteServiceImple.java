
package com.digitalware.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalware.dto.ClienteRequestDTO;
import com.digitalware.dto.ClienteResponseDTO;
import com.digitalware.model.entities.ClienteEntity;
import com.digitalware.model.repositories.ClienteRepository;
import com.digitalware.service.ClienteService;

@Service
public class ClienteServiceImple implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteResponseDTO> listAll() throws Exception {
        Map<Long, ClienteEntity> clientes = clienteRepository.findAll();

        List<ClienteResponseDTO> clientesResponse = new ArrayList<>();

        clientes.forEach((id, cliente) -> {
            ClienteResponseDTO clienteResponse = new ClienteResponseDTO();
            clienteResponse.setId(cliente.getId());
            clienteResponse.setTipoDocumento(cliente.getTipoDocumento());
            clienteResponse.setNumeroDocumento(cliente.getNumeroDocumento());
            clienteResponse.setNombre(cliente.getNombre());
            clienteResponse.setApellido(cliente.getApellido());

            clientesResponse.add(clienteResponse);
        });

        return clientesResponse;
    }

    @Override
    public ClienteResponseDTO getById(Long id) throws Exception {
        ClienteEntity cliente = clienteRepository.findById(id);

        ClienteResponseDTO clienteResponse = new ClienteResponseDTO();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setTipoDocumento(cliente.getTipoDocumento());
        clienteResponse.setNumeroDocumento(cliente.getNumeroDocumento());
        clienteResponse.setNombre(cliente.getNombre());
        clienteResponse.setApellido(cliente.getApellido());

        return clienteResponse;
    }

    @Override
    public ClienteResponseDTO create(ClienteRequestDTO request) throws Exception {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setTipoDocumento(request.getTipoDocumento());
        cliente.setNumeroDocumento(request.getNumeroDocumento());
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());

        cliente = clienteRepository.add(cliente);

        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(cliente.getId());
        response.setTipoDocumento(cliente.getTipoDocumento());
        response.setNumeroDocumento(cliente.getNumeroDocumento());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());

        return response;
    }

    @Override
    public ClienteResponseDTO update(Long id, ClienteRequestDTO request) throws Exception {
        ClienteEntity cliente = clienteRepository.findById(id);
        cliente.setTipoDocumento(request.getTipoDocumento());
        cliente.setNumeroDocumento(request.getNumeroDocumento());
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());

        cliente = clienteRepository.add(cliente);

        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(cliente.getId());
        response.setTipoDocumento(cliente.getTipoDocumento());
        response.setNumeroDocumento(cliente.getNumeroDocumento());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());

        return response;
    }

    @Override
    public void delete(Long id) throws Exception {
        clienteRepository.delete(id);
    }

}
