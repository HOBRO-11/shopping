package com.hobro11.command.core.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hobro11.command.core.exception.exceptions.ShopGeoAlreadyExistException;
import com.hobro11.command.core.exception.exceptions.ShopGeoNotFoundException;
import com.hobro11.command.core.i18n.MessageUtil;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ShopGeoExceptionHandler {
    
    private final MessageUtil messageUtil;

    @ExceptionHandler(ShopGeoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleShopGeoNotFoundException(ShopGeoNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("ShopGeoNotFoundException.message"));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShopGeoAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleShopGeoAlreadyExistException(ShopGeoAlreadyExistException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("ShopGeoAlreadyExistException.message"));
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
}
