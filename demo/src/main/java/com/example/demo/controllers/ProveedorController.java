package com.example.demo.controllers;

import com.example.demo.entities.ProveedorEntity;
import com.example.demo.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/listar")
    public String listar(Model model) {
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        model.addAttribute("proveedores", proveedores);
        return "index";
    }
    @GetMapping("/nuevo_proveedor")
    public String proveedor(){
        return "nuevo_proveedor";
    }
    @PostMapping("/nuevo_proveedor")
    public String nuevoProveedor(@RequestParam("codigo") String codigo,
                                 @RequestParam("afecto_retencion") String afecto_retencion,
                                 @RequestParam("categoria") String categoria,
                                 @RequestParam("nombre_proveedor") String nombre_proveedor){
        proveedorService.guardarProveedor(codigo, afecto_retencion, categoria, nombre_proveedor);
        return "redirect:/listar";
    }
}