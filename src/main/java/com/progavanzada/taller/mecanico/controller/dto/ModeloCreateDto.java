/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para crear nuevos Modelos.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeloCreateDto {

    /**
     * El nombre legal de la persona.
     */
    @Size(min = 4, max = 32, message = "El nombre de modelo no puede exceder los 32 caractéres en longitud.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Un nombre de modelo solo puede tener letras, espacios y guiones.")
    @NotNull
    public String name;

    /**
     * La marca a la cual pertenece este modelo.
     */
    @NotNull
    public Integer brand;

    /**
     * El año el cuál se impartió este modelo específico de automóvil.
     */
    @Min(value = 1800)
    @Max(value = 9999)
    public Integer year;
}
