package com.example.VeryTec.exception;

public class NotificacionNotFoundException extends RuntimeException {

    public NotificacionNotFoundException() {
        super();
    }

    public NotificacionNotFoundException(String message) {
        super(message);
    }

    public NotificacionNotFoundException(long id) {
        super("Notificacion not found: " + id);
    }
}
