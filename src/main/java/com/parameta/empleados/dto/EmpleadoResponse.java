package com.parameta.empleados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data // getters, setters, toString, squals y hashCode
@NoArgsConstructor // molde vacío
@AllArgsConstructor // paquete completo
public class EmpleadoResponse {

    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private LocalDate fechaNacimiento;
    private LocalDate fechaVinculacion;
    private String cargo;
    private Double salario;

    // campos calculados que solo viajan en la respuesta
    private String edad;
    private String tiempoVinculacion;
}