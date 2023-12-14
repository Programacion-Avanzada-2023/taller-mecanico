package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.AutomovilDto;
import com.progavanzada.taller.mecanico.controller.dto.ClienteDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenServicioDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenUpdateDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioDto;
import com.progavanzada.taller.mecanico.entities.Automovil;
import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import com.progavanzada.taller.mecanico.entities.Servicio;
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
public class OrdenService implements OrdenRepositoryCustom {

    @Autowired
    public OrdenRepository repo;

    @Autowired
    private AutomovilService serviceAutomovil;

    @Autowired
    private ServicioService serviceServicio;
    
    @Autowired
    private ClienteService serviceCliente;

    /**
     * Mappea una entidad de Orden de Trabajo hacia su DTO.
     *
     * @param entity La entidad.
     *
     * @return El DTO de la orden de trabajo.
     */
    public OrdenDto mapOrdenToDto(OrdenDeTrabajo entity) {
        AutomovilDto automovil = this.serviceAutomovil.mapAutomovilToDto(entity.automovil);
        ClienteDto cliente = this.serviceCliente.mapClienteToDto(entity.client);

        List<ServicioDto> servicios = new ArrayList<ServicioDto>();
        for (Servicio servicio : entity.servicios)
            servicios.add(this.serviceServicio.mapServiceToDto(servicio));

        OrdenDto dto = new OrdenDto();
        dto.id = entity.id;
        dto.automovil = automovil;
        dto.cliente = cliente;
        dto.detalles = entity.detalles;
        dto.fechaCreacion = entity.fechaCreacion;
        dto.fechaModificacion = entity.fechaModificacion;
        dto.servicios = servicios;

        return dto;
    }

    @Override
    public OrdenDto actualizarOrden(OrdenUpdateDto dto, OrdenDeTrabajo entity) {
        // Mappear campos a la entidad.
        entity.detalles = dto.detalles;

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
        orden.fechaCreacion = java.time.LocalDateTime.now().toString();
        orden.fechaModificacion = java.time.LocalDateTime.now().toString();

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
        orden.fechaModificacion = java.time.LocalDateTime.now().toString();

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
        orden.fechaModificacion = java.time.LocalDateTime.now().toString();

        this.repo.save(orden);

        return this.mapOrdenToDto(orden);
    }

    /* @Override
    public List<OrdenDeTrabajo> buscarOrdenPorTecnico(Integer id) {
        return this.repo.findByTecnico(id);
    } */
    
    @Override
    public List<OrdenDeTrabajo> buscarOrdenPorCliente(Integer clienteId) {
        return this.repo.findByClient_IdAndEliminadoFalse(clienteId);
    }
}
