package com.hobro11.command.core.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hobro11.command.core.exception.exceptions.SaleOptionNotFoundException;
import com.hobro11.command.core.exception.exceptions.SaleOptionUniqueNameException;
import com.hobro11.command.core.i18n.MessageUtil;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class SaleOptionExceptionHandler {
    
    private final MessageUtil messageUtil;

    @ExceptionHandler(SaleOptionNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleSaleOptionNotFoundException(SaleOptionNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("SaleOptionNotFoundException.message"));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SaleOptionUniqueNameException.class)
    public ResponseEntity<Map<String, String>> handleSaleOptionUniqueNameException(SaleOptionUniqueNameException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("SaleOptionUniqueNameException.message"));
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
}
