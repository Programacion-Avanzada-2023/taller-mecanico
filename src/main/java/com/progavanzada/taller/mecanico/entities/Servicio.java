package com.progavanzada.taller.mecanico.entities;

import jakarta.persistence.*;

/**
 *
 * @author yukal
 */
@Entity
@Table(name = "tm_servicio")
public class Servicio {
    /**
     * ID único representativo del servicio.
     */
    @Id
    @GeneratedValue
    private Integer id;
    
    private String descripcion;
}
