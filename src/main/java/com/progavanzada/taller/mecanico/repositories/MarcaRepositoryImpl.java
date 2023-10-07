package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.MarcaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Marca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;

/**
 *
 * @author Usuario
 */
public class MarcaRepositoryImpl implements MarcaRepositoryCustom {

    @Autowired
    @Lazy
    public MarcaRepository repo;

    @Override
    public Marca actualizarMarca(MarcaUpdateDto dto, Marca entity) {
        // Mappear campos a la entidad.
        entity.name = dto.name;

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
