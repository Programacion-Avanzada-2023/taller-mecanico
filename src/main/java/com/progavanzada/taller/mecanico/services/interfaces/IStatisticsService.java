package com.progavanzada.taller.mecanico.services.interfaces;

import com.progavanzada.taller.mecanico.controller.dto.EstadisticaServicioSolicitado;
import java.util.Date;
import java.util.List;

public interface IStatisticsService {
    /**
     * Genera una estadística con los servicios más solicitados por los clientes.
     *
     * @return Estadística con los servicios más solicitados por los clientes.
     */
    public List<EstadisticaServicioSolicitado> generarEstadisticaServiciosMasSolicitados();

    /**
     * Genera una estadística con los servicios más solicitados por los clientes.
     *
     * @param fechaInicio Fecha de inicio del rango de tiempo a considerar.
     * @param fechaFin    Fecha de fin del rango de tiempo a considerar.
     *
     * @return Estadística con los servicios más solicitados por los clientes.
     */
    public List<EstadisticaServicioSolicitado> generarEstadisticaServiciosMasSolicitados(
            Date fechaInicio, Date fechaFin);

    /**
     * Genera una estadística con los servicios más solicitados por los clientes.
     *
     * @param tecnico ID del técnico para el cuál se desea buscar las
     *                estadísticas.
     *
     * @return Estadística con los servicios más solicitados por los clientes.
     */
    public List<EstadisticaServicioSolicitado> generarEstadisticaServiciosMasSolicitados(
            Integer tecnico);
}
