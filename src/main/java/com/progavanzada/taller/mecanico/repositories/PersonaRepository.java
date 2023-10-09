package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la mutación de entidades de Persona.
 */
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    public List<Persona> findByEliminadoFalse();

    public Persona findByIdAndEliminadoFalse(Integer id);

    public Persona findByDni(Integer dni);
}
