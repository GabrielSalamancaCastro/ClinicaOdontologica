package com.dh.clinica.Controller;


import com.dh.clinica.Entity.Odontologo;
import com.dh.clinica.Exception.BadRequestException;
import com.dh.clinica.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;
    Logger logger = Logger.getLogger(String.valueOf(PacienteController.class));

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }



    /* ======== METODOS GET ============== */

    @GetMapping("")
    public List<Odontologo> getAllOdontologos(){
        return odontologoService.findAll();
    }

    @GetMapping("/{id}")
    public Odontologo getOdontologoById(@PathVariable Long id){
        return odontologoService.findById(id).orElse(null);
    }

    @GetMapping("/matricula/{matricula}")
    public Odontologo getOdontologoByMatricula(@PathVariable Integer matricula){
        return odontologoService.findOdontologoByMatricula(matricula);
    }


    /* ======== METODOS POST ============== */

    @PostMapping("/create")
        public ResponseEntity<?> createPaciente(@RequestBody Odontologo odontologo){
            ResponseEntity response;

            if (odontologoService.findOdontologoByMatricula(odontologo.getMatricula()) != null){
                response = new ResponseEntity("El odontologo ya existe, cambielo por favor", HttpStatus.CONFLICT);
                logger.info("El odontolog ya existe, cambielo por favor");
            }else{
                response = new ResponseEntity(odontologoService.save(odontologo),HttpStatus.OK);
            }
            return response;
    }

    /* ======== METODOS DELETE ============== */

    @DeleteMapping("/delete/{id}")
    public String deleteOdontologo(@PathVariable Long id){
        return odontologoService.delete(id);
    }

    /* ======== METODOS PUT ============== */
    @PutMapping("update")
    public ResponseEntity<?> updatePaciente(@RequestBody Odontologo odontologo) throws BadRequestException {
        ResponseEntity response;
        if (odontologoService.findById(odontologo.getId()).isPresent()){
            response = new ResponseEntity(odontologoService.update(odontologo),HttpStatus.OK);
        }else{
            response = new ResponseEntity("El Odontologo con el id: " + odontologo.getId() + "no existe", HttpStatus.NOT_FOUND);
            logger.info("Error al modificar un odontologo, no se encontro!");
            throw new BadRequestException("Odontologo no encontrado!");
        }
        return response;
    }

    /* ======== TEST ============== */

    @PostMapping("/test")
    public List<Odontologo> odontologoTest(){
        Odontologo odontologo1 = new Odontologo("Henry", "Salomon", 1234);
        Odontologo odontologo2 = new Odontologo("Luciana", "Murga", 1231);
        Odontologo odontologo3 = new Odontologo("Linda", "Castro", 1232);
        Odontologo odontologo4 = new Odontologo("Ana", "Mayorga", 1233);
        Odontologo odontologo5 = new Odontologo("Nadina", "Hunt", 1235);

        List<Odontologo> odontologos = new ArrayList<>();

        odontologos.add(odontologoService.save(odontologo1));
        odontologos.add(odontologoService.save(odontologo2));
        odontologos.add(odontologoService.save(odontologo3));
        odontologos.add(odontologoService.save(odontologo4));
        odontologos.add(odontologoService.save(odontologo5));

        return odontologos;

    }










}
