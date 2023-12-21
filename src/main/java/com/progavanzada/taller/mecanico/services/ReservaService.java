package com.progavanzada.taller.mecanico.services;

import com.progavanzada.taller.mecanico.controller.dto.ClienteDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaUpdateDto;
import com.progavanzada.taller.mecanico.controller.dto.TecnicoDto;
import com.progavanzada.taller.mecanico.entities.Cliente;
import com.progavanzada.taller.mecanico.entities.Reserva;
import com.progavanzada.taller.mecanico.entities.Tecnico;
import com.progavanzada.taller.mecanico.repositories.ReservaRepository;
import com.progavanzada.taller.mecanico.services.interfaces.IReservaService;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService implements IReservaService {

    @Autowired
    public ReservaRepository repo;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    /**
     * Construye un DTO de automovil en base a una entidad.
     *
     * @param entity
     * @return
     */
    private ReservaDto mapReservaToDto(Reserva entity) {
        ClienteDto cliente = this.clienteService.mapClienteToDto(entity.client);

        TecnicoDto tecnico = this.tecnicoService.mapTecnicoToDto(entity.tecnico);

        ReservaDto reserva = new ReservaDto();
        reserva.id = entity.id;
        reserva.client = cliente;
        reserva.tecnico = tecnico;
        reserva.fechaInicio = entity.fechaInicio;
        reserva.fechaFin = entity.fechaFin;

        return reserva;
    }

    @Override
    public List<ReservaDto> buscarReservas() {
        List<Reserva> reservas = this.repo.findByEliminadoFalse();

        // Filtrar la lista de reservas para que solo se muestren las que no han pasado.
        reservas.removeIf(reserva -> reserva.fechaFin.before(new Timestamp(System.currentTimeMillis())));

        List<ReservaDto> reservasDto = new java.util.ArrayList<ReservaDto>();
        for (Reserva reserva : reservas)
            reservasDto.add(this.mapReservaToDto(reserva));

        return reservasDto;
    }

    @Override
    public ReservaDto buscarReserva(Integer id) {
        Reserva reserva = this.repo.findByIdAndEliminadoFalse(id);

        if (reserva == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La reserva especificada no existe.", null);

        return this.mapReservaToDto(reserva);
    }

    @Override
    public ReservaDto actualizarReserva(ReservaUpdateDto dto, Reserva entity) {
        // Mappear campos a la entidad.
        entity.fechaInicio = dto.fechaInicio != null ? Timestamp.valueOf(dto.fechaInicio) : entity.fechaInicio;
        entity.fechaFin = dto.fechaFin != null ? Timestamp.valueOf(dto.fechaFin) : entity.fechaFin;

        Reserva reserva = this.repo.save(entity);

        return this.mapReservaToDto(reserva);
    }

    @Override
    public void eliminarReserva(Reserva entity) {
        entity.eliminado = true;

        this.repo.save(entity);
    }

    @Override
    public ReservaDto crearReserva(ReservaCreateDto dto) {
        // Buscar el modelo.
        Tecnico tecnico = this.tecnicoService.repo.findByIdAndEliminadoFalse(dto.tecnico);

        if (tecnico == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El tecnico especificado no existe.", null);

        // Buscar al cliente.
        Cliente cliente = this.clienteService.repo.findByIdAndEliminadoFalse(dto.client);

        if (cliente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente especificado no existe.", null);

        // Validar las fechas.
        Timestamp fechaInicio = Timestamp.valueOf(dto.fechaInicio);
        Timestamp fechaFin = Timestamp.valueOf(dto.fechaFin);

        if (fechaInicio.getTime() < fechaFin.getTime())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de inicio debe ser menor a la fecha de fin.", null);

        // Guardar entidad.
        Reserva reserva = new Reserva();
        reserva.client = cliente;
        reserva.tecnico = tecnico;
        reserva.fechaInicio = Timestamp.valueOf(dto.fechaInicio);
        reserva.fechaFin = Timestamp.valueOf(dto.fechaFin);

        this.repo.save(reserva);

        // Construir DTO y devolver resultado.
        return this.mapReservaToDto(reserva);
    }
}
