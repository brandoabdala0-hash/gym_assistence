package com.gymassistence.gym_assistence.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gymassistence.gym_assistence.model.Asistencia;
import com.gymassistence.gym_assistence.repository.AsistenciaRepository;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    public List<Asistencia> listarAsistencias() {
        return asistenciaRepository.findAll();
    }

    public void guardarAsistencia(Asistencia asistencia) {
        asistenciaRepository.save(asistencia);
    }

    public Asistencia buscarPorId(Integer id) {
        return asistenciaRepository.findById(id).orElse(null);
    }

    public void eliminarAsistencia(Integer id) {
        asistenciaRepository.deleteById(id);
    }
}