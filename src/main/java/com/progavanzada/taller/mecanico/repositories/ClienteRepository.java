package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yukal
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, ClienteRepositoryCustom {
    @Query("SELECT c FROM Cliente c WHERE c.eliminado = false ORDER BY c.id")
    public List<Cliente> buscarTodo();
   
    @Query("SELECT c FROM Marca c WHERE c.eliminado = false AND c.id = :id")
    public Cliente buscarPorId(@Param("id") Integer id);
    
}
