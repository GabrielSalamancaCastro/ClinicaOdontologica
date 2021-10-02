package com.dh.clinica.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Odontologo {

    /* =======ATRIBUTOS ==================== */

    @Id
    @SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
    @Column(name = "idOdontologo")
    private Long id;

    private String nombre;
    private String apellido;
    private Integer matricula;

    @OneToMany(mappedBy = "odontologo",  orphanRemoval = false)
    @JsonIgnore
    private Set<Turno> turnos;


    /* ======= CONSTRUCTORES ==================== */

    public Odontologo(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public Odontologo() {
    }

    /* ======= GETTERS AND SETTERS =============== */

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

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

}


