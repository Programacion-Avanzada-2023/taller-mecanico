package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.CambioEstadoOrden;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioEstadoOrdenRepository extends JpaRepository<CambioEstadoOrden, Integer> {
    public List<CambioEstadoOrden> findByOrdenId(String id);
}
