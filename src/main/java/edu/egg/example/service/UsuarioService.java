package edu.egg.example.service;

import edu.egg.example.entity.Usuario;
import edu.egg.example.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final String MENSAJE_USERNAME = "No existe un usuario registrado con el correo %s";

    @Transactional
    public void crear(Long dni, String nombre, String apellido, LocalDate fechaNacimiento, String correo, String clave) throws Exception {
        if (usuarioRepository.existsById(dni)) {
            throw new Exception("Ya existe un usuario asociado con el DNI ingresado");
        }

        if (usuarioRepository.existsByCorreo(correo)) {
            throw new Exception("Ya existe un usuario asociado con el correo ingresado");
        }

        Usuario usuario = new Usuario();

        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setCorreo(correo);
        usuario.setClave(encoder.encode(clave));
        usuario.setAlta(true);

        usuarioRepository.save(usuario);
    }

    @Transactional
    public void modificar(Long dni, String nombre, String apellido, LocalDate fechaNacimiento, String correo, String clave) {
        usuarioRepository.modificar(dni, nombre, apellido, fechaNacimiento, correo, clave);
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

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(MENSAJE_USERNAME, correo)));
        return new User(usuario.getCorreo(), usuario.getClave(), Collections.emptyList());
    }
}
