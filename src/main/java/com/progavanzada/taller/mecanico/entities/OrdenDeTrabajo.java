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
    public Integer id;
    /**
     * El modelo de este vehículo específico. Dentro del modelo estara el año
     * del modelo
     */
    @OneToOne(targetEntity = Modelo.class, optional = false)
    @MapsId
    public Modelo model;

    public Modelo getModelo() {
        return model;
    }
    /**
     * El dueño de este vehículo. Debe ser siempre una persona de tipo cliente.
     */
    @OneToOne(targetEntity = Cliente.class, optional = false)
    @MapsId
    public Cliente client;

    public Cliente getCliente() {
        return client;
    }
    /**
     * Vehiculo al que se le realiza la orden de trabajo
     */
    @OneToOne(targetEntity = Automovil.class, optional = false)
    @MapsId
    public Automovil automovil;

    public Automovil getAutomovil() {
        return automovil;
    }

    /**
     * los servicios que solicitara el cliente
     */
    @OneToOne(targetEntity = Servicio.class, optional = false)
    @MapsId
    public Servicio servicio;

    public Servicio getServicio() {
        return servicio;
    }

    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    public boolean eliminado = false;
}
