package com.example.VeryTec.service;

import com.example.VeryTec.domain.HorarioVacaciones;
import com.example.VeryTec.exception.HorarioVacacionesNotFoundException;
import com.example.VeryTec.repository.HorarioVacacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class HorarioVacacionesServiceImpl implements HorarioVacacionesService {

    @Autowired
    private HorarioVacacionesRepository horariovacacionesRepository;

    @Override
    public Set<HorarioVacaciones> findAll() {
        return horariovacacionesRepository.findAll();
    }

    @Override
    public Optional<HorarioVacaciones> findByIdHorarioVacaciones(long idHorarioVacaciones) {
        return horariovacacionesRepository.findByIdHorarioVacaciones(idHorarioVacaciones);
    }

    @Override
    public Set<HorarioVacaciones> findByFecha(String fecha) {
        return horariovacacionesRepository.findByFecha(fecha);
    }

    @Override
    public HorarioVacaciones addHorariovacaciones(HorarioVacaciones horarioVacaciones) {
        return horariovacacionesRepository.save(horarioVacaciones);
    }

    @Override
    public HorarioVacaciones modifyHorariovacaciones(long idHorarioVacaciones, HorarioVacaciones newHorarioVacaciones) {
        HorarioVacaciones horarioVacaciones = horariovacacionesRepository.findByIdHorarioVacaciones(idHorarioVacaciones)
                .orElseThrow(() -> new HorarioVacacionesNotFoundException(idHorarioVacaciones));
        newHorarioVacaciones.setIdHorarioVacaciones(horarioVacaciones.getIdHorarioVacaciones());
        return horariovacacionesRepository.save(newHorarioVacaciones);
    }

    @Override
    public void deleteHorariovacaciones(long idHorarioVacaciones) {
        horariovacacionesRepository.findByIdHorarioVacaciones(idHorarioVacaciones)
                .orElseThrow(() -> new HorarioVacacionesNotFoundException(idHorarioVacaciones));
        horariovacacionesRepository.deleteById(idHorarioVacaciones);
    }
}
