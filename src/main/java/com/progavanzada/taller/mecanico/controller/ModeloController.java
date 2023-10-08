package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.ModeloCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ModeloDto;
import com.progavanzada.taller.mecanico.controller.dto.ModeloUpdateDto;
import com.progavanzada.taller.mecanico.entities.Modelo;
import com.progavanzada.taller.mecanico.repositories.ModeloService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author yukal
 */
@RestController
@RequestMapping(path = "/modelos")
public class ModeloController {

    @Autowired
    private ModeloService service;

    /**
     * Busca todos los modelos del dominio.
     *
     * @return Un listado con todas las entidades.
     */
    @GetMapping
    public List<ModeloDto> getModelos() {
        List<Modelo> modelos = this.service.repo.findByEliminadoFalse();
        
        List<ModeloDto> modelosDto = new ArrayList<ModeloDto>();
        for (Modelo modelo : modelos) {
            modelosDto.add(this.service.mapModeloToDto(modelo));
        }
        
        return modelosDto;
    }

    /**
     * Devuelve un modelo existente del dominio (si existe).
     *
     * @param id El ID de la entidad.
     *
     * @return La entidad, si no, null.
     */
    @GetMapping(path = "/{id}")
    public Modelo getModelo(@PathVariable Integer id) {
        Modelo modelo = this.service.repo.findByIdAndEliminadoFalse(id);
        
        return modelo;
    }
   
    /**
     * Crea un nuevo Modelo acorde al tipado de la entidad.
     *
     * @param modelo El cuerpo de un modelo nuevo.
     *
     * @return El nuevo modelo creado.
     */
    @PostMapping
    public ModeloDto createModelo(@Valid @RequestBody ModeloCreateDto modelo) {
        return this.service.crearModelo(modelo);
    }
    
    /**
     * Modifica los campos de un Modelo existente.
     *
     * @param id El ID de la entidad a modificar.
     *
     * @return La entidad modificada.
     */
    @PatchMapping(path = "/{id}")
    public ModeloDto updateModelo(@PathVariable Integer id, @Valid @RequestBody ModeloUpdateDto body) {
        // Buscar la entidad a modificar.
        Modelo modelo = this.service.repo.findByIdAndEliminadoFalse(id);
        
        // Si no hay marca, detener la ejecución y largar la excepción.
        if (modelo == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El modelo especificado no existe.", null);
        
        // Aplicar las modificaciones.
        return this.service.actualizarModelo(body, modelo);
    }
   
    /**
     * Marca el borrado de una entidad de Modelo.
     *
     * @param id El ID de la entidad a marcar como eliminada.
     *
     * @return Verdadero si se logró el borrado.
     */
    @DeleteMapping(path = "/{id}")
    public boolean deleteModelo(@PathVariable Integer id) {
        // Buscar la entidad a borrar.
        Modelo modelo = this.service.repo.findByIdAndEliminadoFalse(id);
        
        // Si no hay marca, detener la ejecución y largar la excepción.
        if (modelo == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El modelo especificado no existe.", null);
        
        // Marcar como eliminada.
        return this.service.borrarModelo(modelo);
    }
}