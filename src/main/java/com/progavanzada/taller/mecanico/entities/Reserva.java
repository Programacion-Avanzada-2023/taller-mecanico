package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author yukal
 */
@Entity
@Table(name = "tm_reservas")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    
    @OneToOne(targetEntity = Cliente.class, optional = false)
    @JoinColumn(name = "cliente_id")
    @NotNull
    public Cliente client;
    
    @OneToOne(targetEntity = Tecnico.class, optional = false)
    @JoinColumn(name = "tecnico_id")
    @NotNull
    public Tecnico tecnico;
    
    @Column(nullable = false)
    public String fechaInicio = java.time.LocalDateTime.now().toString();
    
    //@Column(nullable = false)
    //public String fechaFin = java.time.LocalDateTime.now().toString();
    
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;
}
