package com.gymassistence.gym_assistence.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gymassistence.gym_assistence.model.Usuario;
import com.gymassistence.gym_assistence.repository.UsuarioRepository;

/**
 * Servicio encargado de la lógica de negocio relacionada con los usuarios.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id) {

    return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
    usuarioRepository.deleteById(id);
    }
}