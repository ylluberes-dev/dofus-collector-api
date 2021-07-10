package com.ylluberes.dofus.collector.api.exception;

import com.ylluberes.dofus.collector.api.dto.responses.GenericResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO: auto-inspect customConstraint package to evaluate the constraint that was triggered when
 * TODO: a class level custom constraint is defined
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String PASSWORD_CONFIRM_CONSTRAINT = "PasswordConfirmConstraint";

    /**
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Map<String,String>> body = new LinkedHashMap<>();
        Map<String, String> propertyErrorPair = new LinkedHashMap<>();
        //Finding custom errors (level class)
        ex.getAllErrors().forEach(error -> {
            switch (error.getCode()) {
                case PASSWORD_CONFIRM_CONSTRAINT:
                    propertyErrorPair.put("confirmPassword", error.getDefaultMessage());
                    break;
            }
        });
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            propertyErrorPair.put(fieldError.getField(), fieldError.getDefaultMessage());
        });


        body.put("errors", propertyErrorPair);
        GenericResponse response = new GenericResponse();
        response.setData(body);
        response.setServerStatus(status);
        response.setSuccess(false);
        response.setMessage("Invalid argument(s)");

        return new ResponseEntity<>(response, headers, response.getServerStatus());

    }


}