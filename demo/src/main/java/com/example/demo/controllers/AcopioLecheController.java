package com.example.demo.controllers;

import com.example.demo.entities.AcopioLecheEntity;
import com.example.demo.services.AcopioLecheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class AcopioLecheController {

    @Autowired
    private AcopioLecheService acopioLeche;

    @GetMapping("/fileUpload")
    public String main() {
        return "fileUpload";
    }

    @PostMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        acopioLeche.guardarAcopio(file);
        redirectAttributes.addFlashAttribute("mensaje", "Archivo cargado con éxito");
        acopioLeche.leerCsv("Acopio.csv");
        return "redirect:/fileUpload";
    }

    @GetMapping("/fileInformation")
    public String listar(Model model) {
        ArrayList<AcopioLecheEntity> acopioLeches = acopioLeche.obtenerAcopio();
        model.addAttribute("acopioLeches", acopioLeches);
        return "fileInformation";
    }

    @GetMapping("/acopio/{proveedor}")
    public ResponseEntity<List<AcopioLecheEntity>> obtenerAcopiosPorProveedor(@PathVariable String proveedor) {
        List<AcopioLecheEntity> acopios = acopioLeche.obtenerPorProveedor(proveedor);
        if(acopios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(acopios, HttpStatus.OK);
    }

    //retornar la suma de kls de leche
    @GetMapping("/acopio/sumarKls/{proveedor}")
    public ResponseEntity<Double> sumarKls(@PathVariable String proveedor) {
        double kls = acopioLeche.sumarKls(proveedor);
        if(kls == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(kls, HttpStatus.OK);
    }

    //retornar kls de leche de proveedor por categoria
    @GetMapping("/acopio/sumarKlsPorCategoria/{proveedor}/{categoria}")
    public ResponseEntity<Double> sumarKlsPorCategoria(@PathVariable String proveedor, @PathVariable String categoria) {
        double kls_leche = acopioLeche.sumarKls(proveedor);
        double kls = acopioLeche.klsPorCategoria(categoria, kls_leche);
        if(kls == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(kls, HttpStatus.OK);
    }


}
