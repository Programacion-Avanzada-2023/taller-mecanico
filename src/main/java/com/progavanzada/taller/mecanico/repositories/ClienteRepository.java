package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yukal
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    public List<Cliente> findByEliminadoFalse();

    public Cliente findByIdAndEliminadoFalse(Integer id);
}
