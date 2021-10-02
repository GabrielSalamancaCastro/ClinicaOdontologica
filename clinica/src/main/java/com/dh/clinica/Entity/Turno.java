package com.dh.clinica.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Turno {

    /* ================= ATRIBUTOS ====================*/
    @Id
    @SequenceGenerator(name= "turno_sequence", sequenceName = "turno_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    @Column(name = "idTurno")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "odontologo_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Odontologo odontologo;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime fechaHora;

    /* ================= CONSTRUCTORES====================*/

    public Turno(Odontologo odontologo, Paciente paciente, LocalDateTime fechaHora) {
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fechaHora = fechaHora;
    }

    public Turno() { }

    /* ================= GETTERS AND SETTERS====================*/

    public Long getId() {
        return id;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }




}
