package com.utmn.books_api.config.errorhandler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class ControllerAdvices extends ResponseEntityExceptionHandler {

    private record ExceptionDetails(String message, HttpStatus httpStatus) {
    }

    @ExceptionHandler(value = {
            RuntimeException.class,
            UnsupportedOperationException.class,
            IllegalStateException.class})
    public ResponseEntity<?> runTimeException(Exception ex) {
        var exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(exceptionDetails, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<?> authenticationException(Exception e) {
        var exceptionDetails = new ExceptionDetails(
                e.getMessage(),
                UNAUTHORIZED
        );
        return new ResponseEntity<>(exceptionDetails, UNAUTHORIZED);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<?> accessDenied(Exception e) {
        var exceptionDetails = new ExceptionDetails(
                "У Вас нет доступа",
                FORBIDDEN
        );
        return new ResponseEntity<>(exceptionDetails, FORBIDDEN);
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var messages = Arrays.stream(ex.getDetailMessageArguments()).map(Object::toString).collect(Collectors.joining("\n"));
        var exceptionDetails = new ExceptionDetails(
                messages,
                BAD_REQUEST
        );
        return new ResponseEntity<>(exceptionDetails, BAD_REQUEST);
    }
}
