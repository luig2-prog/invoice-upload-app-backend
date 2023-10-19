package com.fev.csvprocessor.infrastructure.csv.util;

public final class Constant {
    private Constant() {
        // empty constructor
    }

    public static final String[] CSV_HEADERS = {
            "Codigo factura",
            "Nombres",
            "Apellidos",
            "Dirección",
            "Valor a pagar",
            "Fecha de vencimiento",
            "Fecha oportuna de pago",
            "Estado"
    };
}
