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
@Table(name = "producto")
public class Producto {

    @Schema(description = "Identificador del producto", example = "1", required = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idProducto")
    private long idProducto;

    @Schema(description = "Nombre del producto", example = "Crema para piel SAVA")
    @Column(name="nombre")
    private String nombre;

    @Schema(description = "Categoria del producto", example = "Cremas para piel")
    @Column(name="categoria")
    private String categoria;

    @Schema(description = "Precio del producto", example = "9.95")
    @Column(name="precio")
    private float precio;

    @Schema(description = "IVA del producto", example = "21")
    @Column(name="IVA")
    private float IVA;

    @Schema(description = "Descuento del producto", example = "50")
    @Column(name="descuento")
    private int descuento;

    //cambiar nombre por stock
    @Schema(description = "Cantidad del producto", example = "50")
    @Column(name="cantidad")
    private int cantidad;

    @ManyToOne(fetch=FetchType.LAZY)
    private Empresa empresa;

    @JsonIgnore
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Compra> compras;

}
