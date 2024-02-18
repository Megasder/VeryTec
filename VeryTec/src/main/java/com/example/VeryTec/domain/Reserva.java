package com.example.VeryTec.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "reserva")
public class Reserva {

    @Schema(description = "Identificador de la reserva", example = "1", required = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idReserva")
    private long idReserva;

    @Schema(description = "Fecha de la reserva", example = "")
    @Column(name = "fecha")
    private Date fecha;

    @Schema(description = "Hora de la reserva", example = "17:30")
    @Column(name="hora")
    private Time hora;

    @Schema(description = "Si la reserva ha sido aceptada o no", example = "true")
    @Column(name = "confirmacion")
    private boolean confirmacion;


    @ManyToOne(fetch=FetchType.LAZY)
    private Cliente cliente;


    @ManyToOne(fetch=FetchType.LAZY)
    private Usuario usuario;


    @ManyToOne(fetch=FetchType.LAZY)
    private Servicio servicio;
}