package com.example.VeryTec.exception;

public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException() {
        super();
    }


    public UsuarioNotFoundException(String dni) {
        super("Usuario not found: " + dni);
    }
}
