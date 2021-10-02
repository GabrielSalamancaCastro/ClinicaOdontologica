package com.dh.clinica.Repository.Implementacion;

import com.dh.clinica.Entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Query("SELECT o FROM Odontologo o WHERE o.matricula = ?1")
    Odontologo findOdontologoByMatricula(Integer matricula);

}
