package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.entities.Cliente;
import com.progavanzada.taller.mecanico.repositories.PersonaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    @Autowired
    private PersonaRepository repo;

    @PostMapping(path = "guido")
    public Cliente test() {
        Optional<Cliente> cliente = this.repo.findById(0);

        return cliente.get();
    }
}
