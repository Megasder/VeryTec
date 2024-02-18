package com.example.VeryTec.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="horariovacaciones")
public class HorarioVacaciones {
    @Schema(description = "Id de la tabla del horario de vacaciones", example = "232332PL", required = true)
    @Column(name="idHorarioVacaciones")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idHorarioVacaciones;

    @Schema(description = "Fecha de los horarios de vacaciones", example = "12/12/2022")
    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne(fetch=FetchType.LAZY)
    private Usuario usuario;
}
