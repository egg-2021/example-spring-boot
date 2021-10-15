package edu.egg.example.service;

import edu.egg.example.entity.Mascota;
import edu.egg.example.repository.MascotaRepository;
import edu.egg.example.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void crear(String nombre, Long dniDuenio) {
        Mascota mascota = new Mascota();

        mascota.setNombre(nombre);
        mascota.setDuenio(usuarioRepository.findById(dniDuenio).orElse(null));

        mascotaRepository.save(mascota);
    }

    @Transactional
    public void modificar(Integer id, String nombre) {
        mascotaRepository.modificar(id, nombre);
    }

    @Transactional(readOnly = true)
    public List<Mascota> buscarTodas() {
        return mascotaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mascota buscarPorId(Integer id) {
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
        return mascotaOptional.orElse(null);
    }

    @Transactional
    public void eliminar(Integer id) {
        mascotaRepository.deleteById(id);
    }
}
