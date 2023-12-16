package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;

/**
 * DTO para actualizar Ordenes de Trabajo existentes.
 *
 * @author yukal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdenUpdateDto {

    public Boolean confirmada = false;

    @Size(max = 512, message = "El mensaje insertado supera los 512 caractéres.")
    public String detalles;
}
