package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.entities.Persona;
import com.progavanzada.taller.mecanico.repositories.PersonaRepository;
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
@RequestMapping(path = "/Persona")
public class PersonaController {

    @Autowired
    private PersonaRepository repo;

    @GetMapping
    public List<Persona> getPersona() {
        return this.repo.findAll();
    }

    @GetMapping(path = "/{id}")
    public Persona getPersona(@PathVariable Integer id) {
        // Ver si la marca existe.
        Optional<Persona> Persona = this.repo.findById(id);
        
        if (Persona.isEmpty()) return null;
        
        return Persona.get();
    }
}
