/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author yukal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicioCreateDto {
    public String name;

    public String descripcion;

    public float precioUnitario;
}
