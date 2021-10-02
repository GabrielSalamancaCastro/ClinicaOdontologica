package com.dh.clinica.Entity;

import javax.persistence.*;

@Entity
@Table
public class Direccion {

    //==== ATRIBUTOS ======
    @Id
    @SequenceGenerator(name = "direccion_sequence", sequenceName = "direccion_sequence")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "direccion_sequence")
    @Column(name = "idDireccion")
    private Long id;

    private String calle;
    private String numero;
    private String barrio;
    private String ciudad;

    //=== GETTERS AND SETTERS ====


    public Long getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    // ===== CONSTRUCTOR =======


    public Direccion(String calle, String numero, String barrio, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.barrio = barrio;
        this.ciudad = ciudad;
    }

    public Direccion() { }
}
