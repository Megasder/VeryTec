package com.example.VeryTec.service;

import com.example.VeryTec.domain.HorarioVacaciones;

import java.util.Optional;
import java.util.Set;

public interface HorarioVacacionesService {
    Set<HorarioVacaciones> findAll();
    Optional<HorarioVacaciones> findByIdHorarioVacaciones(long idHorarioVacaciones);
    Set<HorarioVacaciones> findByFecha(String fecha);

    HorarioVacaciones addHorariovacaciones(HorarioVacaciones horariovacaciones);
    HorarioVacaciones modifyHorariovacaciones(long idHorarioVacaciones, HorarioVacaciones newHorarioVacaciones);
    void deleteHorariovacaciones(long idHorarioVacaciones);
}
