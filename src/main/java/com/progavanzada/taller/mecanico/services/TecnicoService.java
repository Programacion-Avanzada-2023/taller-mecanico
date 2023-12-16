package com.progavanzada.taller.mecanico.services;

import com.progavanzada.taller.mecanico.controller.dto.PersonaDto;
import com.progavanzada.taller.mecanico.controller.dto.TecnicoDto;
import com.progavanzada.taller.mecanico.entities.Tecnico;
import com.progavanzada.taller.mecanico.repositories.TecnicoRepository;
import com.progavanzada.taller.mecanico.services.interfaces.ITecnicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService implements ITecnicoService {
    @Autowired
    private TecnicoRepository repo;

    @Autowired
    private OrdenService ordenService;

    /**
     * Mappea una entidad de técnico hacia su DTO.
     *
     * @param entity La entidad.
     *
     * @return El DTO del técnico.
     */
    public TecnicoDto mapTecnicoToDto(Tecnico entity) {
        PersonaDto persona = new PersonaDto();
        persona.id = entity.person.id;
        persona.name = entity.person.name;
        persona.surName = entity.person.surName;
        persona.dni = entity.person.dni;
        persona.street = entity.person.street;
        persona.streetNumber = entity.person.streetNumber;
        persona.phoneNumber = entity.person.phoneNumber;
        persona.email = entity.person.email;

        TecnicoDto dto = new TecnicoDto();
        dto.id = entity.id;
        dto.person = persona;

        return dto;
    }

    @Override
    public Tecnico buscarPorId(Integer id) {
        return this.repo.findByIdAndEliminadoFalse(id);
    }

    @Override
    public List<Tecnico> buscarTodos() {
        return this.repo.findByEliminadoFalse();
    }

    /* @Override
    public List<OrdenDeTrabajo> buscarOrdenesDeTecnico(Integer id) {
        Tecnico tecnico = this.repo.findByIdAndEliminadoFalse(id);

        if (tecnico == null)
            return null;

        return this.ordenService.buscarOrdenPorTecnico(id);
    } */
}
