package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.AutomovilCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.AutomovilDto;
import com.progavanzada.taller.mecanico.controller.dto.AutomovilUpdateDto;
import com.progavanzada.taller.mecanico.entities.Automovil;
import com.progavanzada.taller.mecanico.repositories.AutomovilService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author yukal
 */
@RestController
@RequestMapping(path = "/automoviles")
public class AutomovilController {

    @Autowired
    private AutomovilService service;

        /**
     * Busca todos los automoviles del dominio.
     *
     * @return Un listado con todas las entidades.
     */
    @GetMapping
    public List<AutomovilDto> getAutomoviles() {
        List<Automovil> automoviles = this.service.repo.findByEliminadoFalse();
        
        List<AutomovilDto> automovilesDto = new ArrayList<AutomovilDto>();
        for (Automovil automovil : automoviles) {
            automovilesDto.add(this.service.mapAutomovilToDto(automovil));
        }
        
        return automovilesDto;
    }

    /**
     * Devuelve un automovil existente del dominio (si existe).
     *
     * @param id El ID de la entidad.
     *
     * @return La entidad, si no, null.
     */
    @GetMapping(path = "/{id}")
    public Automovil getAutomovil(@PathVariable Integer id) {
        return this.service.repo.findByIdAndEliminadoFalse(id);
    }

    /**
     * Crea un nuevo automovil acorde al tipado de la entidad.
     *
     * @param automovil El cuerpo de un nuevo automovil.
     *
     * @return El nuevo automovil creado.
     */
    @PostMapping
    public AutomovilDto createAutomovil(@Valid @RequestBody AutomovilCreateDto automovil) {
        return this.service.crearAutomovil(automovil);
    }

    /**
     * Modifica los campos de un Automovil existente.
     *
     * @param id El ID de la entidad a modificar.
     *
     * @return La entidad modificada.
     */
    @PatchMapping(path = "/{id}")
    public AutomovilDto updateAutomovil(@PathVariable Integer id, @Valid @RequestBody AutomovilUpdateDto body) {
        // Buscar la entidad a modificar.
        Automovil automovil = this.service.repo.findByIdAndEliminadoFalse(id);

        // Si no hay persona, detener la ejecución y largar la excepción.
        if (automovil == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El automovil especificado no existe.", null);
        }

        // Aplicar las modificaciones.
        return this.service.actualizarAutomovil(body, automovil);
    }

    /**
     * Marca el borrado de una entidad de Automovil.
     *
     * @param id El ID de la entidad a marcar como eliminada.
     *
     * @return Verdadero si se logró el borrado.
     */
    @DeleteMapping(path = "/{id}")
    public boolean deleteAutomovil(@PathVariable Integer id) {
        // Buscar la entidad a borrar.
        Automovil automovil = this.service.repo.findByIdAndEliminadoFalse(id);

        // Si no hay automovil, detener la ejecución y largar la excepción.
        if (automovil == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El automovil especificado no existe.", null);
        }

        // Marcar como eliminada.
        return this.service.borrarAutomovil(automovil);
    }
}

