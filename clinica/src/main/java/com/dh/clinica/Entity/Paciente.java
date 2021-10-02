package com.dh.clinica.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Paciente {

    //==== ATRIBUTOS =======
    @Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "paciente_sequence")
    @Column(name = "idPaciente")
    private Long id;

    private String nombre;
    private String apellido;
    private Integer dni;
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;


    @OneToMany(mappedBy = "paciente", orphanRemoval = false)
    @JsonIgnore
    private Set<Turno> turnos;


    //==== GETTERS AND SETTERS =======

    public Long getId() {
        return id;
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

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    //==== CONSTRUCTOR =======


    public Paciente(String nombre, String apellido, Integer dni, LocalDate fechaIngreso, Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.direccion = direccion;
    }

    public Paciente() { }
}
