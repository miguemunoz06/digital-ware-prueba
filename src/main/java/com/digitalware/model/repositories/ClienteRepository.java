
package com.digitalware.model.repositories;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.digitalware.model.entities.ClienteEntity;
import com.digitalware.model.exception.ClienteNoEncontradoException;
import com.digitalware.model.exception.ProductoNoEncontradoException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ClienteRepository {
    private static final String PERSISTENCE_FILE_NAME_CLIENTES = "/clientes.json";

    @Value("${digital-ware.persistence.path}")
    private String persistencePath;

    @Autowired
    private SequenceRepository sequenceRepository;

    public ClienteEntity add(ClienteEntity entity) throws Exception {
        Map<Long, ClienteEntity> productos = this.findAll();

        if (entity.getId() == null)
            entity.setId(sequenceRepository.nextClientId());

        productos.put(entity.getId(), entity);

        new ObjectMapper()
                .writeValue(
                        ResourceUtils.getFile(new URL(new StringBuilder().append(persistencePath)
                                .append(PERSISTENCE_FILE_NAME_CLIENTES).toString()).toURI()),
                        productos);

        return entity;
    }

    public Map<Long, ClienteEntity> findAll() throws Exception {
        Map<Long, ClienteEntity> productos = null;

        try {
            productos = new ObjectMapper().readValue(
                    new URL(new StringBuilder().append(persistencePath)
                            .append(PERSISTENCE_FILE_NAME_CLIENTES).toString()),
                    new TypeReference<Map<Long, ClienteEntity>>() {
                    });
        } catch (FileNotFoundException e) {
            productos = new HashMap<>();

            new ObjectMapper().writeValue(
                    ResourceUtils.getFile(new URL(new StringBuilder().append(persistencePath)
                            .append(PERSISTENCE_FILE_NAME_CLIENTES).toString()).toURI()),
                    productos);

            e.printStackTrace();
        }

        return productos;
    }

    public ClienteEntity findById(Long id) throws Exception {
        ClienteEntity producto = this.findAll().get(id);

        if (producto == null)
            throw new ClienteNoEncontradoException();

        return producto;
    }

    public void delete(Long id) throws Exception {
        Map<Long, ClienteEntity> productos = this.findAll();

        if (productos.remove(id) == null)
            throw new ProductoNoEncontradoException();

        new ObjectMapper()
                .writeValue(
                        ResourceUtils.getFile(new URL(new StringBuilder().append(persistencePath)
                                .append(PERSISTENCE_FILE_NAME_CLIENTES).toString()).toURI()),
                        productos);
    }
}
