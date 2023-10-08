package com.progavanzada.taller.mecanico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.progavanzada.taller.mecanico.entities.objects.RegExPatterns;
import com.progavanzada.taller.mecanico.entities.pipes.PhoneNumberConstraint;
import com.progavanzada.taller.mecanico.entities.pipes.PhoneNumberConverter;
import com.progavanzada.taller.mecanico.entities.pipes.Unique;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    /**
     * El nombre legal de la persona.
     */
    @Size(min = 2, max = 32, message = "El nombre no puede exceder los 32 caractéres en longitud.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Un nombre solo puede tener letras, espacios y guiones.")
    @Column(nullable = false, length = 32)
    @NotNull
    @NotEmpty
    public String name;

    /**
     * El apellido legal de la persona
     */
    @Size(min = 2, max = 32, message = "El apellido no puede exceder los 32 caractéres en longitud.")
    @Pattern(regexp = RegExPatterns.OnlyLetters_CI, message = "Un apellido solo puede tener letras, espacios y guiones.")
    @Column(nullable = false, length = 32)
    @NotNull
    @NotEmpty
    public String surName;

    /**
     * El Documento Nacional de Indentidad (DNI) de la persona. Este debe ser
     * único.
     */
    @Min(value = 1_000_000)
    @Max(value = 999_999_999)
    @Column(nullable = false, unique = true)
    @Unique(message = "Ya existe una persona con ese DNI.")
    @NotNull
    public Integer dni;

    /**
     * Opcional.
     *
     * El nombre de la calle a donde tiene domicilio esta persona.
     */
    @Size(min = 2, max = 64, message = "Una calle no puede tener más de 64 caracteres.")
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
     * El número de teléfono de la persona, en formato E.164
     */
    @PhoneNumberConstraint(isoCode = "AR")
    @Convert(converter = PhoneNumberConverter.class)
    @Column(nullable = true, length = 32)
    @NotNull
    public String phoneNumber;

    /**
     * Hacky getter para que retorne correctamente el número de teléfono.
     * @return 
     */
    public String getPhoneNumber() {
        PhoneNumberUtil phone = PhoneNumberUtil.getInstance();

        try {
            return phone.format(phone.parse(this.phoneNumber, "AR"), PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            return null;
        }
    }

    /**
     * Opcional.
     *
     * El correo electrónico de la persona.
     */
    @Size(min = 2, max = 64, message = "El correo electrónico introducido es demasiado largo.")
    @Pattern(regexp = RegExPatterns.EmailAddress, message = "El correo electrónico introducido es inválido.")
    @Column(nullable = true, length = 64)
    public String email;

    /**
     * Flag que denota si la entidad fue eliminada o no.
     */
    @Column(nullable = false)
    @JsonIgnore
    public boolean eliminado = false;

    public Integer getDni() {
        return this.dni;
    }
}
