package com.example.VeryTec.repository;

import com.example.VeryTec.domain.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {

    Set<Reserva> findAll();
    Optional<Reserva> findByIdReserva(long idReserva);
    Set<Reserva> findByFecha(Date fecha);
    Set<Reserva> findByHora(Time hora);
    Set<Reserva> findByConfirmacion(boolean confirmacion);

    @Query(value = "SELECT idReserva,fecha,hora,confirmacion FROM reserva reserva WHERE reserva.fecha = :fecha and reserva.confirmacion = :confirmacion",nativeQuery = true)
    Set<Reserva> findByFechaAndConfirmacion(@Param("fecha") Date fecha, @Param("confirmacion") boolean confirmacion);

    @Query(value = "SELECT idReserva,fecha,hora,confirmacion FROM reserva reserva WHERE reserva.fecha = :fecha and reserva.hora = :hora",nativeQuery = true)
    Set<Reserva> findByFechaAndHora(@Param("fecha") Date fecha, @Param("hora") Time hora);
}
