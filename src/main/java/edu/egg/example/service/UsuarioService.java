package edu.egg.example.service;

import edu.egg.example.entity.Usuario;
import edu.egg.example.repository.UsuarioRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void crear(Long dni, String nombre, String apellido, Date fechaNacimiento) {
        Usuario usuario = new Usuario();

        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setFechaNacimiento(fechaNacimiento);

        usuarioRepository.save(usuario);
    }

    @Transactional
    public void modificar(Long dni, String nombre, String apellido, Date fechaNacimiento) {
        usuarioRepository.modificar(dni, nombre, apellido, fechaNacimiento);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorDni(Long dni) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(dni);
        return usuarioOptional.orElse(null);
    }

    @Transactional
    public void eliminar(Long dni) {
        usuarioRepository.deleteById(dni);
    }
}
