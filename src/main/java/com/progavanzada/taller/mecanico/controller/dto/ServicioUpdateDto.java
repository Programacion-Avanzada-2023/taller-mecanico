package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author yukal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicioUpdateDto {
    @Size(min = 4, max = 32, message = "La Descripcion de una orden no puede superar los 32 caractéres.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Los nombres de una marca solo pueden tener letras, espacios y guiones.")
    public String descripcion;
    
}
