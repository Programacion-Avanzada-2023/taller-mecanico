package com.progavanzada.taller.mecanico.repositories;

import com.progavanzada.taller.mecanico.controller.dto.AutomovilCreateDto;
import com.progavanzada.taller.mecanico.controller.dto.AutomovilDto;
import com.progavanzada.taller.mecanico.controller.dto.AutomovilUpdateDto;
import com.progavanzada.taller.mecanico.controller.dto.ClienteDto;
import com.progavanzada.taller.mecanico.controller.dto.MarcaDto;
import com.progavanzada.taller.mecanico.controller.dto.ModeloDto;
import com.progavanzada.taller.mecanico.entities.Automovil;
import com.progavanzada.taller.mecanico.entities.Cliente;
import com.progavanzada.taller.mecanico.entities.Modelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Usuario
 */
@Service
public class AutomovilService implements AutomovilRepositoryCustom {

    @Autowired
    public AutomovilRepository repo;

    @Autowired
    private ModeloService modeloService;

    @Autowired
    private ClienteService clienteService;

    /**
     * Construye un DTO de automovil en base a una entidad.
     *
     * @param entity
     * @return
     */
    public AutomovilDto mapAutomovilToDto(Automovil entity) {
        ModeloDto modelo = this.modeloService.mapModeloToDto(entity.model);

        ClienteDto cliente = this.clienteService.mapClienteToDto(entity.client);

        AutomovilDto automovil = new AutomovilDto();
        automovil.id = entity.id;
        automovil.client = cliente;
        automovil.licensePlate = entity.licensePlate;
        automovil.model = modelo;

        return automovil;
    }

    @Override
    public AutomovilDto actualizarAutomovil(AutomovilUpdateDto dto, Automovil entity) {
        // Mappear campos a la entidad.
        entity.licensePlate = dto.licensePlate != null ? dto.licensePlate : entity.licensePlate;

        Automovil automovil = this.repo.save(entity);
        return this.mapAutomovilToDto(automovil);
    }

    @Override
    public boolean borrarAutomovil(Automovil entity) {
        // Marcar su flag de borrado.
        entity.eliminado = true;

        this.repo.save(entity);
        return true;
    }

    @Override
    public AutomovilDto crearAutomovil(AutomovilCreateDto dto) {
        // Buscar el modelo.
        Modelo modelo = this.modeloService.repo.findByIdAndEliminadoFalse(dto.model);

        if (modelo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El modelo especificado no existe.", null);
        }

        // Buscar al cliente.
        Cliente cliente = this.clienteService.repo.findByIdAndEliminadoFalse(dto.client);

        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente especificado no existe.", null);
        }

        // Guardar entidad.
        Automovil automovil = new Automovil();
        automovil.client = cliente;
        automovil.model = modelo;
        automovil.licensePlate = dto.licensePlate;

        this.repo.save(automovil);

        // Construir DTO y devolver resultado.
        return this.mapAutomovilToDto(automovil);
    }
}
