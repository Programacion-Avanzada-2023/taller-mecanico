package com.progavanzada.taller.mecanico.entities.pipes;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UniquePersonaValidator.class)
@Retention(RUNTIME)
public @interface Unique {
    String message();
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}