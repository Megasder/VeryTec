package com.example.VeryTec.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "servicio")
public class Servicio {

    @Schema(description = "Identificador del servicio", example = "1", required = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idServicio")
    private long idServicio;

    @Schema(description = "Descripcion del Servicio", example = "Este servicio sirve para...")
    @Column(name="descripcion")
    private String descripcion;

    @Schema(description = "Unidades de Tiempo", example = "21")
    @Column(name="unidades_tiempo")
    private float unidades_tiempo;

    @Schema(description = "Precio del servicio", example = "50")
    @Column(name="precio")
    private float precio;

    @ManyToOne(fetch=FetchType.LAZY)
    private Empresa empresa;

    @JsonIgnore
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private List<Reserva> reservas= new ArrayList<>();

}
