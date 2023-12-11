/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.sql.Timestamp;

/**
 *
 * @author yukal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaUpdateDto {
    @Temporal(TemporalType.TIMESTAMP)
    public Timestamp fechaInicio;
    
    //public String fechaFin;
}
