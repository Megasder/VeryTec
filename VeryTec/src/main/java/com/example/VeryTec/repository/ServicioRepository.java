package com.example.VeryTec.repository;

import com.example.VeryTec.domain.Servicio;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ServicioRepository extends CrudRepository<Servicio, Long> {

    Set<Servicio> findAll();
    Optional<Servicio> findByIdServicio(long idServicio);
    Set<Servicio> findByPrecio(float precio);
}
