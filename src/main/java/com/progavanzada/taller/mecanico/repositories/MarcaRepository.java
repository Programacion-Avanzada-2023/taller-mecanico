package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    
}
