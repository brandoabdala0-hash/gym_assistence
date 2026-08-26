package com.gymassistence.gym_assistence.controller;

import java.util.Collections;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymassistence.gym_assistence.model.Cuenta;
import com.gymassistence.gym_assistence.service.CuentaService;
import org.springframework.web.bind.annotation.RequestBody;
import com.gymassistence.gym_assistence.model.LoginRequest;

/**
 * Controlador encargado del registro e inicio de sesión de cuentas
 * utilizadas en la pantalla de login.
 */
@RestController
public class CuentaController {

    private final CuentaService cuentaService;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping("/registrarCuenta")
    public ResponseEntity<Map<String, Object>> registrarCuenta(
        @RequestBody LoginRequest datos) {

        try {
            Cuenta cuenta = cuentaService.registrarCuenta(
            datos.getUsuario(),
            datos.getPassword()
            );

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "mensaje", "Cuenta creada correctamente",
                    "usuario", cuenta.getUsuario()
            ));

        } catch (IllegalArgumentException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "mensaje", e.getMessage()
            ));
        }
    }

    @PostMapping("/loginCuenta")
    public ResponseEntity<Map<String, Object>> loginCuenta(
        @RequestBody LoginRequest datos,
        HttpServletRequest request,
        HttpServletResponse response) {

        Cuenta cuenta = cuentaService.validarCredenciales(
        datos.getUsuario(),
        datos.getPassword()
        );

        if (cuenta == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "mensaje", "Credenciales incorrectas"
            ));
        }

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        cuenta.getUsuario(),
                        null,
                        Collections.emptyList()
                );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "mensaje", "Login exitoso",
                "usuario", cuenta.getUsuario()
        ));
    }
}
