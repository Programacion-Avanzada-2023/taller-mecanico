package com.progavanzada.taller.mecanico.services;

import com.progavanzada.taller.mecanico.controller.dto.AutomovilDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenServicioDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenUpdateDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioDto;
import com.progavanzada.taller.mecanico.entities.Automovil;
import com.progavanzada.taller.mecanico.entities.CambioEstadoOrden;
import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import com.progavanzada.taller.mecanico.entities.Servicio;
import com.progavanzada.taller.mecanico.repositories.CambioEstadoOrdenRepository;
import com.progavanzada.taller.mecanico.repositories.OrdenRepository;
import com.progavanzada.taller.mecanico.services.interfaces.IOrdenService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author yukal
 */
@Service
public class OrdenService implements IOrdenService {

    @Autowired
    public OrdenRepository repo;

    @Autowired
    private CambioEstadoOrdenRepository repoCambioEstadoOrden;

    @Autowired
    private AutomovilService serviceAutomovil;

    @Autowired
    private ServicioService serviceServicio;

    /**
     * Mappea una entidad de Orden de Trabajo hacia su DTO.
     *
     * @param entity La entidad.
     *
     * @return El DTO de la orden de trabajo.
     */
    public OrdenDto mapOrdenToDto(OrdenDeTrabajo entity) {
        AutomovilDto automovil = this.serviceAutomovil.mapAutomovilToDto(entity.automovil);

        List<ServicioDto> servicios = new ArrayList<ServicioDto>();
        for (Servicio servicio : entity.servicios)
            servicios.add(this.serviceServicio.mapServiceToDto(servicio));

        // Buscar todos los cambios de estado de la orden.
        List<CambioEstadoOrden> cambios = this.repoCambioEstadoOrden.findByOrdenId(entity.id);

        OrdenDto dto = new OrdenDto();
        dto.id = entity.id;
        dto.automovil = automovil;
        dto.estado = entity.estado;
        dto.detalles = entity.detalles;
        dto.fechaCreacion = entity.fechaCreacion;
        dto.fechaModificacion = entity.fechaModificacion;
        dto.servicios = servicios;
        dto.cambiosEstado = cambios;

        return dto;
    }

    @Override
    public OrdenDto actualizarOrden(OrdenUpdateDto dto, OrdenDeTrabajo entity) {
        // Mappear campos a la entidad.
        entity.detalles = dto.detalles;
        entity.confirmada = dto.confirmada;

        return this.mapOrdenToDto(this.repo.save(entity));
    }

    @Override
    public boolean borrarOrden(OrdenDeTrabajo entity) {
        // Marcar su flag de borrado.
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }

    @Override
    public OrdenDto crearOrden(OrdenCreateDto dto) {
        // Buscar los servicios especificados.
        List<Servicio> servicios = this.serviceServicio.repo.findByEliminadoFalseAndIdIn(dto.servicios);

        if (servicios.size() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los servicios especificados ("
                    + dto.servicios.toString() + ") no existen o no se pudieron encontrar.", null);
        }

        // Buscar el automovil especificado.
        Automovil automovil = this.serviceAutomovil.repo.findByIdAndEliminadoFalse(dto.automovil);

        if (automovil == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El automóvil especificado no existe.", null);
        }

        OrdenDeTrabajo orden = new OrdenDeTrabajo();
        orden.automovil = automovil;
        orden.servicios = servicios;
        orden.detalles = dto.detalles;
        orden.fechaCreacion = Timestamp.valueOf(java.time.LocalDateTime.now());
        orden.fechaModificacion = Timestamp.valueOf(java.time.LocalDateTime.now());

        this.repo.save(orden);

        return this.mapOrdenToDto(orden);
    }

    @Override
    public OrdenDto agregarServicio(String id, OrdenServicioDto dto) {
        // Buscar si existe la orden.
        OrdenDeTrabajo orden = this.repo.findByIdAndEliminadoFalse(id);

        if (orden == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La orden de trabajo " + id + " no existe.", null);
        }

        // Buscar si el servicio existe.
        Servicio servicio = this.serviceServicio.repo.findByIdAndEliminadoFalse(dto.servicio);

        if (servicio == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El servicio que se solicita agregar no existe.",
                    null);
        }

        // Agregarlo a la lista y actualizar la fecha de modificación.
        orden.servicios.add(servicio);
        orden.fechaModificacion = Timestamp.valueOf(java.time.LocalDateTime.now());

        this.repo.save(orden);

        return this.mapOrdenToDto(orden);
    }

    @Override
    public OrdenDto eliminarServicio(String id, OrdenServicioDto dto) {
        // Buscar si existe la orden.
        OrdenDeTrabajo orden = this.repo.findByIdAndEliminadoFalse(id);

        if (orden == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La orden de trabajo " + id + " no existe.", null);
        }

        // Ver si el servicio está agregado a la orden.
        Servicio servicio = null;
        for (Servicio sv : orden.servicios) {
            if (sv.id == dto.servicio) {
                servicio = sv;
                break;
            }
        }

        if (servicio == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El servicio " + dto.servicio + " no está en esta orden.", null);
        }

        // Eliminar el servicio de la lista, y actualizar la fecha de modificación de la
        // orden.
        orden.servicios.remove(servicio);
        orden.fechaModificacion = Timestamp.valueOf(java.time.LocalDateTime.now());

        this.repo.save(orden);

        return this.mapOrdenToDto(orden);
    }

    /*
     * @Override
     * public List<OrdenDeTrabajo> buscarOrdenPorTecnico(Integer id) {
     * return this.repo.findByTecnico(id);
     * }
     */

    @Override
    public List<OrdenDto> buscarOrdenPorCliente(Integer clienteId) {
        // Buscar las ordenes por el cliente.
        List<OrdenDeTrabajo> ordenes = this.repo.findByAutomovilClientId(clienteId);

        // Mappearlas a su equivalente DTO.
        List<OrdenDto> ordenesDto = new ArrayList<OrdenDto>();
        for (OrdenDeTrabajo orden : ordenes)
            ordenesDto.add(this.mapOrdenToDto(orden));

        return ordenesDto;
    }

    @Override
    public List<CambioEstadoOrden> obtenerCambiosDeEstadoOrden(String id) {
        // Buscar en el repositorio de cambios de estado.
        List<CambioEstadoOrden> cambios = this.repoCambioEstadoOrden.findByOrdenId(id);

        return cambios;
    }
}
