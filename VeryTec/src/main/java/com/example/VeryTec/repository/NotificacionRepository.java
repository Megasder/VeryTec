package com.example.VeryTec.repository;

import com.example.VeryTec.domain.Notificacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface NotificacionRepository extends CrudRepository<Notificacion, Long> {

    Set<Notificacion> findAll();
    Optional<Notificacion> findByIdNotificacion(long idNotificacion);
    Set<Notificacion> findByDescripcion(String descripcion);
    Set<Notificacion> findByAsunto(String asunto);
    Set<Notificacion> findByLeido(boolean leido);
    Set<Notificacion> findByFecha(String fecha);

    @Query(value = "SELECT idNotificacion,asunto,descripcion,fecha,leido FROM notificacion notificacion WHERE notificacion.fecha = :fecha and notificacion.leido = :leido",nativeQuery = true)
    Set<Notificacion> findByFechaAndLeido(@Param("fecha") String fecha, @Param("leido") boolean leido);
}