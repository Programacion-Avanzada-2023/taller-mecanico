package com.progavanzada.taller.mecanico.repositories;

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
public class MarcaService implements MarcaRepositoryCustom {

    @Autowired
    @Lazy
    public MarcaRepository repo;

    @Override
    public Marca actualizarMarca(MarcaUpdateDto dto, Marca entity) {
        // Mappear campos a la entidad.
        entity.name = dto.name != null ? dto.name : entity.name;

        return this.repo.save(entity);
    }

    @Override
    public boolean borrarMarca(Marca entity) {
        // Marcar su flag de borrado.
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }
}
