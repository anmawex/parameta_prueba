package com.parameta.empleados.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class FechaUtil {

    private FechaUtil() {
        throw new IllegalStateException("Clase de utilidad - no instanciar");
    }

    public static String calcularEdad(LocalDate nacimiento) {
        if (Objects.isNull(nacimiento)) return "Fecha no disponible";

        Period p = Period.between(nacimiento, LocalDate.now());
        return String.format("%d años, %d meses, %d días",
                p.getYears(), p.getMonths(), p.getDays());
    }

    public static boolean esMayorDeEdad(LocalDate nacimiento) {
        if (Objects.isNull(nacimiento)) return false;
        return Period.between(nacimiento, LocalDate.now()).getYears() >= 18;
    }

    public static String calcularTiempoVinculacion(LocalDate fecha) {
        if (Objects.isNull(fecha)) return "Fecha no disponible";

        Period p = Period.between(fecha, LocalDate.now());
        return String.format("%d años, %d meses",
                p.getYears(), p.getMonths());
    }
}