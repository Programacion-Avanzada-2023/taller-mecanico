package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.EstadisticaServicioSolicitado;
import com.progavanzada.taller.mecanico.entities.Servicio;
import com.progavanzada.taller.mecanico.entities.objects.Constants;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yukal
 */
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    public List<Servicio> findByEliminadoFalse();

    public List<Servicio> findByEliminadoTrue();

    public List<Servicio> findByEliminadoFalseAndIdIn(List<Integer> ids);

    public Servicio findByIdAndEliminadoFalse(Integer id);

    @Query(value = Constants.ESTADISTICA_SERVICIOS_SOLICITADOS, nativeQuery = true)
    List<Object[]> getEstadisticaServicioSolicitado();

    @Query(value = Constants.ESTADISTICA_SERVICIOS_SOLICITADOS_RANGO_FECHA, nativeQuery = true)
    List<Object[]> getEstadisticaServicioSolicitadoRangoFecha(@Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin);

    @Query(value = Constants.ESTADISTICA_SERVICIOS_SOLICITADOS_TECNICO, nativeQuery = true)
    List<Object[]> getEstadisticaServicioSolicitadoTecnico(@Param("tecnico") Integer tecnico);
}
