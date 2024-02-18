package com.example.VeryTec.service;

import com.example.VeryTec.domain.Reserva;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface ReservaService {
    Set<Reserva> findAll();
    Optional<Reserva> findByIdReserva(long idReserva);
    Set<Reserva> findByFecha(Date fecha);
    Set<Reserva> findByHora(Time hora);
    Set<Reserva> findByConfirmacion(boolean confirmacion);
    Set<Reserva> findByFechaAndHora(Date fecha, Time hora);
    Set<Reserva> findByFechaAndConfirmacion(Date fecha, boolean confirmacion);

    Reserva addReserva(Reserva reserva);
    Reserva modifyReserva(long idReserva, Reserva newReserva);
    void deleteReserva(long idReserva);
}
