package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.OrdenUpdateDto;
import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import com.progavanzada.taller.mecanico.repositories.OrdenRepository;
import com.progavanzada.taller.mecanico.repositories.OrdenService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
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
    public List<OrdenDeTrabajo> getOrdenesDeTrabajo() {
        return this.service.repo.findAll();
    }

    @GetMapping(path = "/{id}")
    public OrdenDeTrabajo getOrdenDeTrabajo(@PathVariable Integer id) {
        return this.service.repo.buscarPorId(id);
    } 
    
    @PostMapping
    public OrdenDeTrabajo createOrden(@RequestBody OrdenDeTrabajo orden) {
        return this.service.repo.save(orden);
    }
    
    @PatchMapping(path = "/{id}")
    public OrdenDeTrabajo updateOrden(@PathVariable Integer id, @Valid @RequestBody OrdenUpdateDto body) {
        // Buscar la entidad a modificar.
        OrdenDeTrabajo orden = this.service.repo.buscarPorId(id);
        
        // Si no hay marca, detener la ejecución y largar la excepción.
        if (orden == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La orden especificada no existe.", null);
        
        // Aplicar las modificaciones.
        return this.service.actualizarOrden(body, orden);
    }
    
    @DeleteMapping(path = "/{id}")
    public boolean deleteOrden(@PathVariable Integer id) {
        // Buscar la entidad a borrar.
        OrdenDeTrabajo orden = this.service.repo.buscarPorId(id);
        
        // Si no hay marca, detener la ejecución y largar la excepción.
        if (orden == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La orden especificada no existe.", null);
        
        // Marcar como eliminada.
        return this.service.borrarOrden(orden);
    }
}


