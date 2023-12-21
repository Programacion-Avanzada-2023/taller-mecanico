package com.progavanzada.taller.mecanico.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "tm_cambios_estado_orden")
public class CambioEstadoOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    /**
     * La orden de trabajo que sufrió un cambio de estado.
     */
    @ManyToOne(targetEntity = OrdenDeTrabajo.class, optional = false)
    public OrdenDeTrabajo orden;

    /**
     * El estado al que cambió la orden.
     */
    @Column(nullable = false)
    public String estado;

    /**
     * Fecha de creación del cambio de estado ISO8601, calculada automáticamente.
     */
    @Column(nullable = false)
    public Timestamp fechaCambio = Timestamp.valueOf(java.time.LocalDateTime.now());
}
