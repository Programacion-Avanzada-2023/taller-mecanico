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
     * ID único representativo de la orden (UUID);
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

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
     * Fecha de creación de la orden de trabajo ISO8601, calculada automáticamente.
     */
    @Column(nullable = false)
    public String fechaCreacion = java.time.LocalDateTime.now().toString();

    /**
     * TODO: No implementado para esta entrega.
     *
     * Fecha de la última modificación de la orden.
     */
    @Column(nullable = true)
    public String fechaModificacion = java.time.LocalDateTime.now().toString();

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
