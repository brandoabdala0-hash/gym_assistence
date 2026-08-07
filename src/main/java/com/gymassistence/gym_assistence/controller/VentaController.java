package com.gymassistence.gym_assistence.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gymassistence.gym_assistence.model.Venta;
import com.gymassistence.gym_assistence.service.VentaService;
import com.gymassistence.gym_assistence.model.Historial;
import com.gymassistence.gym_assistence.service.HistorialService;

@Controller
public class VentaController {

    private final VentaService ventaService;
    private final HistorialService historialService;

    public VentaController(VentaService ventaService,
    HistorialService historialService) {
    this.ventaService = ventaService;
    this.historialService = historialService;
    }

    @GetMapping("/ventas")
    public String ventas(Model model) {

        model.addAttribute("ventas", ventaService.listarVentas());
        model.addAttribute("venta", new Venta());

        return "venta";
    }

    @PostMapping("/guardarVenta")
    public String guardarVenta(
            @RequestParam Long ref,
            @RequestParam String producto,
            @RequestParam Integer cantidad,
            @RequestParam Double precio) {

        Venta venta = new Venta();

        venta.setRef(ref);
        venta.setProducto(producto);
        venta.setCantidad(cantidad);
        venta.setPrecio(precio);

        // Calcula el total automáticamente
        venta.setTotal(cantidad * precio);

        // Guarda la fecha y hora actuales
        venta.setFecha(LocalDateTime.now());

        ventaService.guardarVenta(venta);
        Historial historial = new Historial();

        historial.setIdentificacion(venta.getRef());
        historial.setNombre(venta.getProducto());
        historial.setModulo("Ventas");
        historial.setAccion("Registro");
        historial.setFecha(LocalDateTime.now());

        historialService.guardarHistorial(historial);

        return "redirect:/ventas";
        }

    @GetMapping("/editarVenta/{ref}")
    public String editarVenta(@PathVariable Long ref, Model model) {

        model.addAttribute("venta", ventaService.buscarPorRef(ref));
        model.addAttribute("ventas", ventaService.listarVentas());

        return "venta";
    }

    @GetMapping("/eliminarVenta/{ref}")
    public String eliminarVenta(@PathVariable Long ref) {

    Venta venta = ventaService.buscarPorRef(ref);

    Historial historial = new Historial();
    historial.setIdentificacion(venta.getRef());
    historial.setNombre(venta.getProducto());
    historial.setModulo("Ventas");
    historial.setAccion("Eliminación");
    historial.setFecha(LocalDateTime.now());

    historialService.guardarHistorial(historial);

    ventaService.eliminarVenta(ref);

    return "redirect:/ventas";
    }
}
