package com.dh.clinica.Controller;


import com.dh.clinica.Entity.Direccion;
import com.dh.clinica.Entity.Paciente;
import com.dh.clinica.Exception.BadRequestException;
import com.dh.clinica.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("pacientes")
public class PacienteController {

    //===== ATRIBUTOS =============
    private PacienteService pacienteService;

    Logger logger = Logger.getLogger(String.valueOf(PacienteController.class));

    // ===== CONSTRUCTOR ==========
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    // ====== METODO GET =======

    @GetMapping("")
    public List<Paciente> getAllPacientes(){
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public Paciente findPacienteById(@PathVariable Long id){
        return pacienteService.findById(id).orElse(null);
    }

    @GetMapping("documento/{dni}")
    public Paciente findPacienteByDNI(@PathVariable Integer dni){
        return pacienteService.findPacienteByDNI(dni);
    }

    // ===== METODO POST =======
    @PostMapping("/create")
    public ResponseEntity<?> createPaciente(@RequestBody Paciente paciente){
        ResponseEntity response;

        if (pacienteService.findPacienteByDNI(paciente.getDni()) != null){
            response = new ResponseEntity("El paciente ya existe, cambielo por favor", HttpStatus.CONFLICT);
            logger.info("El paciente ya existe, cambielo por favor");
        }else{
            response = new ResponseEntity(pacienteService.save(paciente),HttpStatus.OK);
        }

        return response;
    }

    // ====== METODO PUT ========
    @PutMapping("/update")
    public ResponseEntity<?> updatePaciente(@RequestBody Paciente paciente) throws BadRequestException {
        ResponseEntity response;
        if (pacienteService.findById(paciente.getId()).isPresent()){
            response = new ResponseEntity(pacienteService.update(paciente),HttpStatus.OK);
        }else{
            response = new ResponseEntity("El paciente con el id: " + paciente.getId() + "no existe", HttpStatus.NOT_FOUND);
            logger.info("Error al modificar un paciente, no se encontro!");
            throw new BadRequestException("Paciente no encontrado!");
        }
        return response;
    }

    // ====== METODO DELETE ======
    @DeleteMapping("/delete/{id}")
    public String deletePaciente(@PathVariable Long id){
        return pacienteService.delete(id);
    }


    // ========== METODO PARA TESTEAR ==============
    @PostMapping("/test")
    public List<Paciente> pacientesTest(){
        Direccion direccion1 = new Direccion("falsa", "1234","Recreo","Bogota");
        Paciente paciente1 = new Paciente("Gabriel","Salamanca",123456789, LocalDate.of(2021,07,20),direccion1);
        Direccion direccion2 = new Direccion("Murillo", "41","Colina","Bogota");
        Paciente paciente2 = new Paciente("Kevin","Martinez",111111, LocalDate.of(2021,05,10),direccion2);

        Direccion direccion3 = new Direccion("Rosales", "1234","San francisco","Bucaramanga");
        Paciente paciente3 = new Paciente("Ambar","Valencia",22222, LocalDate.of(2021,07,20),direccion3);
        Direccion direccion4 = new Direccion("Torres", "20","Tres Postes","Medellin");
        Paciente paciente4 = new Paciente("Sergio","Rueda",33333, LocalDate.of(2021,05,10),direccion4);

        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(pacienteService.save(paciente1));
        pacientes.add(pacienteService.save(paciente2));
        pacientes.add(pacienteService.save(paciente3));
        pacientes.add(pacienteService.save(paciente4));

        return pacientes;

    }






}
