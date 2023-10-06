/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yukal
 */
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    
}

