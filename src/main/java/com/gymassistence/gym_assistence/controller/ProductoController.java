package com.gymassistence.gym_assistence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gymassistence.gym_assistence.model.Producto;
import com.gymassistence.gym_assistence.service.ProductoService;
import java.time.LocalDateTime;

import com.gymassistence.gym_assistence.model.Historial;
import com.gymassistence.gym_assistence.service.HistorialService;

@Controller
public class ProductoController {

    private final ProductoService productoService;
    private final HistorialService historialService;

    public ProductoController(ProductoService productoService,
                          HistorialService historialService) {
    this.productoService = productoService;
    this.historialService = historialService;
    }

    @GetMapping("/productos")
    public String productos(Model model) {

        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("producto", new Producto());

        return "producto";
    }

    @GetMapping("/registrarProducto")
    public String registrarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "registrarProducto";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(
        @RequestParam Long ref,
        @RequestParam String producto,
        @RequestParam Integer cantidad,
        @RequestParam Double costo) {

    Producto p = new Producto();
    p.setRef(ref);
    p.setProducto(producto);
    p.setCantidad(cantidad);
    p.setCosto(costo);

    productoService.guardarProducto(p);
    Historial historial = new Historial();

    historial.setIdentificacion(0L);
    historial.setNombre("Administrador");
    historial.setModulo("Productos");
    historial.setAccion("Registró el producto: " + producto);
    historial.setFecha(LocalDateTime.now());

    historialService.guardarHistorial(historial);

    return "redirect:/productos";
    }

    @GetMapping("/eliminarProducto/{id}")
    public String eliminarProducto(@PathVariable Integer id) {

    productoService.eliminarProducto(id);

    return "redirect:/productos";
    }
}
