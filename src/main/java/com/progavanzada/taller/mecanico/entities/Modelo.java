package com.progavanzada.taller.mecanico.entities;

import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Representa un modelo del taller, con su marca propia vinculada.
 */
@Entity
@Table(name = "tm_modelos")
public class Modelo {

    /**
     * ID único representativo del modelo.
     */
    @Id
    @GeneratedValue
    public Integer id;

    /**
     * El nombre legal de la persona.
     */
    @Size(max = 32, message = "El nombre no puede exceder los 32 caractéres en longitud.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Un nombre solo puede tener letras, espacios y guiones.")
    @Column(nullable = false, length = 32)
    public String name;

    /**
     * La marca a la cual pertenece este modelo.
     */
    @OneToOne(targetEntity = Marca.class, optional = false)
    @MapsId
    public Marca brand;

    /**
     * El año el cuál se impartió este modelo específico de automóvil.
     */
    @Min(value = 1800)
    @Max(value = 9999)
    @Column(nullable = false)
    public Integer year;
}
