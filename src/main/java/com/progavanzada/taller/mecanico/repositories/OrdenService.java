package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.OrdenUpdateDto;
import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author yukal
 */
@Service
public class OrdenService implements OrdenRepositoryCustom {

    @Autowired
    @Lazy
    public OrdenRepository repo;

    @Override
    public OrdenDeTrabajo actualizarOrden(OrdenUpdateDto dto, OrdenDeTrabajo entity) {
        // Mappear campos a la entidad.
        //entity.name = dto.name != null ? dto.name : entity.name;

        return this.repo.save(entity);
    }

    @Override
    public boolean borrarOrden(OrdenDeTrabajo entity) {
        // Marcar su flag de borrado.
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }
}