package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author yukal
 */
@Entity
@Table(name = "tm_servicios")
public class Servicio {

    /**
     * ID único representativo del servicio.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    
    @Size(min = 4, max = 32, message = "El nombre no puede superar los 32 caractéres.")
    @Column(nullable = false, length = 32)
    @NotNull
    public String name;

    @Size(max = 512, message = "La descripcion del servicio no puede superar los 512 caractéres.")
    @Column(nullable = false, length = 512)
    @NotNull
    public String descripcion;

    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;

}
