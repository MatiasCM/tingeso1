package com.example.demo;

import com.example.demo.entities.ProveedorEntity;
import com.example.demo.repositories.ProveedorRepository;
import com.example.demo.services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProveedorServiceTest {
    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    void obtenerProveedores(){
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();

        assertEquals(6, proveedores.size());
    }

    @Test
    void guardarProveedor(){
        String codigo = "01003";
        String retencion = "Si";
        String categoria = "A";
        String nombre = "Bruce Wayne";

        proveedorService.guardarProveedor(codigo,retencion,categoria,nombre);
        ProveedorEntity proveedorGuardado = proveedorRepository.findByCodigo(codigo);

        assertEquals(codigo, proveedorGuardado.getCodigo());
        assertEquals(retencion, proveedorGuardado.getAfecto_retencion());
        assertEquals(categoria, proveedorGuardado.getCategoria());
        assertEquals(nombre, proveedorGuardado.getNombre_proveedor());

    }
}
