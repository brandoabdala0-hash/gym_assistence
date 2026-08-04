package com.gymassistence.gym_assistence.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gymassistence.gym_assistence.model.Producto;
import com.gymassistence.gym_assistence.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto buscarPorRef(Long ref) {
        return productoRepository.findById(ref).orElse(null);
    }

    public void eliminarProducto(Long ref) {
        productoRepository.deleteById(ref);
    }
}