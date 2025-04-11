package com.hobro11.command.core.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hobro11.command.core.exception.exceptions.CartAlreadyExistsException;
import com.hobro11.command.core.exception.exceptions.CartMaxCountExceededException;
import com.hobro11.command.core.exception.exceptions.CartNotFoundException;
import com.hobro11.command.core.i18n.MessageUtil;
import com.hobro11.command.domain.orders.properties.OrdersProperties;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class CartExceptionHandler {

    private final MessageUtil messageUtil;
    private final OrdersProperties prop;

    @ExceptionHandler(CartMaxCountExceededException.class)
    public ResponseEntity<Map<String, String>> handleCartMaxCountExceededException(CartMaxCountExceededException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message",
                messageUtil.getMessage("CartMaxCountExceededException.message", new Object[] { prop.getMaxCount() }));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartNotFoundException(CartNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("CartNotFoundException.message"));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCartAlreadyExistsException(CartAlreadyExistsException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("CartAlreadyExistsException.message"));
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
}
