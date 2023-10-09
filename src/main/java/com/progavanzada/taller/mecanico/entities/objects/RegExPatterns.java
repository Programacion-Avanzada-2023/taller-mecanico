package com.progavanzada.taller.mecanico.entities.objects;

/**
 * Clase estática que posee valores públicos con patrones de expresión regular
 * utilizados en múltiples partes del dominio.
 */
public class RegExPatterns {

    /**
     * Expresión regular que solo admite letras (case-insensitive), espacios y
     * guiones simples.
     *
     * @constant
     */
    public final static String OnlyLetters_CI = "[a-zA-Z \\-]+";

    /**
     * Expresión regular que solo admite números.
     *
     * @constant
     */
    public final static String OnlyNumbers = "[0-9]+";

    /**
     * Expresión regular para validar patentes de dominio Argentino.
     *
     * Extendido para soportar patentes desde 1970 hasta la actualidad (2023).
     *
     * @see
     * https://es.wikipedia.org/wiki/Matr%C3%ADculas_automovil%C3%ADsticas_de_Argentina
     * @constant
     */
    public final static String ArgentineLicensePlate = "^([A-Za-z]{3}[0-9]{3}|[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}|[A-Za-z]{2}\\-[0-9]{4}|[A-Za-z][0-9]{3}[A-Za-z]{3}|[A-Za-z][0-9]{6})$";

    /**
     * Expresión regular que valida correos electrónicos (segun la RFC5322)
     *
     * @see https://datatracker.ietf.org/doc/html/rfc5322
     * @constant
     */
    public final static String EmailAddress = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    /**
     * Expresión regular que valida formatos de UUID4.
     *
     * @see https://stackoverflow.com/a/37322115
     * @constant
     */
    public final static String UUID4 = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";
}
