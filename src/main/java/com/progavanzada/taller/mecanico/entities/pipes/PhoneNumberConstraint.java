package com.progavanzada.taller.mecanico.entities.pipes;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interfaz de decorador para la validación de números de teléfono.
 *
 * @author mazal
 */
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberConstraint {
    String message() default "El número de teléfono que se introdujo es inválido.";
    
    String isoCode() default "AR";
    
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
