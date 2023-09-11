package com.kado.kpbookservice.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.kado.kpbookservice.domain.dto.response.ErrorMessageDto;
import com.kado.kpbookservice.exception.BadRequestException;
import com.kado.kpbookservice.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleNotFoundException(NotFoundException e) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage("Not found");
        errorMessageDto.setDetails(e.getMessage());
        return ResponseEntity.status(404).body(errorMessageDto);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessageDto> handleBadRequestException(BadRequestException e) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage("Bad request");
        errorMessageDto.setDetails(e.getMessage());
        return ResponseEntity.status(400).body(errorMessageDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage("Validation error");
        errorMessageDto.setDetails("Some fields are not valid");
        errorMessageDto.setFieldErrors(e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    ErrorMessageDto.Error error = new ErrorMessageDto.Error();
                    error.setField(fieldError.getField());
                    error.setMessage(fieldError.getDefaultMessage());
                    return error;
                })
                .collect(Collectors.toList()));
        return ResponseEntity.status(400).body(errorMessageDto);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorMessageDto> handleJsonParseException(JsonParseException e) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage("Bad request");
        errorMessageDto.setDetails(e.getMessage());
        return ResponseEntity.status(400).body(errorMessageDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleException(Exception e) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage("Internal server error");
        errorMessageDto.setDetails(e.getMessage());
        return ResponseEntity.status(500).body(errorMessageDto);
    }
}
