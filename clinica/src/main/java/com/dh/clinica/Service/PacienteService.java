package com.dh.clinica.Service;

import com.dh.clinica.Entity.Paciente;
import com.dh.clinica.Repository.Implementacion.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IEntityService<Paciente>{

    //======== ATRIBUTOS =========
    private final IPacienteRepository pacienteRepository;

    // ====== CONSTRUCTOR =======
    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    //=========METODOS=================
    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public String delete(Long id) {
        if(findById(id).isPresent()){
            pacienteRepository.deleteById(id);
            return "Paciente con el id: " +id + "ha sido eliminado";
        }else{
            return "Paciente no existe";
        }
    }

    @Override
    public String update(Paciente paciente) {
        Paciente pacienteUpdate = pacienteRepository.findById(paciente.getId()).get();
        pacienteUpdate.setNombre(paciente.getNombre());
        pacienteUpdate.setApellido(paciente.getApellido());
        pacienteUpdate.setDni(paciente.getDni());
        pacienteUpdate.setFechaIngreso(paciente.getFechaIngreso());
        pacienteUpdate.setDireccion(paciente.getDireccion());

        pacienteRepository.save(pacienteUpdate);

        return "El paciente con id: " + pacienteUpdate.getId() + "se ha actualizado";

    }


    // ===== UTILES =======
    public Paciente findPacienteByDNI(Integer dni){
        return pacienteRepository.findPacienteByDNI(dni);
    }
}
