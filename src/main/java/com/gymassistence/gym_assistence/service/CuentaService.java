package com.gymassistence.gym_assistence.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gymassistence.gym_assistence.model.Cuenta;
import com.gymassistence.gym_assistence.repository.CuentaRepository;

/**
 * Servicio encargado de la lógica de negocio relacionada con las cuentas
 * (registro e inicio de sesión) usadas en el login.
 */
@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final PasswordEncoder passwordEncoder;

    public CuentaService(CuentaRepository cuentaRepository, PasswordEncoder passwordEncoder) {
        this.cuentaRepository = cuentaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registra una nueva cuenta cifrando la contraseña antes de guardarla.
     * Lanza IllegalArgumentException si el usuario ya existe.
     */
    public Cuenta registrarCuenta(String usuario, String password) {

        if (usuario == null || usuario.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Usuario y contraseña son obligatorios");
        }

        if (cuentaRepository.existsByUsuario(usuario)) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(usuario);
        cuenta.setPassword(passwordEncoder.encode(password));

        return cuentaRepository.save(cuenta);
    }

    /**
     * Valida las credenciales comparando la contraseña ingresada con el hash
     * almacenado. Devuelve la cuenta si son válidas, o null en caso contrario.
     */
    public Cuenta validarCredenciales(String usuario, String password) {

        return cuentaRepository.findByUsuario(usuario)
                .filter(cuenta -> passwordEncoder.matches(password, cuenta.getPassword()))
                .orElse(null);
    }
}

