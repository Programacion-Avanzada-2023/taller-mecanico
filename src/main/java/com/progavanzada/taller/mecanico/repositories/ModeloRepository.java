package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Modelo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yukal
 */
@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
    public List<Modelo> findByEliminadoFalse();
    
    public Modelo findByIdAndEliminadoFalse();
}
