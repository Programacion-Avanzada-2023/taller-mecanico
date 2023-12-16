package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progavanzada.taller.mecanico.entities.objects.EstadoOrden;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tm_ordenes")
public class OrdenDeTrabajo {
    /**
     * ID único representativo de la orden (UUID);
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    /**
     * Controla si la orden de trabajo fue confirmada por el cliente.
     */
    @Column(nullable = false)
    public boolean confirmada = false;

    /**
     * Vehiculo al que se le realiza la orden de trabajo
     */
    @ManyToOne(targetEntity = Automovil.class, optional = false)
    @JoinColumn(name = "automovil_id")
    @NotNull
    public Automovil automovil;

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
    @ManyToMany
    @JoinColumn(name = "servicio_id")
    @NotNull
    public List<Servicio> servicios;

    /**
     * El estado actual de la orden de trabajo.
     *
     * - En Espera de Confirmación: La orden de trabajo fue creada, aunque no tiene
     * trabajo realizado.
     * - Confirmada: La orden fue confirmada por el cliente ante su reserva. Está a
     * la espera de que se trabaje por el técnico asignado.
     * - Realizada: La orden fue realizada por el técnico asignado. Esta orden ya no
     * puede ser modificada.
     * - Cancelada: La orden fue cancelada por el cliente. Esta orden ya no puede
     * ser modificada.
     */
    @Column(nullable = false)
    @NotNull
    public String estado = EstadoOrden.EN_ESPERA_DE_CONFIRMACION.toString();

    /**
     * Fecha de creación de la orden de trabajo ISO8601, calculada automáticamente.
     */
    @Column(nullable = false)
    public Timestamp fechaCreacion = Timestamp.valueOf(java.time.LocalDateTime.now());

    /**
     * Fecha de la última modificación de la orden.
     */
    @Column(nullable = true)
    public Timestamp fechaModificacion = Timestamp.valueOf(java.time.LocalDateTime.now());

    /**
     * El técnico asociado a esta órden de trabajo.
     *
     * Puede que no tenga uno al momento de su creación.
     */
    @ManyToOne(targetEntity = Tecnico.class, optional = true)
    @JoinColumn(name = "tecnico_id")
    public Tecnico tecnico = null;

    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;

    /**
     * Calcula el costo total de una orden de trabajo en base a sus servicios e
     * impuestos por marca de automovil.
     * 
     * @return El total de la orden de trabajo con impuestos aplicados.
     */
    public float calcularCostoTotal() {
        // Comenzar el total en 0.
        float costoTotal = 0;

        // Iterar servicios e ir sumando sus precios unitarios.
        for (Servicio servicio : servicios) {
            costoTotal += servicio.getPrecioUnitario();
        }

        // Agregar los agregados por impuestos.
        costoTotal += costoTotal * this.automovil.model.brand.impuestoMarca;

        // Retornar el costo total.
        return costoTotal;
    }
}
