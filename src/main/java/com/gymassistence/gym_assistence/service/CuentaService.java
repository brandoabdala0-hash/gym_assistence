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

        usuario = usuario.trim();

        if (password.length() < 8
                || !password.matches(".*[A-Z].*")
                || !password.matches(".*[a-z].*")
                || !password.matches(".*\\d.*")) {
            throw new IllegalArgumentException(
                    "La contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula y un número");
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

        if (usuario == null || password == null || usuario.isBlank() || password.isBlank()) {
            return null;
        }

        return cuentaRepository.findByUsuario(usuario.trim())
                .filter(cuenta -> passwordEncoder.matches(password, cuenta.getPassword()))
                .orElse(null);
    }
}

