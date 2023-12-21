package com.progavanzada.taller.mecanico.entities.objects;

public enum EstadoOrden {
    /** La orden está esperando a ser confirmada por el cliente. */
    EN_ESPERA_DE_CONFIRMACION("En espera de confirmación"),

    /** El cliente ha confirmado la orden de trabajo. A la espera de su visita. */
    CONFIRMADA("Confirmada"),

    /** El técnico ha finalizado la orden de trabajo. */
    FINALIZADA("Finalizada"),

    /** La orden de trabajo ha sido cancelada. */
    CANCELADA("Cancelada");

    private final String name;

    EstadoOrden(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
