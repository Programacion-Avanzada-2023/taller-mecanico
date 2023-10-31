/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.ServicioCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioUpdateDto;
import com.progavanzada.taller.mecanico.entities.Servicio;

/**
 *
 * @author yukal
 */
public interface ServicioRepositoryCustom {

    ServicioDto actualizarServicio(ServicioUpdateDto dto, Servicio entity);

    boolean borrarServicio(Servicio entity);
    
    ServicioDto eliminarServicio(String id, ServicioDto dto);
    
    ServicioDto crearServicio(ServicioCreateDto dto);
}
