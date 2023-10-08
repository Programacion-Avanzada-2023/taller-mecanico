package com.progavanzada.taller.mecanico.repositories;

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
    Automovil actualizarAutomovil(AutomovilUpdateDto dto, Automovil entity);
    
    /**
     * 
     * @param entity
     * @return 
     */
    boolean borrarAutomovil (Automovil entity);
}
