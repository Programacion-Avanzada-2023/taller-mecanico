package com.progavanzada.taller.mecanico.entities.pipes;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.sql.Timestamp;

public class IsDateAfterTodayValidator implements ConstraintValidator<IsDateAfterToday, String> {
    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null)
            return false;

        // Obtain the current timestamp.
        Timestamp current = new Timestamp(System.currentTimeMillis());

        // Parse the ISO8601 timestamp.
        try {
            Timestamp parsed = Timestamp.valueOf(date);

            return current.before(parsed);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
