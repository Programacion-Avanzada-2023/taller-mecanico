package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface PersonaRepository extends JpaRepository<Cliente, Integer> {

}
