package com.example.VeryTec.exception;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException() {
        super();
    }

    public ClienteNotFoundException(String dni) {
        super("Cliente not found: " + dni);
    }
}
