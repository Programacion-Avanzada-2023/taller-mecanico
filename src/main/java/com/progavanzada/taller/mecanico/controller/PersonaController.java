package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.PersonaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Persona;
import com.progavanzada.taller.mecanico.repositories.PersonaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controlador RESTful para las personas.
 */
@RestController
@RequestMapping(path = "/personas")
public class PersonaController {

    @Autowired
    private PersonaService service;

    /**
     * Busca todas las Personas del dominio.
     *
     * @return Un listado con todas las entidades.
     */
    @GetMapping
    public List<Persona> getPersonas() {
        return this.service.repo.findByEliminadoFalse();
    }

    /**
     * Devuelve una persona existente del dominio (si existe).
     *
     * @param id El ID de la entidad.
     *
     * @return La entidad, si no, null.
     */
    @GetMapping(path = "/{id}")
    public Persona getPersona(@PathVariable Integer id) {
        return this.service.repo.findByIdAndEliminadoFalse(id);
    }

    /**
     * Crea una nueva Persona acorde al tipado de la entidad.
     *
     * @param persona El cuerpo de una persona nueva.
     *
     * @return La nueva persona creada.
     */
    @PostMapping
    public Persona createPersona(@Valid @RequestBody Persona persona) {
        return this.service.repo.save(persona);
    }

    /**
     * Modifica los campos de una Persona existente.
     *
     * @param id El ID de la entidad a modificar.
     *
     * @return La entidad modificada.
     */
    @PatchMapping(path = "/{id}")
    public Persona updatePersona(@PathVariable Integer id, @Valid @RequestBody PersonaUpdateDto body) {
        // Buscar la entidad a modificar.
        Persona persona = this.service.repo.findByIdAndEliminadoFalse(id);

        // Si no hay persona, detener la ejecución y largar la excepción.
        if (persona == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La persona especificada no existe.", null);
        }

        // Aplicar las modificaciones.
        return this.service.actualizarPersona(body, persona);
    }

    /**
     * Marca el borrado de una entidad de Persona.
     *
     * @param id El ID de la entidad a marcar como eliminada.
     *
     * @return Verdadero si se logró el borrado.
     */
    @DeleteMapping(path = "/{id}")
    public boolean deletePersona(@PathVariable Integer id) {
        // Buscar la entidad a borrar.
        Persona persona = this.service.repo.findByIdAndEliminadoFalse(id);

        // Si no hay persona, detener la ejecución y largar la excepción.
        if (persona == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La persona especificada no existe.", null);
        }

        // Marcar como eliminada.
        return this.service.borrarPersona(persona);
    }
}
