package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import com.progavanzada.taller.mecanico.entities.Tecnico;
import java.util.List;

public interface ITecnicoService {
    /**
     * Busca a todos los técnicos disponibles en el dominio.
     *
     * @return La lista de técnicos.
     */
    List<Tecnico> buscarTodos();

    /**
     * Realiza un cruce de información entre el técnico y todas las órdenes asignadas a él.
     *
     * @param id El identificador único del técnico.
     *
     * @return El listado de órdenes de trabajo asignadas a este técnico.
     */
    // List<OrdenDeTrabajo> buscarOrdenesDeTecnico(Integer id);
}
