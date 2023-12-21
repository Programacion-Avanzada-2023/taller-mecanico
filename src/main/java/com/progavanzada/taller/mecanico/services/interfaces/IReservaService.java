package com.progavanzada.taller.mecanico.services.interfaces;

import com.progavanzada.taller.mecanico.controller.dto.ReservaCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Reserva;
import java.util.List;

public interface IReservaService {
    /**
     * Busca a todas las reservas activas.
     *
     * @return Lista de reservas.
     */
    public List<ReservaDto> buscarReservas();

    /**
     * Busca una reserva por su id.
     *
     * @param id
     * @return Reserva.
     */
    public ReservaDto buscarReserva(Integer id);

    /**
     * Crea una reserva.
     *
     * @param reserva
     * @return Reserva creada.
     */
    public ReservaDto crearReserva(ReservaCreateDto reserva);

    /**
     * Actualiza una reserva.
     *
     * @param reserva
     * @param entity
     * 
     * @return Reserva actualizada.
     */
    public ReservaDto actualizarReserva(ReservaUpdateDto reserva, Reserva entity);

    /**
     * Elimina una reserva.
     *
     * @param reserva
     */
    public void eliminarReserva(Reserva reserva);
}
