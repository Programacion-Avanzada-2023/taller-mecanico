package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.MarcaCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.MarcaDto;
import com.progavanzada.taller.mecanico.controller.dto.MarcaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class MarcaService {

    @Autowired
    public MarcaRepository repo;
    
    public MarcaDto mapMarcaToDto(Marca entity) {
        MarcaDto dto = new MarcaDto();
        dto.id = entity.id;
        dto.name = entity.name;
        dto.origen = entity.origen;
        dto.impuestoMarca = entity.impuestoMarca;
        
        return dto;
    }

    public MarcaDto actualizarMarca(MarcaUpdateDto dto, Marca entity) {
        // Mappear campos a la entidad.
        entity.origen = dto.origen != null ? dto.origen : entity.origen;
        entity.impuestoMarca = dto.impuestoMarca != null ? dto.impuestoMarca / 100 : entity.impuestoMarca;
        
        return this.mapMarcaToDto(this.repo.save(entity));
    }

    public boolean borrarMarca(Marca entity) {
        // Marcar su flag de borrado.
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }
    
    public MarcaDto crearMarca(MarcaCreateDto dto) {
        Marca marca = new Marca();
        marca.name = dto.name;
        marca.origen = dto.origen;
        marca.impuestoMarca = dto.impuestoMarca;
        
        this.repo.save(marca);
        
        return this.mapMarcaToDto(marca);
        
    }
}
