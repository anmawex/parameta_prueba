package com.parameta.empleados.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "empleado") // definir el nombre de la tabla
@Getter // genera todos los getters
@Setter // genera todos los getters
@NoArgsConstructor // genera constructor vacío (para JPA)
@AllArgsConstructor // genera constructor con todos los campos
@Builder // crear objetos de forma elegante
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Los nombres no pueden estar vacíos")
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacíos")
    private String apellidos;

    @NotBlank(message = "El tipo de documento es obligatorio")
    @Column(name = "tipo_documento") // snake_case en mysql
    private String tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    @Column(name = "numero_documento")
    private String numeroDocumento;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_vinculacion")
    private LocalDate fechaVinculacion;

    private String cargo;
    private Double salario;
}