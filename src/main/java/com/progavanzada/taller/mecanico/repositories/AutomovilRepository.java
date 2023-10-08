package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Automovil;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yukal
 */
@Repository
public interface AutomovilRepository extends JpaRepository<Automovil, Integer> {
    public List<Automovil> findByEliminadoFalse();
    
    public Automovil findByIdAndEliminadoFalse(Integer id);
}

