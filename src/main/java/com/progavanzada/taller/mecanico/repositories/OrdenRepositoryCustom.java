package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.OrdenCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenServicioDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenUpdateDto;
import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import java.util.List;

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
    OrdenDto actualizarOrden(OrdenUpdateDto dto, OrdenDeTrabajo entity);

    /**
     * Marca una entidad de Orden como borrada.
     *
     * @param entity La entidad a borrar.
     *
     * @return Verdadero si fue borrada.
     */
    boolean borrarOrden(OrdenDeTrabajo entity);

    /**
     * Agrega un nuevo servicio a una orden de trabajo existente.
     *
     * @param id El UUID de la orden a la que se agregará el servicio.
     * @param dto DTO de agregado de servicio.
     *
     * @return La orden de trabajo con el nuevo servicio agregado.
     */
    OrdenDto agregarServicio(String id, OrdenServicioDto dto);

    /**
     * Elimina un servicio existente de una orden de trabajo.
     *
     * @param id El UUID de la orden a la que se le eliminará el servicio.
     * @param dto DTO de eliminado de servicio.
     *
     * @return La orden de trabajo con el servicio eliminado de la lista.
     */
    OrdenDto eliminarServicio(String id, OrdenServicioDto dto);

    /**
     * Crea una nueva orden de trabajo.
     *
     * @param dto DTO para crear una nueva orden.
     *
     * @return La orden de trabajo creada.
     */
    OrdenDto crearOrden(OrdenCreateDto dto);

    /**
     * Busca órdenes de trabajo basadas en el técnico asignado a ellas.
     *
     * @param id El identificador único del técnico.
     *
     * @return La lista de órdenes de trabajo asignadas a este técnico.
     */
    // List<OrdenDeTrabajo> buscarOrdenPorTecnico(Integer id);
}
