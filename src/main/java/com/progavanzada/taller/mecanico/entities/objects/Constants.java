package com.progavanzada.taller.mecanico.entities.objects;

public class Constants {
    /**
     * Query preparada para obtener las estadísticas de los servicios más
     * solicitados.
     */
    public static final String ESTADISTICA_SERVICIOS_SOLICITADOS = "SELECT\r\n" + //
            "    ser.id AS idServicio,\r\n" + //
            "    ser.name AS nombreServicio,\r\n" + //
            "    COUNT(ords.servicios_id) AS cantidadSolicitudes,\r\n" + //
            "    MAX(ord.fecha_modificacion) AS fechaUltimaSolicitud,\r\n" + //
            "    MIN(ord.fecha_modificacion) AS fechaPrimeraSolicitud,\r\n" + //
            "    COUNT(DISTINCT ord.tecnico_id) AS cantidadTecnicos\r\n" + //
            "FROM\r\n" + //
            "    tm_servicios ser\r\n" + //
            "INNER JOIN tm_ordenes_servicios ords ON ser.id = ords.servicios_id\r\n" + //
            "INNER JOIN tm_ordenes ord ON ords.orden_de_trabajo_id = ord.id\r\n" + //
            "INNER JOIN tm_automoviles aut ON ord.automovil_id = aut.id\r\n" + //
            "INNER JOIN tm_clientes cli ON aut.cliente_id = cli.id\r\n" + //
            "INNER JOIN tm_tecnicos tec ON ord.tecnico_id = tec.id\r\n" + //
            "GROUP BY\r\n" + //
            "    ser.id;";
}

