package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.entities.Marca;
import com.progavanzada.taller.mecanico.repositories.MarcaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping(path = "/marcas")
public class MarcaController {

    @Autowired
    private MarcaRepository repo;

    @GetMapping
    public List<Marca> getMarcas() {
        return this.repo.findAll();
    }

    @GetMapping(path = "/{id}")
    public Marca getMarca(@PathVariable Integer id) {
        // Ver si la marca existe.
        Optional<Marca> marca = this.repo.findById(id);
        
        if (marca.isEmpty()) return null;
        
        return marca.get();
    }
}
