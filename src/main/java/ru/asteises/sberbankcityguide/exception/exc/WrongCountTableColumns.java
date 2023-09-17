package ru.asteises.sberbankcityguide.exception.exc;

public class WrongCountTableColumns extends RuntimeException {

    public WrongCountTableColumns(String message) {
        super(message);
    }
}
