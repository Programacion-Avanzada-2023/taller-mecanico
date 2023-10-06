package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import com.progavanzada.taller.mecanico.repositories.OrdenRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yukal
 */
@RestController
@RequestMapping(path = "/OrdenDeTrabajo")
public class OrdenController {

    @Autowired
    private OrdenRepository repo;

    @GetMapping
    public List<OrdenDeTrabajo> getOrdenDeTrabajo() {
        return this.repo.findAll();
    }

    @GetMapping(path = "/{id}")
    public OrdenDeTrabajo getOrdenDeTrabajo(@PathVariable Integer id) {
        // Ver si la Orden de Trabajo existe.
        Optional<OrdenDeTrabajo> OrdenDeTrabajo = this.repo.findById(id);
        
        if (OrdenDeTrabajo.isEmpty()) return null;
        
        return OrdenDeTrabajo.get();
    }
}


