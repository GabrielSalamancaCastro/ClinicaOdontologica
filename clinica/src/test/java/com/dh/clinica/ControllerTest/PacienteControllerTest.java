package com.dh.clinica.ControllerTest;

import com.dh.clinica.Entity.Direccion;
import com.dh.clinica.Entity.Paciente;
import com.dh.clinica.Service.PacienteService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PacienteService pacienteService;

    Direccion dDefault;
    Paciente pDefault;

    @BeforeEach
    void init(){
        dDefault = new Direccion("Avenida Calamar", "200", "Chicago", "Illinois");
        pDefault = new Paciente("Andres", "Lopez", 313131, LocalDate.now(), dDefault);
    }


    @Test
    @Order(1)
    @DisplayName("Insertar registro mediante API en la tabla Paciente")
    void savePaciente(){

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/pacientes/registro")
                    .content(asJsonString(pDefault))
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andExpect(content().contentType("application/json"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(2)
    @DisplayName("Búsqueda por ID mediante API en la tabla Paciente")
    void findPacienteById() {

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", 1))
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.dni").value(pDefault.getDni()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String asJsonString(Object object){
        try{
            ObjectMapper objectMapper = getObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return objectMapper.writeValueAsString(object);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper().registerModule(new ParameterNamesModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }






}
