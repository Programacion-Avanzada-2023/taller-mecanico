/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.ServicioUpdateDto;
import com.progavanzada.taller.mecanico.entities.Servicio;

/**
 *
 * @author yukal
 */
public interface ServicioRepositoryCustom {

    Servicio actualizarServicio(ServicioUpdateDto dto, Servicio entity);

    boolean borrarServicio(Servicio entity);
}
