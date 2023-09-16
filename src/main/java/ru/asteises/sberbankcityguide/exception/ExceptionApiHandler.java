package ru.asteises.sberbankcityguide.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.asteises.sberbankcityguide.exception.exc.SortEnumNameException;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingRequestParameterException(MissingServletRequestParameterException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("RequestParam (String path) - параметр запроса не может быть пустым");
    }

    @ExceptionHandler(FileNotFoundException.class)
    public String handleFileNotFoundException(FileNotFoundException exception) {
        return "Файл не найден: " + exception.getMessage();
    }

    @ExceptionHandler(SortEnumNameException.class)
    public String handleSortEnumNameException(SortEnumNameException exception) {
        return exception.getMessage();
    }
}