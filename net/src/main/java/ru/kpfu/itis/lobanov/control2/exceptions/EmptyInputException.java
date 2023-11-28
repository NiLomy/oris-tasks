package ru.kpfu.itis.lobanov.control2.exceptions;

public class EmptyInputException extends Exception {
    public EmptyInputException() {}
    public EmptyInputException(String message) {
        super(message);
    }
}
