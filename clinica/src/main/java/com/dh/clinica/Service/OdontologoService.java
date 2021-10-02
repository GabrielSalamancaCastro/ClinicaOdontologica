package com.dh.clinica.Service;

import com.dh.clinica.Entity.Odontologo;
import com.dh.clinica.Repository.Implementacion.IOdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IEntityService<Odontologo>{

    /* ======== ATRIBUTOS ============== */
    private IOdontologoRepository odontologoRepository;


    /* ======== CONSTRUCTOR ============== */
    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    /* ======== METODOS OVERRIDE ============== */

    @Override
    public List<Odontologo> findAll() {
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> findById(Long id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public Odontologo save(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public String delete(Long id) {
        if(findById(id).isPresent()){
            odontologoRepository.deleteById(id);
            return "Odontologo con el id: " +id + "ha sido eliminado";
        }else{
            return "Odontolog no existe";
        }
    }


    @Override
    public String update(Odontologo odontologo) {
        Odontologo odontologoUpdate = odontologoRepository.findById(odontologo.getId()).get();
        odontologoUpdate.setNombre(odontologo.getNombre());
        odontologoUpdate.setApellido(odontologo.getApellido());
        odontologoUpdate.setMatricula(odontologo.getMatricula());

        odontologoRepository.save(odontologoUpdate);

        return "El odontologo con el id: " + odontologoUpdate.getId() + "se ha actualizado";
    }



    /* ======== METODOS ADICIONALES UTILES ============== */

    public Odontologo findOdontologoByMatricula(Integer matricula){
        return odontologoRepository.findOdontologoByMatricula(matricula);
    }


}
