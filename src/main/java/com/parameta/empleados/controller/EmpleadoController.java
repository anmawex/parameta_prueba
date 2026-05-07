package com.parameta.empleados.controller;

import com.parameta.empleados.dto.EmpleadoResponse;
import com.parameta.empleados.model.Empleado;
import com.parameta.empleados.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor // generar constructor automáticamente
public class EmpleadoController {
    // final garantizas que no cambie y que se asigne al arrancar
    private final EmpleadoService service;

    @PostMapping
    public ResponseEntity<EmpleadoResponse> crear(@Valid @RequestBody Empleado empleado) {
        return ResponseEntity.ok(service.crearEmpleado(empleado));
    }
}