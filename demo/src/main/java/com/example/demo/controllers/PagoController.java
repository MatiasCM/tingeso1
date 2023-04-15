package com.example.demo.controllers;

import com.example.demo.entities.AcopioLecheEntity;
import com.example.demo.entities.PagoEntity;
import com.example.demo.entities.ProveedorEntity;
import com.example.demo.services.AcopioLecheService;
import com.example.demo.services.GrasaSolidoService;
import com.example.demo.services.PagoService;
import com.example.demo.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private AcopioLecheService acopioLecheService;

    @Autowired
    private GrasaSolidoService grasaSolidoService;

    @GetMapping("/calcular")
    public String calcular(){
        pagoService.eliminarPagos();
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        for(ProveedorEntity proveedor:proveedores){
            String codigo = proveedor.getCodigo();
            String categoria = proveedor.getCategoria();
            PagoEntity pago = new PagoEntity(null, "", codigo, proveedor.getNombre_proveedor(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            double kls_leche = acopioLecheService.sumarKls(codigo);
            double klsPorCategoria = acopioLecheService.klsPorCategoria(categoria, kls_leche);
            double grasa = grasaSolidoService.obtenerGrasa(codigo);
            double pagoGrasa = grasaSolidoService.pagoPorGrasa(grasa, kls_leche);
            double st = grasaSolidoService.obtenerST(codigo);
            double pagoST = grasaSolidoService.pagoPorST(st, kls_leche);
            double bono = acopioLecheService.bonoFrecuencia(codigo, kls_leche);
            double pagoAcopioLeche = klsPorCategoria + pagoGrasa + pagoST + bono;

        }
        return "redirect:/";
    }

    //retornar pago por grasa
    @GetMapping("/gs/pagoGrasa/{proveedor}")
    public ResponseEntity<Double> pagoGrasa(@PathVariable String proveedor) {
        double kls_leche = acopioLecheService.sumarKls(proveedor);
        double grasa = grasaSolidoService.obtenerGrasa(proveedor);
        double pago = grasaSolidoService.pagoPorGrasa(grasa, kls_leche);
        if(pago == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pago, HttpStatus.OK);
    }

    @GetMapping("/pagoST/{proveedor}")
    public ResponseEntity<Double> pagoST(@PathVariable String proveedor) {
        double kls_leche = acopioLecheService.sumarKls(proveedor);
        double st = grasaSolidoService.obtenerST(proveedor);
        double pago = grasaSolidoService.pagoPorST(st, kls_leche);
        if(pago == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pago, HttpStatus.OK);
    }

    @GetMapping("/bono/{proveedor}")
    public ResponseEntity<Double> bono(@PathVariable String proveedor) {
        double kls_leche = acopioLecheService.sumarKls(proveedor);
        double bono = acopioLecheService.bonoFrecuencia(proveedor, kls_leche);
        if(bono == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bono, HttpStatus.OK);
    }

}
