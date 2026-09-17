package com.gymassistence.gym_assistence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gymassistence.gym_assistence.service.HistorialService;

@Controller
public class HistorialController {

    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/historial")
    public String historial(Model model) {

        model.addAttribute("historiales", historialService.listarHistorial());

        return "historial";
    }

    @PostMapping("/eliminarHistorial")
    public String eliminarTodoElHistorial() {
        historialService.eliminarTodoElHistorial();
        return "redirect:/historial";
    }
}
