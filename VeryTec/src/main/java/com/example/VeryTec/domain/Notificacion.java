package com.example.VeryTec.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "notificacion")
public class Notificacion {

    @Schema(description = "Identificador de la notificación", example = "1", required = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idNotificacion")
    private long idNotificacion;

    @Schema(description = "Asunto de la notificación", example = "Aviso 24h")
    @Column(name = "asunto")
    private String asunto;

    @Schema(description = "Descripción de la notificación", example = "AVISO. Tiene una cita mañana a las 17:00h en La Vilasalut")
    @Column(name = "descripcion")
    private String descripcion;

    @Schema(description = "Fecha de la notificación", example = "13/01/2023")
    @Column(name = "fecha")
    private String fecha;

    @Schema(description = "Si la notificación ha sido leída o no", example = "true")
    @Column(name = "leido")
    private boolean leido;

}
