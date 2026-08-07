package com.gymassistence.gym_assistence.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gymassistence.gym_assistence.model.Asistencia;
import com.gymassistence.gym_assistence.model.Historial;
import com.gymassistence.gym_assistence.service.AsistenciaService;
import com.gymassistence.gym_assistence.service.HistorialService;

@Controller
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    private final HistorialService historialService;

    public AsistenciaController(AsistenciaService asistenciaService,
                                HistorialService historialService) {
        this.asistenciaService = asistenciaService;
        this.historialService = historialService;
    }

    @GetMapping("/asistencia")
    public String asistencia(Model model) {

        model.addAttribute("asistencias", asistenciaService.listarAsistencias());
        model.addAttribute("asistencia", new Asistencia());

        return "asistencia";
    }

    @PostMapping("/guardarAsistencia")
    public String guardarAsistencia(
            @RequestParam(required = false) Integer id,
            @RequestParam Long identificacion,
            @RequestParam String nombre) {

        Asistencia asistencia = new Asistencia();

        asistencia.setId(id);
        asistencia.setIdentificacion(identificacion);
        asistencia.setNombre(nombre);
        asistencia.setFecha(LocalDateTime.now());

        asistenciaService.guardarAsistencia(asistencia);

        Historial historial = new Historial();
        historial.setIdentificacion(identificacion);
        historial.setNombre(nombre);
        historial.setModulo("Asistencia");
        historial.setAccion(id == null ? "Registro" : "Actualización");
        historial.setFecha(LocalDateTime.now());

        historialService.guardarHistorial(historial);

        return "redirect:/asistencia";
    }

    @GetMapping("/editarAsistencia/{id}")
    public String editarAsistencia(@PathVariable Integer id, Model model) {

        model.addAttribute("asistencia", asistenciaService.buscarPorId(id));
        model.addAttribute("asistencias", asistenciaService.listarAsistencias());

        return "asistencia";
    }

    @GetMapping("/eliminarAsistencia/{id}")
    public String eliminarAsistencia(@PathVariable Integer id) {

        Asistencia asistencia = asistenciaService.buscarPorId(id);

        Historial historial = new Historial();
        historial.setIdentificacion(asistencia.getIdentificacion());
        historial.setNombre(asistencia.getNombre());
        historial.setModulo("Asistencia");
        historial.setAccion("Eliminación");
        historial.setFecha(LocalDateTime.now());

        historialService.guardarHistorial(historial);

        asistenciaService.eliminarAsistencia(id);

        return "redirect:/asistencia";
    }
}
