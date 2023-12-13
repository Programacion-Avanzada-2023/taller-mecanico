package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.ClienteDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Cliente;
import com.progavanzada.taller.mecanico.entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService implements ReservaRepositoryCustom {

    @Autowired
    public ReservaRepository repo;

    //@Autowired
    //private TecnicoService TecnicoService;

    @Autowired
    private ClienteService clienteService;

    /**
     * Construye un DTO de automovil en base a una entidad.
     *
     * @param entity
     * @return
     */
    public ReservaDto mapReservaToDto(Reserva entity) {
        ClienteDto cliente = this.clienteService.mapClienteToDto(entity.client);

        ReservaDto reserva = new ReservaDto();
        reserva.id = entity.id;
        reserva.client = cliente;
        //reserva.tecnico = tecnico;
        reserva.fechaInicio = entity.fechaInicio;
        reserva.fechaFin = entity.fechaFin;
        return reserva;
    }

    public ReservaDto actualizarReserva(ReservaUpdateDto dto, Reserva entity) {
        // Mappear campos a la entidad.
        entity.fechaInicio = dto.fechaInicio != null ? dto.fechaInicio : entity.fechaInicio;

        Reserva reserva = this.repo.save(entity);
        return this.mapReservaToDto(reserva);
    }

    public boolean borrarReserva(Reserva entity) {
         entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }

    public ReservaDto crearReserva(ReservaCreateDto dto) {
        // Buscar el modelo.
         //Tecnico tecnico= this.TecnicoService.repo.findByIdAndEliminadoFalse(dto.Tecnico);

        //if (tecnico== null) {
        //    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El tecnico especificado no existe.", null);
        //}

        // Buscar al cliente.
        Cliente cliente = this.clienteService.repo.findByIdAndEliminadoFalse(dto.client);

        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente especificado no existe.", null);
        }

        // Guardar entidad.
        Reserva reserva = new Reserva();
        reserva.client = cliente;
        //reserva.tecnico = tecnico;
        reserva.fechaInicio = dto.fechaInicio;
        //reserva .fechaFin= dto.fechaFin;

        this.repo.save(reserva);

        // Construir DTO y devolver resultado.
        return this.mapReservaToDto(reserva);
    }
}
