package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.OrdenUpdateDto;
import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;

/**
 *
 * @author yukal
 */
public interface OrdenRepositoryCustom {
    
    /**
     * Actualiza los campos de una marca aplicando el patrón DTO.
     *
     * @param dto El DTO a mappear contra la entidad.
     * @param entity La entidad a actualizar.
     *
     * @return La Orden con sus campos modificados.
     */
    OrdenDeTrabajo actualizarOrden(OrdenUpdateDto dto, OrdenDeTrabajo entity);

    /**
     * Marca una entidad de Orden como borrada.
     *
     * @param entity La entidad a borrar.
     *
     * @return Verdadero si fue borrada.
     */
    boolean borrarOrden(OrdenDeTrabajo entity);

}
