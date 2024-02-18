package com.example.VeryTec.exception;

public class EmpresaNotFoundException extends RuntimeException {

    public EmpresaNotFoundException() {
        super();
    }


    public EmpresaNotFoundException(String cif) {
        super("Empresa not found: " + cif);
    }
}
