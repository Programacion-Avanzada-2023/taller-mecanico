package com.progavanzada.taller.mecanico.entities;

import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import com.progavanzada.taller.mecanico.entities.pipes.PhoneNumberConstraint;
import com.progavanzada.taller.mecanico.entities.pipes.PhoneNumberConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Representa a una persona real del taller, sea técnico o cliente.
 */
@Entity
@Table(name = "tm_personas")
public class Persona {

    /**
     * ID único representativo de la persona.
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
     * El apellido legal de la persona
     */
    @Size(max = 32, message = "El apellido no puede exceder los 32 caractéres en longitud.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Un apellido solo puede tener letras, espacios y guiones.")
    @Column(nullable = false, length = 32)
    public String surName;

    /**
     * El Documento Nacional de Indentidad (DNI) de la persona. Este debe ser
     * único.
     */
    @Min(value = 1_000_000)
    @Max(value = 999_999_999)
    @Column(nullable = false, unique = true)
    public Integer dni;

    /**
     * El rol asignado a esta persona, lo distingue entre un técnico o un
     * cliente.
     */
    @OneToOne(targetEntity = Rol.class, optional = false)
    @MapsId
    public Rol rol;
   
    /**
     * Opcional.
     * 
     * El nombre de la calle a donde tiene domicilio esta persona.
     */
    @Size(max = 64, message = "Una calle no puede tener más de 64 caracteres.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Un nombre de calle solo puede tener letras, espacios y guiones.")
    @Column(nullable = true)
    public String street;
   
    /**
     * Opcional.
     * 
     * La altura (numérica) a donde tiene domicilio la calle de esta persona.
     */
    @Min(value = 0)
    @Max(value = 9_999)
    @Column(nullable = true)
    public Integer streetNumber;
   
    /**
     * Opcional.
     *
     * El número de teléfono de la persona, en formato E.164
     */
    @PhoneNumberConstraint(isoCode = "AR")
    @Convert(converter = PhoneNumberConverter.class)
    @Column(nullable = true, length = 32)
    public String phoneNumber;
   
    /**
     * Opcional.
     * 
     * El correo electrónico de la persona.
     */
    @Size(max = 64, message = "El correo electrónico introducido es demasiado largo.")
    @Pattern(regexp = RegExPatterns.EmailAddress, message = "El correo electrónico introducido es inválido.")
    @Column(nullable = true, length = 64)
    public String email;

    public Integer getDni() {
        return this.dni;
    }
}