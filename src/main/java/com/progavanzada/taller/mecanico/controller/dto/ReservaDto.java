/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.controller.dto;

import java.sql.Timestamp;

/**
 *
 * @author yukal
 */
public class ReservaDto {
    public Integer id;
    public ClienteDto client;
    public TecnicoDto tecnico; 
    public Timestamp fechaInicio;
    public Timestamp fechaFin;
}
