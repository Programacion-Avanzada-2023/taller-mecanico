package com.progavanzada.taller.mecanico.services;

import com.progavanzada.taller.mecanico.controller.dto.ClienteCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ClienteDto;
import com.progavanzada.taller.mecanico.controller.dto.ClienteUpdateDto;
import com.progavanzada.taller.mecanico.controller.dto.PersonaDto;
import com.progavanzada.taller.mecanico.entities.Cliente;
import com.progavanzada.taller.mecanico.entities.Persona;
import com.progavanzada.taller.mecanico.repositories.ClienteRepository;
import com.progavanzada.taller.mecanico.repositories.PersonaRepository;
import com.progavanzada.taller.mecanico.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Guido
 */
@Service
public class ClienteService implements IClienteService {

    @Autowired
    public ClienteRepository repo;
    
    @Autowired
    private PersonaRepository repoPersona;
    
    public ClienteDto mapClienteToDto(Cliente entity) {
        PersonaDto persona = new PersonaDto();
        persona.id = entity.person.id;
        persona.name = entity.person.name;
        persona.surName = entity.person.surName;
        persona.dni = entity.person.dni;
        persona.street = entity.person.street;
        persona.streetNumber = entity.person.streetNumber;
        persona.phoneNumber = entity.person.phoneNumber;
        persona.email = entity.person.email;
        
        ClienteDto dto = new ClienteDto();
        dto.id = entity.id;
        dto.person = persona;
        
        return dto;
    }

    /**
     * Método que actualiza atributos de cliente. No implementado al no tener
     * atributos de cliente actualizables.
     *
     * @Override public Cliente actualizarCliente(ClienteUpdateDto dto, Cliente
     * entity) { entity.? = dto.?;
     *
     * return this.repo.save(entity); }
     */
    @Override
    public boolean borrarCliente(Cliente entity) {
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }
    
    @Override
    public ClienteDto crearCliente(ClienteCreateDto dto) {
        Persona persona = this.repoPersona.findByIdAndEliminadoFalse(dto.person);
        
        if (persona == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La persona especificada no existe.", null);
       
        Cliente cliente = new Cliente();
        cliente.person = persona;
        
        this.repo.save(cliente);
        
        return this.mapClienteToDto(cliente);
    }
}
