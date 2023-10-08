package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Marca de un vehiculo
 */
@Entity
@Table(name = "tm_marcas")
public class Marca {

    /**
     * El ID único que representa esta marca.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    /**
     * Un nombre visual sobre que marca es.
     */
    @Size(min = 4, max = 32, message = "El nombre de una marca no puede superar los 32 caractéres.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Los nombres de una marca solo pueden tener letras, espacios y guiones.")
    @Column(nullable = false, length = 32)
    public String name;

    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;
}
