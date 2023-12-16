/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.controller.dto;

import com.progavanzada.taller.mecanico.entities.pipes.IsDateAfterToday;

/**
 *
 * @author yukal
 */
public class ReservaCreateDto {
    public Integer client;
    
    public Integer tecnico;

    @IsDateAfterToday(message = "La fecha de inicio no puede estar en el pasado, o el formato es incorrecto (yyyy-mm-dd hh:mm:ss)")
    public String fechaInicio;

    @IsDateAfterToday(message = "La fecha de fin no puede estar en el pasado, o el formato es incorrecto (yyyy-mm-dd hh:mm:ss)")
    public String fechaFin;
}
