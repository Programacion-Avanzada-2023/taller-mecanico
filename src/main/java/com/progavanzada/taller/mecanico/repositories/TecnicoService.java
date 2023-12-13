package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import com.progavanzada.taller.mecanico.entities.Tecnico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService implements ITecnicoService {
    @Autowired
    private TecnicoRepository repo;

    @Autowired
    private OrdenService ordenService;

    /**
     * Mappea una entidad de técnico hacia su DTO.
     *
     * @param entity La entidad.
     *
     * @return El DTO del técnico.
     */

    @Override
    public List<Tecnico> buscarTodos() {
        return this.repo.findByEliminadoFalse();
    }

    /* @Override
    public List<OrdenDeTrabajo> buscarOrdenesDeTecnico(Integer id) {
        Tecnico tecnico = this.repo.findByIdAndEliminadoFalse(id);

        if (tecnico == null)
            return null;

        return this.ordenService.buscarOrdenPorTecnico(id);
    } */
}
