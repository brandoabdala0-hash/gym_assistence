package com.gymassistence.gym_assistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gymassistence.gym_assistence.model.Usuario;

/**
 * Repositorio encargado de acceder a la tabla usuarios.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}