package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.ReservaCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Reserva;
import com.progavanzada.taller.mecanico.services.ReservaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author yukal
 */
@RestController
@RequestMapping(path = "/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public List<ReservaDto> getReservas() {
        return this.service.buscarReservas();
    }

    @GetMapping("/{id}")
    public ReservaDto getReserva(@PathVariable("id") Integer id) {
        return this.service.buscarReserva(id);
    }

    @PostMapping
    public ReservaDto createReserva(@Valid @RequestBody ReservaCreateDto dto) {
        return this.service.crearReserva(dto);
    }

    @PatchMapping("/{id}")
    public ReservaDto updateReserva(@PathVariable(name = "id", required = true) Integer id, @Valid @RequestBody ReservaUpdateDto dto) {
        Reserva reserva = this.service.repo.findByIdAndEliminadoFalse(id);

        if (reserva == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La reserva especificada no existe.", null);

        return this.service.actualizarReserva(dto, reserva);
    }

    @DeleteMapping("/{id}")
    public boolean deleteReserva(@PathVariable(name = "id", required = true) Integer id) {
        Reserva reserva = this.service.repo.findByIdAndEliminadoFalse(id);

        if (reserva == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La reserva especificada no existe.", null);

        this.service.eliminarReserva(reserva);
        return true;
    }

}
