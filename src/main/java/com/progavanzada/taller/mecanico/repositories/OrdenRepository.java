package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yukal
 */
@Repository
public interface OrdenRepository extends JpaRepository<OrdenDeTrabajo, Integer> {
    public List<OrdenDeTrabajo> findByEliminadoFalse();
    
    public OrdenDeTrabajo findByIdAndEliminadoFalse();
    
}
