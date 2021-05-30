package pl.michal.libraryapplication.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.michal.libraryapplication.exception.MissingBookByIsbnException;
import pl.michal.libraryapplication.exception.MissingBookException;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler({MissingBookException.class, MissingBookByIsbnException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String missingEntity(RuntimeException exception){
        return exception.getMessage();
    }

}
