package com.progavanzada.taller.mecanico.services.interfaces;

import com.progavanzada.taller.mecanico.controller.dto.EstadisticaServicioSolicitado;
import java.util.List;

public interface IStatisticsService {
    /**
     * Genera una estadística con los servicios más solicitados por los clientes.
     *
     * @return Estadística con los servicios más solicitados por los clientes.
     */
    public List<EstadisticaServicioSolicitado> generarEstadisticaServiciosMasSolicitados();
}
