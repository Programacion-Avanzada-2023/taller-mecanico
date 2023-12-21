/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author Usuario
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutomovilUpdateDto {
    @Pattern(regexp = RegExPatterns.ArgentineLicensePlate, message = "El formato introducido de patente no es válido.")
    public String licensePlate;
    
    public String km;
}
