package com.example.demo.exception;

import com.example.demo.dto.ApiResponseDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDTO> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiResponseDTO(false, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponseDTO> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(
                new ApiResponseDTO(false, ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponseDTO> handleUnauthorized(UnauthorizedException ex) {
        return new ResponseEntity<>(
                new ApiResponseDTO(false, ex.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleGeneric(Exception ex) {
        return new ResponseEntity<>(
                new ApiResponseDTO(false, "Internal server error"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
