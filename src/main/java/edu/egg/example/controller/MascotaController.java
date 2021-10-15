package edu.egg.example.controller;

import edu.egg.example.entity.Mascota;
import edu.egg.example.service.MascotaService;
import edu.egg.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView mostrarTodas() {
        ModelAndView mav = new ModelAndView("mascotas");
        mav.addObject("mascotas", mascotaService.buscarTodas());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearMascota() {
        ModelAndView mav = new ModelAndView("mascota-formulario");
        mav.addObject("mascota", new Mascota());
        mav.addObject("usuarios", usuarioService.buscarTodos());
        mav.addObject("title", "Crear Mascota");
        mav.addObject("action", "guardar");
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarMascota(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("mascota-formulario");
        mav.addObject("mascota", mascotaService.buscarPorId(id));
        mav.addObject("usuarios", usuarioService.buscarTodos());
        mav.addObject("title", "Editar Mascota");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre, @RequestParam("duenio") Long dniDuenio) {
        mascotaService.crear(nombre, dniDuenio);
        return new RedirectView("/mascotas");
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam Integer id, @RequestParam String nombre) {
        mascotaService.modificar(id, nombre);
        return new RedirectView("/mascotas");
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Integer id) {
        mascotaService.eliminar(id);
        return new RedirectView("/mascotas");
    }
}
