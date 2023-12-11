/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.ReservaCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaDto;
import com.progavanzada.taller.mecanico.controller.dto.ReservaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Reserva;
import com.progavanzada.taller.mecanico.repositories.ReservaService;
import jakarta.validation.Valid;
import java.util.ArrayList;
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
@RequestMapping(path = "/reserva")
public class ReservaController {
        
    @Autowired
    private ReservaService service;
    
    @GetMapping
    public List<ReservaDto> getReservas() {
        List<Reserva> reservas = this.service.repo.findByEliminadoFalse();

        List<ReservaDto> reservasDto = new ArrayList<ReservaDto>();
        for (Reserva reserva : reservas) {
            reservasDto.add(this.service.mapReservaToDto(reserva));
        }

        return reservasDto;
    }
    
    @GetMapping("/{id}")
    public ReservaDto getReserva(@PathVariable("id") Integer id) {
        return this.service.mapReservaToDto(this.service.repo.findByIdAndEliminadoFalse(id));
    }
    
    @PostMapping
    public ReservaDto createReserva(@Valid @RequestBody ReservaCreateDto dto) {
        return this.service.crearReserva(dto);
    }
    
    @PatchMapping("/{id}")
    public ReservaDto updateReserva(@PathVariable("id") Integer id, @Valid @RequestBody ReservaUpdateDto dto) {
        Reserva reserva = this.service.repo.findByIdAndEliminadoFalse(id);

        if (reserva == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La reserva especificada no existe.", null);
        }

        return this.service.actualizarReserva(dto, reserva);
    }
    
    @DeleteMapping("/{id}")
    public boolean deleteOrden(@PathVariable("id") Integer id) {
        Reserva reserva = this.service.repo.findByIdAndEliminadoFalse(id);

        if (reserva == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La orden especificada no existe.", null);
        }

        return this.service.borrarReserva(reserva);
    }
    
}
