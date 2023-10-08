package com.progavanzada.taller.mecanico.entities.pipes;

import static java.lang.annotation.ElementType.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Interfaz de decorador para la validación de números de teléfono.
 *
 * @author mazal
 */
@Target( { FIELD, PARAMETER } )
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumberConstraint {
    String message() default "El número de teléfono que se introdujo es inválido.";
    
    String isoCode() default "AR";
    
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
