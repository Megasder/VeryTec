package com.example.VeryTec.exception;

public class CompraNotFoundException extends RuntimeException {

    public CompraNotFoundException() {
        super();
    }

    public CompraNotFoundException(long idCompra) {
        super("Compra not found: " + idCompra);
    }
}
