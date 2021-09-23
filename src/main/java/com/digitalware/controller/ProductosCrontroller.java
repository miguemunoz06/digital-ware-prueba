
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

import com.digitalware.dto.ProductoRequestDTO;
import com.digitalware.dto.ProductoResponseDTO;
import com.digitalware.model.exception.ProductoNoEncontradoException;
import com.digitalware.service.ProductosService;

@RestController
@RequestMapping("products")
public class ProductosCrontroller {
    @Autowired
    private ProductosService productosService;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listAll() throws Exception {
        return ResponseEntity.ok().body(productosService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getById(@PathVariable("id") Long id)
            throws Exception {
        try {
            return ResponseEntity.ok().body(productosService.getById(id));
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> create(@RequestBody ProductoRequestDTO request)
            throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(productosService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody ProductoRequestDTO request) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(productosService.update(id, request));
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        try {
            productosService.delete(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
