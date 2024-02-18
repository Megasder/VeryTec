package com.example.VeryTec.repository;

import com.example.VeryTec.domain.HorarioVacaciones;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface HorarioVacacionesRepository extends CrudRepository<HorarioVacaciones, Long> {
    Set<HorarioVacaciones> findAll();
    Optional<HorarioVacaciones> findByIdHorarioVacaciones(long idHorarioVacaciones);
    Set<HorarioVacaciones> findByFecha(String fecha);
}
