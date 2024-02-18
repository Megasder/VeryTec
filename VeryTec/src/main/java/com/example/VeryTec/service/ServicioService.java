package com.example.VeryTec.service;

import com.example.VeryTec.domain.Servicio;

import java.util.Optional;
import java.util.Set;

public interface ServicioService {

    Set<Servicio> findAll();
    Optional<Servicio> findByIdServicio(long idServicio);
    Set<Servicio> findByPrecio(float precio);

    Servicio addServicio(Servicio servicio);
    Servicio modifyServicio(long idServicio, Servicio newServicio);
    void deleteServicio(long idServicio);

}
