package com.gymassistence.gym_assistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymassistence.gym_assistence.model.Cuenta;

/**
 * Repositorio encargado de acceder a la tabla cuentas.
 */
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    Optional<Cuenta> findByUsuario(String usuario);

    boolean existsByUsuario(String usuario);
}

