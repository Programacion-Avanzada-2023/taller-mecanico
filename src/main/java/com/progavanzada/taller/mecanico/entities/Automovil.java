package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
   
    /**
     * El modelo de este vehículo específico.
     */
    @ManyToOne(targetEntity = Modelo.class, optional = false)
    @JoinColumn(name = "modelo_id")
    @NotNull
    public Modelo model;
   
    /**
     * El dueño de este vehículo. Debe ser siempre una persona de tipo cliente.
     */
    @OneToOne(targetEntity = Cliente.class, optional = false)
    @JoinColumn(name = "cliente_id")
    @NotNull
    public Cliente client;
    
    /**
     * La patente del vehículo (o dominio).
     */
    @Pattern(regexp = RegExPatterns.ArgentineLicensePlate, message = "El formato introducido de patente no es válido.")
    @Column(nullable = false, length = 8)
    @NotNull
    public String licensePlate;
   
    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;
}
