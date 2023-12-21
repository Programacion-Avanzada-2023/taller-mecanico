package com.progavanzada.taller.mecanico.controller.dto;

import com.progavanzada.taller.mecanico.entities.CambioEstadoOrden;
import java.sql.Timestamp;
import java.util.List;

/**
 * DTO que representa una Orden de Trabajo en la API RESTful.
 */
public class OrdenDto {

    public String id;

    public Boolean confirmada;

    public AutomovilDto automovil;

    public String estado;

    public String detalles;

    public List<ServicioDto> servicios;

    public TecnicoDto tecnico;

    public Timestamp fechaCreacion;

    public Timestamp fechaModificacion;

    public List<CambioEstadoOrden> cambiosEstado;
}
