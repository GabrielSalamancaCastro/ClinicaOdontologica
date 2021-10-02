package com.dh.clinica.ServiceTest;


import com.dh.clinica.Entity.Direccion;
import com.dh.clinica.Entity.Paciente;
import com.dh.clinica.Service.PacienteService;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;
    Paciente pDefault;

    @BeforeEach
    void init(){
        pDefault = new Paciente("Teofilo", "Gutierrez", 12121212, LocalDate.now(), new Direccion("Cordialidad", "42", "La chinita", "Barranquilla"));
    }

    @Test
    @Order(1)
    @DisplayName("Insertar registro -> Paciente")
    void savePaciente(){
        Paciente pac = pacienteService.save(pDefault);
        Assertions.assertNotNull(pac);
        Assertions.assertNotNull(pac.getId());
    }


    @Test
    @Order(2)
    @DisplayName("Búsqueda por ID -> Paciente")
    void findPacienteById() {
        Paciente pac = pacienteService.findById(1L).orElse(null);
        Assertions.assertNotNull(pac);
        Assertions.assertNotNull(pac.getId());
        Assertions.assertTrue(pac instanceof Paciente);
    }

    @Test
    @Order(3)
    @DisplayName("Eliminar registro -> Paciente")
    void deletePaciente() {
        Paciente pac = pacienteService.findById(1L).orElse(null);
        Assertions.assertNotNull(pac);
        pacienteService.delete(pac.getId());
        Paciente resultado = pacienteService.findById(1L).orElse(null);Assertions.assertNull(resultado);
    }

    @Test
    @Order(4)
    @DisplayName("Listar todos -> Paciente")
    public void listAllOdontologosTest( ) {
        Paciente pac2Test = new Paciente("Juan", "Pérez", 30356178, LocalDate.now(), new Direccion("Calle Siempre viva", "454", "Sydney", "Sydney"));
        pacienteService.save(pDefault);
        pacienteService.save(pac2Test);
        List<Paciente> pacientes = pacienteService.findAll();
        Assert.assertTrue( !pacientes.isEmpty() );
        Assert.assertTrue( pacientes.size() == 2 );
    }





}
