package com.example.demo.controllers;

import com.example.demo.entities.AcopioLecheEntity;
import com.example.demo.services.AcopioLecheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

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
        acopioLeche.eliminarAcopios();
        acopioLeche.guardarAcopio(file);
        String filename = file.getOriginalFilename();
        redirectAttributes.addFlashAttribute("mensaje", "Archivo cargado con éxito");
        acopioLeche.leerCsv(filename);
        return "redirect:/fileUpload";
    }

    @GetMapping("/fileInformation")
    public String listar(Model model) {
        ArrayList<AcopioLecheEntity> acopioLeches = acopioLeche.obtenerAcopio();
        model.addAttribute("acopioLeches", acopioLeches);
        return "fileInformation";
    }

}
