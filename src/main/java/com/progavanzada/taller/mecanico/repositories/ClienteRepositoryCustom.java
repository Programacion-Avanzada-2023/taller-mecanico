package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.ClienteUpdateDto;
import com.progavanzada.taller.mecanico.entities.Cliente;


/**
 *
 * @author Guido
 */
public interface ClienteRepositoryCustom {
    /**
     * Método no implementado
     * Cliente no tiene atributos actualizables
    Cliente actualizarCliente(ClienteUpdateDto dto, Cliente entity);
    */
    
    boolean borrarCliente(Cliente entity);
}
