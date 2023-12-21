package com.progavanzada.taller.mecanico.entities.pipes;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsDateAfterTodayValidator.class)
@Documented
public @interface IsDateAfterToday {
    String message() default "{message.key}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
