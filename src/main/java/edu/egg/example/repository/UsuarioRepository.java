package edu.egg.example.repository;

import edu.egg.example.entity.Usuario;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Modifying
    @Query("UPDATE Usuario u SET u.nombre = :nombre, u.apellido = :apellido, u.fechaNacimiento = :fechaNacimiento WHERE u.dni = :dni")
    void modificar(@Param("dni") Long dni, @Param("nombre") String nombre, @Param("apellido") String apellido, @Param("fechaNacimiento") LocalDate fechaNacimiento);

    @Modifying
    @Query("UPDATE Usuario u SET u.alta = true WHERE u.dni = :dni")
    void habilitar(@Param("dni") Long dni);

    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Usuario buscarUsuarioPorUsername(@Param("username") String username);

    Optional<Usuario> findByUsername(String username);
}
