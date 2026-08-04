package com.gymassistence.gym_assistence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gymassistence.gym_assistence.model.Producto;
import com.gymassistence.gym_assistence.service.ProductoService;

@Controller
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public String productos(Model model) {

        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("producto", new Producto());

        return "producto";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(
        @RequestParam Long ref,
        @RequestParam String producto,
        @RequestParam Integer cantidad,
        @RequestParam Double costo) {

    System.out.println("REF = " + ref);
    System.out.println("PRODUCTO = " + producto);
    System.out.println("CANTIDAD = " + cantidad);
    System.out.println("COSTO = " + costo);

    Producto p = new Producto();
    p.setRef(ref);
    p.setProducto(producto);
    p.setCantidad(cantidad);
    p.setCosto(costo);

    productoService.guardarProducto(p);

    return "redirect:/productos";
    }

    @GetMapping("/editarProducto/{ref}")
    public String editarProducto(@PathVariable Long ref, Model model) {

        model.addAttribute("producto", productoService.buscarPorRef(ref));
        model.addAttribute("productos", productoService.listarProductos());

        return "producto";
    }

    @GetMapping("/eliminarProducto/{ref}")
    public String eliminarProducto(@PathVariable Long ref) {

        productoService.eliminarProducto(ref);

        return "redirect:/productos";
    }
}
