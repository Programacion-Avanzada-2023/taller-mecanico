package com.progavanzada.taller.mecanico.services.interfaces;

import com.progavanzada.taller.mecanico.controller.dto.MarcaCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.MarcaDto;
import com.progavanzada.taller.mecanico.controller.dto.MarcaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Marca;

/**
 * Métodos personalizados que debe integrar el repositorio de Marca.
 */
public interface IMarcaService {

    /**
     * Actualiza los campos de una marca aplicando el patrón DTO.
     *
     * @param dto El DTO a mappear contra la entidad.
     * @param entity La entidad a actualizar.
     *
     * @return La Marca con sus campos modificados.
     */
    MarcaDto actualizarMarca(MarcaUpdateDto dto, Marca entity);

    /**
     * Marca una entidad de marca como borrada.
     *
     * @param entity La entidad a borrar.
     *
     * @return Verdadero si fue borrada.
     */
    boolean borrarMarca(Marca entity);
    
    MarcaDto eliminarMarca(String id, MarcaDto dto);
    
    MarcaDto crearMarca(MarcaCreateDto dto);
}
