package com.progavanzada.taller.mecanico.services;

import com.progavanzada.taller.mecanico.controller.dto.ServicioCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioUpdateDto;
import com.progavanzada.taller.mecanico.entities.Servicio;
import com.progavanzada.taller.mecanico.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        dto.name = entity.name;
        dto.descripcion = entity.descripcion;
        dto.precioUnitario = entity.precioUnitario;
        
        return dto;
    }

    //me pidio borrar los Override
    public ServicioDto actualizarServicio(ServicioUpdateDto dto, Servicio entity) {
        // Mappear campos a la entidad.
        entity.descripcion = dto.descripcion != null ? dto.descripcion : entity.descripcion;

        return this.mapServiceToDto(this.repo.save(entity));
    }
    
    //me pidio borrar los Override
    public boolean borrarServicio(Servicio entity) {
        // Marcar su flag de borrado.
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }
    
    public ServicioDto crearServicio(ServicioCreateDto dto) {
        Servicio servicio = new Servicio();
        servicio.name = dto.name;
        servicio.descripcion = dto.descripcion;
        servicio.precioUnitario = dto.precioUnitario;
        
        this.repo.save(servicio);
        
        return this.mapServiceToDto(servicio);
        
    }
}
