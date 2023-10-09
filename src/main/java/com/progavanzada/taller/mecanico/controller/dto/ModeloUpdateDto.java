package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeloUpdateDto {
    @Size(min = 4, max = 32, message = "El nombre de una marca no puede superar los 32 caractéres.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Los nombres de una marca solo pueden tener letras, espacios y guiones.")
    public String name;
    
/**
* Probablemente no se deba poder actualizar el año del modelo.
* Revisar!!!!
* 
    @Size(max = 4, message = "El año del modelo no puede exceder los 4 caracteres de longitud")
    @Pattern(regexp = RegExPatterns.OnlyNumbers, message = "El año del modelo solo puede contener caracteres numéricos.")
    public Integer year;
*/
    
}