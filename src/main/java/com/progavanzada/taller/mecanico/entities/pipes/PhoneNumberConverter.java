package com.progavanzada.taller.mecanico.entities.pipes;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Convierte un supuesto número de teléfono en un formato estandarizado (E.164)
 * internacional.
 *
 * @author mazal
 */
@Converter
public class PhoneNumberConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String phoneNumber) {
        // Crear instancia del parser de la API de Google.
        PhoneNumberUtil phone = PhoneNumberUtil.getInstance();

        try {
            // Intentar parsear el número, si se puede, formatearlo acorde al estándar.
            return phone.format(phone.parse(phoneNumber, "AR"), PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(String phoneNumber) {
        // No hace falta hacer nada en la materialización. Pasar como tal.
        return phoneNumber;
    }
}
