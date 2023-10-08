package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.AutomovilUpdateDto;
import com.progavanzada.taller.mecanico.entities.Automovil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author Usuario
 */
public class AutomovilService implements AutomovilRepositoryCustom {
    
    @Autowired
    @Lazy
    public AutomovilRepository repo;
    
    @Override
    public Automovil actualizarAutomovil(AutomovilUpdateDto dto, Automovil entity){
        // Mappear campos a la entidad.
        entity.licensePlate = dto.licensePlate != null ? dto.licensePlate : entity.licensePlate;

        return this.repo.save(entity);
    }

    @Override
    public boolean borrarAutomovil(Automovil entity) {
        // Marcar su flag de borrado.
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }
    
}
