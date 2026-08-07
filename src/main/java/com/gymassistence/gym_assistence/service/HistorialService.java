package com.gymassistence.gym_assistence.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gymassistence.gym_assistence.model.Historial;
import com.gymassistence.gym_assistence.repository.HistorialRepository;

@Service
public class HistorialService {

    private final HistorialRepository historialRepository;

    public HistorialService(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public List<Historial> listarHistorial() {
        return historialRepository.findAll();
    }

    public void guardarHistorial(Historial historial) {
        historialRepository.save(historial);
    }

    public Historial buscarPorId(Integer id) {
        return historialRepository.findById(id).orElse(null);
    }

    public void eliminarHistorial(Integer id) {
        historialRepository.deleteById(id);
    }
}