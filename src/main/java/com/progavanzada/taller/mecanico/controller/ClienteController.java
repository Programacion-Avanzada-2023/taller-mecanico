package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.entities.Cliente;
import com.progavanzada.taller.mecanico.repositories.ClienteRepository;
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
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    @GetMapping
    public List<Cliente> getClientes() {
        return this.repo.findAll();
    }

    @GetMapping(path = "/{id}")
    public Cliente getCliente(@PathVariable Integer id) {
        // Ver si la Cliente existe.
        Optional<Cliente> Cliente = this.repo.findById(id);
        
        if (Cliente.isEmpty()) return null;
        
        return Cliente.get();
    }
}