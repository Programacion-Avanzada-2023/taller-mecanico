package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tm_tecnicos")
public class Tecnico {
    /**
     * ID único representativo del técnico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    /**
     * Persona a la cuál se vinculan los datos de este técnico.
     */
    @OneToOne(targetEntity = Persona.class, optional = false)
    @JoinColumn(name = "tecnico_persona_id")
    @NotNull
    public Persona person;

    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;
}
