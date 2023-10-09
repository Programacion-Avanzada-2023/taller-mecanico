package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.MarcaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Marca;

/**
 * Métodos personalizados que debe integrar el repositorio de Marca.
 */
public interface MarcaRepositoryCustom {

    /**
     * Actualiza los campos de una marca aplicando el patrón DTO.
     *
     * @param dto El DTO a mappear contra la entidad.
     * @param entity La entidad a actualizar.
     *
     * @return La Marca con sus campos modificados.
     */
    Marca actualizarMarca(MarcaUpdateDto dto, Marca entity);

    /**
     * Marca una entidad de marca como borrada.
     *
     * @param entity La entidad a borrar.
     *
     * @return Verdadero si fue borrada.
     */
    boolean borrarMarca(Marca entity);
}
