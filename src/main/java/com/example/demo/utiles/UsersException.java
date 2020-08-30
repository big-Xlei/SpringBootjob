package com.example.demo.utiles;

public class UsersException extends RuntimeException{
    public UsersException() {
    }

    public UsersException(String message) {
        super(message);
    }

    public UsersException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsersException(Throwable cause) {
        super(cause);
    }

    public UsersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
