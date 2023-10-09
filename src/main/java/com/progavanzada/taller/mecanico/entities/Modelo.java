package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    /**
     * El nombre legal de la persona.
     */
    @Size(min = 4, max = 32, message = "El nombre de modelo no puede exceder los 32 caractéres en longitud.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Un nombre de modelo solo puede tener letras, espacios y guiones.")
    @Column(nullable = false, length = 32)
    @NotNull
    public String name;

    /**
     * La marca a la cual pertenece este modelo.
     */
    @ManyToOne(targetEntity = Marca.class, optional = false)
    @JoinColumn(name = "marca_id")
    @NotNull
    public Marca brand;

    /**
     * El año el cuál se impartió este modelo específico de automóvil.
     */
    @Min(value = 1800)
    @Max(value = 9999)
    @Column(nullable = false)
    @NotNull
    public Integer year;

    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;
}
