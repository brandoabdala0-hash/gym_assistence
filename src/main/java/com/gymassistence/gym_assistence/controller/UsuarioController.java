package com.gymassistence.gym_assistence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gymassistence.gym_assistence.model.Usuario;
import com.gymassistence.gym_assistence.service.UsuarioService;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDateTime;

import com.gymassistence.gym_assistence.model.Historial;
import com.gymassistence.gym_assistence.service.HistorialService;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final HistorialService historialService;

    public UsuarioController(UsuarioService usuarioService,
                       HistorialService historialService) {

    this.usuarioService = usuarioService;
    this.historialService = historialService;
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

    // Registrar Usuario (formulario independiente)
    @GetMapping("/registrarUsuario")
    public String registrarUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrarUsuario";
    }

    // Usuarios
    @GetMapping("/usuarios")
    public String usuarios(Model model) {

    model.addAttribute("usuarios", usuarioService.listarUsuarios());

    model.addAttribute("usuario", new Usuario());

    return "usuarios";
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

    boolean esNuevo = (usuario.getId() == null);

    usuarioService.guardarUsuario(usuario);

    Historial historial = new Historial();

    historial.setIdentificacion(usuario.getIdentificacion());
    historial.setNombre(usuario.getNombre());
    historial.setModulo("Usuarios");
    historial.setAccion(esNuevo ? "Registro" : "Actualización");
    historial.setFecha(LocalDateTime.now());

    historialService.guardarHistorial(historial);

    return "redirect:/usuarios";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {

    Usuario usuario = usuarioService.buscarPorId(id);

    Historial historial = new Historial();

    historial.setIdentificacion(usuario.getIdentificacion());
    historial.setNombre(usuario.getNombre());
    historial.setModulo("Usuarios");
    historial.setAccion("Eliminación");
    historial.setFecha(LocalDateTime.now());

    historialService.guardarHistorial(historial);

    usuarioService.eliminarUsuario(id);

    return "redirect:/usuarios";
    }

}