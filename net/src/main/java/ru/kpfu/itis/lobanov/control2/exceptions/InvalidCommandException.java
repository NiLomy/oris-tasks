package ru.kpfu.itis.lobanov.control2.exceptions;

public class InvalidCommandException extends InvalidInputException {
    public InvalidCommandException(){}
    public InvalidCommandException(String message) {
        super(message);
    }
}
