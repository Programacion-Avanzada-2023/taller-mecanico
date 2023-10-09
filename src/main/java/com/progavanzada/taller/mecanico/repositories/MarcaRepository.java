package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Marca;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para las entidades de Marca.
 */
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    public List<Marca> findByEliminadoFalse();

    public Marca findByIdAndEliminadoFalse(Integer id);
}
