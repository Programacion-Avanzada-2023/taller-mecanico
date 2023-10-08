package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.AutomovilCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.AutomovilDto;
import com.progavanzada.taller.mecanico.controller.dto.AutomovilUpdateDto;
import com.progavanzada.taller.mecanico.entities.Automovil;

/**
 *
 * @author Usuario
 */
public interface AutomovilRepositoryCustom {
    /**
     * 
     * @param dto
     * @param entity
     * @return 
     */
    AutomovilDto actualizarAutomovil(AutomovilUpdateDto dto, Automovil entity);
    
    /**
     * 
     * @param entity
     * @return 
     */
    boolean borrarAutomovil (Automovil entity);
    
    /**
     * Crear un nuevo automovil del dominio.
     *
     * @param dto El DTO de creacion.
     * 
     * @return El automovil creado.
     */
    AutomovilDto crearAutomovil(AutomovilCreateDto dto);
}
