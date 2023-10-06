package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.entities.Servicio;
import com.progavanzada.taller.mecanico.repositories.ServicioRepository;
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
@RequestMapping(path = "/Servicio")
public class ServicioController {

    @Autowired
    private ServicioRepository repo;

    @GetMapping
    public List<Servicio> getServicio() {
        return this.repo.findAll();
    }

    @GetMapping(path = "/{id}")
    public Servicio getServicio(@PathVariable Integer id) {
        // Ver si servicio existe.
        Optional<Servicio> Servicio = this.repo.findById(id);
        
        if (Servicio.isEmpty()) return null;
        
        return Servicio.get();
    }
}
