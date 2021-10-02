package com.dh.clinica.Controller;


import com.dh.clinica.Entity.Direccion;
import com.dh.clinica.Entity.Odontologo;
import com.dh.clinica.Entity.Paciente;
import com.dh.clinica.Entity.Turno;
import com.dh.clinica.Exception.BadRequestException;
import com.dh.clinica.Service.OdontologoService;
import com.dh.clinica.Service.PacienteService;
import com.dh.clinica.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;


@CrossOrigin
@RestController
@RequestMapping("turnos")
public class TurnoController {

    Logger logger = Logger.getLogger(String.valueOf(PacienteController.class));

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private Direccion direccion;



    /* ======== METODOS GET ============== */
    @GetMapping("")
    public List<Turno> getAllTurnos(){
        return turnoService.findAll();
    }

    @GetMapping("/{id}")
    public Turno getTurnoById(@PathVariable Long id){
        return turnoService.findById(id).orElse(null);
    }

    /* ======== METODOS POST ============== */
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody Turno turno) throws BadRequestException {
        ResponseEntity response;

        if(pacienteService.findById(turno.getPaciente().getId()).isPresent() && odontologoService.findById(turno.getOdontologo().getId()).isPresent()) {
            response = ResponseEntity.ok(turnoService.save(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            logger.info("Error al registrar un turno, paciente no encontrado");
            throw new BadRequestException("Paciente no encontrado!");
        }
        return response;
    }

    /* ======== METODOS DELETE ============== */
    @DeleteMapping("/delete/{id}")
    public String deleteTurno(@PathVariable Long id){
        return turnoService.delete(id);
    }

    /* ======== METODOS TEST ============== */

    @PostMapping("/test")
    public Turno test(){
       Direccion direccion= new Direccion("Avenida", "123", "Los tres postes", "Barranquilla");
       Paciente paciente = new Paciente("Andres","Castro",1111, LocalDate.of(2020,10,10),direccion);
       Odontologo odontologo = new Odontologo("Simon", "Sueldo", 12345);
       Turno turno1 = new Turno(odontologo, paciente, LocalDateTime.of(2021,2,15,8,30 ));

       turnoService.save(turno1);

        return turno1;
    }



    }












