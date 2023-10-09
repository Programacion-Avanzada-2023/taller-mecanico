package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tm_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @OneToOne(targetEntity = Persona.class, optional = false)
    @JoinColumn(name = "persona_id")
    @NotNull
    public Persona person;

    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;

    public Integer getLicenciaConducir() {
        // Obtener la licencia de conducir desde la persona
        if (person != null) {
            return person.getDni(); // Asumimos que DNI es igual a la licencia de conducir
        } else {
            return null; // O maneja el caso cuando persona es nula
        }
    }
}
