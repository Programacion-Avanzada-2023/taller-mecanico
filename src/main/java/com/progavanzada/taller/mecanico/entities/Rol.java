package com.progavanzada.taller.mecanico.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

/**
 * Roles de las personas registradas en el sistema.
 */
@Entity
@Table(name = "tm_roles")
public class Rol {

    /**
     * El ID único del rol.
     */
    @Id
    @GeneratedValue
    public Integer id;

    /**
     * El nombre visual del rol.
     */
    @Size(max = 32, message = "El nombre de un rol debe ser corto (menor a 32 caracteres)")
    @Column(nullable = false, length = 32)
    public String name;
}
