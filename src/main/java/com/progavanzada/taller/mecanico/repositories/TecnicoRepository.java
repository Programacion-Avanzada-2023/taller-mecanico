package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Tecnico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    public List<Tecnico> findByEliminadoFalse();

    public Tecnico findByIdAndEliminadoFalse(Integer id);
}
