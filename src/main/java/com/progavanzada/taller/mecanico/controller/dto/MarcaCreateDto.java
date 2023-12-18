/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 *
 * @author yukal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarcaCreateDto {
    public String name;
    public String origen;

    @Max(value = 100, message = "El impuesto de la marca no puede ser mayor al 100%")
    @Min(value = 0, message = "El impuesto de la marca no puede ser menor al 0%")
    public Integer impuestoMarca;
}
