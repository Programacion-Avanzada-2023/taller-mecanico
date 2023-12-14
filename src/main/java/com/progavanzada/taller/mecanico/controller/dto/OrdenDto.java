package com.progavanzada.taller.mecanico.controller.dto;

import java.util.List;

/**
 * DTO que representa una Orden de Trabajo en la API RESTful.
 */
public class OrdenDto {

    public String id;
    public AutomovilDto automovil;
    public ClienteDto cliente;
    public String detalles;
    public List<ServicioDto> servicios;
    public String fechaCreacion;
    public String fechaModificacion;
}
