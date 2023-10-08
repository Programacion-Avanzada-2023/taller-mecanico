package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.AutomovilDto;
import com.progavanzada.taller.mecanico.controller.dto.ClienteCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ClienteDto;
import com.progavanzada.taller.mecanico.entities.Automovil;
import com.progavanzada.taller.mecanico.entities.Cliente;
import com.progavanzada.taller.mecanico.repositories.ClienteService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controlador RESTful para las entidades de Cliente.
 * @author yukal
 */
@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    /**
     * Busca todos los clientes del dominio.
     *
     * @return Un listado con todas las entidades.
     */
    @GetMapping
    public List<ClienteDto> getClientes() {
        List<Cliente> clientes = this.service.repo.findByEliminadoFalse();
        
        List<ClienteDto> clienteDtos = new ArrayList<ClienteDto>();
        for (Cliente cliente : clientes) {
            clienteDtos.add(this.service.mapClienteToDto(cliente));
        }
        
        return clienteDtos;
    }

    /**
     * Devuelve un cliente existente del dominio (si existe).
     *
     * @param id El ID de la entidad.
     *
     * @return La entidad, si no, null.
     */
    @GetMapping(path = "/{id}")
    public Cliente getCliente(@PathVariable Integer id) {
        return this.service.repo.findByIdAndEliminadoFalse(id);
    }

    /**
     * Crea un nuevo cliente acorde al tipado de la entidad.
     *
     * @param cliente El cuerpo de una cliente nuevo.
     *
     * @return El nuevo cliente creado.
     */
    @PostMapping
    public ClienteDto createCliente(@Valid @RequestBody ClienteCreateDto dto) {
        return this.service.crearCliente(dto);
    }

    /**
     * Marca el borrado de una entidad de Cliente.
     *
     * @param id El ID de la entidad a marcar como eliminada.
     *
     * @return Verdadero si se logró el borrado.
     */
    @DeleteMapping(path = "/{id}")
    public boolean deleteCliente(@PathVariable Integer id) {
        // Buscar la entidad a borrar.
        Cliente cliente = this.service.repo.findByIdAndEliminadoFalse(id);

        // Si no hay cliente, detener la ejecución y largar la excepción.
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente especificado no existe.", null);
        }

        // Marcar como eliminado.
        return this.service.borrarCliente(cliente);
    }
}
