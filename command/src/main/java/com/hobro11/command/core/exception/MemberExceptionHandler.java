package com.hobro11.command.core.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hobro11.command.core.exception.exceptions.MemberNotFoundException;
import com.hobro11.command.core.exception.exceptions.MemberUniqueBrnException;
import com.hobro11.command.core.i18n.MessageUtil;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class MemberExceptionHandler {

    private final MessageUtil messageUtil;

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMemberNotFoundException(MemberNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("MemberNotFoundException.message"));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberUniqueBrnException.class)
    public ResponseEntity<Map<String, String>> handleMemberUniqueBrnException(MemberUniqueBrnException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", messageUtil.getMessage("MemberUniqueBrnException.message"));
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
    
}
