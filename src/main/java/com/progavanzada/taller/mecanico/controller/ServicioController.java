package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.ServicioCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioDto;
import com.progavanzada.taller.mecanico.controller.dto.ServicioUpdateDto;
import com.progavanzada.taller.mecanico.entities.Servicio;
import com.progavanzada.taller.mecanico.repositories.ServicioService;
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
@RequestMapping(path = "/servicios")
public class ServicioController {

    @Autowired
    private ServicioService service;

    /**
     * Busca todos los servicios del dominio.
     *
     * @return Un listado con todas las entidades.
     */
    @GetMapping
    public List<ServicioDto> getServicios() {
        List<Servicio> servicios = this.service.repo.findByEliminadoFalse();
        
        List<ServicioDto> serviciosDto = new ArrayList<ServicioDto>();
        for (Servicio servicio : servicios) {
            serviciosDto.add(this.service.mapServiceToDto(servicio));
        }
        
        return serviciosDto;
    }

    /**
     * Devuelve un servicio existente del dominio (si existe).
     *
     * @param id El ID de la entidad.
     *
     * @return La entidad, si no, null.
     */
    @GetMapping(path = "/{id}")
    public ServicioDto getServicio(@PathVariable("id") Integer id) {
        return this.service.mapServiceToDto(this.service.repo.findByIdAndEliminadoFalse(id));
    }
   
    /**
     * Crea un nuevo servicio  acorde al tipado de la entidad.
     *
     * @param marca El cuerpo de una marca nueva.
     *
     * @return El nuevo servicio creada.
     */
    @PostMapping
    public ServicioDto createServicio(@Valid @RequestBody ServicioCreateDto dto) {
        return this.service.crearServicio(dto);
    }
    
    /**
     * Modifica los campos de un servicio existente.
     *
     * @param id El ID de la entidad a modificar.
     *
     * @return La entidad modificada.
     */
    @PatchMapping(path = "/{id}")
    public ServicioDto updateServicio(@PathVariable Integer id, @Valid @RequestBody ServicioUpdateDto body) {
        // Buscar la entidad a modificar.
        Servicio servicio = this.service.repo.findByIdAndEliminadoFalse(id);
        
        // Si no hay servicio, detener la ejecución y largar la excepción.
        if (servicio == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El servicio especificado no existe.", null);
        
        // Aplicar las modificaciones.
        return this.service.actualizarServicio(body, servicio);
    }
   
    /**
     * Marca el borrado de una entidad de Servicio.
     *
     * @param id El ID de la entidad a marcar como eliminada.
     *
     * @return Verdadero si se logró el borrado.
     */
    @DeleteMapping(path = "/{id}")
    public boolean deleteServicio(@PathVariable Integer id) {
        // Buscar la entidad a borrar.
        Servicio servicio = this.service.repo.findByIdAndEliminadoFalse(id);
        
        // Si no hay servicio, detener la ejecución y largar la excepción.
        if (servicio == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El servicio especificado no existe.", null);
        
        // Marcar como eliminada.
        return this.service.borrarServicio(servicio);
    }
}
