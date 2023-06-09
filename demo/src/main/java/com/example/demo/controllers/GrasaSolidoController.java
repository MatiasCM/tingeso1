package com.example.demo.controllers;

import com.example.demo.entities.GrasaSolidoEntity;
import com.example.demo.services.GrasaSolidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping
public class GrasaSolidoController {
    @Autowired
    private GrasaSolidoService grasaSolido;

    @GetMapping("/fileUpload_grasa")
    public String main() {
        return "fileUpload_grasa";
    }

    @PostMapping("/fileUpload_grasa")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        grasaSolido.eliminarGS();
        grasaSolido.guardarGS(file);
        String filename = file.getOriginalFilename();
        redirectAttributes.addFlashAttribute("mensaje", "Archivo cargado con éxito");
        grasaSolido.leerCsv(filename);
        return "redirect:/fileUpload_grasa";
    }

    @GetMapping("/fileInformation_grasa")
    public String listar(Model model) {
        ArrayList<GrasaSolidoEntity> grasaSolidos = grasaSolido.obtenerGS();
        model.addAttribute("grasaSolidos", grasaSolidos);
        return "fileInformation_grasa";
    }

}
