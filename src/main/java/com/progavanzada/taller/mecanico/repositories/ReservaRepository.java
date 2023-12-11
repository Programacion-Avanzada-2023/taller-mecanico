/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yukal
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    
    public List<Reserva> findByEliminadoFalse();

    public Reserva findByIdAndEliminadoFalse(Integer id);

}
