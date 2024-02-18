package com.example.VeryTec.service;

import com.example.VeryTec.domain.Notificacion;
import com.example.VeryTec.exception.NotificacionNotFoundException;
import com.example.VeryTec.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class NotificacionServiceImpl implements NotificacionService{

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Override
    public Set<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    @Override
    public Optional<Notificacion> findByIdNotificacion(long idNotificacion) {
        return notificacionRepository.findByIdNotificacion(idNotificacion);
    }

    @Override
    public Set<Notificacion> findByAsunto(String asunto) {
        return notificacionRepository.findByAsunto(asunto);
    }

    @Override
    public Set<Notificacion> findByDescripcion(String descripcion) {
        return notificacionRepository.findByDescripcion(descripcion);
    }

    @Override
    public Set<Notificacion> findByFecha(String fecha) {
        return notificacionRepository.findByFecha(fecha);
    }

    @Override
    public Set<Notificacion> findByLeido(boolean leido) {
        return notificacionRepository.findByLeido(leido);
    }

    @Override
    public Set<Notificacion> findByFechaAndLeido(String fecha, boolean leido) {
        return notificacionRepository.findByFechaAndLeido(fecha, leido);
    }

    @Override
    public Notificacion addNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public Notificacion modifyNotificacion(long idNotificacion, Notificacion newNotificacion) {
        Notificacion notificacion = notificacionRepository.findByIdNotificacion(idNotificacion)
                .orElseThrow(() -> new NotificacionNotFoundException(idNotificacion));
        newNotificacion.setIdNotificacion(notificacion.getIdNotificacion());
        return notificacionRepository.save(newNotificacion);
    }

    @Override
    public void deleteNotificacion(long idNotificacion) {
        notificacionRepository.findById(idNotificacion)
                .orElseThrow(() -> new NotificacionNotFoundException(idNotificacion));
        notificacionRepository.deleteById(idNotificacion);
    }


}
