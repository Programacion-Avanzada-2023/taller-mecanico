package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 *
 * @author yukal
 */
@Entity
@Table(name = "tm_ordenes")
public class OrdenDeTrabajo {

    /**
     * ID único representativo de la orden.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    /**
     * El modelo de este vehículo específico. Dentro del modelo estara el año
     * del modelo
     */
    @OneToOne(targetEntity = Modelo.class, optional = false)
    @MapsId
    @NotNull
    public Modelo model;

    public Modelo getModelo() {
        return model;
    }
    /**
     * El dueño de este vehículo. Debe ser siempre una persona de tipo cliente.
     */
    @OneToOne(targetEntity = Cliente.class, optional = false)
    @MapsId
    @NotNull
    public Cliente client;

    public Cliente getCliente() {
        return client;
    }
    /**
     * Vehiculo al que se le realiza la orden de trabajo
     */
    @OneToOne(targetEntity = Automovil.class, optional = false)
    @MapsId
    @NotNull
    public Automovil automovil;

    public Automovil getAutomovil() {
        return automovil;
    }

    /**
     * Opcional.
     *
     * Otros detalles relevantes con respecto a la orden de trabajo.
     */
    @Size(max = 512, message = "El mensaje insertado supera los 512 caractéres.")
    @Column(nullable = true)
    public String detalles = null;

    /**
     * los servicios que solicitara el cliente
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orden_services_id")
    @NotNull
    public List<Servicio> servicios;

    public List<Servicio> getServicios() {
        return servicios;
    }

    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;
}
