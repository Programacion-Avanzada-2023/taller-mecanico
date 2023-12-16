package com.progavanzada.taller.mecanico.services.interfaces;

import com.progavanzada.taller.mecanico.controller.dto.PersonaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Persona;

/**
 * Métodos personalizados para el repositorio de Persona.
 */
public interface IPersonaService {
    /**
     * Actualiza los campos de una persona aplicando el patrón DTO.
     *
     * @param dto El DTO a mappear contra la entidad.
     * @param entity La entidad a actualizar.
     *
     * @return La Persona con sus campos modificados.
     */
    Persona actualizarPersona(PersonaUpdateDto dto, Persona entity);
    
    /**
     * Marca una entidad de persona como borrada.
     *
     * @param entity La entidad a borrar.
     *
     * @return Verdadero si fue borrada.
     */
    boolean borrarPersona(Persona entity);
}
