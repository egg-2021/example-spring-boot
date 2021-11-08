package edu.egg.example.service;

import edu.egg.example.entity.Rol;
import edu.egg.example.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Transactional
    public void crear(String nombre) {
        Rol rol = new Rol();
        rol.setNombre(nombre);
        rolRepository.save(rol);
    }

    @Transactional(readOnly = true)
    public List<Rol> buscarTodos() {
        return rolRepository.findAll();
    }
}
