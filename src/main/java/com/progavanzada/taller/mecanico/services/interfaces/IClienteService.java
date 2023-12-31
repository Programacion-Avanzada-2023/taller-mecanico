package com.progavanzada.taller.mecanico.services.interfaces;

import com.progavanzada.taller.mecanico.controller.dto.ClienteCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ClienteDto;
import com.progavanzada.taller.mecanico.entities.Cliente;


/**
 *
 * @author Guido
 */
public interface IClienteService {
    /**
     * Método no implementado
     * Cliente no tiene atributos actualizables
    Cliente actualizarCliente(ClienteUpdateDto dto, Cliente entity);
    */
    
    boolean borrarCliente(Cliente entity);
    
    ClienteDto crearCliente(ClienteCreateDto dto);
}
