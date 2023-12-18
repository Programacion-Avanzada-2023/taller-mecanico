package com.progavanzada.taller.mecanico.entities.pipes;

import com.progavanzada.taller.mecanico.services.PersonaService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniquePersonaValidator implements ConstraintValidator<Unique, Integer> {

    @Autowired
    private PersonaService service;

    @Override
    public void initialize(Unique unique) {
        unique.message();
    }

    @Override
    public boolean isValid(Integer dni, ConstraintValidatorContext context) {
        return service != null && service.repo.findByDni(dni) != null;
    }
}