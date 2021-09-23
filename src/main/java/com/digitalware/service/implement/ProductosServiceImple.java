
package com.digitalware.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalware.dto.ProductoRequestDTO;
import com.digitalware.dto.ProductoResponseDTO;
import com.digitalware.model.entities.ProductoEntity;
import com.digitalware.model.repositories.ProductoRepository;
import com.digitalware.service.ProductosService;

@Service
public class ProductosServiceImple implements ProductosService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDTO> listAll() throws Exception {
        Map<Long, ProductoEntity> productos = productoRepository.findAll();

        List<ProductoResponseDTO> productosResponse = new ArrayList<>();

        productos.forEach((id, producto) -> {
            ProductoResponseDTO productoResponse = new ProductoResponseDTO();
            productoResponse.setId(producto.getId());
            productoResponse.setNombre(producto.getNombre());
            productoResponse.setPrecio(producto.getPrecio());
            productoResponse.setInventario(producto.getInventario());

            productosResponse.add(productoResponse);
        });

        return productosResponse;
    }

    @Override
    public ProductoResponseDTO getById(Long id) throws Exception {
        ProductoEntity producto = productoRepository.findById(id);

        ProductoResponseDTO productoResponse = new ProductoResponseDTO();
        productoResponse.setId(producto.getId());
        productoResponse.setNombre(producto.getNombre());
        productoResponse.setPrecio(producto.getPrecio());
        productoResponse.setInventario(producto.getInventario());

        return productoResponse;
    }

    @Override
    public ProductoResponseDTO create(ProductoRequestDTO request) throws Exception {
        ProductoEntity producto = new ProductoEntity();
        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setInventario(request.getInventario());

        producto = productoRepository.add(producto);

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setPrecio(producto.getPrecio());
        response.setInventario(producto.getInventario());

        return response;
    }

    @Override
    public ProductoResponseDTO update(Long id, ProductoRequestDTO request) throws Exception {
        ProductoEntity producto = productoRepository.findById(id);
        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setInventario(request.getInventario());

        producto = productoRepository.add(producto);

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setPrecio(producto.getPrecio());
        response.setInventario(producto.getInventario());

        return response;
    }

    @Override
    public void delete(Long id) throws Exception {
        productoRepository.delete(id);
    }
}
