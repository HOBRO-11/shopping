package com.hobro11.command.core.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hobro11.command.core.exception.exceptions.ShopPageNotFoundException;
import com.hobro11.command.core.exception.exceptions.ShopPageUniqueTitleException;
import com.hobro11.command.core.i18n.MessageUtil;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ShopExceptionHandler {

    private final MessageUtil messageUtil;
    
    @ExceptionHandler(ShopPageNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleShopPageNotFoundException(ShopPageNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("ShopPageNotFoundException.message"));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShopPageUniqueTitleException.class)
    public ResponseEntity<Map<String, String>> handleShopPageUniqueTitleException(ShopPageUniqueTitleException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("ShopPageUniqueTitleException.message"));
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
}
