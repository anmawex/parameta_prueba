package com.parameta.empleados.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice // la clase intercepta errores de todos los Controllers
public class GlobalExceptionHandler {
    // 1. Atrapa errores de @NotBlank, @NotNull, etc. (Campos vacíos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> manejarValidacionesDeCampos(MethodArgumentNotValidException ex) {
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("estado", HttpStatus.BAD_REQUEST.value());
        // Extraemos todos los mensajes de error de las anotaciones
        String mensajes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        cuerpoError.put("mensaje", "Errores de validación: " + mensajes);
        return new ResponseEntity<>(cuerpoError, HttpStatus.BAD_REQUEST);
    }
    // 2. Atrapa errores de lógica de negocio (como el de Mayor de Edad)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> manejarErrorDeNegocio(IllegalArgumentException ex) {
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("estado", HttpStatus.BAD_REQUEST.value());
        cuerpoError.put("mensaje", ex.getMessage()); // Aquí saldrá: "El empleado debe ser mayor de edad..."
        return new ResponseEntity<>(cuerpoError, HttpStatus.BAD_REQUEST);
    }
}
/*
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> manejarValidacionesDeCampos(MethodArgumentNotValidException ex) {
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("estado", HttpStatus.BAD_REQUEST.value());

        // Aquí extraemos solo los mensajes que tú escribiste en las anotaciones @NotBlank
        String mensajes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        cuerpoError.put("mensaje", "Errores de validación: " + mensajes);
        return new ResponseEntity<>(cuerpoError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class) // Atrapa específicamente esta excepción
    public ResponseEntity<Object> manejarValidacion(IllegalArgumentException ex) {

        // Creamos un mapa para que la respuesta sea un JSON ordenado
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("mensaje", ex.getMessage()); // Aquí va tu "El empleado debe ser mayor de edad"
        cuerpoError.put("estado", HttpStatus.BAD_REQUEST.value());

        // Devolvemos un código 400 (Bad Request) de un 500
        return new ResponseEntity<>(cuerpoError, HttpStatus.BAD_REQUEST);
    }
}
*/