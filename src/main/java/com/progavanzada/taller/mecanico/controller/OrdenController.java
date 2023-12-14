package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.OrdenCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenServicioDto;
import com.progavanzada.taller.mecanico.controller.dto.OrdenUpdateDto;
import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import com.progavanzada.taller.mecanico.repositories.OrdenService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author yukal
 */
@RestController
@RequestMapping(path = "/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService service;

    @GetMapping
    public List<OrdenDto> getOrdenes() {
        List<OrdenDeTrabajo> ordenes = this.service.repo.findByEliminadoFalse();

        List<OrdenDto> ordenesDto = new ArrayList<OrdenDto>();
        for (OrdenDeTrabajo orden : ordenes) {
            ordenesDto.add(this.service.mapOrdenToDto(orden));
        }

        return ordenesDto;
    }

    @GetMapping("/cliente/{clienteId}")
    public OrdenDto getCliente(@PathVariable Integer clienteId) {
        return this.service.mapOrdenToDto((OrdenDeTrabajo) this.service.repo.findByClient_IdAndEliminadoFalse(clienteId));
    }
    
    @GetMapping("/{id}")
    public OrdenDto getOrden(@PathVariable("id") String id) {
        return this.service.mapOrdenToDto(this.service.repo.findByIdAndEliminadoFalse(id));
    }

    @PostMapping
    public OrdenDto createOrden(@Valid @RequestBody OrdenCreateDto dto) {
        return this.service.crearOrden(dto);
    }

    @PatchMapping("/{id}")
    public OrdenDto updateOrden(@PathVariable("id") String id, @Valid @RequestBody OrdenUpdateDto dto) {
        OrdenDeTrabajo orden = this.service.repo.findByIdAndEliminadoFalse(id);

        if (orden == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La orden especificada no existe.", null);
        }

        return this.service.actualizarOrden(dto, orden);
    }

    @DeleteMapping("/{id}")
    public boolean deleteOrden(@PathVariable("id") String id) {
        OrdenDeTrabajo orden = this.service.repo.findByIdAndEliminadoFalse(id);

        if (orden == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La orden especificada no existe.", null);
        }

        return this.service.borrarOrden(orden);
    }
    
    /**
     * Agrega un nuevo servicio a una orden.
     *
     * @param id ID de la Orden a agregar servicios.
     * @param dto DTO de los servicios a agregar.
     *
     * @return La orden con los nuevos servicios agregados.
     */
    @PostMapping("/{id}/servicios")
    public OrdenDto addServiceToOrden(@PathVariable("id") String id, @Valid @RequestBody OrdenServicioDto dto) {
        return this.service.agregarServicio(id, dto);
    }
    
    /**
     * Borra un servicio de una orden existente.
     *
     * @param id ID de la Orden a remover servicios.
     * @param dto DTO de los servicios a remover.
     *
     * @return La orden con los servicios eliminados.
     */
    @DeleteMapping("/{id}/servicios")
    public OrdenDto removeServiceFromOrden(@PathVariable("id") String id, @Valid @RequestBody OrdenServicioDto dto) {
        return this.service.eliminarServicio(id, dto);
    }
}
