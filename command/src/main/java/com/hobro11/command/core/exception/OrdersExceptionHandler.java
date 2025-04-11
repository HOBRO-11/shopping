package com.hobro11.command.core.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hobro11.command.core.exception.exceptions.OrderNumberGenerateFailedException;
import com.hobro11.command.core.exception.exceptions.OrdersCheckSumExceededException;
import com.hobro11.command.core.exception.exceptions.OrdersNotFoundException;
import com.hobro11.command.core.i18n.MessageUtil;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class OrdersExceptionHandler {
    
    private final MessageUtil messageUtil;

    @ExceptionHandler(OrdersNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOrdersNotFoundException(OrdersNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("OrdersNotFoundException.message"));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrdersCheckSumExceededException.class)
    public ResponseEntity<Map<String, String>> handleOrdersCheckSumExceededException(OrdersCheckSumExceededException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("OrdersCheckSumExceedException.message"));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNumberGenerateFailedException.class)
    public ResponseEntity<Map<String, String>> handleOrderNumberGenerateFailedException(OrderNumberGenerateFailedException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("OrderNumberGenerateFailedException.message"));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
