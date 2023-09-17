package ru.asteises.sberbankcityguide.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.asteises.sberbankcityguide.exception.exc.EmptyCityListException;
import ru.asteises.sberbankcityguide.exception.exc.SortEnumNameException;
import ru.asteises.sberbankcityguide.exception.exc.WrongCountTableColumns;
import ru.asteises.sberbankcityguide.exception.exc.WrongFilePathException;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingRequestParameterException(
            MissingServletRequestParameterException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(WrongFilePathException.class)
    public ResponseEntity<String> handleWrongFilePathException(WrongFilePathException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(SortEnumNameException.class)
    public ResponseEntity<String> handleSortEnumNameException(SortEnumNameException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(WrongCountTableColumns.class)
    public ResponseEntity<String> handleWrongCountTableColumnsException(WrongCountTableColumns exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EmptyCityListException.class)
    public ResponseEntity<String> handleEmptyCityListException(EmptyCityListException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}