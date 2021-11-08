package edu.egg.example.controller;

import edu.egg.example.entity.Usuario;
import edu.egg.example.service.UsuarioService;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView mostrarTodos(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("usuarios");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("usuarios", usuarioService.buscarTodos());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearUsuario() {
        ModelAndView mav = new ModelAndView("usuario-formulario");
        mav.addObject("usuario", new Usuario());
        mav.addObject("title", "Crear Usuario");
        mav.addObject("action", "guardar");
        return mav;
    }

    @GetMapping("/editar/{dni}")
    public ModelAndView editarUsuario(@PathVariable Long dni) {
        ModelAndView mav = new ModelAndView("usuario-formulario");
        mav.addObject("usuario", usuarioService.buscarPorDni(dni));
        mav.addObject("title", "Editar Usuario");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellido, @RequestParam LocalDate fechaNacimiento, @RequestParam String correo, @RequestParam String clave, RedirectAttributes attributes) {
        try {
            usuarioService.crear(dni, nombre, apellido, fechaNacimiento, correo, clave);
            attributes.addFlashAttribute("exito", "El usuario ha sido creado exitosamente");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/usuarios");
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellido, @RequestParam LocalDate fechaNacimiento, @RequestParam String correo, @RequestParam String clave, RedirectAttributes attributes) {
        usuarioService.modificar(dni, nombre, apellido, fechaNacimiento, correo, clave);
        return new RedirectView("/usuarios");
    }

    @PostMapping("/habilitar/{dni}")
    public RedirectView habilitar(@PathVariable Long dni) {
        usuarioService.habilitar(dni);
        return new RedirectView("/usuarios");
    }

    @PostMapping("/eliminar/{dni}")
    public RedirectView eliminar(@PathVariable Long dni) {
        usuarioService.eliminar(dni);
        return new RedirectView("/usuarios");
    }
}
