package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Size(max = 500, message = "La descripcion del servicio no puede superar los 32 caractéres.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "La descripcion del servicio solo pueden tener letras, espacios y guiones.")
    @Column(nullable = false, length = 500)
    public String descripcion;

    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;

}
