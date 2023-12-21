package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * Representa el DTO de una request para actualizar los campos de una Marca (selectivo).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarcaUpdateDto {
    @Size(min = 4, max = 32, message = "El origen de una marca no puede superar los 32 caractéres.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Los nombres de una marca solo pueden tener letras, espacios y guiones.")
    public String origen;

    @Max(value = 100, message = "El impuesto de una marca no puede superar el 100%.")
    @Min(value = 0, message = "El impuesto de una marca no puede ser menor a 0%.")
    public Integer impuestoMarca;
}
