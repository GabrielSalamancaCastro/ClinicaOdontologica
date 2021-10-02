package com.dh.clinica.Service;

import com.dh.clinica.Entity.Turno;
import com.dh.clinica.Repository.Implementacion.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements IEntityService<Turno>{


    /* ======== ATRIBUTOS ============== */
    private ITurnoRepository turnoRepository;

    /* ======== CONSTRUCTOS ============== */

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    /* ======== METODOS OVERRIDE ============== */
    @Override
    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    @Override
    public Optional<Turno> findById(Long id) {
        return turnoRepository.findById(id);
    }

    @Override
    public Turno save(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public String delete(Long id) {
        if(findById(id).isPresent()){
            turnoRepository.deleteById(id);
            return "Turno con el id: " +id + "ha sido eliminado";
        }else{
            return "Turno no existe";
        }
    }

    @Override
    public String update(Turno turno) {
        Turno turnoUpdate = turnoRepository.findById(turno.getId()).get();

        turnoRepository.save(turnoUpdate);
        return "El turno ha sido actualizado";
    }
}
