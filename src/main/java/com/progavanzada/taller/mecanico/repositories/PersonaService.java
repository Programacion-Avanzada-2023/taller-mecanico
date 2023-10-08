package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.PersonaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Implementación de los métodos custom para el repositorio.
 */
@Service
public class PersonaService implements PersonaRepositoryCustom {

    @Autowired
    @Lazy
    public PersonaRepository repo;

    @Override
    public Persona actualizarPersona(PersonaUpdateDto dto, Persona entity) {
        // Mappear campos del DTO hacia la entidad.
        entity.name = dto.name != null ? dto.name : entity.name;
        entity.surName = dto.surName != null ? dto.surName : entity.surName;
        entity.street = dto.street != null ? dto.street : entity.street;
        entity.streetNumber = dto.streetNumber != null ? dto.streetNumber : entity.streetNumber;
        entity.phoneNumber = dto.phoneNumber != null ? dto.phoneNumber : entity.phoneNumber;
        entity.email = dto.email != null ? dto.email : entity.email;

        return this.repo.save(entity);
    }

    @Override
    public boolean borrarPersona(Persona entity) {
        // Marcar la flag de eliminada.
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }
}
