package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yukal
 */
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    public List<Servicio> findByEliminadoFalse();
    
    public List<Servicio> findByEliminadoFalseAndIdIn(List<Integer> ids);
    
    public Servicio findByIdAndEliminadoFalse(Integer id);
    
    //public List<Servicio> findByOrderByCantidadSolicitudesDesc(Pageable pageable);
    
}

