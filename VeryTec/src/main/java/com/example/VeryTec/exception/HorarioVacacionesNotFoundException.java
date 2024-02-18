package com.example.VeryTec.exception;

public class HorarioVacacionesNotFoundException extends RuntimeException {

    public HorarioVacacionesNotFoundException() {
        super();
    }

    public HorarioVacacionesNotFoundException(long idhorariovacaciones) {
        super("HorarioVacaciones not found: " + idhorariovacaciones);
    }
}
