package com.example.VeryTec.service;

import com.example.VeryTec.domain.Reserva;
import com.example.VeryTec.exception.ReservaNotFoundException;
import com.example.VeryTec.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservaServiceImpl implements ReservaService{

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public Set<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Optional<Reserva> findByIdReserva(long idReserva) {
        return reservaRepository.findById(idReserva);
    }

    @Override
    public Set<Reserva> findByFecha(Date fecha) {
        return reservaRepository.findByFecha(fecha);
    }

    @Override
    public Set<Reserva> findByHora(Time hora) {
        return reservaRepository.findByHora(hora);
    }

    @Override
    public Set<Reserva> findByConfirmacion(boolean confirmacion) {
        return reservaRepository.findByConfirmacion(confirmacion);
    }

    @Override
    public Set<Reserva> findByFechaAndHora(Date fecha, Time hora) {
        return reservaRepository.findByFechaAndHora(fecha, hora);
    }

    @Override
    public Set<Reserva> findByFechaAndConfirmacion(Date fecha, boolean confirmacion) {
        return reservaRepository.findByFechaAndConfirmacion(fecha, confirmacion);
    }

    @Override
    public Reserva addReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva modifyReserva(long idReserva, Reserva newReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ReservaNotFoundException(idReserva));
        newReserva.setIdReserva(reserva.getIdReserva());
        return reservaRepository.save(newReserva);
    }

    @Override
    public void deleteReserva(long idReserva) {
        reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ReservaNotFoundException(idReserva));
        reservaRepository.deleteById(idReserva);
    }
}
