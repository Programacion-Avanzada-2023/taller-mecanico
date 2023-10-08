package com.progavanzada.taller.mecanico.controller.dto;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class OrdenDto {
    public Integer id;
    public ModeloDto model;
    public ClienteDto client;
    public AutomovilDto automovil;
    public String detalles;
    public List<ServicioDto> servicios;
}
