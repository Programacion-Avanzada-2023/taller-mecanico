/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.ModeloCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ModeloDto;
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
    ModeloDto actualizarModelo(ModeloUpdateDto dto, Modelo entity);
    
    /**
     * 
     * @param entity
     * @return 
     */
    boolean borrarModelo(Modelo entity);
    
    /**
     * Crea un nuevo modelo en la base de datos.
     *
     * @param dto El DTO para la creación.
     * 
     * @return El modelo creado.
     */
    ModeloDto crearModelo(ModeloCreateDto dto);
}
