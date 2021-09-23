
package com.digitalware.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalware.dto.ClienteResponseDTO;
import com.digitalware.dto.ProductCompraRequestDTO;
import com.digitalware.dto.ProductoCompradoResponseDTO;
import com.digitalware.dto.ProductoResponseDTO;
import com.digitalware.dto.UltimaVentaResponseDTO;
import com.digitalware.dto.VentaRequestDTO;
import com.digitalware.dto.VentaResponseDTO;
import com.digitalware.model.entities.ClienteEntity;
import com.digitalware.model.entities.ProductoCompradoEntity;
import com.digitalware.model.entities.ProductoEntity;
import com.digitalware.model.entities.VentaEntity;
import com.digitalware.model.exception.InventarioInsuficienteException;
import com.digitalware.model.repositories.ClienteRepository;
import com.digitalware.model.repositories.ProductoRepository;
import com.digitalware.model.repositories.VentaRepository;
import com.digitalware.service.VentasService;

@Service
public class VentasServiceImple implements VentasService {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<VentaResponseDTO> listAll() throws Exception {
        Map<Long, VentaEntity> ventas = ventaRepository.findAll();

        List<VentaResponseDTO> ventasResponse = new ArrayList<>();

        ventas.forEach((id, venta) -> {
            ClienteEntity cliente = venta.getCliente();

            ClienteResponseDTO clienteResponse = new ClienteResponseDTO();
            clienteResponse.setId(cliente.getId());
            clienteResponse.setTipoDocumento(cliente.getTipoDocumento());
            clienteResponse.setNumeroDocumento(cliente.getNumeroDocumento());
            clienteResponse.setNombre(cliente.getNombre());
            clienteResponse.setApellido(cliente.getApellido());

            List<ProductoCompradoResponseDTO> productosCompradosResponse = new ArrayList<>();

            venta.getProductosComprados().forEach(productoComprado -> {
                ProductoEntity producto = productoComprado.getProducto();

                ProductoResponseDTO productoResponse = new ProductoResponseDTO();
                productoResponse.setId(producto.getId());
                productoResponse.setNombre(producto.getNombre());
                productoResponse.setPrecio(producto.getPrecio());
                productoResponse.setInventario(producto.getInventario());

                ProductoCompradoResponseDTO productoCompradoResponse =
                        new ProductoCompradoResponseDTO();
                productoCompradoResponse.setCantidad(productoComprado.getCantidad());
                productoCompradoResponse.setProducto(productoResponse);

                productosCompradosResponse.add(productoCompradoResponse);
            });

            VentaResponseDTO ventaResponse = new VentaResponseDTO();
            ventaResponse.setId(venta.getId());
            ventaResponse.setFecha(venta.getFecha());
            ventaResponse.setCliente(clienteResponse);
            ventaResponse.setProductosComprados(productosCompradosResponse);

            ventasResponse.add(ventaResponse);
        });

        return ventasResponse;
    }

    @Override
    public VentaResponseDTO getById(Long id) throws Exception {
        VentaEntity venta = ventaRepository.findById(id);

        ClienteEntity cliente = venta.getCliente();

        ClienteResponseDTO clienteResponse = new ClienteResponseDTO();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setTipoDocumento(cliente.getTipoDocumento());
        clienteResponse.setNumeroDocumento(cliente.getNumeroDocumento());
        clienteResponse.setNombre(cliente.getNombre());
        clienteResponse.setApellido(cliente.getApellido());

        List<ProductoCompradoResponseDTO> productosCompradosResponse = new ArrayList<>();

        venta.getProductosComprados().forEach(productoComprado -> {
            ProductoEntity producto = productoComprado.getProducto();

            ProductoResponseDTO productoResponse = new ProductoResponseDTO();
            productoResponse.setId(producto.getId());
            productoResponse.setNombre(producto.getNombre());
            productoResponse.setPrecio(producto.getPrecio());
            productoResponse.setInventario(producto.getInventario());

            ProductoCompradoResponseDTO productoCompradoResponse =
                    new ProductoCompradoResponseDTO();
            productoCompradoResponse.setCantidad(productoComprado.getCantidad());
            productoCompradoResponse.setProducto(productoResponse);

            productosCompradosResponse.add(productoCompradoResponse);
        });

        VentaResponseDTO ventaResponse = new VentaResponseDTO();
        ventaResponse.setId(venta.getId());
        ventaResponse.setFecha(venta.getFecha());
        ventaResponse.setCliente(clienteResponse);
        ventaResponse.setProductosComprados(productosCompradosResponse);

        return ventaResponse;
    }

    @Override
    public VentaResponseDTO create(VentaRequestDTO request) throws Exception {
        List<ProductCompraRequestDTO> productosCompradosRequest = request.getProductosComprados();

        List<ProductoCompradoEntity> productosComprados = new ArrayList<>();
        for (ProductCompraRequestDTO productoCompradoRequest : productosCompradosRequest) {
            ProductoEntity producto =
                    productoRepository.findById(productoCompradoRequest.getProductoId());

            if (producto.getInventario() < productoCompradoRequest.getCantidad())
                throw new InventarioInsuficienteException();

            producto.setInventario(
                    (short) (producto.getInventario() - productoCompradoRequest.getCantidad()));

            producto = productoRepository.add(producto);

            ProductoCompradoEntity productoComprado = new ProductoCompradoEntity();
            productoComprado.setCantidad(productoCompradoRequest.getCantidad());
            productoComprado.setProducto(producto);

            productosComprados.add(productoComprado);
        }

        VentaEntity venta = new VentaEntity();
        venta.setCliente(clienteRepository.findById(request.getClientId()));
        venta.setProductosComprados(productosComprados);

        venta = ventaRepository.add(venta);

        ClienteEntity cliente = venta.getCliente();

        ClienteResponseDTO clienteResponse = new ClienteResponseDTO();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setTipoDocumento(cliente.getTipoDocumento());
        clienteResponse.setNumeroDocumento(cliente.getNumeroDocumento());
        clienteResponse.setNombre(cliente.getNombre());
        clienteResponse.setApellido(cliente.getApellido());

        List<ProductoCompradoResponseDTO> productosCompradosResponse = new ArrayList<>();

        venta.getProductosComprados().forEach(productoComprado -> {
            ProductoEntity producto = productoComprado.getProducto();

            ProductoResponseDTO productoResponse = new ProductoResponseDTO();
            productoResponse.setId(producto.getId());
            productoResponse.setNombre(producto.getNombre());
            productoResponse.setPrecio(producto.getPrecio());
            productoResponse.setInventario(producto.getInventario());

            ProductoCompradoResponseDTO productoCompradoResponse =
                    new ProductoCompradoResponseDTO();
            productoCompradoResponse.setCantidad(productoComprado.getCantidad());
            productoCompradoResponse.setProducto(productoResponse);

            productosCompradosResponse.add(productoCompradoResponse);
        });

        VentaResponseDTO ventaResponse = new VentaResponseDTO();
        ventaResponse.setId(venta.getId());
        ventaResponse.setFecha(venta.getFecha());
        ventaResponse.setCliente(clienteResponse);
        ventaResponse.setProductosComprados(productosCompradosResponse);

        return ventaResponse;
    }

    @Override
    public List<UltimaVentaResponseDTO> ultimasCompras(Short numCompras) throws Exception {
        List<VentaEntity> ventas = new ArrayList<>(ventaRepository.findAll().values());
        ventas.sort((venta1, venta2) -> venta2.getFecha().compareTo(venta1.getFecha()));

        List<UltimaVentaResponseDTO> ultimasVentasResponse = new ArrayList<>();

        for (int i = 0; i < ventas.size() && ultimasVentasResponse.size() < numCompras; i++) {
            VentaEntity venta = ventas.get(i);
            ClienteEntity cliente = venta.getCliente();

            ClienteResponseDTO clienteResponse = new ClienteResponseDTO();
            clienteResponse.setId(cliente.getId());
            clienteResponse.setTipoDocumento(cliente.getTipoDocumento());
            clienteResponse.setNumeroDocumento(cliente.getNumeroDocumento());
            clienteResponse.setNombre(cliente.getNombre());
            clienteResponse.setApellido(cliente.getApellido());

            UltimaVentaResponseDTO ultimaVentaResponse = new UltimaVentaResponseDTO();
            ultimaVentaResponse.setFechaUltimaCompra(venta.getFecha());
            ultimaVentaResponse.setCliente(clienteResponse);

            if (!ultimasVentasResponse.contains(ultimaVentaResponse))
                ultimasVentasResponse.add(ultimaVentaResponse);
        }

        return ultimasVentasResponse;
    }

}
