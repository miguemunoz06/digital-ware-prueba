
package com.digitalware.model.repositories;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.digitalware.model.entities.SequenceEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class SequenceRepository {
    public Long nextProductId() throws Exception {
        SequenceEntity sequences = this.readFile();

        Long id = sequences.getProduct();

        sequences.setProduct(id == null ? (id = 1L) : ++id);

        this.writeFile(sequences);

        return id;
    }

    public Long nextClientId() throws Exception {
        SequenceEntity sequences = this.readFile();

        Long id = sequences.getClient();

        sequences.setClient(id == null ? (id = 1L) : ++id);

        this.writeFile(sequences);

        return id;
    }

    public Long nextVentaId() throws Exception {
        SequenceEntity sequences = this.readFile();

        Long id = sequences.getVenta();

        sequences.setVenta(id == null ? (id = 1L) : ++id);

        this.writeFile(sequences);

        return id;
    }

    private SequenceEntity readFile() throws Exception {
        SequenceEntity sequences = null;

        try {
            sequences = new ObjectMapper().readValue(
                    new URL("file:src/main/resources/persistence/sequences.json"),
                    SequenceEntity.class);
        } catch (IOException e) {
            sequences = new SequenceEntity();
            sequences.setProduct(1L);
            sequences.setClient(1L);

            this.writeFile(sequences);
        }

        return sequences;
    }

    private void writeFile(SequenceEntity sequences) throws Exception {
        new ObjectMapper().writeValue(ResourceUtils
                .getFile(new URL("file:src/main/resources/persistence/sequences.json")), sequences);
    }
}
