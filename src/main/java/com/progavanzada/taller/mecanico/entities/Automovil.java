package com.progavanzada.taller.mecanico.entities;

import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

/**
 * Representa a un automovil con su respectivo modelo y dueño.
 */
@Entity
@Table(name = "tm_automoviles")
public class Automovil {
    
    /**
     * ID único representativo del vehículo.
     */
    @Id
    @GeneratedValue
    private Integer id;
   
    /**
     * El modelo de este vehículo específico.
     */
    @OneToOne(targetEntity = Modelo.class)
    @Column(nullable = false)
    private Modelo model;
   
    /**
     * El dueño de este vehículo. Debe ser siempre una persona de tipo cliente.
     */
    @OneToOne(targetEntity = Persona.class)
    @Column(nullable = false)
    private Persona client;
    
    /**
     * La patente del vehículo (o dominio).
     */
    @Pattern(regexp = RegExPatterns.ArgentineLicensePlate, message = "El formato introducido de patente no es válido.")
    @Column(nullable = false, length = 8)
    private String licensePlate;
}
