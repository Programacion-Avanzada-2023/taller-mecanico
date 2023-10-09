package com.progavanzada.taller.mecanico.controller.dto;

import java.util.List;

/**
 * DTO para crear nuevas ordenes de trabajo.
 *
 * @author Usuario
 */
public class OrdenCreateDto {

    public Integer automovil;
    public String detalles;
    public List<Integer> servicios;
}
