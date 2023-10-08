package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
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
public interface OrdenRepository extends JpaRepository<OrdenDeTrabajo, Integer> {
    @Query("SELECT o FROM OrdenDeTrabajo o WHERE m.eliminado = false ORDER BY m.id")
    public List<OrdenDeTrabajo> buscarTodo();
   
    @Query("SELECT o FROM OrdenDeTrabajo o WHERE m.eliminado = false AND m.id = :id")
    public OrdenDeTrabajo buscarPorId(@Param("id") Integer id);
}
