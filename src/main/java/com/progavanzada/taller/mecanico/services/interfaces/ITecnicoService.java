package com.progavanzada.taller.mecanico.services.interfaces;

import com.progavanzada.taller.mecanico.controller.dto.TecnicoDto;
import java.util.List;

public interface ITecnicoService {
    /**
     * Busca a todos los técnicos disponibles en el dominio.
     *
     * @return La lista de técnicos.
     */
    List<TecnicoDto> buscarTodos();

    /**
     * Busca a un técnico por su identificador único.
     *
     * @param id El identificador único del técnico.
     *
     * @return El técnico encontrado.
     */
    TecnicoDto buscarPorId(Integer id);

    /**
     * Realiza un cruce de información entre el técnico y todas las órdenes asignadas a él.
     *
     * @param id El identificador único del técnico.
     *
     * @return El listado de órdenes de trabajo asignadas a este técnico.
     */
    // List<OrdenDeTrabajo> buscarOrdenesDeTecnico(Integer id);
}
