package com.gymassistence.gym_assistence.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gymassistence.gym_assistence.model.Venta;
import com.gymassistence.gym_assistence.repository.VentaRepository;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta buscarPorRef(Long ref) {
        return ventaRepository.findById(ref).orElse(null);
    }

    public void eliminarVenta(Long ref) {
        ventaRepository.deleteById(ref);
    }
}
