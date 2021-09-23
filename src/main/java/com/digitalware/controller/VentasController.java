
package com.digitalware.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalware.dto.UltimaVentaResponseDTO;
import com.digitalware.dto.VentaRequestDTO;
import com.digitalware.dto.VentaResponseDTO;
import com.digitalware.model.exception.InventarioInsuficienteException;
import com.digitalware.model.exception.VentaNoEncontradoException;
import com.digitalware.service.VentasService;

@RestController
@RequestMapping("ventas")
public class VentasController {
    @Autowired
    private VentasService ventasService;

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listAll() throws Exception {
        return ResponseEntity.ok().body(ventasService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> getById(@PathVariable("id") Long id) throws Exception {
        try {
            return ResponseEntity.ok().body(ventasService.getById(id));
        } catch (VentaNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> create(@RequestBody VentaRequestDTO request)
            throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ventasService.create(request));
        } catch (InventarioInsuficienteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("untimasCompras")
    public ResponseEntity<List<UltimaVentaResponseDTO>>
            ultimasCompras(@PathParam("numCompras") Short numCompras) throws Exception {
        return ResponseEntity.ok().body(ventasService.ultimasCompras(numCompras));
    }
}
