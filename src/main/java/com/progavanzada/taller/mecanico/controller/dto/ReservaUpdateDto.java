package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.progavanzada.taller.mecanico.entities.pipes.IsDateAfterToday;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaUpdateDto {
    @IsDateAfterToday(message = "La fecha de inicio no puede estar en el pasado, o el formato es incorrecto (yyyy-mm-dd hh:mm:ss)")
    @Temporal(TemporalType.TIMESTAMP)
    public String fechaInicio;
    
    @IsDateAfterToday(message = "La fecha de fin no puede estar en el pasado, o el formato es incorrecto (yyyy-mm-dd hh:mm:ss)")
    @Temporal(TemporalType.TIMESTAMP)
    public String fechaFin;
}
