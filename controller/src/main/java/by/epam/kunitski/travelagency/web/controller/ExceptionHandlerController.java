package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> defaultErrorHandler( RuntimeException exc ) {
        return error(INTERNAL_SERVER_ERROR, exc);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(EntityNotFoundException exc) {
        return error(NOT_FOUND, exc);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
