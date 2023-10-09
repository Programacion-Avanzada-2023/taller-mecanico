/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.MarcaDto;
import com.progavanzada.taller.mecanico.controller.dto.ModeloCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ModeloDto;
import com.progavanzada.taller.mecanico.controller.dto.ModeloUpdateDto;
import com.progavanzada.taller.mecanico.entities.Marca;
import com.progavanzada.taller.mecanico.entities.Modelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Usuario
 */
@Service
public class ModeloService implements ModeloRepositoryCustom {
    
    @Autowired
    public ModeloRepository repo;
    
    @Autowired
    private MarcaRepository repoMarca;
    
    /**
     * Mappea una entidad de Modelo hacia su equivalente en DTO. (te odio java)
     *
     * @param modelo La entidad del Modelo.
     *
     * @return El DTO del Modelo.
     */
    public ModeloDto mapModeloToDto(Modelo modelo) {
        ModeloDto resDto = new ModeloDto();
        resDto.id = modelo.id;
        resDto.name = modelo.name;
        resDto.year = modelo.year;
        
        MarcaDto marcaDto = new MarcaDto();
        marcaDto.id = modelo.brand.id;
        marcaDto.name = modelo.brand.name;
        
        resDto.brand = marcaDto;
        return resDto;
    }
    
    @Override
    public ModeloDto actualizarModelo(ModeloUpdateDto dto, Modelo entity) {
        // Mappear campos a la entidad.
        entity.name = dto.name != null ? dto.name : entity.name;

        Modelo modelo = this.repo.save(entity);
        
        // Crear DTO para la devolución de la entidad.
        return this.mapModeloToDto(modelo);
    }
    
    @Override
    public boolean borrarModelo(Modelo entity){
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }
    
    @Override
    public ModeloDto crearModelo(ModeloCreateDto dto) {
        // Buscar la marca a la que apunta el modelo.
        Marca marca = this.repoMarca.findByIdAndEliminadoFalse(dto.brand);
        
        if (marca == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La marca especificada no existe.", null);
        }
        
        // Si existe, instanciar el nuevo modelo y guardarlo.
        Modelo modelo = new Modelo();
        modelo.name = dto.name;
        modelo.brand = marca;
        modelo.year = dto.year;
        
        this.repo.save(modelo);
        
        // Retornar el DTO.
        return this.mapModeloToDto(modelo);
    }
}
