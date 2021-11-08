package edu.egg.example.controller;

import edu.egg.example.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/crear")
    public ModelAndView crear() {
        return new ModelAndView("rol-formulario");
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre) {
        rolService.crear(nombre);
        return new RedirectView("/home");
    }
}
