package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.ServicioDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioUpdateDto;
import com.progavanzada.taller.mecanico.entities.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author yukal
 */
@Service
public class ServicioService {
    @Autowired
    public ServicioRepository repo;
    
    /**
     * Mappea una entidad de Servicio a su DTO.
     *
     * @param entity La entidad de Servicio.
     *
     * @return El DTO del servicio.
     */
    public ServicioDto mapServiceToDto(Servicio entity) {
        ServicioDto dto = new ServicioDto();
        dto.id = entity.id;
        dto.descripcion = entity.descripcion;
        
        return dto;
    }

    //me pidio borrar los Override
    public Servicio actualizarServicio(ServicioUpdateDto dto, Servicio entity) {
        // Mappear campos a la entidad.
        entity.descripcion = dto.descripcion != null ? dto.descripcion : entity.descripcion;

        return this.repo.save(entity);
    }
    
    //me pidio borrar los Override
    public boolean borrarServicio(Servicio entity) {
        // Marcar su flag de borrado.
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }
}
