package com.progavanzada.taller.mecanico.controller;

import com.progavanzada.taller.mecanico.entities.OrdenDeTrabajo;
import com.progavanzada.taller.mecanico.repositories.OrdenRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yukal
 */
@RestController
@RequestMapping(path = "/OrdenDeTrabajo")
public class OrdenController {

    @Autowired
    private OrdenRepository repo;

    @GetMapping
    public List<OrdenDeTrabajo> getOrdenDeTrabajo() {
        return this.repo.findAll();
    }

    @GetMapping(path = "/{id}")
    public OrdenDeTrabajo getOrdenDeTrabajo(@PathVariable Integer id) {
        // Ver si la Orden de Trabajo existe.
        Optional<OrdenDeTrabajo> OrdenDeTrabajo = this.repo.findById(id);
        
        if (OrdenDeTrabajo.isEmpty()) return null;
        
        return OrdenDeTrabajo.get();
    }
    
    /**
     * ChatGPT me tiro esta otra forma de hacer lo anterior
     * 
     *  @GetMapping(path = "/{id}")
        public OrdenDeTrabajo getOrdenDeTrabajo(@PathVariable Integer id) {
            return this.repo.findById(id).orElse(null);
        } 
     */
    
    
    /**
     * Lo que quiero hacer es crear la nueva orden de trabajo, utilizando el post y como 
     * vi por internet, se debe hacer algo asi
     */
    @PostMapping(path ="/nueva_Orden")
    public OrdenDeTrabajo createOrden(@RequestBody OrdenDeTrabajo newOrden) {
        // Guarda la nueva Orden de Trabajo en la base de datos
        return this.repo.save(newOrden);
        
        
    }
    
    /**
     * Guardar los datos tomando en cuenta los atributos que solicita la orden (no estan todos)
     * 
    @PostMapping(path ="/nueva_Orden")
    public OrdenDeTrabajo createOrden(@RequestBody OrdenRequest ordenRequest) {
        // Crea una nueva Orden de Trabajo
        OrdenDeTrabajo nuevaOrden = new OrdenDeTrabajo();
        
        // Obtiene el ModeloVehiculo, Cliente y Servicio por ID
        ModeloVehiculo modelo = modeloRepository.findById(ordenRequest.getModeloId()).orElse(null);
        Cliente cliente = clienteRepository.findById(ordenRequest.getClienteId()).orElse(null);
        Servicio servicio = servicioRepository.findById(ordenRequest.getServicioId()).orElse(null);
        
        // Asigna los datos a la nueva Orden de Trabajo
        nuevaOrden.setModeloVehiculo(modelo);
        nuevaOrden.setCliente(cliente);
        nuevaOrden.setServicio(servicio);
        
        // Guarda la nueva Orden de Trabajo en la base de datos
        return ordenRepository.save(nuevaOrden);
    }
    
    */
}


