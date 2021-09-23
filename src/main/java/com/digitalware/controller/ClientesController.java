
package com.digitalware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalware.dto.ClienteRequestDTO;
import com.digitalware.dto.ClienteResponseDTO;
import com.digitalware.model.exception.ClienteNoEncontradoException;
import com.digitalware.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClientesController {
    @Autowired
    private ClienteService clientesService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listAll() throws Exception {
        return ResponseEntity.ok().body(clientesService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getById(@PathVariable("id") Long id)
            throws Exception {
        try {
            return ResponseEntity.ok().body(clientesService.getById(id));
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(@RequestBody ClienteRequestDTO request)
            throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody ClienteRequestDTO request) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(clientesService.update(id, request));
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        try {
            clientesService.delete(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
