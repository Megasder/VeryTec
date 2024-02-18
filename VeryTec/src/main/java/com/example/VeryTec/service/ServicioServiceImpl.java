package com.example.VeryTec.service;

import com.example.VeryTec.domain.Servicio;
import com.example.VeryTec.exception.ServicioNotFoundException;
import com.example.VeryTec.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Set;

@Service
public class ServicioServiceImpl implements ServicioService{

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Set<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    @Override
    public Optional<Servicio> findByIdServicio(long idServicio) {return servicioRepository.findByIdServicio(idServicio);
    }

    @Override
    public Set<Servicio> findByPrecio(float precio) {
        return servicioRepository.findByPrecio(precio);
    }

    @Override
    public Servicio addServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio modifyServicio(long idServicio, Servicio newServicio) {
        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new ServicioNotFoundException(idServicio));
        newServicio.setIdServicio(servicio.getIdServicio());
        return servicioRepository.save(newServicio);
    }

    @Override
    public void deleteServicio(long idServicio) {
        servicioRepository.findById(idServicio)
                .orElseThrow(() -> new ServicioNotFoundException(idServicio));
        servicioRepository.deleteById(idServicio);
    }


}
