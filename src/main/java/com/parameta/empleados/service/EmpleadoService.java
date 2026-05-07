package com.parameta.empleados.service;

import com.parameta.empleados.dto.EmpleadoResponse;
import com.parameta.empleados.model.Empleado;
import com.parameta.empleados.repository.EmpleadoRepository;
import com.parameta.empleados.util.FechaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoResponse crearEmpleado(Empleado empleado) {

        // Validaciones de Negocio
        validarDatosEmpleado(empleado);

        // Persistencia (Guardar en DB)
        // (Es buena práctica que el repositorio devuelva la entidad gestionada)
        Empleado empleadoGuardado = repository.save(empleado);

        // Mapeo de Entidad a DTO (Construir respuesta)
        return transformarARespuesta(empleadoGuardado);
    }

    private void validarDatosEmpleado(Empleado empleado) {
        if (Objects.isNull(empleado.getNombres()) || empleado.getNombres().isBlank()) {
            throw new IllegalArgumentException("El nombre del empleado es obligatorio");
        }

        if (!FechaUtil.esMayorDeEdad(empleado.getFechaNacimiento())) {
            throw new IllegalArgumentException("El empleado debe ser mayor de edad para ser vinculado");
        }

    }

    private EmpleadoResponse transformarARespuesta(Empleado empleado) {
        EmpleadoResponse response = new EmpleadoResponse();

        // Copiamos las propiedades básicas (nombre, apellidos, etc.)
        BeanUtils.copyProperties(empleado, response);

        // Calculamos los campos extra que no están en la entidad pero sí en el DTO
        response.setEdad(
                FechaUtil.calcularEdad(empleado.getFechaNacimiento())
        );

        response.setTiempoVinculacion(
                FechaUtil.calcularTiempoVinculacion(empleado.getFechaVinculacion())
        );

        return response;
    }
}