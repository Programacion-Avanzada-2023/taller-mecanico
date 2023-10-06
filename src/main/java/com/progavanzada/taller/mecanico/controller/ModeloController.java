package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.entities.Modelo;
import com.progavanzada.taller.mecanico.repositories.ModeloRepository;
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
@RequestMapping(path = "/Modelo")
public class ModeloController {

    @Autowired
    private ModeloRepository repo;

    @GetMapping
    public List<Modelo> getModelo() {
        return this.repo.findAll();
    }

    @GetMapping(path = "/{id}")
    public Modelo getModelo(@PathVariable Integer id) {
        // Ver si Modelo existe.
        Optional<Modelo> Modelo = this.repo.findById(id);
        
        if (Modelo.isEmpty()) return null;
        
        return Modelo.get();
    }
}