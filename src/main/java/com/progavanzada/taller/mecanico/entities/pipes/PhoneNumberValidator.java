package com.progavanzada.taller.mecanico.entities.pipes;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Constraint de validación personalizado que integra con libphonenumber para
 * dar un constraint en Jakarta, utilizable en parámetros de entidades.
 *
 * @author mazal
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
    /** El codigo ISO del país del cuál se está validando el número de teléfono. Por defecto es "AR" (Argentina) */
    private String isoCode;
    
    @Override
    public void initialize(PhoneNumberConstraint constraint) {
        this.isoCode = constraint.isoCode();
    }
    
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext ctx) {
        if (phoneNumber == null) return true;
        
        // Crear instancia del parser de la API de Google.
        PhoneNumberUtil phone = PhoneNumberUtil.getInstance();
        
        try {
            PhoneNumber parsed = phone.parse(phoneNumber, this.isoCode);
            
            // Intentar parsear el número.
            return parsed instanceof PhoneNumber;
        } catch (NumberParseException e) {
            return false;
        }
    }
}
