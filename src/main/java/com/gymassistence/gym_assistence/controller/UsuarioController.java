package com.gymassistence.gym_assistence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gymassistence.gym_assistence.model.Usuario;
import com.gymassistence.gym_assistence.service.UsuarioService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Login
    @GetMapping("/")
    public String login() {
        return "login";
    }

    // Panel principal
    @GetMapping("/panel")
    public String panel() {
        return "panel";
    }

    // Usuarios
    @GetMapping("/usuarios")
    public String usuarios(Model model) {

    model.addAttribute("usuarios", usuarioService.listarUsuarios());

    model.addAttribute("usuario", new Usuario());

    return "usuarios";
    }

    // Ventas
    @GetMapping("/ventas")
    public String ventas() {
        return "ventas";
    }

    // Asistencia
    @GetMapping("/asistencia")
    public String asistencia() {
        return "asistencia";
    }

    // Historial
    @GetMapping("/historial")
    public String historial() {
        return "historial";
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model) {

    Usuario usuario = usuarioService.buscarPorId(id);

    model.addAttribute("usuario", usuario);
    model.addAttribute("usuarios", usuarioService.listarUsuarios());

    return "usuarios";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {

        usuarioService.guardarUsuario(usuario);

        return "redirect:/usuarios";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {

        usuarioService.eliminarUsuario(id);

        return "redirect:/usuarios";
    }

}