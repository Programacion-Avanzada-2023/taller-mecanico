/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.ModeloUpdateDto;
import com.progavanzada.taller.mecanico.entities.Modelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author Usuario
 */
public class ModeloService implements ModeloRepositoryCustom {
    
    @Autowired
    @Lazy
    public ModeloRepository repo;
    
    @Override
    public Modelo actualizarModelo(ModeloUpdateDto dto, Modelo entity) {
        // Mappear campos a la entidad.
        entity.name = dto.name != null ? dto.name : entity.name;

        return this.repo.save(entity);
    }
    
    public boolean borrarModelo(Modelo entity){
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }
}
