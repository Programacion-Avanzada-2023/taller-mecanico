package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 *
 * @author yukal
 */
@Entity
@Table(name = "tm_reservas")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer id;
    
    @ManyToOne(targetEntity = Cliente.class)
    @JoinColumn(name = "cliente_id")
    @NotNull
    public Cliente client;
    
    @ManyToOne(targetEntity = Tecnico.class)
    @JoinColumn(name = "tecnico_id")
    @NotNull
    public Tecnico tecnico;
    
    @Column(nullable = false)
    public Timestamp fechaInicio = Timestamp.valueOf(java.time.LocalDateTime.now());
    
    @Column(nullable = false)
    public Timestamp fechaFin;
    
    @Column(nullable = false)   
    @JsonIgnore
    public boolean eliminado = false;
}
