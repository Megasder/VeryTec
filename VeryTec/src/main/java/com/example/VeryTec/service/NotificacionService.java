package com.example.VeryTec.service;

import com.example.VeryTec.domain.Notificacion;

import java.util.Optional;
import java.util.Set;

public interface NotificacionService {
    Set<Notificacion> findAll();
    Optional<Notificacion> findByIdNotificacion(long idNotificacion);
    Set<Notificacion> findByAsunto(String asunto);
    Set<Notificacion> findByDescripcion(String descripcion);
    Set<Notificacion> findByFecha(String fecha);
    Set<Notificacion> findByLeido(boolean leido);

    Set<Notificacion> findByFechaAndLeido(String fecha , boolean leido);

    Notificacion addNotificacion(Notificacion notificacion);
    Notificacion modifyNotificacion(long idNotificacion, Notificacion newNotificacion);
    void deleteNotificacion(long idNotificacion);
}