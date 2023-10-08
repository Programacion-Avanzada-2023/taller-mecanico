package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.controller.dto.MarcaUpdateDto;
import com.progavanzada.taller.mecanico.entities.Marca;
import com.progavanzada.taller.mecanico.repositories.MarcaRepositoryImpl;
import jakarta.validation.Valid;
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
    private MarcaRepositoryImpl repoImplementation;

    /**
     * Busca todas las marcas del dominio.
     *
     * @return Un listado con todas las entidades.
     */
    @GetMapping
    public List<Marca> getMarcas() {
        return this.repoImplementation.repo.buscarTodo();
    }

    /**
     * Devuelve una marca existente del dominio (si existe).
     *
     * @param id El ID de la entidad.
     *
     * @return La entidad, si no, null.
     */
    @GetMapping(path = "/{id}")
    public Marca getMarca(@PathVariable Integer id) {
        return this.repoImplementation.repo.buscarPorId(id);
    }
   
    /**
     * Crea una nueva Marca acorde al tipado de la entidad.
     *
     * @param marca El cuerpo de una marca nueva.
     *
     * @return La nueva marca creada.
     */
    @PostMapping
    public Marca createMarca(@RequestBody Marca marca) {
        return this.repoImplementation.repo.save(marca);
    }
    
    /**
     * Modifica los campos de una Marca existente.
     *
     * @param id El ID de la entidad a modificar.
     *
     * @return La entidad modificada.
     */
    @PatchMapping(path = "/{id}")
    public Marca updateMarca(@PathVariable Integer id, @Valid @RequestBody MarcaUpdateDto body) {
        // Buscar la entidad a modificar.
        Marca marca = this.repoImplementation.repo.buscarPorId(id);
        
        // Si no hay marca, detener la ejecución y largar la excepción.
        if (marca == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La marca especificada no existe.", null);
        
        // Aplicar las modificaciones.
        return this.repoImplementation.actualizarMarca(body, marca);
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
        Marca marca = this.repoImplementation.repo.buscarPorId(id);
        
        // Si no hay marca, detener la ejecución y largar la excepción.
        if (marca == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La marca especificada no existe.", null);
        
        // Marcar como eliminada.
        return this.repoImplementation.borrarMarca(marca);
    }
}
