package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.entities.Automovil;
import com.progavanzada.taller.mecanico.repositories.AutomovilRepository;
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
@RequestMapping(path = "/automoviles")
public class AutomovilController {

    @Autowired
    private AutomovilRepository repo;

    @GetMapping
    public List<Automovil> getAutomoviles() {
        return this.repo.findAll();
    }

    @GetMapping(path = "/{id}")
    public Automovil getAutomovil(@PathVariable Integer id) {
        // Ver si la Automovil existe.
        Optional<Automovil> Automovil = this.repo.findById(id);
        
        if (Automovil.isEmpty()) return null;
        
        return Automovil.get();
    }
}

