package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Marca;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para las entidades de Marca.
 */
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer>, MarcaRepositoryCustom {
    @Query("SELECT m FROM Marca m WHERE m.eliminado = false ORDER BY m.id")
    public List<Marca> buscarTodo();
   
    @Query("SELECT m FROM Marca m WHERE m.eliminado = false AND m.id = :id")
    public Marca buscarPorId(@Param("id") Integer id);
}
