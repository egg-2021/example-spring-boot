package edu.egg.example.service;

import edu.egg.example.entity.Usuario;
import edu.egg.example.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void crear(Long dni, String nombre, String apellido, LocalDate fechaNacimiento) throws Exception {
        if (usuarioRepository.existsById(dni)) {
            throw new Exception("Ya existe un usuario con el DNI indicado");
        }

        Usuario usuario = new Usuario();

        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setAlta(true);

        usuarioRepository.save(usuario);
    }

    @Transactional
    public void modificar(Long dni, String nombre, String apellido, LocalDate fechaNacimiento) {
        usuarioRepository.modificar(dni, nombre, apellido, fechaNacimiento);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorDni(Long dni) {
        return usuarioRepository.findById(dni).orElse(null);
    }

    @Transactional
    public void habilitar(Long dni) {
        usuarioRepository.habilitar(dni);
    }

    @Transactional
    public void eliminar(Long dni) {
        usuarioRepository.deleteById(dni);
    }
}
