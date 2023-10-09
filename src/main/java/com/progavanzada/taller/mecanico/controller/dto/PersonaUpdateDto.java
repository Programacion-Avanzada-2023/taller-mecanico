package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import com.progavanzada.taller.mecanico.entities.pipes.PhoneNumberConstraint;
import com.progavanzada.taller.mecanico.entities.pipes.PhoneNumberConverter;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO que denota los campos modificables de Persona a través de REST.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaUpdateDto {

    @Size(min = 2, max = 32, message = "El nombre no puede exceder los 32 caractéres en longitud.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Un nombre solo puede tener letras, espacios y guiones.")
    public String name;

    @Size(min = 2, max = 32, message = "El apellido no puede exceder los 32 caractéres en longitud.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Un apellido solo puede tener letras, espacios y guiones.")
    public String surName;

    @Size(min = 2, max = 64, message = "Una calle no puede tener más de 64 caracteres.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Un nombre de calle solo puede tener letras, espacios y guiones.")
    public String street;
    
    @Min(value = 0)
    @Max(value = 9_999)
    public Integer streetNumber;
    
    @PhoneNumberConstraint(isoCode = "AR")
    @Convert(converter = PhoneNumberConverter.class)
    public String phoneNumber;
   
    @Size(min = 2, max = 64, message = "El correo electrónico introducido es demasiado largo.")
    @Pattern(regexp = RegExPatterns.EmailAddress, message = "El correo electrónico introducido es inválido.")
    public String email;
}
