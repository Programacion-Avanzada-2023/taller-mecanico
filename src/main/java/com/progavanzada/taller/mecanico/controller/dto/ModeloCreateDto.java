/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO para crear nuevos Modelos.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeloCreateDto {

    public String name;

    public Integer brand;

    public Integer year;
}
