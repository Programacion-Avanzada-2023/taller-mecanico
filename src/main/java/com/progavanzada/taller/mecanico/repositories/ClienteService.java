package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.ClienteUpdateDto;
import com.progavanzada.taller.mecanico.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guido
 */
@Service
public class ClienteService implements ClienteRepositoryCustom {
    
    @Autowired
    @Lazy
    public ClienteRepository repo;
/**
 * Método que actualiza atributos de cliente.
 * No implementado al no tener atributos de cliente actualizables.
    @Override
    public Cliente actualizarCliente(ClienteUpdateDto dto, Cliente entity) {
        entity.? = dto.?;
        
        return this.repo.save(entity);
    }
*/
    
    @Override
    public boolean borrarCliente(Cliente entity) {
        entity.eliminado = true;
        
        this.repo.save(entity);
        return true;
    }
}
