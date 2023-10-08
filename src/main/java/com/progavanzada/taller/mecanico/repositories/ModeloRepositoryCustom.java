/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.ModeloUpdateDto;
import com.progavanzada.taller.mecanico.entities.Modelo;

/**
 * 
 */
public interface ModeloRepositoryCustom {
    /**
     * 
     * @param dto
     * @param entity
     * @return 
     */
    Modelo actualizarModelo(ModeloUpdateDto dto, Modelo entity);
    
    /**
     * 
     * @param entity
     * @return 
     */
    boolean borrarModelo(Modelo entity);
}
