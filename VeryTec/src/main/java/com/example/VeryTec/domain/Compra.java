package com.example.VeryTec.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compra")
public class Compra {

    @Schema(description = "Identificador de la compra", example = "1", required = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_compra")
    private long id_compra;

    @Schema(description = "Fecha de la compra", example = "")
    @Column(name = "fecha")
    private Date fecha;

    @Schema(description = "Hora de la compra", example = "17:30")
    @Column(name="hora")
    private Time hora;

    @Schema(description = "Cantidad", example = "9.95")
    @Column(name="cantidad")
    private int cantidad;

    @ManyToOne(fetch=FetchType.LAZY)
    private Cliente cliente;

    @ManyToOne(fetch=FetchType.LAZY)
    private Producto producto;

}
