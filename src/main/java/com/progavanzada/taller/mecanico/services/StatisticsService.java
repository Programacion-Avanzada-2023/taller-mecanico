package com.progavanzada.taller.mecanico.services;

import com.progavanzada.taller.mecanico.controller.dto.EstadisticaServicioSolicitado;
import com.progavanzada.taller.mecanico.repositories.ServicioRepository;
import com.progavanzada.taller.mecanico.services.interfaces.IStatisticsService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService implements IStatisticsService {
    @Autowired
    private ServicioRepository repo;

    private List<EstadisticaServicioSolicitado> construirEstadisticasDesdeAnonimo(List<Object[]> objeto) {
        List<EstadisticaServicioSolicitado> estadisticas = new ArrayList<>();

        for (Object[] result : objeto) {
            EstadisticaServicioSolicitado estadistica = new EstadisticaServicioSolicitado(
                    (Integer) result[0], // idServicio
                    (String) result[1], // nombreServicio
                    (Long) result[2], // cantidadSolicitudes
                    (Date) result[3], // fechaUltimaSolicitud
                    (Date) result[4], // fechaPrimeraSolicitud
                    (Long) result[5] // cantidadTecnicos
            );
            estadisticas.add(estadistica);
        }

        return estadisticas;
    }

    @Override
    public List<EstadisticaServicioSolicitado> generarEstadisticaServiciosMasSolicitados() {
        // Con las ordenes de trabajo, escarbar los servicios asociados a cada una de
        // ellas y empezar el conteo.
        //
        // Para cada servicio, se debe contar la cantidad de veces que se ha solicitado,
        // la fecha de la última vez, la fecha de la primera vez y la cantidad de
        // clientes que lo han solicitado.
        List<Object[]> resultList = this.repo.getEstadisticaServicioSolicitado();

        return this.construirEstadisticasDesdeAnonimo(resultList);
    }

    @Override
    public List<EstadisticaServicioSolicitado> generarEstadisticaServiciosMasSolicitados(
            Date fechaInicio, Date fechaFin) {
        List<Object[]> resultList = this.repo.getEstadisticaServicioSolicitadoRangoFecha(
                fechaInicio, fechaFin);

        return this.construirEstadisticasDesdeAnonimo(resultList);
    }

    @Override
    public List<EstadisticaServicioSolicitado> generarEstadisticaServiciosMasSolicitados(
            Integer tecnico) {
        List<Object[]> resultList = this.repo.getEstadisticaServicioSolicitadoTecnico(tecnico);

        return this.construirEstadisticasDesdeAnonimo(resultList);
    }
}
