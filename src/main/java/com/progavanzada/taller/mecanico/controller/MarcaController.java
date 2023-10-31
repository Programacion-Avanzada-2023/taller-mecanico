package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.MarcaCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.MarcaDto;
import com.progavanzada.taller.mecanico.controller.dto.MarcaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Marca;
import com.progavanzada.taller.mecanico.repositories.MarcaService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controlador RESTful para Marcas.
 */
@RestController
@RequestMapping(path = "/marcas")
public class MarcaController {

    @Autowired
    private MarcaService service;

    /**
     * Busca todas las marcas del dominio.
     *
     * @return Un listado con todas las entidades.
     */
    @GetMapping
    public List<MarcaDto> getMarcas() {
        List<Marca> marcas = this.service.repo.findByEliminadoFalse();
        
        List<MarcaDto> marcasDto = new ArrayList<MarcaDto>();
        for (Marca marca : marcas) {
            marcasDto.add(this.service.mapMarcaToDto(marca));
        }
        
        return marcasDto;
    }

    /**
     * Devuelve una marca existente del dominio (si existe).
     *
     * @param id El ID de la entidad.
     *
     * @return La entidad, si no, null.
     */
    @GetMapping(path = "/{id}")
    public MarcaDto getMarca(@PathVariable("id") Integer id) {
        return this.service.mapMarcaToDto(this.service.repo.findByIdAndEliminadoFalse(id));
    }
   
    /**
     * Crea una nueva Marca acorde al tipado de la entidad.
     *
     * @param marca El cuerpo de una marca nueva.
     *
     * @return La nueva marca creada.
     */
    @PostMapping
    public MarcaDto createMarca(@Valid @RequestBody MarcaCreateDto dto) {
        return this.service.crearMarca(dto);
    }
    
    /**
     * Modifica los campos de una Marca existente.
     *
     * @param id El ID de la entidad a modificar.
     *
     * @return La entidad modificada.
     */
    @PatchMapping(path = "/{id}")
    public MarcaDto updateMarca(@PathVariable("id") Integer id, @Valid @RequestBody MarcaUpdateDto body) {
        // Buscar la entidad a modificar.
        Marca marca = this.service.repo.findByIdAndEliminadoFalse(id);
        
        // Si no hay marca, detener la ejecución y largar la excepción.
        if (marca == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La marca especificada no existe.", null);
        
        // Aplicar las modificaciones.
        return this.service.actualizarMarca(body, marca);
    }
   
    /**
     * Marca el borrado de una entidad de Marca.
     *
     * @param id El ID de la entidad a marcar como eliminada.
     *
     * @return Verdadero si se logró el borrado.
     */
    @DeleteMapping(path = "/{id}")
    public boolean deleteMarca(@PathVariable Integer id) {
        // Buscar la entidad a borrar.
        Marca marca = this.service.repo.findByIdAndEliminadoFalse(id);
        
        // Si no hay marca, detener la ejecución y largar la excepción.
        if (marca == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La marca especificada no existe.", null);
        
        // Marcar como eliminada.
        return this.service.borrarMarca(marca);
    }
}
