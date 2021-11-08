package edu.egg.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE usuario SET alta = false WHERE dni = ?")
public class Usuario {

    @Id
    private Long dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    @OneToMany(mappedBy = "duenio")
    private List<Mascota> mascotas;

    private Boolean alta;
}
