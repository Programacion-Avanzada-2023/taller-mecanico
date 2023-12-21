package com.progavanzada.taller.mecanico.services;

import com.progavanzada.taller.mecanico.controller.dto.EstadisticaServicioSolicitado;
import com.progavanzada.taller.mecanico.repositories.ServicioRepository;
import com.progavanzada.taller.mecanico.services.interfaces.IStatisticsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {

    @Mock
    private ServicioRepository servicioRepository;

    @InjectMocks
    private IStatisticsService statisticsService = new StatisticsService();

    @Test
    public void test_generarEstadisticaServiciosSolicitados() {
        // Mocks de los datos de estadística (provenientes de la query).
        Object[] result1 = { 1, "Servicio1", 5L, new Date(), new Date(), 3L };
        Object[] result2 = { 2, "Servicio2", 8L, new Date(), new Date(), 2L };

        List<Object[]> mockedResults = Arrays.asList(result1, result2);

        // Asignar el mock al método del repositorio (simular el resultado de la query
        // cuando se ejecuta el método)
        when(servicioRepository.getEstadisticaServicioSolicitado()).thenReturn(mockedResults);

        // Llamada al método de generación.
        List<EstadisticaServicioSolicitado> estadisticas = statisticsService
                .generarEstadisticaServiciosMasSolicitados();

        assertEquals(2, estadisticas.size());

        EstadisticaServicioSolicitado estadistica1 = estadisticas.get(0);
        assertEquals(1, estadistica1.idServicio);
        assertEquals("Servicio1", estadistica1.nombreServicio);
        assertEquals(5L, estadistica1.cantidadSolicitudes);

        EstadisticaServicioSolicitado estadistica2 = estadisticas.get(1);
        assertEquals(2, estadistica2.idServicio);
        assertEquals("Servicio2", estadistica2.nombreServicio);
        assertEquals(8L, estadistica2.cantidadSolicitudes);
    }
}