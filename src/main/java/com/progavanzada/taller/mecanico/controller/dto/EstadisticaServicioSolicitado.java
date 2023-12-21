package com.progavanzada.taller.mecanico.controller.dto;

import java.util.Date;

/**
 * Instancia unívoca de una solicitud de servicio específico.
 */
public class EstadisticaServicioSolicitado {
    /**
     * ID único del servicio recontado.
     */
    public Integer idServicio;

    /**
     * Nombre del servicio.
     */
    public String nombreServicio;

    /**
     * La cantidad de técnicos que han realizado este servicio.
     */
    public Long cantidadTecnicos;

    /**
     * Cantidad de veces que se ha solicitado el servicio.
     */
    public Long cantidadSolicitudes;

    /**
     * Fecha de la última vez que se solicitó el servicio.
     */
    public Date fechaUltimaSolicitud;

    /**
     * Fecha de la primera vez que se solicitó el servicio.
     */
    public Date fechaPrimeraSolicitud;

    public EstadisticaServicioSolicitado(Integer idServicio, String nombreServicio,
            Long cantidadSolicitudes, Date fechaUltimaSolicitud,
            Date fechaPrimeraSolicitud, Long cantidadTecnicos) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.cantidadSolicitudes = cantidadSolicitudes;
        this.fechaUltimaSolicitud = fechaUltimaSolicitud;
        this.fechaPrimeraSolicitud = fechaPrimeraSolicitud;
        this.cantidadTecnicos = cantidadTecnicos;
    }
}
