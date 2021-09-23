
package com.digitalware.model.repositories;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.digitalware.model.entities.VentaEntity;
import com.digitalware.model.exception.VentaNoEncontradoException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class VentaRepository {
    private static final String PERSISTENCE_FILE_NAME_VENTAS = "/ventas.json";

    @Value("${digital-ware.persistence.path}")
    private String persistencePath;

    @Autowired
    private SequenceRepository sequenceRepository;

    public VentaEntity add(VentaEntity entity) throws Exception {
        Map<Long, VentaEntity> ventas = this.findAll();

        if (entity.getId() == null) {
            entity.setId(sequenceRepository.nextVentaId());
            entity.setFecha(new Date());
        }

        ventas.put(entity.getId(), entity);

        new ObjectMapper().writeValue(ResourceUtils.getFile(new URL(new StringBuilder()
                .append(persistencePath).append(PERSISTENCE_FILE_NAME_VENTAS).toString()).toURI()),
                ventas);

        return entity;
    }

    public Map<Long, VentaEntity> findAll() throws Exception {
        Map<Long, VentaEntity> ventas = null;

        try {
            ventas = new ObjectMapper().readValue(
                    new URL(new StringBuilder().append(persistencePath)
                            .append(PERSISTENCE_FILE_NAME_VENTAS).toString()),
                    new TypeReference<Map<Long, VentaEntity>>() {
                    });
        } catch (FileNotFoundException e) {
            ventas = new HashMap<>();

            new ObjectMapper().writeValue(
                    ResourceUtils.getFile(new URL(new StringBuilder().append(persistencePath)
                            .append(PERSISTENCE_FILE_NAME_VENTAS).toString()).toURI()),
                    ventas);

            e.printStackTrace();
        }

        return ventas;
    }

    public VentaEntity findById(Long id) throws Exception {
        VentaEntity venta = this.findAll().get(id);

        if (venta == null)
            throw new VentaNoEncontradoException();

        return venta;
    }

    public void delete(Long id) throws Exception {
        Map<Long, VentaEntity> ventas = this.findAll();

        if (ventas.remove(id) == null)
            throw new VentaNoEncontradoException();

        new ObjectMapper().writeValue(ResourceUtils.getFile(new URL(new StringBuilder()
                .append(persistencePath).append(PERSISTENCE_FILE_NAME_VENTAS).toString()).toURI()),
                ventas);
    }
}
