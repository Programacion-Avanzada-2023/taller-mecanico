package com.progavanzada.taller.mecanico.entities;

import jakarta.persistence.*;
/**
 *
 * @author yukal
 */
@Entity
@Table(name = "tm_OrdenDeTrabajo")
public class OrdenDeTrabajo {

    /**
     * ID único representativo de la orden.
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * El modelo de este vehículo específico.
     * Dentro del modelo estara el año del modelo
     */
    @OneToOne(targetEntity = Modelo.class, optional = false)
    @MapsId
    private Modelo model;
   
    /**
     * El dueño de este vehículo. Debe ser siempre una persona de tipo cliente.
     */
    @OneToOne(targetEntity = Persona.class, optional = false)
    @MapsId
    private Persona client;
    
    /**
     * Vehiculo al que se le realiza la orden de trabajo
     */
    @OneToOne(targetEntity = Automovil.class, optional = false)
    @MapsId
    private Automovil automovil;
    
    /**
     * los servicios que solicitara el cliente
     */
    @OneToOne(targetEntity = Servicio.class, optional = false)
    @MapsId
    private Servicio Servicio;
    
}
