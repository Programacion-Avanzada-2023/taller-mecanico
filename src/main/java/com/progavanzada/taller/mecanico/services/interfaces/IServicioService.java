/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.progavanzada.taller.mecanico.services.interfaces;

import com.progavanzada.taller.mecanico.controller.dto.ServicioCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioUpdateDto;
import com.progavanzada.taller.mecanico.entities.Servicio;

/**
 *
 * @author yukal
 */
public interface IServicioService {

    ServicioDto actualizarServicio(ServicioUpdateDto dto, Servicio entity);

    boolean borrarServicio(Servicio entity);
    
    ServicioDto crearServicio(ServicioCreateDto dto);

    ServicioDto recuperarServicio(Servicio entity);
}
