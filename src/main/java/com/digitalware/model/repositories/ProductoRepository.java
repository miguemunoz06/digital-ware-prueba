
package com.digitalware.model.repositories;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.digitalware.model.entities.ProductoEntity;
import com.digitalware.model.exception.ProductoNoEncontradoException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ProductoRepository {
    private static final String PERSISTENCE_FILE_NAME_PRODUCTOS = "/productos.json";

    @Value("${digital-ware.persistence.path}")
    private String persistencePath;

    @Autowired
    private SequenceRepository sequenceRepository;

    public ProductoEntity add(ProductoEntity entity) throws Exception {
        Map<Long, ProductoEntity> productos = this.findAll();

        if (entity.getId() == null)
            entity.setId(sequenceRepository.nextProductId());

        productos.put(entity.getId(), entity);

        new ObjectMapper()
                .writeValue(
                        ResourceUtils.getFile(new URL(new StringBuilder().append(persistencePath)
                                .append(PERSISTENCE_FILE_NAME_PRODUCTOS).toString()).toURI()),
                        productos);

        return entity;
    }

    public Map<Long, ProductoEntity> findAll() throws Exception {
        Map<Long, ProductoEntity> productos = null;

        try {
            productos = new ObjectMapper().readValue(
                    new URL(new StringBuilder().append(persistencePath)
                            .append(PERSISTENCE_FILE_NAME_PRODUCTOS).toString()),
                    new TypeReference<Map<Long, ProductoEntity>>() {
                    });
        } catch (IOException e) {
            productos = new HashMap<>();

            new ObjectMapper().writeValue(
                    ResourceUtils.getFile(new URL(new StringBuilder().append(persistencePath)
                            .append(PERSISTENCE_FILE_NAME_PRODUCTOS).toString()).toURI()),
                    productos);

            e.printStackTrace();
        }

        return productos;
    }

    public ProductoEntity findById(Long id) throws Exception {
        ProductoEntity producto = this.findAll().get(id);

        if (producto == null)
            throw new ProductoNoEncontradoException();

        return producto;
    }

    public void delete(Long id) throws Exception {
        Map<Long, ProductoEntity> productos = this.findAll();

        if (productos.remove(id) == null)
            throw new ProductoNoEncontradoException();

        new ObjectMapper()
                .writeValue(
                        ResourceUtils.getFile(new URL(new StringBuilder().append(persistencePath)
                                .append(PERSISTENCE_FILE_NAME_PRODUCTOS).toString()).toURI()),
                        productos);
    }

}
