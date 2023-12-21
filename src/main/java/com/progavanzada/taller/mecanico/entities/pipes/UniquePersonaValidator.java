package com.progavanzada.taller.mecanico.entities.pipes;

import com.progavanzada.taller.mecanico.repositories.PersonaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniquePersonaValidator implements ConstraintValidator<Unique, Integer> {

    @Autowired
    private PersonaRepository repo;

    @Override
    public void initialize(Unique unique) {
        unique.message();
    }

    @Override
    public boolean isValid(Integer dni, ConstraintValidatorContext context) {
        Boolean isValid = repo.findByDni(dni) == null;

        return isValid;
    }
}