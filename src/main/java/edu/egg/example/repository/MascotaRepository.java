package edu.egg.example.repository;

import edu.egg.example.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

    @Modifying
    @Query("UPDATE Mascota m SET m.nombre = :nombre WHERE m.id = :id")
    void modificar(@Param("id") Integer id, @Param("nombre") String nombre);
}
