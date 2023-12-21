package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;

/**
 *
 * @author yukal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicioUpdateDto {
    @Size(min = 4, max = 32, message = "La Descripcion de una orden no puede superar los 32 caractéres.")
    public String descripcion;
}
