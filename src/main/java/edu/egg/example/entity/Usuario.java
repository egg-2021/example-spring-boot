package edu.egg.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {
    
    @Id
    private Long dni;
    
    private String nombre;
    private String apellido;
    
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    @OneToMany(mappedBy = "duenio")
    private List<Mascota> mascotas;
    
    public Usuario() {
        this.mascotas = new ArrayList<>();
    }
    
    public Usuario(Long dni, String nombre, String apellido, Date fechaNacimiento, List<Mascota> mascotas) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.mascotas = mascotas;
    }
    
    public Long getDni() {
        return dni;
    }
    
    public void setDni(Long dni) {
        this.dni = dni;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public List<Mascota> getMascotas() {
        return mascotas;
    }
    
    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
