package com.progavanzada.taller.mecanico.entities;

import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Representa una marca de automóvil
 */
@Entity
@Table(name = "tm_marcas")
public class Marca {

    /**
     * El ID único que representa esta marca.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Un nombre visual sobre que marca es.
     */
    @Size(max = 32, message = "El nombre de una marca no puede superar los 32 caractéres.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Los nombres de una marca solo pueden tener letras, espacios y guiones.")
    @Column(nullable = false, length = 32)
    private String name;
}
